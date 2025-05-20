<%-- 
    Document   : logout
    Created on : 12/02/2025, 08:07:47 AM
    Author     : IPS OBRERO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <script>
            localStorage.removeItem("theme");
        </script>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession(false);
            if (sesion != null) {
                sesion.invalidate();
            }
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
        %>

        <script>
            window.location.href = "${pageContext.request.contextPath}/index.jsp";
        </script>
    </body>
</html>