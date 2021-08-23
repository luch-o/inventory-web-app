const { RESTDataSource } = require('apollo-datasource-rest');

const serverConfig = require('../server');

class LoginAPI extends RESTDataSource{
    constructor(){
        super();
        this.baseURL = serverConfig.login_api_url;
    }

}

module.exports = LoginAPI;