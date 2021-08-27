const inventoryResolver = {
    Query:{
        productById: (_, {productId}, { dataSources, userIdToken}) => {

            
            if( productId.cuentaid == userIdToken)
                
                return dataSources.InventoryAPI.productById(productId.productoid);

            else
                
                return null
            
        }
    },

    Mutation:{
        createProduct:(_, {product},{dataSources,userIdToken}) =>{
            let variable = {"name":product.name, "description":product.description, "price":product.price, "stock":product.stock}
            let var1 = JSON.parse(JSON.stringify(variable))
            console.log(product)
            console.log(var1)
            if(product.cuentaid == userIdToken)
                return dataSources.InventoryAPI.createProduct(var1)
            else
                return null

        }
    },

};
module.exports = inventoryResolver;