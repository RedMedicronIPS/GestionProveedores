<%-- 
    Document   : terceros
    Created on : 7/04/2025, 01:23:28 PM
    Author     : IPS OBRERO
--%>

<%@page import="clases.ConfiguracionGeneral.GeneralCiudad"%>
<%@page import="clases.ConfiguracionGeneral.GeneralCIIU"%>
<%@page import="clases.ConfiguracionGeneral.GeneralDepartamento"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="clases.ConfiguracionGeneral.GeneralPais"%>
<%@page import="clases.ConfiguracionGeneral.GeneralTipoIdentificacion"%>
<%@page import="java.util.List"%>
<%@page import="clases.ConfiguracionGeneral.GeneralTercero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>    
        <link rel="stylesheet" href="estilos/estiloCard.css">  
    </head>

    <%

        String paisesJs = GeneralPais.getListaEnArregloJs();
        String ciuJs = GeneralCiudad.getListaEnArregloJs();
        String deptoJs = GeneralDepartamento.getListaEnArregloJs();
        String pais = request.getParameter("pais");
        if (pais != null && !pais.isEmpty()) {
            out.print(GeneralDepartamento.getDepartamentosPorPais(pais));
            return;
        }

        String departamento = request.getParameter("departamento");
        if (departamento != null && !departamento.isEmpty()) {
            out.print(clases.ConfiguracionGeneral.GeneralCiudad.getCiudadesPorDepartamentoNombre(departamento));
            return;
        }
        String accion = request.getParameter("accion");
        String id = request.getParameter("id");
        GeneralTercero tercero = new GeneralTercero();

        if ("Actualizar".equals(accion) && id != null && !id.trim().isEmpty() && id.matches("\\d+")) {
            tercero = new GeneralTercero(id);
        } else {
            tercero = new GeneralTercero();
        }

        String lista = "";
        List<GeneralTercero> data = GeneralTercero.listInObjects("", "");
        for (GeneralTercero tro : data) {
            lista += "<tr>";
            lista += "<td>" + tro.getId() + "</td>";
            lista += "<td>" + tro.getTercero_codigo() + "</td>";
            lista += "<td>" + tro.getTercero_id_tipo_identificacion() + "</td>";
            lista += "<td>" + tro.getTercero_razon_nombres() + "</td>";
            lista += "<td>" + tro.getTercero_fecha_nacimiento() + "</td>"; // Cambiado aquí
            lista += "<td>" + tro.getTercero_direccion() + "</td>"; // Cambiado aquí
            lista += "<td>" + tro.getTercero_telefono() + "</td>"; // Cambiado aquí
            lista += "<td>" + tro.getTercero_correo() + "</td>"; // Cambiado aquí
            lista += "<td>" + tro.getTercero_pais() + "</td>";
            lista += "<td>" + tro.getTercero_departamento() + "</td>";
            lista += "<td>" + tro.getTercero_ciudad() + "</td>";
            lista += "<td>" + tro.getTercero_ciiu() + "</td>";
            lista += "<td>" + tro.getTerceroEmpleado() + "</td>";
            lista += "<td>" + tro.getTerceroProveedor() + "</td>";
            lista += "<td>" + tro.getTerceroAccionistaAsociado() + "</td>";
            lista += "<td>" + tro.getTercero_tipo() + "</td>"; // Cambiado aquí
            lista += "<td>" + tro.getTerceroFacturar() + "</td>";
            lista += "<td><a href='main.jsp?CONTENIDO=GUI/Terceros/terceros.jsp&accion=Actualizar&id=" + tro.getId() + "' title='Modificar'><img src='recursos/update.png' class='icon'></a><a href='javascript:eliminar(" + tro.getId() + ")' title='Eliminar'><img src='recursos/delete.png' class='icon'></a></td>";

            lista += "</tr>";
        }


    %>

    <body>
        <div class="container-wrapper">
            <div class="row g-3">
                <div class="col-12">
                    <div class="card border-0 rounded-4 bg-light dark:bg-dark text-dark dark:text-white" style="max-width: 100%;">
                        <!-- Encabezado centrado -->
                        <div class="card-header bg-white border-bottom d-flex justify-content-center align-items-center py-3 px-4">
                            <h6 class="mb-0 text-dark fw-semibold text-uppercase" style="letter-spacing: 1px;">
                                <%= accion != null ? accion.toUpperCase() : ""%> TERCEROS
                            </h6>
                        </div>

                        <div class="card-body px-6 py-4" style="width: 100%; max-width: none;">
                            <form id="form" method="post" action="${pageContext.request.contextPath}/GUI/Terceros/tercerosActualizar.jsp" onsubmit="return validarFormulario()" autocomplete="off">
                                <input type="hidden" name="id" value="<%= tercero.getId() != null ? tercero.getId() : ""%>">
                                <input type="hidden" name="accion" value="<%= accion != null && accion.equals("Actualizar") ? "Actualizar" : "Create"%>">

                                <div class="row">
                                    <!-- Columna izquierda (Formulario) -->
                                    <div class="col-md-6 border-end pe-3">
                                        <div class="row g-3 align-items-end">
                                            <div class="col-sm-2">
                                                <label class="form-label small text-secondary">ID</label>
                                                <input type="text" class="form-control form-control-sm border-0 shadow-sm" value="<%= (tercero.getId() == null || tercero.getId().isEmpty()) ? "" : tercero.getId()%>" disabled>
                                            </div>
                                            <div class="col-sm-3">
                                                <label class="form-label small text-secondary">Código (NIT/CC...): <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control form-control-sm border-0 shadow-sm" name="tercero_codigo" value="<%=tercero.getTercero_codigo()%>" maxlength="18" required>
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="form-label small text-secondary">Tipo Documento:</label>
                                                <select class="form-control form-control-sm border-0 shadow-sm" name="tercero_id_tipo_identificacion">
                                                    <%= GeneralTipoIdentificacion.getListaEnOption(tercero.getTercero_id_tipo_identificacion())%>
                                                </select>
                                            </div>  
                                            <div class="col-sm-3">
                                                <label class="form-label small text-secondary">Fecha de Nacimiento: <span class="text-danger">*</span></label>
                                                <input type="date" class="form-control form-control-sm border-0 shadow-sm" name="tercero_fecha_nacimiento" value="<%= tercero.getTercero_fecha_nacimiento()%>" required>
                                            </div>
                                            <div class="col-sm-5">
                                                <label class="form-label small text-secondary">Razón Social o Nombres: <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control form-control-sm border-0 shadow-sm" name="tercero_razon_nombres" value="<%=tercero.getTercero_razon_nombres()%>" required>
                                            </div>
                                            <div class="col-sm-5">
                                                <label class="form-label small text-secondary">Correo electrónico: <span class="text-danger">*</span></label>
                                                <input type="email" class="form-control form-control-sm border-0 shadow-sm" name="tercero_correo" value="<%=tercero.getTercero_correo()%>" required>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="form-label small text-secondary">Teléfono: <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control form-control-sm border-0 shadow-sm" name="tercero_telefono" value="<%=tercero.getTercero_telefono()%>" maxlength="15" required>
                                            </div>
                                            <div class="col-md-4">
                                                <label class="form-label small text-secondary">Dirección: <span class="text-danger">*</span></label>
                                                <input type="text" class="form-control form-control-sm border-0 shadow-sm" name="tercero_direccion" value="<%=tercero.getTercero_direccion()%>" required>
                                            </div>
                                            <div class="col-md-2">
                                                <label class="form-label small text-secondary">País:</label>
                                                <input type="text" id="paisInput" name="tercero_pais" class="form-control form-control-sm border-0 shadow-sm" placeholder="Escribe un país" autocomplete="off" value="<%=tercero.getTercero_pais()%>">
                                            </div>

                                            <div class="col-md-3">
                                                <label class="form-label small text-secondary">Departamento: </label>
                                                <input type="text" id="deptoInput" name="tercero_departamento" class="form-control form-control-sm border-0 shadow-sm" placeholder="Escribe un depto" autocomplete="off" value="<%=tercero.getTercero_departamento()%>">
                                            </div>
                                            <div class="col-md-3">
                                                <label class="form-label small text-secondary">Ciudad: </label>
                                                <input type="text" id="ciuInput" name="tercero_ciudad" class="form-control form-control-sm border-0 shadow-sm" placeholder="Escribe una ciudad" autocomplete="off" value="<%=tercero.getTercero_ciudad()%>">
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Columna derecha -->
                                    <div class="col-md-6 ps-3">
                                        <div class="row g-3">                                                                            
                                            <div class="col-md-12">
                                                <label class="form-label small text-secondary">CIIU:</label>
                                                <select class="form-control form-control-sm border-0 shadow-sm" name="tercero_ciiu">
                                                    <%= GeneralCIIU.getListaEnOption(tercero.getTercero_ciiu())%>
                                                </select>
                                            </div>

                                            <!-- Roles -->
                                            <div class="col-md-12">
                                                <div class="row g-2">
                                                    <div class="col-md-4">
                                                        <label class="form-label small text-secondary">Empleado:</label>
                                                        <div class="d-flex">                                                            
                                                            <div class="form-check me-3">
                                                                <input class="form-check-input" type="radio" name="tercero_empleado" 
                                                                       value="Sí" <%= tercero.isCheckedEmpleado("Sí")%>>
                                                                <label class="form-check-label">Sí</label>
                                                            </div>
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio" name="tercero_empleado" 
                                                                       value="No" <%= tercero.isCheckedEmpleado("No")%>>
                                                                <label class="form-check-label">No</label>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-4">
                                                        <label class="form-label small text-secondary">Proveedor:</label>
                                                        <div class="d-flex">
                                                            <div class="form-check me-3">
                                                                <input class="form-check-input" type="radio" name="tercero_proveedor" 
                                                                       value="Sí" <%= tercero.isCheckedProveedor("Sí")%>>
                                                                <label class="form-check-label">Sí</label>
                                                            </div>
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio" name="tercero_proveedor" 
                                                                       value="No" <%= tercero.isCheckedProveedor("No")%>>
                                                                <label class="form-check-label">No</label>
                                                            </div>

                                                        </div>
                                                    </div>

                                                    <div class="col-md-4">
                                                        <label class="form-label small text-secondary">Accionista/Asociado:</label>
                                                        <div class="d-flex">
                                                            <div class="form-check me-3">
                                                                <input class="form-check-input" type="radio" name="tercero_accionista_asociado" 
                                                                       value="Sí" <%= tercero.isCheckedAccionista("Sí")%>>
                                                                <label class="form-check-label">Sí</label>
                                                            </div>
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio" name="tercero_accionista_asociado" 
                                                                       value="No" <%= tercero.isCheckedAccionista("No")%>>
                                                                <label class="form-check-label">No</label>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-12">
                                                <div class="row g-2">
                                                    <div class="col-md-6">
                                                        <label class="form-label small text-secondary">Tipo de Tercero:</label>
                                                        <div class="d-flex">
                                                            <div class="form-check me-3">
                                                                <input class="form-check-input" type="radio" name="tercero_tipo" value="Persona Natural" <%= tercero.isCheckedTT("Persona Natural")%>>
                                                                <label class="form-check-label">Persona Natural</label>
                                                            </div>
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio" name="tercero_tipo" value="Persona Jurídica" <%= tercero.isCheckedTT("Persona Jurídica")%>>
                                                                <label class="form-check-label">Persona Jurídica</label>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6">
                                                        <label class="form-label small text-secondary">¿Facturar?:</label>
                                                        <div class="d-flex">
                                                            <div class="form-check me-3">
                                                                <input class="form-check-input" type="radio" name="tercero_facturar" 
                                                                       value="Sí" <%= tercero.isCheckedFacturar("Sí")%>>
                                                                <label class="form-check-label">Sí</label>
                                                            </div>
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio" name="tercero_facturar" 
                                                                       value="No" <%= tercero.isCheckedFacturar("No")%>>
                                                                <label class="form-check-label">No</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-footer bg-white border-top py-3 px-4 text-end">
                                    <button type="submit" class="btn btn-sm px-4 fw-semibold rounded-pill">
                                        <%= accion != null && accion.equals("Actualizar") ? "Actualizar Tercero" : "Agregar Tercero"%>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <p>
    <center>
        <div class="container-wrapper mt-4">
            <div class="container-card">               
                <h6 class="mb-0 fw-bold">LISTA DE TERCEROS</h6> 

                <div class="table-responsive">
                    <table id="tablaterceros" class="table table-striped table-bordered w-100">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Código</th>
                                <th>Tipo Doc</th>
                                <th>Nombre/Razón</th>
                                <th>Teléfono</th>
                                <th>Correo</th>
                                <th>Ciudad</th>

                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<GeneralTercero> datas = GeneralTercero.listInObjects("", "");
                                for (GeneralTercero tro : datas) {
                                   /* if (!Boolean.TRUE.equals(tro.getTercero_estado())) {
                                        continue; // Saltar si no está activo
                                    }*/

                                    String modalId = "modalTercero" + tro.getId();

                                    out.print("<tr>");
                                    out.print("<td>" + tro.getId() + "</td>");
                                    out.print("<td>" + tro.getTercero_codigo() + "</td>");
                                    out.print("<td>" + tro.getTercero_id_tipo_identificacion() + "</td>");
                                    out.print("<td>" + tro.getTercero_razon_nombres() + "</td>");
                                    out.print("<td>" + tro.getTercero_telefono() + "</td>");
                                    out.print("<td>" + tro.getTercero_correo() + "</td>");
                                    out.print("<td>" + tro.getTercero_ciudad() + "</td>");

                                    out.print("<td><button class='btn btn-sm btn-primary' data-bs-toggle='modal' data-bs-target='#" + modalId
                                            + "'>Ver registro</button> <a href='main.jsp?CONTENIDO=GUI/Terceros/terceros.jsp&accion=Actualizar&id=" + tro.getId()
                                            + "' title='Modificar'><img src='recursos/update.png' class='icon'></a> <a href='javascript:eliminar(" + tro.getId()
                                            + ")' title='Eliminar'><img src='recursos/delete.png' class='icon'></a></td>");
                                    out.print("</tr>");

                                    out.print("<div class='modal fade' id='" + modalId + "' tabindex='-1'>");
                                    out.print("<div class='modal-dialog modal-lg'>");
                                    out.print("<div class='modal-content'>");

                                    out.print("<div class='modal-header'><h5 class='modal-title'>Detalle del Tercero</h5>");
                                    out.print("<button type='button' class='btn-close' data-bs-dismiss='modal'></button></div>");

                                    out.print("<div class='modal-body'><div class='row'>");

                                    out.print("<div class='col-md-6'>");
                                    out.print("<p><strong>ID:</strong> " + tro.getId() + "</p>");
                                    out.print("<p><strong>Código:</strong> " + tro.getTercero_codigo() + "</p>");
                                    out.print("<p><strong>Tipo Identificación:</strong> " + tro.getTercero_id_tipo_identificacion() + "</p>");
                                    out.print("<p><strong>Razón/Nombres:</strong> " + tro.getTercero_razon_nombres() + "</p>");
                                    out.print("<p><strong>Fecha Nacimiento:</strong> " + tro.getTercero_fecha_nacimiento() + "</p>");
                                    out.print("<p><strong>Dirección:</strong> " + tro.getTercero_direccion() + "</p>");
                                    out.print("<p><strong>Teléfono:</strong> " + tro.getTercero_telefono() + "</p>");
                                    out.print("<p><strong>Correo:</strong> " + tro.getTercero_correo() + "</p>");
                                    out.print("<p><strong>País:</strong> " + tro.getTercero_pais() + "</p>");
                                    out.print("</div>");

                                    out.print("<div class='col-md-6'>");
                                    out.print("<p><strong>Departamento:</strong> " + tro.getTercero_departamento() + "</p>");
                                    out.print("<p><strong>Ciudad:</strong> " + tro.getTercero_ciudad() + "</p>");
                                    out.print("<p><strong>CIIU:</strong> " + tro.getTercero_ciiu() + "</p>");
                                    out.print("<p><strong>Empleado:</strong> " + tro.getTerceroEmpleado() + "</p>");
                                    out.print("<p><strong>Proveedor:</strong> " + tro.getTerceroProveedor() + "</p>");
                                    out.print("<p><strong>Accionista/Asociado:</strong> " + tro.getTerceroAccionistaAsociado() + "</p>");
                                    out.print("<p><strong>Tipo:</strong> " + tro.getTercero_tipo() + "</p>");
                                    out.print("<p><strong>Facturar:</strong> " + tro.getTerceroFacturar() + "</p>");
                                    out.print("<p><strong>Estado:</strong> " + tro.getTercero_estado() + "</p>");
                                    out.print("</div>");

                                    out.print("</div></div>");
                                    out.print("<div class='modal-footer'><button class='btn btn-secondary' data-bs-dismiss='modal'>Cerrar</button></div>");
                                    out.print("</div></div></div>");
                                }
                            %>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
    </center>



    <script>
        $(document).ready(function () {
            let alturaDisponible = window.innerHeight - 200;

            $('#tablaterceros').DataTable({
                scrollY: alturaDisponible + 'px',
                scrollCollapse: true,
                scrollX: true,
                "order": [[0, "desc"]],
                "paging": true,
                "searching": true,
                "info": true,
                "lengthMenu": [5, 10, 25, 50, 100],
                "language": {
                    "lengthMenu": "Mostrar MENU registros por página",
                    "zeroRecords": "No se encontraron resultados",
                    "info": "Mostrando página PAGE de PAGES",
                    "infoEmpty": "No hay registros disponibles",
                    "infoFiltered": "(filtrado de MAX registros totales)",
                    "search": "Buscar:",
                    "paginate": {
                        "first": "&#171;",
                        "last": "&#187;",
                        "next": "&#9658;",
                        "previous": "&#9668;"
                    }
                }
            });
        });
        var paises = <%= paisesJs%>;
        console.log(paises);
        $("#paisInput").autocomplete({
            source: paises,
            select: function (event, ui) {
                $("#deptoInput").prop("disabled", false);
                cargarDepartamentos(ui.item.value);
            }
        });

        var deptos = <%= deptoJs%>;
        console.log(deptos);
        $("#deptoInput").autocomplete({
            source: deptos,
            select: function (event, ui) {
                $("#ciuInput").prop("disabled", false);
                cargarCiudades(ui.item.value);
            }
        });

        var ciudades = <%= ciuJs%>;
        console.log(ciudades);
        $("#ciuInput").autocomplete({
            source: ciudades
        });

        $("#deptoInput").prop("disabled", true);
        $("#ciuInput").prop("disabled", true);

        if ("<%= accion%>" === "Actualizar" || "<%= tercero.getTercero_pais()%>" !== "") {
            $("#deptoInput").prop("disabled", false);
        }
        if ("<%= accion%>" === "Actualizar" || "<%= tercero.getTercero_departamento()%>" !== "") {
            $("#ciuInput").prop("disabled", false);
        }
        document.getElementById("selectPais").addEventListener("change", function () {
            const pais = this.value;
            const otroPais = document.getElementById("otroPais");
            const selectDepartamento = document.getElementById("selectDepartamento");
            const otroDepartamento = document.getElementById("otroDepartamento");
            const selectCiudad = document.getElementById("selectCiudad");
            const otroCiudad = document.getElementById("otroCiudad");

            if (pais) {
                $("#deptoInput").prop("disabled", false);
                selectDepartamento.classList.remove("d-none");

                if (pais === "Colombia") {
                    otroPais.classList.add("d-none");
                    selectCiudad.classList.remove("d-none");
                    otroCiudad.classList.add("d-none");
                    cargarDepartamentos(pais);
                } else {
                    otroPais.classList.remove("d-none");
                    selectDepartamento.classList.add("d-none");
                    otroDepartamento.classList.remove("d-none");
                    selectCiudad.classList.add("d-none");
                    otroCiudad.classList.remove("d-none");
                }
            } else {
                $("#deptoInput").prop("disabled", true);
                $("#ciuInput").prop("disabled", true);
                selectDepartamento.classList.add("d-none");
                selectCiudad.classList.add("d-none");
            }
        });

        document.getElementById("selectDepartamento").addEventListener("change", function () {
            const departamento = this.value;
            const otroDepartamento = document.getElementById("otroDepartamento");

            if (departamento === "Otro") {
                otroDepartamento.classList.remove("d-none");
            } else {
                otroDepartamento.classList.add("d-none");
                $("#ciuInput").prop("disabled", false);
                cargarCiudades(departamento);
            }
        });

        function cargarCiudades(departamento) {
            $.getJSON("GUI/General/getCiudades.jsp", {idDepartamento: departamento}, function (data) {
                $("#ciuInput").autocomplete({
                    source: data
                });
            });
        }

        document.getElementById("selectCiudad").addEventListener("change", function () {
            const ciudad = this.value;
            const otroCiudad = document.getElementById("otroCiudad");

            if (ciudad === "Otro") {
                otroCiudad.classList.remove("d-none");
            } else {
                otroCiudad.classList.add("d-none");
            }
        });

        window.addEventListener("DOMContentLoaded", () => {
            cargarDepartamentos();
        });

        function cargarDepartamentos(pais) {
            $.get("GUI/General/getDepartamentos.jsp", {pais: pais}, function (data) {
                let departamentos = '<option value="" disabled selected hidden>Seleccione un departamento</option>' + data;
                departamentos += '<option value="Otro">Otro</option>';
                $("#selectDepartamento").html(departamentos);
            });
        }

        function eliminar(id) {
            const dialog = $('<div>')
                    .html('<div class="dialog-content"><i class="fas fa-exclamation-triangle warning-icon"></i><p>¿Realmente desea eliminar este registro?</p></div>');

            $(dialog).dialog({
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                dialogClass: "confirm-dialog no-title",
                open: function () {

                    $('.ui-dialog-titlebar', this.parentNode).remove();
                },
                buttons: [
                    {
                        text: "Eliminar",
                        class: "btn-eliminar",
                        click: function () {
                            $(this).dialog("close");
                            const loading = $('<div class="loading-overlay"><div class="spinner"></div></div>');
                            $('body').append(loading);
                            window.location.href = 'GUI/Terceros/tercerosActualizar.jsp?accion=Delete&id=' + id +
                                    '&redirect=' + encodeURIComponent('main.jsp?CONTENIDO=GUI/Terceros/terceros.jsp');
                        }
                    },
                    {
                        text: "Cancelar",
                        class: "btn-cancelar",
                        click: function () {
                            $(this).dialog("close");
                        }
                    }
                ]

            });
        }
    </script>
</body>
</html>