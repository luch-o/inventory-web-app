from rest_framework import serializers
from .models import bankUser

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = bankUser
        fields = ["username", "password"]

    def create(self, validated_data):
        user = self.Meta.model(username=validated_data['username'])
        user.set_password(validated_data['password'])
        user.save()
        return user
    