//Se llama al typedef (esquema) de cada submodulo


const loginTypeDefs = require('./login_type_def');
const inventoryTypeDefs = require('./inventory_type_def');
//const salesTypeDefs = require ('./sales_type_def');

//Se unen

const schemasArrays = [loginTypeDefs, inventoryTypeDefs];
//, salesTypeDefs];

//Se exportan
module.exports = schemasArrays; 