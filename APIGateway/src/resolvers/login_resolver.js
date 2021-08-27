const loginResolver ={
    Query:{
        userById:(_,{userId}, {dataSources, userIdToken}) => {
            if(userId == userIdToken)
                return dataSources.LoginAPI.userById(userId)
            else
                return null
            
        
        },

        getAllUsers:(_, __, {dataSources}) => {

            return dataSources.LoginAPI.getAllUsers()
        },

        
    },

    Mutation:{
        registerUser:(_,{user},{dataSources}) => {
            return dataSources.LoginAPI.registerUser(user)
        },

        authRequest:(_,{credentials}, {dataSources}) =>{

            return dataSources.LoginAPI.authRequest(credentials)
        },

        refreshToken:(_,{refresh}, {dataSources}) => {

            return dataSources.LoginAPI.refreshToken(refresh)
        },

    }


    

};

module.exports = loginResolver;