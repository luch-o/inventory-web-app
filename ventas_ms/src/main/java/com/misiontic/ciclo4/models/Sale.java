package com.misiontic.ciclo4.models;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.util.Date;
import java.util.List;



@Document(collection = "Sale")
@Data
public class Sale {

    @Id
    @SequenceGenerator(name = "PROJECT_ID_GENERATOR", sequenceName = "PROJECT_SEQ", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_ID_GENERATOR")
    private Long codigoVenta;

    @Field(name = "fecha")
    
    private Date fechaVenta;

    @Field(name = "cedula")
    /*@NotNull(message = "Especifique la cedula")
    @Size(min = 1,max = 40)*/
    private Integer cedulaCliente;

    private List<Productos> productosVendidos;

    /*@Field(name = "subTotal")
    private Float subTotalVenta;

    @Field(name = "total")
    private Float totalIvaVenta;*/

    public Double getTotal() {
        Double total = 0.0;
        for (Productos productoVendido : this.productosVendidos) {
            total += productoVendido.getTotal();
        }
        return total;
    }

    public Sale(Long codigoVenta, Integer cedulaCliente, List<Productos> productosVendidos) {
        this.codigoVenta = codigoVenta;
        this.cedulaCliente = cedulaCliente;
        this.productosVendidos = productosVendidos;
    }

    public Sale() {

    }

    public void setProductosVendidos(List<Productos> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }
}
