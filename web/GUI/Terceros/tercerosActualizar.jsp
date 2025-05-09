<%@page import="clases.ConfiguracionGeneral.GeneralTercero"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Configurar encoding
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    // Obtener parámetros
    String accion = request.getParameter("accion");
    String id = request.getParameter("id");

    // Validar acción
    if (accion == null || accion.isEmpty()) {
        response.sendRedirect(request.getContextPath() + "/main.jsp?CONTENIDO=GUI/Terceros/terceros.jsp&mensaje=Acción no especificada");
        return;
    }

    // Crear objeto
    GeneralTercero tercero = new GeneralTercero();
    if (id != null && !id.isEmpty()) {
        tercero = new GeneralTercero(id);
    }

    // Asignar valores (excepto para DELETE)
    if (!"Delete".equals(accion)) {
        // Campos básicos
        tercero.setTercero_codigo(request.getParameter("tercero_codigo"));
        tercero.setTercero_id_tipo_identificacion(request.getParameter("tercero_id_tipo_identificacion"));
        tercero.setTercero_razon_nombres(request.getParameter("tercero_razon_nombres"));
        tercero.setTercero_fecha_nacimiento(request.getParameter("tercero_fecha_nacimiento"));
        tercero.setTercero_direccion(request.getParameter("tercero_direccion"));
        tercero.setTercero_telefono(request.getParameter("tercero_telefono"));
        tercero.setTercero_correo(request.getParameter("tercero_correo"));

        // Campos de ubicación
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

        // Resto de campos
        tercero.setTercero_ciiu(request.getParameter("tercero_ciiu"));

        // Campos booleanos (radio buttons) - Solución definitiva
        // Asignamos directamente los valores "true"/"false" recibidos del formulario
        tercero.setTercero_empleado(request.getParameter("tercero_empleado"));
        tercero.setTercero_proveedor(request.getParameter("tercero_proveedor"));
        tercero.setTercero_accionista_asociado(request.getParameter("tercero_accionista_asociado"));
        tercero.setTercero_facturar(request.getParameter("tercero_facturar"));

        // Campos adicionales
        tercero.setTercero_tipo(request.getParameter("tercero_tipo"));
        String estadoParam = request.getParameter("tercero_estado");
        tercero.setTercero_estado("1".equalsIgnoreCase(estadoParam));

        // Log para depuración - Verificamos los valores recibidos
        Logger.getLogger("tercerosActualizar").log(Level.INFO, "Valores recibidos de radio buttons:");
        Logger.getLogger("tercerosActualizar").log(Level.INFO, "Empleado: " + request.getParameter("tercero_empleado"));
        Logger.getLogger("tercerosActualizar").log(Level.INFO, "Proveedor: " + request.getParameter("tercero_proveedor"));
        Logger.getLogger("tercerosActualizar").log(Level.INFO, "Accionista: " + request.getParameter("tercero_accionista_asociado"));
        Logger.getLogger("tercerosActualizar").log(Level.INFO, "Facturar: " + request.getParameter("tercero_facturar"));
    }

    // Ejecutar acción
    boolean resultado = false;
    String mensaje = "";

    try {
        if ("Actualizar".equals(accion)) {
            Logger.getLogger("tercerosActualizar").log(Level.INFO, "Iniciando actualización para ID: " + id);
            resultado = tercero.update();
            mensaje = resultado ? "Tercero actualizado correctamente" : "Error al actualizar tercero";

            // Log adicional para verificar los valores que se están actualizando
            Logger.getLogger("tercerosActualizar").log(Level.INFO, "Valores que se están actualizando:");
            Logger.getLogger("tercerosActualizar").log(Level.INFO, "Empleado (DB): " + tercero.getTerceroEmpleado());
            Logger.getLogger("tercerosActualizar").log(Level.INFO, "Proveedor (DB): " + tercero.getTerceroProveedor());
            Logger.getLogger("tercerosActualizar").log(Level.INFO, "Accionista (DB): " + tercero.getTerceroAccionistaAsociado());
            Logger.getLogger("tercerosActualizar").log(Level.INFO, "Facturar (DB): " + tercero.getTerceroFacturar());
        } else if ("Create".equals(accion)) {
            resultado = tercero.create();
            mensaje = resultado ? "Tercero creado correctamente" : "Error al crear tercero";
        } else if ("Delete".equals(accion)) {
            resultado = tercero.delete();
            mensaje = resultado ? "Tercero eliminado correctamente" : "Error al eliminar tercero";
        }
    } catch (Exception e) {
        mensaje = "Error grave: " + e.getMessage();
        Logger.getLogger("tercerosActualizar").log(Level.SEVERE, "Error al procesar acción", e);
    }

    // Redirección
    String redirectURL = request.getContextPath() + "/main.jsp?CONTENIDO=GUI/Terceros/terceros.jsp&mensaje="
            + java.net.URLEncoder.encode(mensaje, "UTF-8");
    response.sendRedirect(redirectURL);
%>