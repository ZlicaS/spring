<%-- 
    Document   : details
    Created on : Jun 30, 2019, 3:13:15 PM
    Author     : Sapsaj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../../../resources/styles/index2.css">
        <title>Stavka zahteva</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>

        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10" >

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/stavkaZahteva/find/${idZahteva}">Stavka zahteva sa ID-ijem: ${idZahteva}</a> / Detalji </font></b>
                </div>
            </div>


            <div class='text-right'>
                <a href="/stavkaZahteva/update/${idZahteva}/${stavkaZahteva.rbr}" class="btn btn-dark"><span class="fa fa-fw fa-pencil"></span></a>
                <a href="/stavkaZahteva/find/${idZahteva}" class="btn btn-dark"><span class="fa fa-fw fa-remove"></span></a>
            </div>


            <div class='form-horizontal'>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Rbr zahteva </label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${stavkaZahteva.rbr}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Opis </label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${stavkaZahteva.opis}
                        </p>
                    </div>
                </div>

            </div>
        </div>

        <div class="col-6 col-md-1"></div>

    </body>
</html>
