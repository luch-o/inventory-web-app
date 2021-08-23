//const accountResolver = require('./account_resolver');
//const transactionResolver = require('./transaction_resolver');
//const userResolver = require('./users_resolver');

const loginResolver = require('./login_resolver');
const inventoryResolver = require('./inventory_resolver');
const salesResolver = require('./sales_resolver');

const lodash = require('lodash');

const resolvers = lodash.merge(loginResolver, inventoryResolver, salesResolver);

module.exports = resolvers;