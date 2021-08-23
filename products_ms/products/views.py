from rest_framework import viewsets, status
from rest_framework.response import Response
from rest_framework.decorators import action

from products.serializers import ProductSerializer
from products.models import Product


class ProductViewSet(viewsets.ModelViewSet):
    queryset = Product.objects.all()
    serializer_class = ProductSerializer

    @action(detail=True, methods=['PATCH'])
    def reduce(self, request, *args, **kwargs):
        """
        Extra `reduce` action to reduce a product stock by a given amount
        """
        quantity_key = 'quantity'
        product = self.get_object()
        if quantity_key not in request.data:
            return Response({
                "detail": f"{quantity_key} field expected"
            }, status=status.HTTP_422_UNPROCESSABLE_ENTITY)
        quantity = request.data[quantity_key]
        stock = product.stock - quantity
        if stock < 0:
            return Response({
                "detail": "Not enough product units in stock"
            }, status=status.HTTP_422_UNPROCESSABLE_ENTITY)
        product.stock = stock
        product.save()
        serializer = self.serializer_class(product, context={'request': request})
        return Response(serializer.data)
