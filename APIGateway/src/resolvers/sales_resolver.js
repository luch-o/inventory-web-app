const { conformsTo } = require("lodash");

const salesResolver = {
    Query:{
        obtenerVentas:(_,{cuentaid},{dataSources,userIdToken}) =>{
            if(cuentaid == userIdToken)
                return dataSources.SaleAPI.obtenerVentas();
            else
                return null
        },

        verCarrito:(_, {cuentaid},{dataSources,userIdToken}) =>{
            if(cuentaid == userIdToken)
                return dataSources.SaleAPI.verCarrito();
            else
                return null
        },
    },

    Mutation:{
        realizarVenta:(_,{ventaid},{dataSources,userIdToken}) =>{
            
            let separation = {"cedulaCliente": ventaid.cedulaCliente, "codigoVenta": ventaid.codigoVenta, "productosVendidos": ventaid.productosVendidos}
            let body = JSON.parse(JSON.stringify(separation))
            console.log(body)
            return dataSources.SaleAPI.realizarVenta(body);
        },

        agregarVenta:(_,{ventaid},{dataSources,userIdToken}) =>{
            let separation = {"productosVendidos": ventaid.carrito}
            let body = JSON.parse(JSON.stringify(separation))
            console.log(body)
            return dataSources.SaleAPI.agregarVenta(body)
        },

    }
};

module.exports = salesResolver;