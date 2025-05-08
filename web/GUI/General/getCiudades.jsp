<%@ page import="java.util.List" %>
<%@ page import="clases.ConfiguracionGeneral.GeneralCiudad" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%
    String idDepartamento = request.getParameter("idDepartamento");

    if (idDepartamento != null && !idDepartamento.isEmpty()) {
        // Llamamos al método que obtiene las ciudades de un departamento usando el nombre del departamento
        List<String> ciudades = GeneralCiudad.getCiudadesPorDepartamentoNombre(idDepartamento);

        // Generamos el HTML para las opciones del select de ciudades
        StringBuilder options = new StringBuilder("<option value='' disabled selected hidden>Seleccione una ciudad</option>");
        for (String ciudad : ciudades) {
            options.append("<option value='" + ciudad + "'>" + ciudad + "</option>");
        }

        out.print(options.toString());
    }
%>
