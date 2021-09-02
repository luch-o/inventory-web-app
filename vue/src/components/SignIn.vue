<template>
<div class="container">
            <div class="row no-gutters">
                <div class="col-lg-5 p-0 imagen">
                    <img src="@/assets/logo.jpg" class="img-fluid" alt="Responsive image">
                </div>
                <div class="col-lg-7 px-5 pt-5 pb-5">
                    <h1 class="font-weight-bold py-3">¡Registrate!</h1>
                    <h4>Crea una nueva cuenta</h4>
                    <form>
                        <div class="form-row">
                            <div class="col-lg-7">
                                <input type="text" placeholder="Usuario"  v-model="user.username" class="form-control my-3 p-2">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-lg-7">
                                <input type="password" placeholder="Contraseña" v-model="user.password" class="form-control my-3 p-2">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-lg-7">
                                <input type="password" v-model="password2" placeholder="Repetir contraseña" class="form-control my-3 p-2">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-lg-7">
                                <button type="button" class="btn1 mt-3 mb-5" v-on:click="registrarse">Registrarme</button>
                            </div>
                        </div>
                        <a href="/login">¿Ya tienes una cuenta? Inicia sesión!</a>
                    </form>
                </div>
            </div>
</div>
    
</template>


<script>
    import gql from "graphql-tag"
    import "bootstrap/dist/css/bootstrap.min.css"
    import "bootstrap/dist/js/bootstrap"
    export default {
        name: "SignIn",

        data:function(){
            return {
                user:{
                    username: "",
                    password: ""
                    
                },

                password2: ""
                
            }
        },
        methods: {
            
            registrarse: async function(){
                if(this.user.password !== this.password2){
                    alert("Las contraseñas son diferentes!!")
                }
                else{
                    await this.$apollo.mutate({
                    mutation: gql`
                        mutation Mutation($registerUserUser: sign_in) {
                            registerUser(user: $registerUserUser) {
                                username
                            }
                        }`, 
                        variables: {
                            registerUserUser: this.user
                        }
                    })   
                
                    alert("El usuario ha sido creado existosamente")
                }
                
                
            },
            
        }

    }
</script>


<style>
*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
.container{
    margin: 100px;
}
body{
    background-color: rgb(195, 216, 216);
}
.row{
    background: white;
    border-radius: 30px;
    box-shadow: 4px 4px 14px grey;
}
.imagen{
    display: flex;
}
img{
    border-top-left-radius: 30px;
    border-bottom-left-radius: 30px;
}
.btn1{
    border: none;
    outline: none;
    height: 50px;
    width: 100%;
    background-color: black;
    color: white;
    border-radius: 4px;
    font-weight: bold;
}
.btn1:hover{
    background: white;
    border: 1px solid;
    color: black;
}
a{
    text-decoration: none;
}
</style>