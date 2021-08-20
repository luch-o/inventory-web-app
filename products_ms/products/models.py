from django.db import models


class Product(models.Model):
    code = models.BigAutoField(primary_key=True)
    name = models.CharField(max_length=128  , unique=True)
    description = models.CharField(max_length=256)
    price = models.IntegerField()
    stock = models.IntegerField()
