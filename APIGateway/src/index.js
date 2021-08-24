
const { ApolloServer } = require('apollo-server');

const typeDefs = require('./typeDefs');
const resolvers = require('./resolvers');
//const AccountAPI = require('./dataSources/account_api');
//const UsersAPI = require('./dataSources/users_api');

const LoginAPI = require('./dataSources/login_api');
const SalesAPI = require('./dataSources/sales_api');
const InventoryAPI  = require('./dataSources/inventory_api');

const authentication = require('./utils/authentication');


const server = new ApolloServer({
    context: authentication,
    typeDefs,
    resolvers,
    dataSources: () => ({
        //accountAPI: new AccountAPI(),
        //usersAPI: new UsersAPI(),
        loginAPI: new LoginAPI(),
        salesAPI: new SalesAPI(),
        inventoryAPI: new InventoryAPI(), 

    }),
    introspection: true,
    playground: true
});

server.listen(process.env.PORT || 4000).then(({ url }) => {
    console.log(`🚀  Server ready at ${url}`);
});