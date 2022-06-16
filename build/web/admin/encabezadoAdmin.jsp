<%-- 
    Document   : encabezadoAdmin
    Created on : 14-jun-2022, 23:39:15
    Author     : rcane
--%>
<%@page import="java.net.URLEncoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!usuario.admin}">
    <%
        String mensaje = URLEncoder.encode("No eres administrador para acceder a esta página", "latin1");
            response.sendRedirect(response.encodeRedirectURL("ObtenerInicio?error="
                    + mensaje));
    %>                
</c:if>
