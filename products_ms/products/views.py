from django.http import HttpResponseBadRequest

from rest_framework.decorators import api_view
from rest_framework.response import Response

from .serializers import ProductSerializer
from .models import Product

@api_view(['GET'])
def product_list(request):
    products = Product.objects.all()
    serializer = ProductSerializer(products, many=True)
    return Response(serializer.data)

@api_view(['GET'])
def product_detail(request, pk):
    products = Product.objects.get(code=pk)
    serializer = ProductSerializer(products, many=False)
    return Response(serializer.data)

@api_view(['POST'])
def product_create(request):
    serializer = ProductSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
    else:
        return HttpResponseBadRequest("Invalid request body")
    return Response(serializer.data)

@api_view(['POST'])
def product_update(request, pk):
    product = Product.objects.get(code=pk)
    serializer = ProductSerializer(instance=product, data=request.data)
    if serializer.is_valid():
        serializer.save()
    else:
        return HttpResponseBadRequest("Invalid request body")
    return Response(serializer.data)
    
@api_view(['DELETE'])
def product_delete(request, pk):
    product = Product.objects.get(code=pk)
    product.delete()
    return Response('Product succesfully deleted')
    