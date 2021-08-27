const { gql } = require('apollo-server');

const loginTypeDefs = gql`
    type Login {
        id: Int
        username: String
        password: String
    }
  

    input sign_in{
        username: String!
        password: String!
    }

    type Query{
        userById(userId: Int!): Login
        getAllUsers: [Login]
        
    }
    
    type Mutation{
        registerUser(user:sign_in): Login
    }

   
    


    

`;

module.exports = loginTypeDefs; 