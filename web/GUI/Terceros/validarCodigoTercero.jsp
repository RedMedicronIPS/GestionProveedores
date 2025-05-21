<%@page import="clases.ConfiguracionGeneral.GeneralTercero"%>
<%@page import="org.json.JSONObject"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    String codigo = request.getParameter("codigo");
    String id = request.getParameter("id");
    
    JSONObject json = new JSONObject();
    boolean existe = false;
    
    try {
        if (codigo != null && !codigo.trim().isEmpty()) {
            existe = GeneralTercero.existeCodigoTercero(codigo, (id != null && !id.trim().isEmpty()) ? id : null);
        }
        json.put("existe", existe);
    } catch(Exception e) {
        json.put("error", e.getMessage());
    }
    
    out.print(json.toString());
    out.flush();
%>