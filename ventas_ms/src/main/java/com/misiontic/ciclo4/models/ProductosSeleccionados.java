package com.misiontic.ciclo4.models;

import lombok.Data;

@Data
public class ProductosSeleccionados extends Productos{


    public ProductosSeleccionados(String nombreProducto, Integer codigoProducto,Double precioProducto,Integer cantidadProducto) {
        super(nombreProducto, codigoProducto, precioProducto,cantidadProducto);

    }

    public ProductosSeleccionados() {
    }

    public Double getSubTotal(){
        return this.getPrecioProducto() * this.getCantidadProducto();
    }

    public Double getTotal(){
        return this.getSubTotal()* 1.19;
    }
    }
