
from django.conf import settings
from rest_framework import status
from rest_framework.response import Response
from rest_framework_simplejwt.views import TokenVerifyView
from rest_framework_simplejwt.backends import TokenBackend
from rest_framework_simplejwt.exceptions import InvalidToken, TokenError

from rest_framework.response import Response
from rest_framework import mixins, viewsets, status

from .serializers import UserSerializer
from .models import AppUser


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
        if not 'username' in request.data or not 'password' in request.data:
            return Response({
                "detail": "Expected \'username\' and \'password\' keys in request body"
            }, status=status.HTTP_422_UNPROCESSABLE_ENTITY)
        user = self.model(username=request.data["username"])
        user.set_password(request.data["password"])
        user.save()
        return Response(self.serializer_class(user).data)
    

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
