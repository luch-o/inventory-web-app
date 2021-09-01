<template>
    <div class="text-center mt-5">
        <form style="max-width:480px;margin:auto;">
            <br>
            <div class="shadow p-3 mb-5 bg-white rounded">
                <h1>FERRETERIA</h1>
                <label>GRUPO 5 MINTIC</label>
            </div>
            <br>
            <h1 class="h3 mb-3 font-weight-normal">Log In</h1>
            <input type="text" id="usuario" name="usuario" class="form-control" placeholder="Usuario" required autofocus>
            <input type="password" id="password" name="password" placeholder="Contraseña" class="form-control">
            <br>
            <div class="mt-3">
                <button class="btn btn-lg btn-primary btn-block">Log In</button>
            </div>
            <br>
            <div class="Registrar usuario">
                <a class="link__registro" href="/registrousuario/">Registrate</a>
            </div>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
</template>

<script>
    import "bootstrap/dist/css/bootstrap.min.css"
    import "bootstrap/dist/js/bootstrap"

export default {
    
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