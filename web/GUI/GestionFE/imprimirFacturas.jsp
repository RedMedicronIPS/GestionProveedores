<%-- 
    Document   : imprimirFacturas
    Created on : 22/05/2025, 03:08:00 PM
    Author     : IPS OBRERO
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="clases.FacturacionElectronica.FacturacionElectronica"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%
   String idsParam = request.getParameter("ids");
    List<FacturacionElectronica> facturasSeleccionadas = new ArrayList<>();
    if (idsParam != null && !idsParam.isEmpty()) {
        String[] idsArray = idsParam.split(",");
        facturasSeleccionadas = FacturacionElectronica.listByIds(Arrays.asList(idsArray));
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Imprimir Facturas</title>
    <style>
        table {border-collapse: collapse; width: 100%; font-size: 12px;}
        th, td {border: 1px solid black; padding: 5px; text-align: left;}
    </style>
</head>
<body onload="window.print(); window.close();">
    <h3>Facturas electrónicas seleccionadas</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Centro de operaciones</th>
                <th>Etapa</th>
                <th>Fecha factura</th>
                <th>Estado</th>
                <th>Factura autorizada</th>
                <th>Concepto</th>
                <th>Razon social proveedor</th>
                <th>Razon social adquiriente</th>
            </tr>
        </thead>
        <tbody>
            <% for(FacturacionElectronica f : facturasSeleccionadas) { %>
                <tr>
                    <td><%= f.getId() %></td>
                    <td><%= f.getDeptoCentroOperaciones() %></td>
                    <td><%= f.getEtapa() %></td>
                    <td><%= f.getFechaFactura() %></td>
                    <td><%= f.getEstadoFactura() %></td>
                    <td><%= f.getNumFacturaAutorizada() %></td>
                    <td><%= f.getConcepto() %></td>
                    <td><%= f.getRazonSocialProveedor() %></td>
                    <td><%= f.getRazonSocialAdquiriente() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
        <body onload="window.print(); window.close();">

</body>
</html>

