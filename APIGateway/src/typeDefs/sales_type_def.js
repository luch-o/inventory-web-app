const { gql } = require('apollo-server');

const salesTypeDefs = gql`

    type productos{
        codigo: Int
        nombre: String
        precio: Float
        cantidad: Int
    }

    type venta{
        codigo: Int
        cedula: Int
        productosVendidos: [productos!]
    }

    type Query{
        obtenerVentas: [venta]
    }
    
`;

module.exports = salesTypeDefs; 