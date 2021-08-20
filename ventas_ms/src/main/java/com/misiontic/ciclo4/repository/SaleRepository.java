package com.misiontic.ciclo4.repository;

import com.misiontic.ciclo4.models.Productos;
import com.misiontic.ciclo4.models.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Collection;

public interface SaleRepository extends MongoRepository<Sale,Long> {
    ArrayList<Productos> findByproductosCarrito(ArrayList<Productos> productos);


}
