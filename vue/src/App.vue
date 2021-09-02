<template>
<nav class="navbar navbar-light bg-light fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">INVENTARIO FERRETERIA</a>
    <button v-if="is_auth" class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
      <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Menú</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">INICIO</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">PRODUCTOS</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">VENTAS</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" v-on:click="logOut" data-bs-dismiss="offcanvas" >CERRAR SESION</a>
          </li>
        </ul>
          
      </div>
    </div>
  </div>

</nav>

  <div class="main-component">
      
      <router-view v-on:log-in="logIn"></router-view>
      
  </div>

  
</template>

<script>
  import "bootstrap/dist/css/bootstrap.min.css"
  import "bootstrap/dist/js/bootstrap"
  import gql from "graphql-tag";

  export default {
    name: "App",

    data: function () {
      return {
        is_auth: false,
      };
    },

    created: function () {

      if(this.is_auth == true){
        this.updateAccessToken();
      }
      
    },

    methods:{
      //Preguntar registro, porque no pinta el registro, error await en el apollo
      updateAccessToken: async function () {
        if (localStorage.getItem("refresh_token") == null) {
          this.$router.push({ name: "login" });
          this.is_auth = false;
          return;
        }
        await this.$apollo
        .mutate({
          mutation: gql`
            mutation RefreshTokenMutation($refreshTokenRefresh: String) {
              refreshToken(refresh: $refreshTokenRefresh) {
                access
              }
            }
          `,
          variables: {
            refreshTokenRefresh: localStorage.getItem("refresh_token"),
          },
        })
        .then((result) => {
          localStorage.setItem("access_token", result.data.refreshToken.access);
          this.is_auth = true;
        })
        .catch((error) => {
          alert("Su sesión expiró, vuelva a iniciar sesión.");
          this.$router.push({ name: "login" });
          this.is_auth = false;
          localStorage.clear();
        });
        
      },

      logIn: async function (data, username) {
        
        localStorage.setItem("access_token", data.access);
        localStorage.setItem("refresh_token", data.refresh);
        localStorage.setItem("user_id", data.user_id);
        localStorage.setItem("current_username", username);

        await this.updateAccessToken();
        if (this.is_auth){
          this.home();
        } 
      },

      home: function () {
        this.$router.push({
          name: "home",
          params: { username: localStorage.getItem("current_username") },
        });
      },
      logOut: async function () {
        localStorage.removeItem("access_token");
        localStorage.removeItem("refresh_token");
        localStorage.removeItem("user_id");
        localStorage.removeItem("current_username");
        alert("Se ha cerrado sesion exitosamente");
        await this.updateAccessToken();
      },

      


    },
  }
</script>

<style>
  *{
    background: white;
  }
  p{
    margin: 100px;
  }
  
</style>
