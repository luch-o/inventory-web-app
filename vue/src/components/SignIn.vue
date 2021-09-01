<template>
    <section class="Form my-1">
        <div class="container">
            <div class="row no-gutters">
                <div class="col-lg-5 p-0 imagen">
                    <img src="/logo.jpg" class="img-fluid" alt="Responsive image">
                </div>
                <div class="col-lg-7 px-5 pt-5 pb-5">
                    <h1 class="font-weight-bold py-3">¡Registrate!</h1>
                    <h4>Crea una nueva cuenta</h4>
                    <form>
                        <div class="form-row">
                            <div class="col-lg-7">
                                <input type="text" placeholder="Usuario" class="form-control my-3 p-2">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-lg-7">
                                <input type="password" placeholder="Contraseña" class="form-control my-3 p-2">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-lg-7">
                                <input type="password" placeholder="Repetir contraseña" class="form-control my-3 p-2">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-lg-7">
                                <button type="button" class="btn1 mt-3 mb-5">Registrarme</button>
                            </div>
                        </div>
                        <a href="#">¿Ya tienes una cuenta? Inicia sesión!</a>
                    </form>
                </div>
            </div>
        </div>
    </section>
</template>


<script>
export default {
    
}
</script>


<style>
*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
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