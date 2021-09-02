<template>
<div class="px-4 py-5 my-5 text-center">
    <h1 class="display-5 fw-bold">Bienvenido</h1>
    <div class="col-lg-6 mx-auto">
      
      <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
        <button type="button" class="btn btn-primary btn-lg px-4 gap-3" v-on:click="gosignin" >Registrarse</button>
        <button type="button" class="btn btn-outline-secondary btn-lg px-4" v-on:click="gologin">Iniciar sesion</button>
      </div>

    </div>
    
  </div>
    
    
    
</template>

<script>
    import "bootstrap/dist/css/bootstrap.min.css"
    import "bootstrap/dist/js/bootstrap"
    export default{
        name: 'Welcome',

        methods:{
            gosignin: function(){
                this.$router.push({name: "signin"});
            },
            gologin: function(){
                this.$router.push({name: "login"});
            }
        }
    }
</script>

<style>

</style>