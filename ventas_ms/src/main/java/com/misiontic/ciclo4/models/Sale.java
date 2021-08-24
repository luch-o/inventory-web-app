package com.misiontic.ciclo4.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Document(collection = "Sale")
@Data
public class Sale {

    @Id
    @SequenceGenerator(name = "PROJECT_ID_GENERATOR", sequenceName = "PROJECT_SEQ", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_ID_GENERATOR")
    private Long codigoVenta;

    @Field(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    @Field(name = "cedula")
    /*@NotNull(message = "Especifique la cedula")
    @Size(min = 1,max = 40)*/
    private Integer cedulaCliente;

    //@DBRef // Toma como referencia el array de producto sin ser parte de una entidad relacional
    private List<ProductosSeleccionados> productosVendidos;

    /*@Field(name = "subTotal")
    private Float subTotalVenta;

    @Field(name = "total")
    private Float totalIvaVenta;*/

    public Double getTotal() {
        Double total = 0.0;
        for (ProductosSeleccionados productoVendido : this.productosVendidos) {
            total += productoVendido.getTotal();
        }
        return total;
    }

    public Sale(Long codigoVenta, Integer cedulaCliente, List<ProductosSeleccionados> productosVendidos) {
        this.codigoVenta = codigoVenta;
        this.cedulaCliente = cedulaCliente;
        this.productosVendidos = productosVendidos;
    }

    public Sale() {

    }

    public void setProductosVendidos(List<ProductosSeleccionados> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }
}
