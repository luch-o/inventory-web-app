const { gql } = require('apollo-server');

const inventoryTypeDefs = gql`

    type Product {
        cuentaid: Int
        name: String
        description: String
        price: Int
        stock: Int
        id: Int
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


    input modproducto{
        name: String
        description: String
        price: Int
        stock: Int
        id: Int!
        cuentaid: Int!
    }

    input redproducto{
        id: Int!
        stock: Int
        cuentaid: Int!
    }

    type Query{
        productById(productId: getproductoid): Product
    }

    extend type Query{
        inventoryProducts: [Product]
    }

    type Mutation{
        createProduct(product: addnewproduct): Product
        
    }
    
    extend type Mutation{
        deleteProduct(productId: getproductoid): Product
        inventoryProductMod(productId: modproducto): Product
        reduceProductRedStock(productId: redproducto): Product
    }
    
    
`;

module.exports = inventoryTypeDefs; 