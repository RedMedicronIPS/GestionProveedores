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
        <style>

            table.dataTable {
                width: 100% !important;
            }

            table.dataTable th,
            table.dataTable td {
                white-space: nowrap;
            }

            .dataTables_scrollHeadInner, .dataTables_scrollHeadInner table {
                width: 100% !important;
            }

        </style>

    </head>
    <%
        String mensaje = request.getParameter("mensaje");
        String error = request.getParameter("error");
        if ("1".equals(request.getParameter("formData"))) {

        }
    %>
    <script>
        $(document).ready(function () {
        <% if (mensaje != null && !mensaje.isEmpty()) {%>
            mostrarMensaje('<%= mensaje%>', 'success');
        <% } %>
        <% if (error != null && !error.isEmpty()) {%>
            mostrarMensaje('<%= error%>', 'error');
            if ('<%= error%>'.includes('Ya existe un tercero')) {
                $('input[name="tercero_codigo"]').focus();
            }
        <% } %>
        });
    </script>

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

        List<GeneralTercero> data = GeneralTercero.listInObjects("", "");
    %>

    <body>
        <div class="container-wrapper">
            <div class="row g-3">
                <div class="col-12">
                    <div class="card border-0 rounded-4 bg-light dark:bg-dark text-dark dark:text-white" style="max-width: 100%;">                      
                        <div class="card-header bg-white border-bottom d-flex justify-content-center align-items-center py-3 px-4">
                            <h6 class="mb-0 text-dark fw-semibold text-uppercase" style="letter-spacing: 1px;">
                                <%= accion != null ? accion.toUpperCase() : ""%> TERCEROS
                            </h6>
                        </div>

                        <div class="card-body px-6 py-4" style="width: 100%; max-width: none;">
                            <form id="form" method="post" action="${pageContext.request.contextPath}/GUI/Terceros/tercerosActualizar.jsp" onsubmit="return validarFormulario()" autocomplete="off">
                                <input type="hidden" name="id" value="<%= tercero.getId() != null ? tercero.getId() : ""%>">
                                <input type="hidden" name="accion" value="<%= accion != null && accion.equals("Actualizar") ? "Actualizar" : "Create"%>">
                                <input type="hidden" name="tercero_estado" value="true">

                                <div class="row g-3">
                                    <div class="col-sm-1">
                                        <label class="form-label small text-secondary">ID</label>
                                        <input type="text" class="form-control form-control-sm border-0 shadow-sm" value="<%= (tercero.getId() == null || tercero.getId().isEmpty()) ? "" : tercero.getId()%>" disabled>
                                    </div>

                                    <div class="col text-center">
                                        <label class="form-label small text-secondary">Tipo de Tercero:</label>
                                        <div class="d-flex justify-content-center">
                                            <div class="form-check me-2">
                                                <input class="form-check-input" type="radio" name="tercero_tipo" value="Persona Natural" 
                                                       <%= (tercero.getId() == null || "Persona Natural".equals(tercero.getTercero_tipo())) ? "checked" : ""%>>
                                                <label class="form-check-label">Persona Natural</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="tercero_tipo" value="Persona Jurídica" 
                                                       <%= "Persona Jurídica".equals(tercero.getTercero_tipo()) ? "checked" : ""%>>
                                                <label class="form-check-label">Persona Jurídica</label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-sm-1">
                                        <label class="form-label small text-secondary">Código (NIT/CC...): <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control form-control-sm border-0 shadow-sm" name="tercero_codigo" value="<%=tercero.getTercero_codigo()%>" maxlength="18" required>
                                    </div>

                                    <div class="col-sm-2">
                                        <label class="form-label small text-secondary">Tipo Documento:</label>
                                        <select class="form-control form-control-sm model" name="tercero_id_tipo_identificacion">
                                            <option value="">Seleccione un tipo</option>
                                            <%= GeneralTipoIdentificacion.getListaEnOption(tercero.getTercero_id_tipo_identificacion())%>
                                        </select>
                                    </div>
                                    <div class="col-sm-2 persona-natural">
                                        <label class="form-label small text-secondary">Nombres: <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control form-control-sm border-0 shadow-sm" name="tercero_razon_nombres" value="<%=tercero.getTercero_razon_nombres()%>">
                                    </div>

                                    <div class="col-sm-2 persona-natural">
                                        <label class="form-label small text-secondary">Apellidos: <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control form-control-sm border-0 shadow-sm" name="tercero_razon_apellidos" value="<%=tercero.getTercero_razon_apellidos()%>">
                                    </div>

                                    <div class="col-sm-4 persona-juridica">
                                        <label class="form-label small text-secondary">Razón social: <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control form-control-sm border-0 shadow-sm" name="tercero_razon_social" value="<%=tercero.getTercero_razon_social()%>">
                                    </div>
                                    <div class="col-sm-1">
                                        <label class="form-label small text-secondary">Fecha de Nacimiento: </label>
                                        <input type="date" class="form-control form-control-sm border-0 shadow-sm" name="tercero_fecha_nacimiento" value="<%= tercero.getTercero_fecha_nacimiento()%>" >
                                    </div>
                                    <div class="col-sm-1">
                                        <label class="form-label small text-secondary">Teléfono: <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control form-control-sm border-0 shadow-sm" name="tercero_telefono" value="<%=tercero.getTercero_telefono()%>" maxlength="15" required>
                                    </div>

                                    <div class="col-sm-4">
                                        <label class="form-label small text-secondary">Correo electrónico: <span class="text-danger">*</span></label>
                                        <input type="email" class="form-control form-control-sm border-0 shadow-sm" name="tercero_correo" value="<%=tercero.getTercero_correo()%>" required>
                                    </div>
                                    <div class="col-md-3">
                                        <label class="form-label small text-secondary">Dirección: <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control form-control-sm border-0 shadow-sm" name="tercero_direccion" value="<%=tercero.getTercero_direccion()%>" required>
                                    </div>
                                    <div class="col-md-1">
                                        <label class="form-label small text-secondary">País:</label>
                                        <input type="text" id="paisInput" name="tercero_pais" class="form-control form-control-sm border-0 shadow-sm" placeholder="Escribe un país" autocomplete="off" value="<%=tercero.getTercero_pais()%>">
                                    </div>

                                    <div class="col-md-2">
                                        <label class="form-label small text-secondary">Departamento:</label>
                                        <input type="text" id="deptoInput" name="tercero_departamento" class="form-control form-control-sm border-0 shadow-sm" placeholder="Escribe un depto" autocomplete="off" value="<%=tercero.getTercero_departamento()%>">
                                    </div>

                                    <div class="col-md-2">
                                        <label class="form-label small text-secondary">Municipio:</label>
                                        <input type="text" id="ciuInput" name="tercero_ciudad" class="form-control form-control-sm border-0 shadow-sm" placeholder="Escribe una ciudad" autocomplete="off" value="<%=tercero.getTercero_ciudad()%>">
                                    </div>

                                    <div class="col-md-8 mb-3">
                                        <label class="form-label small text-secondary">CIIU:</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control form-control-sm border-0 shadow-sm" 
                                                   id="tercero_ciiu_input" name="tercero_ciiu_display" 
                                                   placeholder="Seleccione la Clasificación Industrial Internacional Uniforme" 
                                                   value="<%= tercero.getCIIU() != null ? tercero.getCIIU() : ""%>" 
                                                   readonly onclick="abrirModalCIIU()">
                                            <input type="hidden" id="tercero_ciiu_id" name="tercero_ciiu" value="<%= tercero.getTercero_ciiu() != null ? tercero.getTercero_ciiu() : ""%>">
                                            <button class="btn btn-outline-secondary btn-sm" type="button" onclick="abrirModalCIIU()">
                                                <i class="fas fa-search"></i>
                                            </button>
                                        </div>
                                    </div>

                                    <div class="col-md-2 pe-1 text-center">
                                        <label class="form-label small text-secondary">Proveedor:</label>
                                        <div class="d-flex justify-content-center">
                                            <div class="form-check me-2">
                                                <input class="form-check-input" type="radio" name="tercero_proveedor" value="Sí" <%= "Actualizar".equals(accion) && "Sí".equals(tercero.getTerceroProveedor()) ? "checked" : ""%>>
                                                <label class="form-check-label">Sí</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="tercero_proveedor" value="No" <%= "Actualizar".equals(accion) && "No".equals(tercero.getTerceroProveedor()) ? "checked" : ""%>>
                                                <label class="form-check-label">No</label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-2 ps-1 text-center">
                                        <label class="form-label small text-secondary">¿Está obligado a facturar?:</label>
                                        <div class="d-flex justify-content-center">
                                            <div class="form-check me-2">
                                                <input class="form-check-input" type="radio" name="tercero_facturar" value="Sí" <%= "Actualizar".equals(accion) && "Sí".equals(tercero.getTerceroFacturar()) ? "checked" : ""%>>
                                                <label class="form-check-label">Sí</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="tercero_facturar" value="No" <%= "Actualizar".equals(accion) && "No".equals(tercero.getTerceroFacturar()) ? "checked" : ""%>>
                                                <label class="form-check-label">No</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-footer border-top py-1 px-4 text-end" style="background-color: #e2f4ff;">
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

                <ul class="nav nav-tabs" id="tercerosTabs" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="naturales-tab" data-bs-toggle="tab" data-bs-target="#naturales" type="button" role="tab">Personas Naturales</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="juridicas-tab" data-bs-toggle="tab" data-bs-target="#juridicas" type="button" role="tab">Personas Jurídicas</button>
                    </li>
                </ul>

                <div class="tab-content" id="tercerosTabsContent">                
                    <div class="tab-pane fade show active" id="naturales" role="tabpanel">
                        <div class="table-responsive">
                            <table id="tablaNaturales" class="table table-striped table-bordered w-100">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Código</th>
                                        <th>Tipo de documento</th>
                                        <th>Nombres</th>
                                        <th>Apellidos</th>
                                        <th>Teléfono</th>
                                        <th>Correo</th>
                                        <th>Ciudad</th>                    
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (GeneralTercero tro : data) {
                                            if ("Persona Natural".equals(tro.getTercero_tipo())) {
                                                String modalId = "modalTercero" + tro.getId();
                                    %>
                                    <tr>
                                        <td><%= tro.getId()%></td>
                                        <td><%= tro.getTercero_codigo()%></td>
                                        <td><%= tro.getNombreTI()%></td>
                                        <td><%= tro.getTercero_razon_nombres()%></td>
                                        <td><%= tro.getTercero_razon_apellidos()%></td>
                                        <td><%= tro.getTercero_telefono()%></td>
                                        <td><%= tro.getTercero_correo()%></td>
                                        <td><%= tro.getTercero_ciudad()%></td>                              
                                        <td>
                                            <button class='btn btn-sm btn-primary' data-bs-toggle='modal' data-bs-target='#<%= modalId%>'>Ver registro</button>
                                            <a href='main.jsp?CONTENIDO=GUI/Terceros/terceros.jsp&accion=Actualizar&id=<%= tro.getId()%>' title='Modificar'><img src='recursos/update.png' class='icon'></a>
                                            <a href='javascript:desactivar(<%= tro.getId()%>)' title='Desactivar'><img src='recursos/desactivar.png' class='icon'></a>
                                        </td>
                                    </tr>
                                    <% }
                                        } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="juridicas" role="tabpanel">
                        <div class="table-responsive">
                            <table id="tablaJuridicas" class="table table-striped table-bordered w-100">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Código</th>
                                        <th>Tipo de documento</th>
                                        <th>Razón social</th>
                                        <th>Teléfono</th>
                                        <th>Correo</th>
                                        <th>Ciudad</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (GeneralTercero tro : data) {
                                            if ("Persona Jurídica".equals(tro.getTercero_tipo())) {
                                                String modalId = "modalTercero" + tro.getId();
                                    %>
                                    <tr>
                                        <td><%= tro.getId()%></td>
                                        <td><%= tro.getTercero_codigo()%></td>
                                        <td><%= tro.getNombreTI()%></td>
                                        <td><%= tro.getTercero_razon_social()%></td>
                                        <td><%= tro.getTercero_telefono()%></td>
                                        <td><%= tro.getTercero_correo()%></td>
                                        <td><%= tro.getTercero_ciudad()%></td>
                                        <td>
                                            <button class='btn btn-sm btn-primary' data-bs-toggle='modal' data-bs-target='#<%= modalId%>'>Ver registro</button>
                                            <a href='main.jsp?CONTENIDO=GUI/Terceros/terceros.jsp&accion=Actualizar&id=<%= tro.getId()%>' title='Modificar'><img src='recursos/update.png' class='icon'></a>
                                            <a href='javascript:desactivar(<%= tro.getId()%>)' title='Desactivar'><img src='recursos/desactivar.png' class='icon'></a>
                                        </td>
                                    </tr>

                                    <% }
                                        } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <% for (GeneralTercero tro : data) {
                        String modalId = "modalTercero" + tro.getId();
                %>
                <div class='modal fade' id='<%= modalId%>' tabindex='-1'>
                    <div class='modal-dialog modal-xl'>
                        <div class='modal-content'>
                            <div class='modal-header'>
                                <h5 class='modal-title w-100 text-center'>Detalles del Tercero</h5>
                                <button type='button' class='btn-close' data-bs-dismiss='modal'></button>
                            </div>
                            <div class='modal-body'>
                                <div class='table-responsive'>
                                    <table class='table tabla-detalle-tercero'>
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Código</th>
                                                <th>Tipo de documento</th>
                                                    <% if ("Persona Natural".equals(tro.getTercero_tipo())) { %>
                                                <th>Nombres</th>
                                                <th>Apellidos</th>
                                                    <% } else { %>
                                                <th colspan="2">Razón social</th>
                                                    <% }%>
                                                <th>Fecha de nacimiento</th>
                                                <th colspan="2">Dirección</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><%= tro.getId()%></td>
                                                <td><%= tro.getTercero_codigo()%></td>
                                                <td><%= tro.getNombreTI()%></td>
                                                <% if ("Persona Natural".equals(tro.getTercero_tipo())) {%>
                                                <td><%= tro.getTercero_razon_nombres()%></td>
                                                <td><%= tro.getTercero_razon_apellidos()%></td>
                                                <% } else {%>
                                                <td colspan="2"><%= tro.getTercero_razon_social()%></td>
                                                <% }%>
                                                <td><%= tro.getTercero_fecha_nacimiento()%></td>
                                                <td colspan="2"><%= tro.getTercero_direccion()%></td>
                                            </tr>

                                            <tr>
                                                <th>Teléfono</th>
                                                <th>Correo</th>
                                                <th>País</th>
                                                <th>Departamento</th>
                                                <th>Ciudad</th>
                                                <th>CIIU</th>
                                                <th>¿Proveedor?</th>
                                                <th>Tipo persona</th>
                                            </tr>
                                            <tr>
                                                <td><%= tro.getTercero_telefono()%></td>
                                                <td><%= tro.getTercero_correo()%></td>
                                                <td><%= tro.getTercero_pais()%></td>
                                                <td><%= tro.getTercero_departamento()%></td>
                                                <td><%= tro.getTercero_ciudad()%></td>
                                                <td><%= tro.getCIIU()%></td>
                                                <td><%= tro.getTerceroProveedor()%></td>
                                                <td><%= tro.getTercero_tipo()%></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class='modal-footer'>
                                <button class='btn btn-secondary' data-bs-dismiss='modal'>Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    </center>

    <div class="modal fade" id="modalCIIU" tabindex="-1" aria-labelledby="modalCIIULabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalCIIULabel">Seleccionar CIIU</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <input type="text" id="buscarCIIU" class="form-control form-control-sm" placeholder="Buscar por código o nombre...">
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table id="tablaCIIU" class="table table-sm table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Seleccionar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                List<GeneralCIIU> listaCIIU = GeneralCIIU.listInObjects(null, "codigo, nombre");
                                for (GeneralCIIU ciiu : listaCIIU) {%>
                                <tr>
                                    <td><%= ciiu.getCodigo()%></td>
                                    <td><%= ciiu.getNombre()%></td>
                                    <td>
                                        <button type="button" class="btn btn-sm btn-primary" 
                                                onclick="seleccionarCIIU('<%= ciiu.getId()%>', '<%= ciiu.getCodigo()%> - <%= ciiu.getNombre()%>')">
                                            Seleccionar
                                        </button>
                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $.fn.dataTable.ext.errMode = 'none';
            let alturaDisponible = window.innerHeight - 200;

            $('#tablaNaturales').DataTable({
                scrollY: alturaDisponible + 'px',
                scrollCollapse: true,
                scrollX: true,
                autoWidth: false,
                columnDefs: [
                    {width: "60px", targets: 0},
                    {width: "120px", targets: 1},
                    {width: "150px", targets: 2},
                    {width: "200px", targets: 3},
                    {width: "120px", targets: 4},
                    {width: "220px", targets: 5},
                    {width: "120px", targets: 6},
                    {width: "150px", targets: 7}
                ],
                order: [[0, "desc"]],
                paging: true,
                searching: true,
                info: true,
                lengthMenu: [5, 10, 25, 50, 100],
                language: {
                    lengthMenu: "Mostrar _MENU_ registros por página",
                    zeroRecords: "No se encontraron resultados",
                    info: "Mostrando página _PAGE_ de _PAGES_",
                    infoEmpty: "No hay registros disponibles",
                    infoFiltered: "(filtrado de _MAX_ registros totales)",
                    search: "Buscar:",
                    paginate: {
                        first: "&#171;",
                        last: "&#187;",
                        next: "&#9658;",
                        previous: "&#9668;"
                    }
                }
            });

            $('#tablaJuridicas').DataTable({
                scrollY: alturaDisponible + 'px',
                scrollCollapse: true,
                scrollX: true,
                autoWidth: false,
                columnDefs: [
                    {width: "60px", targets: 0},
                    {width: "120px", targets: 1},
                    {width: "150px", targets: 2},
                    {width: "200px", targets: 3},
                    {width: "120px", targets: 4},
                    {width: "220px", targets: 5},
                    {width: "120px", targets: 6},
                    {width: "150px", targets: 7}
                ],
                order: [[0, "desc"]],
                paging: true,
                searching: true,
                info: true,
                lengthMenu: [5, 10, 25, 50, 100],
                language: {
                    lengthMenu: "Mostrar _MENU_ registros por página",
                    zeroRecords: "No se encontraron resultados",
                    info: "Mostrando página _PAGE_ de _PAGES_",
                    infoEmpty: "No hay registros disponibles",
                    infoFiltered: "(filtrado de _MAX_ registros totales)",
                    search: "Buscar:",
                    paginate: {
                        first: "&#171;",
                        last: "&#187;",
                        next: "&#9658;",
                        previous: "&#9668;"
                    }
                }

            });

            $('.dataTables_wrapper').on('error.dt', function (e, settings, techNote, message) {
                console.error('Error en DataTable:', message);
                window.location.reload();
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

        function desactivar(id) {
            const dialog = $('<div>')
                    .html('<div class="dialog-content"><i class="fas fa-exclamation-triangle warning-icon"></i><p>¿Realmente desea desactivar este tercero?</p></div>');
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
                        text: "Desactivar",
                        class: "btn-warning",
                        click: function () {
                            $(this).dialog("close");
                            const loading = $('<div class="loading-overlay"><div class="spinner"></div></div>');
                            $('body').append(loading);

                            window.location.href = 'GUI/Terceros/tercerosActualizar.jsp?accion=Desactivar&id=' + id;
                        }
                    },
                    {
                        text: "Cancelar",
                        class: "btn-secondary",
                        click: function () {
                            $(this).dialog("close");
                        }
                    }
                ]
            });
        }

        function mostrarMensaje(texto, tipo) {
            const alertType = tipo === 'success' ? 'alert-success' : 'alert-danger';
            const alert = $('<div class="alert ' + alertType + ' alert-dismissible fade show fixed-top mx-auto mt-3" style="max-width: 500px; z-index: 9999;">'
                    + texto + '<button type="button" class="btn-close" data-bs-dismiss="alert"></button></div>');

            $('body').append(alert);
            setTimeout(() => alert.alert('close'), 5000);
        }

        function abrirModalCIIU() {
            $('#modalCIIU').modal('show');
            if (!$.fn.DataTable.isDataTable('#tablaCIIU')) {
                $('#tablaCIIU').DataTable({
                    paging: true,
                    pageLength: 10,
                    lengthMenu: [5, 10, 25, 50],
                    language: {
                        lengthMenu: "Mostrar _MENU_ registros por página",
                        zeroRecords: "No se encontraron resultados",
                        info: "Mostrando página _PAGE_ de _PAGES_",
                        infoEmpty: "No hay registros disponibles",
                        infoFiltered: "(filtrado de _MAX_ registros totales)",
                        search: "Buscar:",
                        paginate: {
                            first: "Primero",
                            last: "Último",
                            next: "Siguiente",
                            previous: "Anterior"
                        }
                    }
                });
            }

            $('#buscarCIIU').keyup(function () {
                $('#tablaCIIU').DataTable().search($(this).val()).draw();
            });
        }

        function seleccionarCIIU(id, texto) {
            $('#tercero_ciiu_id').val(id);
            $('#tercero_ciiu_input').val(texto);
            $('#modalCIIU').modal('hide');
        }

        function inicializarCIIU() {
            const ciiuId = '<%= tercero.getTercero_ciiu()%>';
            if (ciiuId) {
                if (!$('#tercero_ciiu_input').val()) {
                    $.get('${pageContext.request.contextPath}/GUI/Terceros/getCIIU.jsp', {id: ciiuId}, function (data) {
                        if (data) {
                            $('#tercero_ciiu_input').val(data.codigo + ' - ' + data.nombre);
                        }
                    });
                }
            }
        }

        $(document).ready(function () {
            inicializarCIIU();
        });
    </script>
    <script>
        $(document).ready(function () {
            function manejarTipoPersona() {
                const tipo = $('input[name="tercero_tipo"]:checked').val();

                if (tipo === 'Persona Natural') {
                    $('.persona-natural').show();
                    $('.persona-juridica').hide();
                    $('[name="tercero_razon_nombres"], [name="tercero_razon_apellidos"]').prop('required', true);
                    $('[name="tercero_razon_social"]').prop('required', false);
                } else if (tipo === 'Persona Jurídica') {
                    $('.persona-natural').hide();
                    $('.persona-juridica').show();
                    $('[name="tercero_razon_nombres"], [name="tercero_razon_apellidos"]').prop('required', false);
                    $('[name="tercero_razon_social"]').prop('required', true);
                }
            }
            $('.persona-natural').show();
            $('.persona-juridica').hide();

            manejarTipoPersona();
            $('input[name="tercero_tipo"]').on('change', manejarTipoPersona);
        });
        function actualizarColumnas() {
            const tipo = $('input[name="tercero_tipo"]:checked').val();
            if (tipo === 'Persona Natural') {
                $('.col-nombres, .col-apellidos').show();
                $('.col-razon').hide();
            } else {
                $('.col-nombres, .col-apellidos').hide();
                $('.col-razon').show();
            }
        }

        $(document).ready(function () {
            actualizarColumnas();
            $('input[name="tercero_tipo"]').change(actualizarColumnas);
        });
        $(document).ready(function () {
            if ($('input[name="tercero_tipo"]:checked').length === 0) {
                $('input[name="tercero_tipo"][value="Persona Natural"]').prop('checked', true);
            }
            manejarTipoPersona();
        });

    </script>
</body>
</html>