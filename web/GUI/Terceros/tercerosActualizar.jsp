<%@page import="clases.ConfiguracionGeneral.GeneralTercero"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="com.microsoft.sqlserver.jdbc.SQLServerException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
//ar
    String accion = request.getParameter("accion");
    String id = request.getParameter("id");
    boolean isAjax = "true".equals(request.getParameter("isAjax"));
    boolean success = false;
    String mensaje = "";
    String error = "";

    if (accion == null || accion.isEmpty()) {
        mensaje = "Acción no especificada";
        if (isAjax) {
            out.print("{\"success\":false, \"message\":\"" + mensaje + "\"}");
            return;
        } else {
            response.sendRedirect(request.getContextPath() + "/main.jsp?CONTENIDO=GUI/Terceros/terceros.jsp&mensaje=" + java.net.URLEncoder.encode(mensaje, "UTF-8"));
            return;
        }
    }

    try {
        if ("Desactivar".equals(accion)) {
            if (id != null && !id.isEmpty()) {
                GeneralTercero tercero = new GeneralTercero(id);
                success = tercero.desactivar();
                mensaje = success ? "Tercero desactivado correctamente" : "Error al desactivar tercero";
                if (isAjax) {
                    out.print("{\"success\":" + success + ", \"message\":\"" + mensaje + "\"}");
                    return;
                }
            }
        } else {
            GeneralTercero tercero = new GeneralTercero();
            if (id != null && !id.isEmpty()) {
                tercero = new GeneralTercero(id);
            }

            if (!"Delete".equals(accion)) {
                tercero.setTercero_codigo(request.getParameter("tercero_codigo"));
                tercero.setTercero_id_tipo_identificacion(request.getParameter("tercero_id_tipo_identificacion"));
                tercero.setTercero_razon_nombres(request.getParameter("tercero_razon_nombres"));
                tercero.setTercero_fecha_nacimiento(request.getParameter("tercero_fecha_nacimiento"));
                tercero.setTercero_direccion(request.getParameter("tercero_direccion"));
                tercero.setTercero_telefono(request.getParameter("tercero_telefono"));
                tercero.setTercero_correo(request.getParameter("tercero_correo"));
                String pais = request.getParameter("tercero_pais");
                String depto = request.getParameter("tercero_departamento");
                String ciudad = request.getParameter("tercero_ciudad");

                if (pais != null && !pais.isEmpty()) {
                    tercero.setTercero_pais(pais);
                }
                if (depto != null && !depto.isEmpty()) {
                    tercero.setTercero_departamento(depto);
                }
                if (ciudad != null && !ciudad.isEmpty()) {
                    tercero.setTercero_ciudad(ciudad);
                }

                tercero.setTercero_ciiu(request.getParameter("tercero_ciiu"));
                tercero.setTercero_proveedor(request.getParameter("tercero_proveedor"));
                tercero.setTercero_facturar(request.getParameter("tercero_facturar"));
                tercero.setTercero_tipo(request.getParameter("tercero_tipo"));

                if ("Actualizar".equals(accion)) {
                    tercero.setTercero_estado(tercero.getTercero_estado());
                } else {
                    tercero.setTercero_estado(true);
                }
            }

            if ("Actualizar".equals(accion)) {
                success = tercero.update();
                mensaje = success ? "Tercero actualizado correctamente" : "Error al actualizar tercero";
            } else if ("Create".equals(accion)) {
                success = tercero.create();
                mensaje = success ? "Tercero creado correctamente" : "Error, el tercero ya  existe!";
            } else if ("Delete".equals(accion)) {
                success = tercero.desactivar();
                mensaje = success ? "Tercero eliminado correctamente" : "Error al eliminar tercero";
            }
        }
    } catch (Exception e) {
        
        Throwable cause = e;
        while (cause != null) {
            if (cause instanceof SQLServerException) {
                SQLServerException sqlEx = (SQLServerException) cause;
                if (sqlEx.getMessage().contains("UQ_tercero_codigo")) {
                    error = "Error: Ya existe un tercero con este número de documento (cédula/NIT)";
                    break;
                }
            }
            cause = cause.getCause();
        }

        if (error.isEmpty()) {
            error = "Error grave: " + e.getMessage();
        }

        Logger.getLogger("tercerosActualizar").log(Level.SEVERE, "Error al procesar acción", e);
    }
    
    String redirectParams = "";
    if (!mensaje.isEmpty()) {
        redirectParams = "&mensaje=" + java.net.URLEncoder.encode(mensaje, "UTF-8");
    }
    if (!error.isEmpty()) {
        redirectParams += "&error=" + java.net.URLEncoder.encode(error, "UTF-8");
    }
    
    if (!error.isEmpty() && "Create".equals(accion)) {
        redirectParams += "&formData=1";
        redirectParams += "&tercero_codigo=" + java.net.URLEncoder.encode(request.getParameter("tercero_codigo"), "UTF-8");
        redirectParams += "&tercero_id_tipo_identificacion=" + java.net.URLEncoder.encode(request.getParameter("tercero_id_tipo_identificacion"), "UTF-8");
        redirectParams += "&tercero_razon_nombres=" + java.net.URLEncoder.encode(request.getParameter("tercero_razon_nombres"), "UTF-8");        
    }

    String redirectURL = request.getContextPath() + "/main.jsp?CONTENIDO=GUI/Terceros/terceros.jsp" + redirectParams;
    response.sendRedirect(redirectURL);
%>
