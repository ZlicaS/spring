<%-- 
    Document   : login
    Created on : 29.06.2019., 11.19.53
    Author     : Rakić
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" type="text/css" href="../../resources/styles/login.css">
        <title>Login</title>

    </head>
    <body>
        <div class="list-group">
            <div class="list-group-item" align="center">
                <b><font size="4">Uloguj se</font></b>
            </div>
        </div>

        <form:form action="login" method="post" modelAttribute="zaposleni1">
            <table align ="center">
                <tr>
                    <td>Ime</td>
                    <td><form:input path="korisnickoIme" /></td>
                </tr>
                <br> 
                <tr>
                    <td>Sifra</td>
                    <td><form:password path="korisnickaSifra" /></td>
                </tr>
                <tr>
                    <td align="center"><button type="submit">Login</button></td>
                </tr>
            </table>
            <br>
        </form:form>

        <span style="color: red;">${message}</span>
    </body>
</html>