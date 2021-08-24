const { gql } = require('apollo-server');

const inventoryTypeDefs = gql`

    type Product {

        code: String!
        name: String
        description: String
        price: Integer
        stock: Integer
    }

    type Query{
        productById(code: String!): Product
    }
`;

module.exports = inventoryTypeDefs; 