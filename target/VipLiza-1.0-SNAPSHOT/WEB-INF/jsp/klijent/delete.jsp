<%-- 
    Document   : delete
    Created on : Jun 28, 2019, 8:26:02 PM
    Author     : Sapsaj
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../../resources/styles/index2.css">
        <title>Klijent delete</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/zaposleni/find">Evidencija klijenta</a> / Brisanje </font></b>
                </div>
            </div>
            <br><br>

            <form:form action="/klijent/delete/${klijent.idKlijent}" method="post">
                <div class="form-group">
                    <label for="idKlijent">Sifra klijenta</label>
                    ${klijent.idKlijent}
                </div>

                <div class="form-group">
                    <label for="naziv">Naziv</label>
                    ${klijent.naziv}
                </div>

                <div class="form-group">
                    <label for="pib">Pib</label>
                    ${klijent.pib}
                </div>

                <div class="form-group">
                    <label for="telefon">Telefon</label>
                    ${klijent.telefon}
                </div>

                <div class="form-group">
                    <label for="webStrana">Web strana</label>
                    ${klijent.webStrana}
                </div>

                <div class="form-group">
                    <label for="godinaOsnivanja">Godina osnivanja</label>
                    ${klijent.godinaOsnivanja}
                </div>

                <div class="form-group">
                    <label for="delatnost">Delatnost</label>
                    ${klijent.delatnost.sifraDelatnosti}
                </div>

                <div class="form-group">
                    <label for="mesto">Mesto</label>
                    ${klijent.mesto.postanskiBroj}
                </div>

                <div class="form-group">
                    <label for="potencijalniKlijent">Potencijalni klijent</label>
                    ${klijent.potencijalniKlijent.idPotKlijenta}
                </div>

                <br>

                <div class='text-left'>
                    <button type="submit" class="btn btn-dark"><span class="fa fa-fw fa-trash"></span></button>
                    <a href="/zaposleni/find" class="btn btn-dark"><span class="fa fa-fw fa-remove"></span></a>
                </div>
            </form:form>

        </div>
        <div class="col-6 col-md-1"></div>
    </body>

</html>

