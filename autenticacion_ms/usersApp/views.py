
from django.conf import settings
from rest_framework import status
from rest_framework.response import Response
from rest_framework_simplejwt.views import TokenVerifyView
from rest_framework_simplejwt.backends import TokenBackend
from rest_framework_simplejwt.exceptions import InvalidToken, TokenError

from rest_framework.response import Response
from rest_framework import mixins, viewsets, status

from usersApp.serializers import UserSerializer
from usersApp.models import AppUser


class UserViewSet(mixins.CreateModelMixin,
                  mixins.ListModelMixin,
                  mixins.RetrieveModelMixin,
                  viewsets.GenericViewSet):
    """
    A viewset that provides `retrieve`, `create`, and `list` actions for the User Model.
    """
    model = AppUser
    queryset = AppUser.objects.all()
    serializer_class = UserSerializer

    def create(self, request, *args, **kwargs):
        try:
            user = self.model.objects.create_user(**request.data)
            return Response(self.serializer_class(user).data)
        except ValueError as e:
            return Response({
                'detail': str(e)
            }, status=status.HTTP_400_BAD_REQUEST)
        except Exception:
            return Response({
                'detail': "Expecting only \'username\' and \'password\' in request"
            }, status=status.HTTP_400_BAD_REQUEST)    
    

class VerifyTokenView(TokenVerifyView):
    def post(self, request, *args, **kwargs):
        token = request.data['token']
        tokenBackend = TokenBackend(algorithm=settings.SIMPLE_JWT['ALGORITHM'])
        serializer = self.get_serializer(data=request.data)
        try:
            serializer.is_valid(raise_exception=True)
            valid_data = tokenBackend.decode(token,verify=False)
            serializer.validated_data['UserId'] = valid_data['user_id']
        except TokenError as e:
            raise InvalidToken(e.args[0])
        return Response(serializer.validated_data, status=status.HTTP_200_OK)
