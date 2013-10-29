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
        <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
        <title>Search stop timetables</title>
    </head>
    <body>
        <h1>Search stop timetables!</h1>
        <h2>Use the stop name to search for stops with the Name specified(Stop names are not unique)</h2>
        <h2>Use the stop number to search for lines that stop on the Stop(Stop numbers are unique)</h2>
        <form:form commandName="searchForm" action="${pageContext.request.contextPath}/app/search" method="POST">
            <label>Stop name:   </label> <form:input path="stopName" /><br>
            <label>Stop number: </label> <form:input path="stopNumber"/> <br> 
            <input type="submit" />
        </form:form> 

        <h2>${error}</h2>
        
        <c:if test="${results!=null}">
            <h2>Information about the Stop</h2>
            <table>
                <tr>
                    <th>Stops name</th>
                    <th>Stops address</th>
                    <th>Stops code</th>
                    <th>Stops short code</th>
                    <th>Stops coordinates</th>
                </tr>
                <tr>
                    <td><a href="http://maps.google.com/?q=${results.wgs_coords}" target="_blank">${results.name_fi}</a></td>
                    <td>${results.address_fi}</td>
                    <td>${results.code}</td>
                    <td>${results.code_short}</td>
                    <td>${results.wgs_coords}</td>
                </tr>


            </table>


            <h2>Next ten lines that stop at the Stop</h2>
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
            <h2>Lines that pass the stop and their destinations</h2>
            <table>
                <tr>
                    <th>Line number</th>
                    <th>Destination</th>
                </tr>
                <c:if test="${results.lines!=null}">
                    <c:forEach var="result" items="${results.linesParsed}">
                        <tr>
                            <td>${result.line}</td>
                            <td>${result.destination}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </c:if>

        <c:if test="${stopName!=null}" >
            <h2>Stops with the name used in search</h2>
            <h2>Use the stop code to search for the lines that pass the Stop!</h2>
            <table>
                <tr>
                    <th>Stops name</th>
                    <th>Stops address</th>
                    <th>Stops code</th>
                    <th>Stops short code</th>
                </tr>
                <c:forEach var="stop" items="${stopName}">
                    <tr>
                        <td><a href="http://maps.google.com/?q=${stop.wgs_coords}" target="_blank">${stop.name_fi}</a></td> 
                        <td>${stop.address_fi}</td> 
                        <td>${stop.code}</td> 
                        <td>${stop.code_short}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>




    </body>
</html>