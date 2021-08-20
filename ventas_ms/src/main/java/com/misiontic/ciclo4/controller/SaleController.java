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

@RestController
@RequestMapping(path = "/sale") //Ruta general de ventas
public class SaleController {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductosRepository productosRepository;
    @Autowired
    private ProductosVendidosRepository productosVendidosRepository;

    @PostMapping(value = "/realizar") // Cuando le da el boton de confirmar compra en el front
    public String realizarVenta(HttpServletRequest request, RedirectAttributes redirectAttributes){
        ArrayList<ProductosSeleccionados> productosCarrito = this.obtenerCarrito(request);
        if (productosCarrito == null || productosCarrito.size() <= 0) {
            return "redirect:/sale/";
        }
        //Recorre los productos del carrito y descuenta las cantidades del inventario
        for (ProductosSeleccionados productosSeleccionados : productosCarrito){
            Productos p = productosRepository.findBynombreProducto(productosSeleccionados.getNombreProducto());
            if (p==null) continue;
            p.descontarInventario(productosSeleccionados.getCantidadProducto());
            productosRepository.save(p);
            ProductosVendido productosVendido = new ProductosVendido(productosSeleccionados.getCodigoProducto(),productosSeleccionados.getPrecioProducto(),productosSeleccionados.getNombreProducto(),productosSeleccionados.getCantidad());
            productosVendidosRepository.save(productosVendido);
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
    private void guardarCarrito(ArrayList<ProductosSeleccionados> productosCarrito, HttpServletRequest request) {
        request.getSession().setAttribute("productosCarrito",productosCarrito);
    }
    // Crea el array de carrito
    private ArrayList<ProductosSeleccionados> obtenerCarrito(HttpServletRequest request) {
        ArrayList<ProductosSeleccionados> productosCarrito = (ArrayList<ProductosSeleccionados>) request.getSession().getAttribute("productosCarrito");
        if (productosCarrito == null) {
            productosCarrito = new ArrayList<>();
        }
        return productosCarrito;
    }

    @GetMapping("/verCarrito")
    public ArrayList<ProductosSeleccionados> MostrarCarrito(Model model, HttpServletRequest request) {
        ArrayList<ProductosSeleccionados> productosCarrito = this.obtenerCarrito(request);
        return productosCarrito;
    }

    @PostMapping(value = "/agregar") // Agrega productos al carrito siempre y cuando cumplan con las condiciones de cantidades, si esta el producto o no
    public Object agregarAlCarrito(@ModelAttribute ProductosSeleccionados productos, HttpServletRequest request, RedirectAttributes redirectAttrs){
        ArrayList<ProductosSeleccionados> productosCarrito = this.obtenerCarrito(request);
        Productos porcodigo = productosRepository.findByCodigoProducto(productos.getCodigoProducto());
        if (porcodigo == null){
            redirectAttrs.addFlashAttribute("mensaje","El producto con el codigo "+productos.getCodigoProducto()+" no se encuentra").addFlashAttribute("clase","warning");
            return "redirect:/sale";
        }
        if (porcodigo.sinCantidad()){
            redirectAttrs.addFlashAttribute("mensaje", "El producto esta agotado").addFlashAttribute("clase","warning");
        }

        boolean encontrado = false;
        if (!encontrado){
            productosCarrito.add(new ProductosSeleccionados(porcodigo.getNombreProducto(), porcodigo.getPrecioProducto(), porcodigo.getCodigoProducto(), porcodigo.getCantidadProducto(), 1f));
        }
        this.guardarCarrito(productosCarrito, request);
        return productosCarrito;
    }

}
