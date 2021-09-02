const { RESTDataSource } = require('apollo-datasource-rest');

const serverConfig = require('../server');

class SaleAPI extends RESTDataSource{
    constructor(){
        super();
        this.baseURL = serverConfig.sales_api_url;
    }

    async obtenerVentas(){
        return await this.get(`/sale/ventas`); //Obtener listado de ventas X
    }
    
    async agregarVenta(ventaid){
        //ventaid = JSON.parse(JSON.stringify(ventaid));
        return await this.post(`/sale/agregar`, ventaid); //Array listado de carrito
    }

    async quitarVenta(ventaid){
        return await this.post(`/sale/quitar/${ventaid}`); //Recibe id venta
    }

    async cancelarVenta(){
        return await this.post(`/sale/cancelar`);  //No recibe nada.
    }

    async realizarVenta(ventaid1){
        //ventaid1 = JSON.parse(JSON.stringify(ventaid1))
        return await this.post(`/sale/realizar`, ventaid1); //Recibe la cedula y codigo de venta ?
    }

    async verCarrito(){
        return await this.get(`/sale/verCarrito`); //ver listado del carrito actual X
    }

}

module.exports = SaleAPI;