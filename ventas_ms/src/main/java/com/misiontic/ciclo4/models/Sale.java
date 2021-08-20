package com.misiontic.ciclo4.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;


@Document(collection = "Sale")
@Data
public class Sale {

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
    private ArrayList<ProductosVendido> productosCarrito;

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
}
