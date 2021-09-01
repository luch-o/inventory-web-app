<template>
    <div class="text-center mt-5">
        <form v-on:submit.prevent="processAuthUser" style="max-width:480px;margin:auto;">
            <br>
            <div id="AuthUser"  class="shadow p-3 mb-5 bg-white rounded">
                <h1>FERRETERIA</h1>
                <label>GRUPO 5 MINTIC</label>
            </div>
            <br>
            <h1 class="h3 mb-3 font-weight-normal">Log In</h1>
            <input type="text" id="usuario" name="usuario" class="form-control" v-model="user_in.username" placeholder="Username" required autofocus>
            <input type="password" id="password" name="password" placeholder="Password" v-model="user_in.password" class="form-control">
            <br>
            <div class="mt-3">
                <button  type="submit" class="btn btn-lg btn-primary btn-block">Log In</button>
            </div>
            <br>
            <div class="Registrar usuario">
                <a class="link__registro" href="/registrousuario/">Registrate</a>
            </div>
        </form>
    </div>
    
</template>

<script>

    import "bootstrap/dist/css/bootstrap.min.css"
    import "bootstrap/dist/js/bootstrap"
    import gql from "graphql-tag";
    import jwt_decode from "jwt-decode"

    export default {
        name:"Login",

        data: function(){
            return {
                user_in: {
                    username:"",
                    password:""
                }
            }
        },

        methods: {
            processAuthUser: async function(){
                await this.$apollo.mutate({
                    mutation: gql`
                        mutation ($authRequestCredentials: sign_in) {
                            authRequest(credentials: $authRequestCredentials) {
                                access
                                refresh
                            }
                        }`, 
                    variables: {
                        authRequestCredentials: this.user_in
                    }
                    
                })  .then((result) => {

                        let data = result.data.authenticate
                        data.user_id = jwt_decode(data.access).user_id.toString().padStart(3, "0")

                        this.$emit('log-in', data, this.user_in.username)

                    }).catch((error) => {
                        alert("El usuario y/o contraseña son incorrectos")
                    });
                    
            }
        }
    } 
    
</script>

<style>

    input[type="usuario"] {
        border-bottom-left-radius: 0px;
        border-bottom-right-radius: 0px;
    }
    input[type="password"] {
        border-bottom-left-radius: 0px;
        border-bottom-right-radius: 0px;
    }
    
</style>