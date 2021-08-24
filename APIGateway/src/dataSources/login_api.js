const { RESTDataSource } = require('apollo-datasource-rest');

const serverConfig = require('../server');

class LoginAPI extends RESTDataSource{
    constructor(){
        super();
        this.baseURL = serverConfig.login_api_url;
    }

    async authRequest(credentials) {
        credentials = new Object(JSON.parse(JSON.stringify(credentials))); //Verificar token de acceso
        return await this.post(`/token/`, credentials);
    }

    async refreshToken(token) {
        token = new Object(JSON.parse(JSON.stringify({refresh: token}))); //Renovacion del token de acceso
        return await this.post(`/token/refresh/`, token);
    }


    async registerUser(user){
        user = new Object(JSON.parse(JSON.stringify(user))); //Registra usuario
        return await this.post(`/register/`);
    }

    async userById(userId){
        return await this.get(`/users/${userId}`); //Obtener usuario por ID
    }

    


}

module.exports = LoginAPI;