package com.misiontic.ciclo4.repository;

import com.misiontic.ciclo4.models.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaleRepository extends MongoRepository<Sale,Long> {
}
