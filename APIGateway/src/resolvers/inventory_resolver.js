const { partial, property } = require("lodash");

const inventoryResolver = {
    Query:{
        productById: (_, {productId}, { dataSources, userIdToken}) => {

            
            if( productId.cuentaid == userIdToken)
                
                return dataSources.InventoryAPI.productById(productId.productoid);

            else

                return null
            
        },

        inventoryProducts:(_, __, {dataSources}) =>{
            
            return dataSources.InventoryAPI.inventoryProducts();
            
        }
    },

    Mutation:{
        createProduct:(_, {product},{dataSources,userIdToken}) =>{
            let separation = {"name":product.name, "description":product.description, "price":product.price, "stock":product.stock}
            let body = JSON.parse(JSON.stringify(separation))
            if(product.cuentaid == userIdToken)
                return dataSources.InventoryAPI.createProduct(body)
            else
                return null

        },

        deleteProduct:(_,{productId},{dataSources, userIdToken}) =>{
            if(productId.cuentaid == userIdToken)
                return dataSources.InventoryAPI.deleteProduct(productId.productoid)
            else
                return null
        },

        inventoryProductMod:(_,{productId},{dataSources,userIdToken}) =>{
            let separation = {"name":productId.name, "description":productId.description, "price":productId.price, "stock":productId.stock}
            let body1 = JSON.parse(JSON.stringify(separation))
            let var1 = productId.id
            
            if(productId.cuentaid == userIdToken)
                return dataSources.InventoryAPI.inventoryProductMod(var1,body1) //Nueva variable aqui
            else
                return null
        },

        reduceProductRedStock:(_,{productId}, {dataSources,userIdToken}) =>{
            let separation = {"quantity":productId.stock}
            let body1 = JSON.parse(JSON.stringify(separation))
            let var1 = productId.id
            if(productId.cuentaid == userIdToken)
                return dataSources.InventoryAPI.reduceProductRedStock(var1,body1)
            else
                return null
        },
        
        modifyProduct:(_,{productId},{dataSources,userIdToken}) =>{
            let separation = {"id":productId.id,"name":productId.name, "description":productId.description,"stock":productId.stock,"price":productId.price}
            let body1 = JSON.parse(JSON.stringify(separation))
            let var1 = productId.id
            console.log(separation)
            console.log(body1)
            if(productId.cuentaid == userIdToken)
                return dataSources.InventoryAPI.modifyProduct(var1,body1)
            else
                return null
        },

    },

};
module.exports = inventoryResolver;