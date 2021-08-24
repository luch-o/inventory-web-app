from rest_framework import serializers
from .models import AppUser

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = AppUser
        fields = ["username", "id"]

    def create(self, validated_data):
        user = self.Meta.model(username=validated_data['username'])
        user.set_password(validated_data['password'])
        user.save()
        return user
    