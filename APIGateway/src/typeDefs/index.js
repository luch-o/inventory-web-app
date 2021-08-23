//Se llama al typedef (esquema) de cada submodulo
//const accountTypeDefs = require('./account_type_def');
//const transacctionTypeDefs = require('./transacction_type_def');
//const usersTypeDefs = require('./users_type_defs');

const loginTypeDefs = require('./login_type_def');
const inventoryTypeDefs = require('./inventory_type_def');
const salesTypeDefs = require ('./sales_type_def');

//Se unen
//const schemasArrays = [accountTypeDefs, transacctionTypeDefs, usersTypeDefs];
const schemasArrays = [loginTypeDefs, inventoryTypeDefs, salesTypeDefs];

//Se exportan
module.exports = schemasArrays; 