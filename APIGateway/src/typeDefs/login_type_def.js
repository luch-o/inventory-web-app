const { gql } = require('apollo-server');

const loginTypeDefs = gql`
    type Login {
        id: Int!
        username: String
        password: String
        
    }
    
    type Query{
        userById(id: Int!): Login
    }
    

`;

module.exports = loginTypeDefs; 