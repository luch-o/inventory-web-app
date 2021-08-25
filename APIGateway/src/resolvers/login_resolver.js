const loginResolver ={
    Query:{
        userById:(_, {userId}, {dataSources, userIdToken}) => {
            if(userId == userIdToken) 
                return dataSources.LoginAPI.userById(userId)
            else
                return null
        },
    },

    

};

module.exports = loginResolver;