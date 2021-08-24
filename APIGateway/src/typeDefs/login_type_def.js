const { gql } = require('apollo-server');

const loginTypeDefs = gql`
    type Login {
        id: Integer!
        username: String
        password: String
    }
    
    type Query{
        userById(id: Integer!): Login
    }
`;

module.exports = loginTypeDefs; 