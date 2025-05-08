
<%-- 
    Document   : main
    Created on : 31/01/2025, 09:48:16 AM
    Author     : IPS OBRERO
--%>
<%@page import="clases.ConfiguracionGeneral.GeneralUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?error=2");
        return;
    }

    GeneralUsuario usuario = (GeneralUsuario) sesion.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de F.E Y GProveedores</title>

    <!-- jQuery (primero) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- jQuery UI (Autocomplete) -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>

    <!-- DataTables -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Otros estilos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">       
    <link href="https://fonts.googleapis.com/css2?family=Work+Sans:wght@300;400&family=Nunito:wght@300;400&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="estilos/estiloMain.css">  

    <script>
        if (localStorage.getItem("theme") === "dark") {
            document.documentElement.classList.add("dark-mode");
        }
    </script>
</head>


    <body>
         <nav class="navbar navbar-expand-lg navbar-dark px-3">
        <a class="navbar-brand" href="#">
            <img src="recursos/Logo.jpg" alt="Logo de RMIPS" width="35%" height="100%">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <!-- Izquierda (vacía o logo adicional si necesitas) -->
            <ul class="navbar-nav me-auto d-flex align-items-center gap-2"></ul>

            <!-- Íconos centrados -->
         <ul class="navbar-nav center-icons">
                <li class="nav-item">
                    <a href="main.jsp?CONTENIDO=inicio.jsp" class="nav-link text-white text-center" title="Inicio">
                        <i class="fas fa-home fa-lg"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="main.jsp?CONTENIDO=GUI/General/acercaDe.jsp" class="nav-link text-white text-center" title="Acerca de">
                        <i class="fas fa-user-tie fa-lg"></i> 
                    </a>
                </li>
            </ul>

            <!-- Derecha (menú y avatar) -->
          
<ul class="navbar-nav right-icons">
                <li class="nav-item dropdown">
                    <a class="nav-link" href="#" id="menuDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-th grid-icon"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-end custom-menu p-3" aria-labelledby="menuDropdown">
                        <h6 class="fw-bold">Menú</h6>
                        <input type="text" class="form-control mb-2" placeholder="Buscar en el menú">
                        <ul class="list-unstyled mb-0">
                            <%= usuario.typeObject().obtenerMenuOpcionesRolUsuario(usuario.getIdRol())%>
                        </ul>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" role="button" data-bs-toggle="dropdown">
                        <img src="recursos/user.jpg" alt="Usuario" class="rounded-circle" width="35" height="35" style="border: 2px solid green;">
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li>
                            <a class="dropdown-item" href="#" id="toggleTheme">
                                <i class="fas fa-adjust"></i> Modo Oscuro/Claro
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item text-danger" href="Interfaces/General/logout.jsp">
                                <i class="fas fa-sign-out-alt"></i> Cerrar Sesión
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>




        <div class="content">        
            <jsp:include page='<%=request.getParameter("CONTENIDO")%>' flush='true'/>
        </div>






        <div class="modal fade" id="perfilModal" tabindex="-1" aria-labelledby="perfilModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="perfilModalLabel">Editar Perfil</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="perfilForm" action="Interfaces/Usuarios/actualizarPerfil.jsp" method="post">
                            <input type="hidden" name="idUsuario" value="<%= usuario.getId()%>">

                            <div class="mb-3">
                                <label class="form-label">Nombre</label>
                                <input type="text" name="nombre" class="form-control" value="<%= usuario.getGeneral_usu_nombre()%>"  disabled>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Apellido</label>
                                <input type="text" name="apellido" class="form-control" value="<%= usuario.getGeneral_usu_apellido()%>" disabled>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Correo</label>
                                <input type="email" name="correo" class="form-control" value="<%= usuario.getGeneral_usu_correo_electronico()%>" disabled>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Celular</label>
                                <input type="text" name="celular" class="form-control" value="<%= usuario.getGeneral_usu_num_celular()%>" disabled>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Número de Identificación</label>
                                <input type="text" name="numeroIdentificacion" class="form-control" value="<%= usuario.getGeneral_usu_num_identificacion()%>" disabled>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Nueva Contraseña (Opcional)</label>
                                <div class="input-group">
                                    <input type="password" id="clave" name="clave" class="form-control">
                                    <button class="btn btn-outline-secondary toggle-password" type="button" data-target="clave">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Confirmar Contraseña</label>
                                <div class="input-group">
                                    <input type="password" id="confirmarClave" class="form-control">
                                    <button class="btn btn-outline-secondary toggle-password" type="button" data-target="confirmarClave">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                </div>
                                <small id="errorClave" class="text-danger d-none">Tus contraseñas son diferentes</small>
                            </div>
                            <input type="hidden" name="origen" value="perfil">

                            <button type="submit" class="btn btn-primary">Guardar cambios</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
     
<script>
document.addEventListener("DOMContentLoaded", function () {
    // Modo oscuro desde localStorage
    if (localStorage.getItem("theme") === "dark") {
        document.documentElement.classList.add("dark-mode");
    }

    // Botón de cambio de tema
    const toggleThemeBtn = document.getElementById("toggleTheme");
    if (toggleThemeBtn) {
        toggleThemeBtn.addEventListener("click", function () {
            document.documentElement.classList.toggle("dark-mode");
            const newTheme = document.documentElement.classList.contains("dark-mode") ? "dark" : "light";
            localStorage.setItem("theme", newTheme);
        });
    }

    // Inicializar Bootstrap Dropdowns
    function initializeDropdowns() {
        const dropdownElementList = [].slice.call(document.querySelectorAll('.dropdown-toggle'));
        dropdownElementList.forEach(function (dropdownToggleEl) {
            new bootstrap.Dropdown(dropdownToggleEl);
        });
    }
    initializeDropdowns();

    // Prevenir cierre de dropdown personalizado
    const dropdownMenu = document.querySelector('.custom-menu');
    if (dropdownMenu) {
        dropdownMenu.addEventListener('click', function (e) {
            e.stopPropagation();
        });
    }

    // Autocompletar menú personalizado con jQuery
    const $inputBuscar = $('.custom-menu input[type="text"]');
    $inputBuscar.on('keyup', function () {
        const filtro = $(this).val().toLowerCase();
        $('.custom-menu ul').children().each(function () {
            const texto = $(this).text().toLowerCase();
            $(this).toggle(texto.includes(filtro));
        });
    });

    // Submenús con toggle
    document.querySelectorAll('.toggle-menu').forEach(function (element) {
        element.addEventListener('click', function (e) {
            e.preventDefault();
            const submenu = this.nextElementSibling;
            if (submenu && submenu.classList.contains('submenu')) {
                submenu.classList.toggle('show');
            }
        });
    });

    // Validación de clave en perfil
    const clave = document.getElementById("clave");
    const confirmarClave = document.getElementById("confirmarClave");
    const errorClave = document.getElementById("errorClave");
    const form = document.getElementById("perfilForm");

    if (clave && confirmarClave && errorClave && form) {
        confirmarClave.addEventListener("input", function () {
            if (clave.value !== confirmarClave.value) {
                errorClave.classList.remove("d-none");
            } else {
                errorClave.classList.add("d-none");
            }
        });

        form.addEventListener("submit", function (event) {
            if (clave.value !== confirmarClave.value) {
                errorClave.classList.remove("d-none");
                event.preventDefault();
            }
        });
    }

    // Toggle de visibilidad de contraseña
    document.querySelectorAll(".toggle-password").forEach(button => {
        button.addEventListener("click", function () {
            const targetId = this.getAttribute("data-target");
            const input = document.getElementById(targetId);
            if (input) {
                if (input.type === "password") {
                    input.type = "text";
                    this.innerHTML = '<i class="fas fa-eye-slash"></i>';
                } else {
                    input.type = "password";
                    this.innerHTML = '<i class="fas fa-eye"></i>';
                }
            }
        });
    });
});
</script>

    </body>
</html> 




