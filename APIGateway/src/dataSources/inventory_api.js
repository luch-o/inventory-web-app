const { RESTDataSource } = require('apollo-datasource-rest');

const serverConfig = require('../server');

class InventoryAPI extends RESTDataSource {
    constructor(){
        super();
        this.baseURL = serverConfig.inventory_api_url;
    }

    async productById(productId){
        console.log(productId)
        return await this.get(`/products/${productId}/`);  //Muestra producto por ID

    }
    async createProduct(product){
        product = new Object(JSON.parse(JSON.stringify(product)));  //Crear un producto
        return await this.post('/products/',product);

    }
    async modifyProduct(productId, modProduct){
        modProduct = new Object(JSON.parse(JSON.stringify(modProduct)));
        return await this.put(`/products/${productId}`, modProduct);   //Modificar un producto

    }
    async deleteProductById(productId){
        return await this.delete(`/products/${productId}`);  //Eliminar producto por ID

    }
    async inventoryProducts(){
        return await this.get(`/products/`); //Muestra todos los productos

    }
    async inventoryProductById(productId, partialProduct){
        partialProduct = new Object(JSON.parse(JSON.stringify(partialProduct))); //Modificacion parcial del producto
        return await this.patch(`/products/${productId}`, partialProduct);

    }
    async reduceProductById(productId, redProduct){
        redProduct = new Object(JSON.parse(JSON.stringify(redProduct)));
        return await this.patch(`/products/${productId}/reduce`, redProduct); //Reducir stock del producto por ID

    }


}

module.exports = InventoryAPI;