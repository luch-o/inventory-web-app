package com.misiontic.ciclo4.repository;

import com.misiontic.ciclo4.models.Productos;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductosRepository extends MongoRepository<Productos,String> {
     Productos findBynombreProducto(String nombreProducto);
     Productos findByCodigoProducto (Integer codigoProducto);

}