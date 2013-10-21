<%-- 
    Document   : search
    Created on : 21-Oct-2013, 12:50:30
    Author     : mhaanran
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search stop timetables</title>
    </head>
    <body>
        <h1>Search stop timetables!</h1>
        <form:form commandName="search" action="${pageContext.request.contextPath}/search" method="POST">
            <form:input path="stopName" />
            <form:input path="stopNumber" />
            <input type="submit" />
        </form:form>
        <p>${result}</p>
    </body>
</html>
