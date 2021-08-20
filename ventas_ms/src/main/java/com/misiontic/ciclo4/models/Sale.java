package com.misiontic.ciclo4.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;


@Document(collection = "Sale")
@Data
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long codigoVenta;

    @Field(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    @Field(name = "cedula")
    @NotNull(message = "Especifique la cedula")
    @Size(min = 1,max = 40)
    private Integer cedulaCliente;

    //@DBRef // Toma como referencia el array de producto sin ser parte de una entidad relacional
    private ArrayList<Productos> productosCarrito;

    @Field(name = "subTotal")
    @NotNull
    private Float subTotalVenta;

    @Field(name = "total")
    @NotNull
    private Float totalIvaVenta;

    public Sale(Float subTotalVenta) {
        this.subTotalVenta = subTotalVenta;
    }

    public Sale() {

    }


    public Long getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Long codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(Integer cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public ArrayList<Productos> getProductosCarrito() {
        return productosCarrito;
    }

    public void setProductosCarrito(ArrayList<Productos> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }

    public Float getSubTotalVenta() {
        return subTotalVenta;
    }

    public void setSubTotalVenta(Float subTotalVenta) {
        this.subTotalVenta = subTotalVenta;
    }

    public Float getTotalIvaVenta() {
        return totalIvaVenta;
    }

    public void setTotalIvaVenta(Float totalIvaVenta) {
        this.totalIvaVenta = totalIvaVenta;
    }

    public Sale(Date fechaVenta, Integer cedulaCliente, ArrayList<Productos> productosCarrito, Float subTotalVenta, Float totalIvaVenta) {
        this.fechaVenta = fechaVenta;
        this.cedulaCliente = cedulaCliente;
        this.productosCarrito = productosCarrito;
        this.subTotalVenta = subTotalVenta;
        this.totalIvaVenta = totalIvaVenta;
    }
}
