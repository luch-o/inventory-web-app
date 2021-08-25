package com.misiontic.ciclo4.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "Productos")
@Data

public class Productos {

    @Field(name = "nombre") /*
    @NotNull(message = "Especifique el nombre")
    @Size(min = 1,max = 40) */
    private String nombreProducto;

    @Id
    @Field(name = "codigo")/*
    @NotNull(message = "Especifique el codigo")
    @Size(min = 1,max = 20)*/
    private Integer codigoProducto;

    @Field(name = "precio")/*
    @NotNull(message = "Especifique el precio")
    @Size(min = 1,max = 20)*/
    private Double precioProducto;

    @Field(name = "inventario")/*
    @NotNull(message = "Especifique la cantidad")
    @Size(min = 1,max = 10)*/
    private Integer cantidadProducto;

    public Productos(String nombreProducto, Integer codigoProducto, Double precioProducto, Integer cantidadProducto) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantidadProducto = cantidadProducto;
        this.codigoProducto = codigoProducto;

    }

    public Productos() {
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void descontarInventario(Integer cantidadProducto){
        this.cantidadProducto -= cantidadProducto;
    }

    public Double getSubTotal(){
        return this.cantidadProducto * this.precioProducto ;
    }

    public Double getTotal(){
        return this.getSubTotal()* 1.19;
    }
}


