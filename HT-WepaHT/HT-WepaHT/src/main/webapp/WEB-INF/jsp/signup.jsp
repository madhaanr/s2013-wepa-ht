<%-- 
    Document   : signup
    Created on : 24-Oct-2013, 15:50:15
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
        <title>Timetable SignUp</title>
    </head>
    <body>
        <h1>Timetable new user signup page!</h1>
        <form:form commandName="user" action="${pageContext.request.contextPath}/app/signup" method="POST">
            <form:label path="username">User Name: </form:label><form:input path="username" required="true"/> <br>
            <form:label path="password">Password: </form:label><form:input type="password" path="password" required="true"/> <br>            
            <input type="submit" />
        </form:form>
    </body>
</html>
