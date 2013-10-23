<%-- 
    Document   : search
    Created on : 21-Oct-2013, 12:50:30
    Author     : mhaanran
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css">
        <title>Search stop timetables</title>
    </head>
    <body>
        <h1>Search stop timetables!</h1>
        <form:form commandName="searchForm" action="${pageContext.request.contextPath}/app/search" method="POST">
            <label>Stop name:   </label> <form:input path="stopName" /><br>
            <label>Stop number: </label> <form:input path="stopNumber"/> <br> 
            <input type="submit" />
        </form:form> 

        <c:if test="${results!=null}">
            <table>
                <tr>
                    <th>Stops name</th>
                    <th>Stops address</th>
                    <th>Stops code</th>
                    <th>Stops coordinates</th>
                </tr>
                <tr>
                    <td>${results.name_fi}</td>
                    <td>${results.address_fi}</td>
                    <td>${results.code}</td>
                    <td>${results.wgs_coords}</td>
                </tr>


            </table>
            <table>
                <tr>
                    <th>Line code</th>
                    <th>At Stop in Minutes</th>
                </tr>
                <c:forEach var="departure" items="${results.departures}">
                    <tr>
                        <td>${departure.code}</td> 
                        <td>${departure.time}</td> 
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <br>
        <c:forEach var="result" items="${results.lines}">
            <c:out value="${result}" />
        </c:forEach>
    </body>
</html>