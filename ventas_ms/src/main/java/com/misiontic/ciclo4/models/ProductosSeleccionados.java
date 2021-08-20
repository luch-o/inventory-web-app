package com.misiontic.ciclo4.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class ProductosSeleccionados extends Productos{
    private Integer cantidad;

    public ProductosSeleccionados(String nombreProducto, Double precioProducto, Integer codigoProducto, Integer cantidad, float v) {
        super(nombreProducto, precioProducto, codigoProducto);
        this.cantidad = cantidad;
    }
    public void aumentarCantidad(){
        this.cantidad++;
    }
    public Integer getCantidad(){
        return cantidad;
    }
    public Double getSubTotal(){
        return this.getPrecioProducto() * this.cantidad;
    }

    public Double getTotal(){
        return this.getSubTotal()* 1.19;
    }
}
