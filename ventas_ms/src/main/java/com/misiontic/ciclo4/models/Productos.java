package com.misiontic.ciclo4.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "Productos")
@Data
public class Productos {

    @Id
    private String id_productos;

    @Field(name = "nombre")
    @NotNull(message = "Especifique el nombre")
    @Size(min = 1,max = 40)
    private String nombreProducto;

    @Field(name = "codigo")
    @NotNull(message = "Especifique el codigo")
    @Size(min = 1,max = 20)
    private Integer codigoProducto;

    @Field(name = "precio")
    @NotNull(message = "Especifique el precio")
    @Size(min = 1,max = 20)
    private double precioProducto;

    @Field(name = "cantidad")
    @NotNull(message = "Especifique la cantidad")
    @Size(min = 1,max = 10)
    private Integer cantidadProducto;

    public Productos(String nombreProducto, Integer codigoProducto, double precioProducto, Integer cantidadProducto) {
        this.nombreProducto = nombreProducto;
        this.codigoProducto = codigoProducto;
        this.precioProducto = precioProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public String getId() {
        return id_productos;
    }

    public void setId(String id) {
        this.id_productos = id_productos;
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

    public Integer getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Integer codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public void aumentarCantidad() {
        this.cantidadProducto++;
    }

    public boolean sinCantidad() {
        return this.cantidadProducto <= 0;
    }

}
