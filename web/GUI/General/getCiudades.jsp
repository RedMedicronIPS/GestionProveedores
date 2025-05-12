<%@ page import="java.util.List" %>
<%@ page import="clases.ConfiguracionGeneral.GeneralCiudad" %>
<%@ page contentType="application/json" pageEncoding="UTF-8" %>
<%
    String idDepartamento = request.getParameter("idDepartamento");

    if (idDepartamento != null && !idDepartamento.isEmpty()) {
        List<String> ciudades = GeneralCiudad.getCiudadesPorDepartamentoNombre(idDepartamento);

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < ciudades.size(); i++) {
            String ciudad = ciudades.get(i).replace("\"", "\\\""); 
            json.append("{\"label\":\"").append(ciudad).append("\",\"value\":\"").append(ciudad).append("\"}");
            if (i < ciudades.size() - 1) json.append(",");
        }
        json.append("]");
        out.print(json.toString());
    }
%>
