<%-- 
    Document   : login
    Created on : 29-Oct-2013, 11:45:05
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
        <title>Login page</title>
    </head>
    <body>
        <h1>Login to timetables service!</h1>
        <form:form commandName="user" action="${pageContext.request.contextPath}/app/login" method="POST">
            <form:label path="username">User Name: </form:label><form:input path="username" required="true"/> <br>
            <form:label path="password">Password: </form:label><form:input type="password" path="password" required="true"/> <br>
            
            <input type="submit" />
        </form:form>
    </body>
</html>
