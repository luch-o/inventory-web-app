const { gql } = require('apollo-server');

const salesTypeDefs = gql`

    type productos{
        id: Int
        name: String
        description: String
        price: Float
        stock: Int
        subTotal: Float
        total: Float
    }

    type venta{
        cuentaid: Int
        total: Float
        codigoVenta: Int
        cedulaCliente: Int
        productosVendidos: [productos]
    }

    type venta1{
        cuentaid: Int
        total: Float
        codigoVenta: Int
        cedulaCliente: Int
        productosVendidos: [productos]
    }

    input productosCar{
        id: Int
        name: String
        description: String
        price: Int
        stock: Int
    }

    input agregarVentavar{
        cuentaid: Int
        codigoVenta: Int
        cedulaCliente: Int
        productosVendidos: [productosCar]
    }

    input agregarCarrito{
        cuentaid: Int
        carrito: [productosCar]
    }

    type Query{
        obtenerVentas(cuentaid: Int ): [venta]
    }

    extend type Query{
        verCarrito(cuentaid: Int): [productos]
    }
    
    type Mutation{
        realizarVenta(ventaid: agregarVentavar ): venta
    }
    
    extend type Mutation{
        agregarVenta(ventaid: agregarCarrito): [productos]
    }
`;

module.exports = salesTypeDefs; 