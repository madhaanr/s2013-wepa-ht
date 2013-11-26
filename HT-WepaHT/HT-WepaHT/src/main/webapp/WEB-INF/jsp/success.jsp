<%-- 
    Document   : success.jsp
    Created on : 24-Oct-2013, 16:23:48
    Author     : mhaanran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
        <title>Signup succeeded</title>
    </head>
    <body>
        <h1>Signup succeeded!</h1>
        <h2><a href="${pageContext.request.contextPath}/app/menu">Menu</a></h2>
    </body>
</html>
