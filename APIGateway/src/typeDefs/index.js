//Se llama al typedef (esquema) de cada submodulo
const accountTypeDefs = require('./account_type_def');
const transacctionTypeDefs = require('./transacction_type_def');
const usersTypeDefs = require('./users_type_defs');

//Se unen
const schemasArrays = [accountTypeDefs, transacctionTypeDefs, usersTypeDefs];

//Se exportan
module.exports = schemasArrays; 