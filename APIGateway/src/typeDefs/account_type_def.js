const { gql } = require('apollo-server');

const accountTypeDefs = gql`
    type Account {
        userId: String!
        balance: Int
        lastChange: String
    }
    
    type Query {
        accountByUserId(userId: String!): Account
    }
`;

module.exports = accountTypeDefs; 
