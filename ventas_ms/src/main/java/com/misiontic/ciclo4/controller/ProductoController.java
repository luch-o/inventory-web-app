package com.misiontic.ciclo4.controller;

import com.misiontic.ciclo4.models.Productos;
import com.misiontic.ciclo4.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    private ProductosRepository productosRepository;

            public ProductoController(ProductosRepository productosRepository) {
            /*
                this.productosRepository = productosRepository;

                Productos newProducto1 = new Productos("Tornillos",5000,10,1);
                Productos newProducto2 = new Productos("Laminas",6000,8,2);
                Productos newProducto3 = new Productos("Martillo",15000,20,3);
                Productos newProducto4 = new Productos("Ladrillos",3000,35,4);

                this.productosRepository.save(newProducto1);
                this.productosRepository.save(newProducto2);
                this.productosRepository.save(newProducto3);
                this.productosRepository.save(newProducto4);

    */
    Productos newProducto5 = new Productos("Tornilluuuos", 5, 10000.7, 1);
    this.productosRepository.save(newProducto5);
}
    @GetMapping("/producto/{nombreProducto}")
    Productos getProductos(@PathVariable String nombreProducto) {
        return productosRepository.findBynombreProducto(nombreProducto);
    }
}


