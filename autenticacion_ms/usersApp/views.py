
from django.conf import settings
from rest_framework import serializers, status
from rest_framework.response import Response
from rest_framework_simplejwt.views import TokenVerifyView
from rest_framework_simplejwt.backends import TokenBackend
from rest_framework_simplejwt.exceptions import InvalidToken, TokenError

from rest_framework.decorators import api_view
from rest_framework.response import Response

from .serializers import UserSerializer
from .models import AppUser, UserManager

@api_view(['GET'])
def user_list(request):
    users = AppUser.objects.all()
    serializers = UserSerializer(users, many=True)
    return Response(serializers.data)

@api_view(['POST'])
def create_user(request):
    serializer = UserSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response(request.data)
    return Response("Invalid request", status=400)
    

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
