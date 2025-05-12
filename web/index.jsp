<%@page import="clasesDataBase.ConectorBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String mensaje = "";
    if (request.getParameter("error") != null) {
        switch (request.getParameter("error")) {
            case "1":
                mensaje = "Usuario o contraseña no válida";
                break;
            case "2":
                mensaje = "Acceso denegado";
                break;
            default:
                mensaje = "Error desconocido";
        }
    }
    HttpSession sesion = request.getSession(false);
    if (sesion != null && sesion.getAttribute("usuario") != null) {
        response.sendRedirect("main.jsp?CONTENIDO=inicio.jsp");
        return;
    }
   
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
        <link rel="stylesheet" type="text/css" href="estilos/index.css?v=2">

    </head>
    <body>
        <div class="container h-100">
            <div class="d-flex justify-content-center h-100 align-items-center">
                <div class="card">                
                    <div class="left-panel">
                        <img src="recursos/obrerorm.jpeg" alt="Imagen lateral">
                    </div>

                    <div class="right-panel">
                        <div class="card-header position-relative">
                            <h4>Iniciar Sesión</h4>
                            <img src="recursos/Logo.jpg" class="small-logo" alt="Logo">
                        </div>
                        <div class="card-body">
                            <form method="post" action="GUI/Usuarios/validarUsuario.jsp">
                                <div class="input-group form-group" style="margin-bottom: 20px;">

                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name="general_usu_num_identificacion" required placeholder="Usuario" autocomplete="username">
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-key"></i></span>
                                    </div>
                                    <input type="password" class="form-control" name="general_usu_clave" required placeholder="Contraseña" autocomplete="current-password">
                                </div>
                                <div class="row align-items-center remember">
                                    <input type="checkbox" id="rememberMe"> Recordar contraseña
                                </div>
                                <p id="error" class="error-message" style="color: red;"><%= mensaje%></p>
                                <div class="form-group">
                                    <button type="submit" class="btn float-right login_btn">Ingresar</button>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer mt-4">
                            <div class="d-flex justify-content-center links mb-2">
                                ¿No tienes una cuenta? <a href="#"> Registrarse</a>
                            </div>
                            <div class="d-flex justify-content-center">
                                <a href="#">¿Olvidaste tu contraseña?</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <video autoplay loop muted playsinline style="position:fixed; right:0; bottom:0; min-width:100%; min-height:100%; z-index:-1;">
            <source src="https://www.dropbox.com/scl/fi/3ojz3i59quh46gbybsh8h/fondomedicron.mp4?rlkey=6n27xf21wi3lfj5tuyx3elqt9&st=9q4fvhgi&raw=1" type="video/mp4">
            Tu navegador no soporta videos HTML5.
        </video>


        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const userInput = document.querySelector('input[name="general_usu_num_identificacion"]');
                const passInput = document.querySelector('input[name="general_usu_clave"]');
                const rememberMe = document.getElementById("rememberMe");

                if (localStorage.getItem("remember") === "true") {
                    userInput.value = localStorage.getItem("username") || "";
                    passInput.value = localStorage.getItem("password") || "";
                    rememberMe.checked = true;
                }

                document.querySelector("form").addEventListener("submit", function () {
                    if (rememberMe.checked) {
                        localStorage.setItem("remember", "true");
                        localStorage.setItem("username", userInput.value);
                        localStorage.setItem("password", passInput.value);
                    } else {
                        localStorage.removeItem("remember");
                        localStorage.removeItem("username");
                        localStorage.removeItem("password");
                    }
                });
            });
        </script>
    </body>
</html>
