package com.misiontic.ciclo4.repository;

import com.misiontic.ciclo4.models.Productos;
import com.misiontic.ciclo4.models.ProductosSeleccionados;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductosRepository extends MongoRepository<Productos,Long> {
     Productos findBynombreProducto(String nombreProducto);
     Productos findByCodigoProducto (Integer codigoProducto);
     ProductosSeleccionados findByCantidad(Double cantidad);

}
