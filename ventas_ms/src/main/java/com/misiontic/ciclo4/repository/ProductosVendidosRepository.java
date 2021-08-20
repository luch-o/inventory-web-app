package com.misiontic.ciclo4.repository;

import com.misiontic.ciclo4.models.ProductosVendido;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.persistence.Id;

public interface ProductosVendidosRepository extends MongoRepository<ProductosVendido,Integer> {
}
