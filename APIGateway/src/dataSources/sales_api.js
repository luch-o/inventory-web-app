const { RESTDataSource } = require('apollo-datasource-rest');

const serverConfig = require('../server');

class SalesAPI extends RESTDataSource{
    constructor(){
        super();
        this.baseURL = serverConfig.sales_api_url;
    }

}

module.exports = SalesAPI;