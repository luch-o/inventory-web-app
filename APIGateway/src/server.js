module.exports = {
    login_api_url: process.env.LOGIN_API_URL || 'https://ferreterialogin.herokuapp.com',
    inventory_api_url: process.env.INVENTORY_API_URL || 'https://c4-products-ms.herokuapp.com/',
    //sales_api_url: process.env.SALES_API_URL || '',
    
};

//heroku login
//heroku container:login
//heroku container:push web --app apigatewayproyecto
//heroku container:release web --app apigatewayproyecto
//https://apigatewayproyecto.herokuapp.com/