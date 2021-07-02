<%-- 
    Document   : details
    Created on : Jun 29, 2019, 12:49:35 PM
    Author     : Sapsaj
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../../resources/styles/index2.css">
        <title>Zahtev details</title>
    </head>
    <body>


        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/proracun/find">Zahtev</a> / Detalji </font></b>
                </div>
            </div>

            <div class='text-right'>
                <a href="/zahtev/update/${zahtev.idZahteva}" class="btn btn-dark"><span class="fa fa-fw fa-pencil"></span></a>
                <a href="/zahtev/find" class="btn btn-dark"><span class="fa fa-fw fa-remove"></span></a>
            </div>

            <div class='form-horizontal'>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Sifra zahteva </label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${zahtev.idZahteva}
                        </p>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='control-label col-xs-4'>Datum</label>
                    <div class='col-xs-8'>
                        <p class='form-control-static'>
                            ${zahtev.datum}
                        </p> 
                    </div>
                </div>  

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Odobreno</label>
                    <div class='col-xs-8'>
                        <p class='form-control-static'>
                            ${zahtev.odobreno}
                        </p> 
                    </div>
                </div>  

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Zaposleni</label>
                    <div class='col-xs-8'>
                        <p class='form-control-static'>
                            ${zahtev.zaposleni1.ime}
                        </p> 
                    </div>
                </div>  

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Account manager</label>
                    <div class='col-xs-8'>
                        <p class='form-control-static'>
                            ${zahtev.zaposleni2.ime}
                        </p> 
                    </div>
                </div> 

                <br>

                <div class='form-group'>
                    <table class="table table-condensed table-align">
                        <thead>
                            <tr>
                                <th>Rbr</th>
                                <th>Opis</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${zahtev.stavkeZahteva}" var="stavkaZahteva">
                                <tr>
                                    <td>${stavkaZahteva.rbr}</td>
                                    <td>${stavkaZahteva.opis}</td>
                                    <td>
                                        <div class='text-right'>
                                            <div class='btn-group'>
                                                <a href="/stavkaZahteva/details/${zahtev.idZahteva}/${stavkaZahteva.rbr}" class="btn btn-dark"><span class='fa fa-fw fa-question'></span></a>
                                                <a href="/stavkaZahteva/update/${zahtev.idZahteva}/${stavkaZahteva.rbr}" class="btn btn-dark"><span class='fa fa-fw fa-pencil'></span></a>
                                                <a href="/stavkaZahteva/delete/${zahtev.idZahteva}/${stavkaZahteva.rbr}" class="btn btn-dark"><span class='fa fa-fw fa-trash'></span></a>
                                            </div>
                                        </div>
                                    </td>  
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-6 col-md-1"></div>
            <br><br>
            </body>
            </html>
