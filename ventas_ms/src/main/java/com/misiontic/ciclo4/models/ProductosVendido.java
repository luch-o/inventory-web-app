package com.misiontic.ciclo4.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Document

//@RequiredArgsConstructor
public class ProductosVendido {


    @Id
    private Integer codigo;

    private Double precio;
    private String nombre;
    private Integer cantidad;

    public ProductosVendido(Integer codigo, Double precio, String nombre, Integer cantidad) {
        this.codigo = codigo;
        this.precio=precio;
        this.nombre=nombre;
        this.cantidad=cantidad;
    }


    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Double getSubTotal(){
        return this.getPrecio() * this.getCantidad();
    }

    public Double getTotal(){
        return this.getSubTotal()* 1.19;
    }
    }

