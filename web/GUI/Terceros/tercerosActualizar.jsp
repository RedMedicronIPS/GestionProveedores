<%@page import="clases.ConfiguracionGeneral.GeneralTercero"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    
    String accion = request.getParameter("accion");
    String id = request.getParameter("id");
    boolean isAjax = "true".equals(request.getParameter("isAjax"));
    boolean success = false;
    String mensaje = "";

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

                tercero.setTercero_empleado(request.getParameter("tercero_empleado"));
                tercero.setTercero_proveedor(request.getParameter("tercero_proveedor"));
                tercero.setTercero_accionista_asociado(request.getParameter("tercero_accionista_asociado"));
                tercero.setTercero_facturar(request.getParameter("tercero_facturar"));

                tercero.setTercero_tipo(request.getParameter("tercero_tipo"));
                
                if ("Actualizar".equals(accion)) {
                    tercero.setTercero_estado(tercero.getTercero_estado());
                } else {
                    tercero.setTercero_estado(true); 
                }

                Logger.getLogger("tercerosActualizar").log(Level.INFO, "Valores recibidos de radio buttons:");
                Logger.getLogger("tercerosActualizar").log(Level.INFO, "Empleado: " + request.getParameter("tercero_empleado"));
                Logger.getLogger("tercerosActualizar").log(Level.INFO, "Proveedor: " + request.getParameter("tercero_proveedor"));
                Logger.getLogger("tercerosActualizar").log(Level.INFO, "Accionista: " + request.getParameter("tercero_accionista_asociado"));
                Logger.getLogger("tercerosActualizar").log(Level.INFO, "Facturar: " + request.getParameter("tercero_facturar"));
            }

            if ("Actualizar".equals(accion)) {
                Logger.getLogger("tercerosActualizar").log(Level.INFO, "Iniciando actualización para ID: " + id);
                success = tercero.update();
                mensaje = success ? "Tercero actualizado correctamente" : "Error al actualizar tercero";
                Logger.getLogger("tercerosActualizar").log(Level.INFO, "Valores que se están actualizando:");
                Logger.getLogger("tercerosActualizar").log(Level.INFO, "Empleado (DB): " + tercero.getTerceroEmpleado());
                Logger.getLogger("tercerosActualizar").log(Level.INFO, "Proveedor (DB): " + tercero.getTerceroProveedor());
                Logger.getLogger("tercerosActualizar").log(Level.INFO, "Accionista (DB): " + tercero.getTerceroAccionistaAsociado());
                Logger.getLogger("tercerosActualizar").log(Level.INFO, "Facturar (DB): " + tercero.getTerceroFacturar());
            } else if ("Create".equals(accion)) {
                success = tercero.create();
                mensaje = success ? "Tercero creado correctamente" : "Error al crear tercero";
            } else if ("Delete".equals(accion)) {
                success = tercero.desactivar();
                mensaje = success ? "Tercero eliminado correctamente" : "Error al eliminar tercero";
            }
        }
    } catch (Exception e) {
        mensaje = "Error grave: " + e.getMessage();
        Logger.getLogger("tercerosActualizar").log(Level.SEVERE, "Error al procesar acción", e);
    }

    String redirectURL = request.getContextPath() + "/main.jsp?CONTENIDO=GUI/Terceros/terceros.jsp&mensaje="
            + java.net.URLEncoder.encode(mensaje, "UTF-8");
    response.sendRedirect(redirectURL);
%>