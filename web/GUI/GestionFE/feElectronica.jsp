<%-- 
    Document   : feElectronica
    Created on : 9/05/2025, 12:15:41 PM
    Author     : IPS OBRERO
--%>

<%@page import="clases.FacturacionElectronica.FacturacionElectronica"%>
<%@page import="clases.FacturacionElectronica.FacturacionElectronica"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="estilos/estiloCard.css">  

    </head>
    <body>
        <div class="container-wrapper">
            <div class="row g-3">
                <div class="col-12">
                    <div class="card border-0 rounded-4 bg-light dark:bg-dark text-dark dark:text-white" style="max-width: 100%;">
                        <div class="card-header bg-white border-bottom d-flex justify-content-center align-items-center py-3 px-4">
                            <h6 class="mb-0 text-dark fw-semibold text-uppercase fw-bold" style="letter-spacing: 1px;">
                                Facturación Electrónica
                            </h6>
                        </div>
                        <div class="card-body d-flex justify-content gap-2 px-4">
                            <button id="btnImprimir" class="btn btn-sm btn-primary">Imprimir seleccionados</button>
                            <button id="btnExportar" class="btn btn-sm btn-success">Exportar seleccionados</button>
                            <button class="btn btn-sm btn-danger" title="Revisar facturas pendientes por correo" onclick='enviarCorreoFactura(facturaId, "correo@dominio.com")'>
                                <img src="https://ssl.gstatic.com/ui/v1/icons/mail/rfr/gmail.ico" alt="Gmail" width="18px">
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <center>
        <div class="container-wrapper mt-4">
            <div class="container-card">               
                <h6 class="mb-0 ">LISTA DE FACTURAS ELECTRONICAS</h6> 
                <table class="table table-striped table-bordered w-100">
                    <thead class="table">
                        <tr>
                            <th><input type="checkbox" id="selectAll"></th> 
                            <th>ID</th>
                            <th>Centro de operaciones</th>
                            <th>Etapa</th>
                            <th>Fecha factura</th>
                            <th>Estado</th>
                            <th>Factura autorizada</th>
                            <th>Concepto</th>
                            <th>Razon social proveedor</th>
                            <th>Razon social adquiriente</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<FacturacionElectronica> facturas = FacturacionElectronica.listInObjects(null, null);
                            for (FacturacionElectronica factura : facturas) {
                        %>
                        <tr>
                            <td>
                                <input type="checkbox" class="fila-checkbox" value="<%= factura.getId()%>">
                            </td>
                            <td><%= factura.getId()%></td>
                            <td><%= factura.getDeptoCentroOperaciones()%></td>
                            <td><%= factura.getEtapa()%></td>
                            <td><%= factura.getFechaFactura()%></td>
                            <td><%= factura.getEstadoFactura()%></td>
                            <td><%= factura.getNumFacturaAutorizada()%></td>                                                 
                            <td><%= factura.getConcepto()%></td>                           
                            <td><%= factura.getRazonSocialProveedor()%></td>                           
                            <td><%= factura.getRazonSocialAdquiriente()%></td>                           
                            <td>                                
                                <a href='main.jsp?CONTENIDO=GUI/GestionFE/feElectronica.jsp&accion=Actualizar&id=<%= factura.getId()%>' title='Modificar'><img src='recursos/update.png' class='icon'></a>
                                <a href='javascript:desactivar(<%= factura.getId()%>)' title='Desactivar'><img src='recursos/desactivar.png' class='icon'></a>
                            </td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>

            </div>                  
        </div>

    </center>
    <script>
        $(document).ready(function () {
            $.fn.dataTable.ext.errMode = 'none';

            const alturaDisponible = window.innerHeight - 200;

            $('table').DataTable({
                scrollY: alturaDisponible + 'px',
                scrollCollapse: true,
                scrollX: true,
                autoWidth: false,
                columnDefs: [
                    {width: "60px", targets: 0},
                    {width: "150px", targets: 1},
                    {width: "100px", targets: 2},
                    {width: "120px", targets: 3},
                    {width: "100px", targets: 4},
                    {width: "150px", targets: 5},
                    {width: "150px", targets: 6},
                    {width: "200px", targets: 7},
                    {width: "200px", targets: 8},
                    {width: "120px", targets: 9}
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

        document.addEventListener("DOMContentLoaded", function () {
            const selectAll = document.getElementById("selectAll");
            const checkboxes = document.querySelectorAll(".fila-checkbox");

            selectAll.addEventListener("change", function () {
                checkboxes.forEach(cb => cb.checked = selectAll.checked);
            });
        });
    </script>
    <div id="printArea" style="display:none;"></div>

    <script>
        document.getElementById("btnImprimir").addEventListener("click", function () {
            const seleccionados = Array.from(document.querySelectorAll(".fila-checkbox:checked"));
            if (seleccionados.length === 0) {
                alert("Seleccione al menos una factura.");
                return;
            }

            const cabecera = document.querySelector("table thead").outerHTML;
            const filasSeleccionadas = seleccionados.map(cb => cb.closest("tr").outerHTML).join("");

            const tablaHTML = `
        <table border="1" cellspacing="0" cellpadding="5" style="border-collapse: collapse; width: 100%;">
        ${cabecera}
          <tbody>${filasSeleccionadas}</tbody>
        </table>
      `;

            const printArea = document.getElementById("printArea");
            printArea.innerHTML = tablaHTML;
            printArea.style.display = "block";

            window.print();

            printArea.style.display = "none";
        });
    </script>

</body>
</html>
