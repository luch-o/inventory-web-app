const loginResolver ={
    Query:{
        userById:(_,{userId}, {dataSources}) => {
            
            return dataSources.LoginAPI.userById(userId);
        
        },

        getAllUsers:(_, __, {dataSources}) => {

            return dataSources.LoginAPI.getAllUsers();
        },

        
    },

    Mutation:{
        registerUser:(_,{user},{dataSources}) => {
            return dataSources.LoginAPI.registerUser(user);
        },
    }


    

};

module.exports = loginResolver;