const loginResolver ={
    Query:{
        userById:(_,{id}, {dataSources}) => {

            return dataSources.LoginAPI.userById();
            
        },
    },

    

};

module.exports = loginResolver;