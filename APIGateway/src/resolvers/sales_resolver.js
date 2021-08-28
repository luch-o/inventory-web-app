const salesResolver = {
    Query:{
        obtenerVentas:(_,__,{dataSources,userIdToken}) =>{
            return dataSources.SaleAPI.obtenerVentas();
        }
    }
};

module.exports = salesResolver;