package com.misiontic.ciclo4.controller;

import com.misiontic.ciclo4.models.Productos;
import com.misiontic.ciclo4.models.Sale;
import com.misiontic.ciclo4.repository.ProductosRepository;
import com.misiontic.ciclo4.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "/sale") //Ruta general de ventas
public class SaleController {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductosRepository productosRepository;

    @PostMapping(value = "/realizar") // Cuando le da el boton de confirmar compra en el front
    public String realizarVenta(HttpServletRequest request, RedirectAttributes redirectAttributes){
        ArrayList<Productos> productosCarrito = this.obtenerCarrito(request);
        if (productosCarrito == null || productosCarrito.size() <= 0) {
            return "redirect:/sale/";
        }
        //Recorre los productos del carrito y descuenta las cantidades del inventario
        for (Productos productos : productosCarrito){
            Productos p = productosRepository.findBynombreProducto(productos.getNombreProducto());
            if (p==null) continue;
            p.descontarInventario(productos.getCantidadProducto());
            productosRepository.save(p);
        }
        this.limpiarCarrito(request); // Limpia el carrito despues de terminar el recorrido de productos
        // Guarda el objeto de ventas
        Sale sale = saleRepository.save(new Sale());
        redirectAttributes.addFlashAttribute("mensaje","Venta realizada de forma exitosa").addFlashAttribute("clase","success");
        return "redirect:/sale/";
    }

    private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }
    // Mantiene los productos agregados en carrito durante la sesion
    private void guardarCarrito(ArrayList<Productos> productosCarrito, HttpServletRequest request) {
        request.getSession().setAttribute("productosCarrito",productosCarrito);
    }
    // Crea el array de carrito
    private ArrayList<Productos> obtenerCarrito(HttpServletRequest request) {
        ArrayList<Productos> productosCarrito = (ArrayList<Productos>) request.getSession().getAttribute("productosCarrito");
        if (productosCarrito == null) {
            productosCarrito = new ArrayList<>();
        }
        return productosCarrito;
    }

    @PostMapping(value = "/agregar") // Agrega productos al carrito siempre y cuando cumplan con las condiciones de cantidades, si esta el producto o no
    public String agregarAlCarrito(@ModelAttribute Productos productos, HttpServletRequest request, RedirectAttributes redirectAttrs){
        ArrayList<Productos> productosCarrito = this.obtenerCarrito(request);
        Productos porcodigo = productosRepository.findByCodigo(productos.getCodigoProducto());
        if (porcodigo == null){
            redirectAttrs.addFlashAttribute("mensaje","El producto con el codigo "+productos.getCodigoProducto()+" no se encuentra").addFlashAttribute("clase","warning");
            return "redirect:/sale";
        }
        if (porcodigo.sinCantidad()){
            redirectAttrs.addFlashAttribute("mensaje", "El producto esta agotado").addFlashAttribute("clase","warning");
        }
        if (porcodigo.getCantidadProducto()<productos.getCantidadProducto()){
            redirectAttrs.addFlashAttribute("mensaje","La cantidad de productos seleccionados, no se encuentran en el inventario").addFlashAttribute("clase","warning");
        }
        boolean encontrado = false;
        if (!encontrado){
            productosCarrito.add(new Productos(porcodigo.getNombreProducto(), porcodigo.getPrecioProducto(), porcodigo.getCodigoProducto(), porcodigo.getCantidadProducto()));
        }
        this.guardarCarrito(productosCarrito, request);
        return "redirect:/sale/";
    }

}
