<%-- 
    Document   : details
    Created on : Jun 28, 2019, 6:25:25 PM
    Author     : Sapsaj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../../resources/styles/index2.css">
        <title>Klijenti details</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/klijent/find">Klijenti</a> / Detalji </font></b>
                </div>
            </div>
            <div class='text-right'>
                <a href="/klijent/update/${klijent.idKlijent}" class="btn btn-dark"><span class="fa fa-fw fa-pencil"></span></a>
                <a href="/klijent/find" class="btn btn-dark"><span class="fa fa-fw fa-remove"></span></a>
            </div>

            <div class='form-horizontal'>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Naziv</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${klijent.naziv}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>PIB</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${klijent.pib}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Telefon</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${klijent.telefon}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Web strana</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${klijent.webStrana}
                        </p>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='control-label col-xs-4'>Godina osnivanja</label>
                    <div class='col-xs-8'>
                        <p class="form-control-static">
                            ${klijent.godinaOsnivanja}
                        </p>
                    </div>
                </div>


            </div>
        </div>
        <div class="col-6 col-md-1"></div>
        <br><br>
    </body>

</html>
