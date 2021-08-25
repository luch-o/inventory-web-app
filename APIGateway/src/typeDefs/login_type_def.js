const { gql } = require('apollo-server');

const loginTypeDefs = gql`
    type Login {
        username: String
    }

    type Query{
        userById(userId: Int!): Login
    }
    

`;

module.exports = loginTypeDefs; 