<%@page import="clases.ConfiguracionGeneral.GeneralDepartamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String pais = request.getParameter("pais");
    if (pais != null && !pais.isEmpty()) {
        out.print(GeneralDepartamento.getDepartamentosPorPais(pais));
    }
%>
