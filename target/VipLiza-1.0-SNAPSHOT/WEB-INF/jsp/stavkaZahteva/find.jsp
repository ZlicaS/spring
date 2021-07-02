<%-- 
    Document   : find
    Created on : Jun 29, 2019, 5:50:31 PM
    Author     : Sapsaj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../../resources/styles/index2.css">
        <title>Stavka zahteva</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/stavkaZahteva/find/${idZahteva}">Stavka zahteva sa ID-ijem: ${idZahteva}</a> / Unos </font></b>
                </div>
            </div>
            <br><br>

            <div class="container-fluid">
                <form:form action="/stavkaZahteva/find/${idZahteva}" method="post" >


                    <table class='table table-condensed table-align'>
                        <thead>
                            <tr>
                                <th>Rbr</th>
                                <th>Opis</th>                        

                                <th class='text-right'><a href="/stavkaZahteva/insert/${idZahteva}" class="btn btn-dark"><span class='fa fa-fw fa-plus'></span></a></th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${stavkaZahtevaList}" var="stavkaZahteva" >

                                <tr>                            
                                    <td>
                                        <p class='form-control-static'>
                                            ${stavkaZahteva.rbr}
                                        </p>
                                    </td>
                                    <td>
                                        <p class='form-control-static'>
                                            ${stavkaZahteva.opis}
                                        </p>
                                    </td>                                                   
                                    <td>
                                        <div class='text-right'>
                                            <div class='btn-group'>
                                                <a href="/stavkaZahteva/details/${idZahteva}/${stavkaZahteva.rbr}" class="btn btn-dark"><span class='fa fa-fw fa-question'></span></a>
                                                <a href="/stavkaZahteva/update/${idZahteva}/${stavkaZahteva.rbr}" class="btn btn-dark"><span class='fa fa-fw fa-pencil'></span></a>
                                                <a href="/stavkaZahteva/delete/${idZahteva}/${stavkaZahteva.rbr}" class="btn btn-dark"><span class='fa fa-fw fa-trash'></span></a>
                                            </div>
                                        </div>
                                    </td>               
                                </tr>
                            </c:forEach>                              
                        </tbody>
                    </table>    
                    <br><br>
                    <div class='text-right'>
                        <a href="/zahtev/insert/${idZahteva}" class="btn btn-dark"><span class="fa fa-fw fa-check"></span></a>
                    </div>
                </form:form>
            </div>
        </div>
        <div class="col-6 col-md-1"></div>
    </body>
</html>
