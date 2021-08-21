const transactionResolver = {
    Query: {
        transacctionByIdUser: (_, { userId }, {dataSources, userIdToken}) => {
            if(userId == userIdToken) 
                return dataSources.accountAPI.transacctionByIdUser(userId)
            else
                return null
        }
    },
    Mutation: {
        createTransacction: (_, {transaction}, {dataSources, userIdToken}) => {
            if(transaction.userIdOrigin == userIdToken) 
                return dataSources.accountAPI.createTransacction(transaction)
            else
                return null
        }
        
    }
};


module.exports = transactionResolver;