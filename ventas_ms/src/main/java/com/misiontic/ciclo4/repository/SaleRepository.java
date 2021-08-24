package com.misiontic.ciclo4.repository;

import com.misiontic.ciclo4.models.ProductosSeleccionados;
import com.misiontic.ciclo4.models.ProductosVendido;
import com.misiontic.ciclo4.models.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface SaleRepository extends MongoRepository<Sale,Long> {


    ArrayList<ProductosSeleccionados> findByProductosVendidos(ArrayList<ProductosSeleccionados> productosCarrito);
}
