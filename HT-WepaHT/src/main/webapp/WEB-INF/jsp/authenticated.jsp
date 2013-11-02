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
        <h1>Save your favourite searches!</h1>
        <h2>Saved searches</h2>
        <h2>Save search</h2>
        <form:form commandName="searchToSave" action="${pageContext.request.contextPath}/app/authenticated" method="POST">
            <form:label path="searchName">Search name </form:label><form:input path="searchName" required="true"/> <br>
            <form:label path="stopName">Stop name: </form:label><form:input type="stopName" path="stopName" required="false"/> <br>
            <form:label path="stopNumber">Stop number: </form:label><form:input type="stopNumber" path="stopNumber" required="false"/> <br>
            <input type="submit" />
        </form:form>
        
        <h4><a href="<c:url value="j_spring_security_logout"/>" >Logout</a></h4>
</body>
</html>
