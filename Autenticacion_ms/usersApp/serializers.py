from rest_framework import serializers
from .models import bankUser

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = bankUser
        fields = '__all__'
