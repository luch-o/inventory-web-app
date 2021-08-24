package com.misiontic.ciclo4.controller;

import com.misiontic.ciclo4.models.Productos;
import com.misiontic.ciclo4.models.ProductosSeleccionados;
import com.misiontic.ciclo4.models.ProductosVendido;
import com.misiontic.ciclo4.models.Sale;
import com.misiontic.ciclo4.repository.ProductosRepository;
import com.misiontic.ciclo4.repository.ProductosVendidosRepository;
import com.misiontic.ciclo4.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path = "/sale") //Ruta general de ventas
public class SaleController {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductosRepository productosRepository;
    @Autowired
    private ProductosVendidosRepository productosVendidosRepository;

    //Variables y metodos:

    // Crea el array de carrito
    private ArrayList<ProductosSeleccionados> obtenerCarrito(HttpServletRequest request) {
        ArrayList<ProductosSeleccionados> productosCarrito = (ArrayList<ProductosSeleccionados>) request.getSession().getAttribute("productosCarrito");
        if (productosCarrito == null) {
            productosCarrito = new ArrayList<>();
        }
        return productosCarrito;
    }

    // Mantiene los productos agregados en carrito durante la sesion
    private void guardarCarrito(ArrayList<ProductosSeleccionados> productosCarrito, HttpServletRequest request) {
        request.getSession().setAttribute("productosCarrito", productosCarrito);
    }

    private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }


    // End points GET:

    @GetMapping("/verCarrito")
    public ArrayList<ProductosSeleccionados> MostrarCarrito(Model model, HttpServletRequest request) {
        ArrayList<ProductosSeleccionados> productosCarrito = this.obtenerCarrito(request);
        return productosCarrito;
    }

    @GetMapping("/ventas")
    public List<Sale> MostrarVentas() {
        return saleRepository.findAll();
    }

    // End points POST:

    @PostMapping(value = "/agregar")
    // Agrega productos al carrito siempre y cuando cumplan con las condiciones de cantidades, si esta el producto o no
    public Object agregarAlCarrito(@RequestBody ProductosSeleccionados productos, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductosSeleccionados> productosCarrito = this.obtenerCarrito(request);
        var codigoEntrada = productos.getCodigoProducto();
        Productos porcodigo = productosRepository.findByCodigoProducto(codigoEntrada);
        if (porcodigo == null) {
            redirectAttrs.addFlashAttribute("mensaje", "El producto con el codigo " + productos.getCodigoProducto() + " no se encuentra").addFlashAttribute("clase", "warning");
            return "El producto con el codigo " + productos.getCodigoProducto() + " no se encuentra";
        }
        if (codigoEntrada.equals(porcodigo.getCodigoProducto())) {
            //Encuentra si el producto ya esta en el carrito y no lo deja volver a agregarlo
            for (int i = 0; i < productosCarrito.size(); i++) {
                if (productosCarrito.get(i).getCodigoProducto().equals(codigoEntrada)) {
                    redirectAttrs.addFlashAttribute("mensaje", "El producto ya se encuentra en el carrito de compras").addFlashAttribute("clase", "warning");
                    return "El producto ya se encuentra en el carrito de compras";
                }
            }
            // Compara la cantidad a comprar con el inventario actual del producto
            if (productos.getCantidadProducto() > porcodigo.getCantidadProducto()) {
                redirectAttrs.addFlashAttribute("mensaje", "La cantidad requerida es mayor al inventario").addFlashAttribute("clase", "warning");
                return "La cantidad requerida es mayor al inventario";
            }

            productosCarrito.add(new ProductosSeleccionados(porcodigo.getNombreProducto(), porcodigo.getCodigoProducto(), porcodigo.getPrecioProducto(), productos.getCantidadProducto()));

        }
        this.guardarCarrito(productosCarrito, request);
        return productosCarrito;
    }

    @PostMapping(value = "/realizar") // Cuando le da el boton de confirmar compra en el front
    public String realizarVenta(@RequestBody Sale venta, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ArrayList<ProductosSeleccionados> productosCarrito = this.obtenerCarrito(request);
        List<ProductosSeleccionados> ListaProductoSale = new LinkedList<>();

        if (productosCarrito == null || productosCarrito.size() <= 0) {
            return "redirect:/sale/";
        }

        //Recorre los productos del carrito y descuenta las cantidades del inventario
        for (ProductosSeleccionados productosSeleccionados : productosCarrito) {
            Productos p = productosRepository.findBynombreProducto(productosSeleccionados.getNombreProducto());
            if (p == null) continue;

            var resta = (p.getCantidadProducto())-(productosSeleccionados.getCantidadProducto());
            //p.descontarInventario(resta);
            p.setCantidadProducto(resta);
            productosRepository.save(p);

            ListaProductoSale.add(productosSeleccionados);
            //ProductosVendido vendido= new ProductosVendido(productosSeleccionados.getCodigoProducto(), productosSeleccionados.getPrecioProducto(), productosSeleccionados.getNombreProducto(), productosSeleccionados.getCantidadProducto());
            //productosVendidosRepository.save(vendido);

        }
        Sale sale = saleRepository.save(new Sale(venta.getCodigoVenta(),venta.getCedulaCliente(),ListaProductoSale));
        //productosVendidosRepository.deleteAll();
        this.limpiarCarrito(request); // Limpia el carrito despues de terminar el recorrido de productos

        redirectAttributes.addFlashAttribute("mensaje", "Venta realizada de forma exitosa").addFlashAttribute("clase", "success");
        return "redirect:/sale/";
    }
}

