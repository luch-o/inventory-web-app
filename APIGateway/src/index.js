
const { ApolloServer } = require('apollo-server');

const typeDefs = require('./typeDefs');
const resolvers = require('./resolvers');
const LoginAPI = require('./dataSources/login_api');
const InventoryAPI = require('./dataSources/inventory_api');


const authentication = require('./utils/authentication');


const server = new ApolloServer({
    context: authentication,
    typeDefs,
    resolvers,
    dataSources: () => ({
        LoginAPI: new LoginAPI(),
        //salesAPI: new SalesAPI(),
        InventoryAPI: new InventoryAPI(), 

    }),
    introspection: true,
    playground: true
});

server.listen(process.env.PORT || 4000).then(({ url }) => {
    console.log(`🚀  Server ready at ${url}`);
});