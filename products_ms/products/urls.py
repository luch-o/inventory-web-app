from django.urls import path
from . import views

urlpatterns = [
    path('', views.product_list, name="product-list"),
    path('detail/<int:pk>/', views.product_detail, name="product-detail"),
    path('create/', views.product_create, name="product-create"),
    path('update/<int:pk>', views.product_update, name="product-update"),
    path('update_stock/<int:pk>', views.product_update_stock, name="produc-update-stock"),
    path('delete/<int:pk>', views.product_delete, name="product-delete"),
]
