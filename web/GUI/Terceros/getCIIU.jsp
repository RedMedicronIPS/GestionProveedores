<%-- 
    Document   : getCIIU
    Created on : 15/05/2025, 11:44:31 AM
    Author     : IPS OBRERO
--%>

<%@page import="clases.ConfiguracionGeneral.GeneralCIIU"%>
<%@page contentType="application/json"%>
<%
    String id = request.getParameter("id");
    if (id != null && !id.trim().isEmpty()) {
        GeneralCIIU ciiu = new GeneralCIIU(id);
        if (ciiu.getId() != null && !ciiu.getId().isEmpty()) {
            out.print("{\"codigo\":\"" + (ciiu.getCodigo() != null ? ciiu.getCodigo() : "") + "\", " +
                      "\"nombre\":\"" + (ciiu.getNombre() != null ? ciiu.getNombre() : "") + "\"}");
            return;
        }
    }
    out.print("{}");
%>