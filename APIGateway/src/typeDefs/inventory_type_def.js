const { gql } = require('apollo-server');

const inventoryTypeDefs = gql`

    type Product {
        cuentaid: Int
        name: String
        description: String
        price: Int
        stock: Int
    }
    input getproductoid{
        productoid: Int
        cuentaid: Int
    }
    
    input addnewproduct{
        cuentaid: Int!
        name: String
        description: String
        price: Int
        stock: Int
    }

    type Query{
        productById(productId: getproductoid): Product
    }

    type Mutation{
        createProduct(product: addnewproduct): Product
    }
`;

module.exports = inventoryTypeDefs; 