<%-- 
    Document   : update
    Created on : Jun 30, 2019, 3:21:16 PM
    Author     : Sapsaj
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../../../resources/styles/index2.css">
        <title>Stavka zahteva update</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/stavkaZahteva/find/${idZahteva}">Stavka zahteva sa ID-ijem: ${idZahteva}</a> / Izmena </font></b>
                </div>
            </div>
            <br><br>

            <form:form action="/stavkaZahteva/update/${idZahteva}/${stavkaZahteva.rbr}" method="post" modelAttribute="stavkaZahteva" >
                <div class="form-group">
                   <form:label path="opis">Opis</form:label>
                        <form:input path="opis" class="form-control" id="opis" value="${stavkaZahteva.opis}"/>
                </div>

                <br>                        
                <div class='text-right'>
                    <button type="submit" class="btn btn-dark"><span class="fa fa-fw fa-check-square"></span></button>
                    <a href="/stavkaZahteva/find/${idZahteva}" class="btn btn-dark"><span class="fa fa-fw fa-remove"></span></a>
                </div>
            </form:form> 

        </div>

        <div class="col-6 col-md-1"></div>
    </body>
</html>
