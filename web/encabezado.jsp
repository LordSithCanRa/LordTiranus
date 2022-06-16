<%-- 
    Document   : encabezado
    Created on : 14-jun-2022, 23:31:41
    Author     : rcane
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty usuario}">
    <jsp:forward page="index.jsp?media=Sesion Finalizada"/>
</c:if>