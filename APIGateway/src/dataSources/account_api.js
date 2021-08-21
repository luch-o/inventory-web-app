const { RESTDataSource } = require('apollo-datasource-rest');

const serverConfig = require('../server');

class AccountAPI extends RESTDataSource {
  constructor() {
    super();
    this.baseURL = serverConfig.account_api_url;    
  }

  async accountByUserId(userId){
    return await this.get(`/accounts/${userId}`);    
  }

  async createTransacction(transacction){
    transacction = new Object(JSON.parse(JSON.stringify(transacction)));
    return await this.post('/transactions', transacction);    
  }

  async transacctionByIdUser(userId){
    return await this.get(`/transactions/${userId}`);
    
  }
}

module.exports = AccountAPI;