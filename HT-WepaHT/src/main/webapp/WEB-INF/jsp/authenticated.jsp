<%-- 
    Document   : authenticated
    Created on : 31-Oct-2013, 17:40:20
    Author     : mhaanran
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
        <title>Authenticated</title>
    </head>
    <body>
        <h1>Welcome </h1>
    <h4><a href="<c:url value="j_spring_security_logout"/>" >Logout</a></h4>
</body>
</html>
