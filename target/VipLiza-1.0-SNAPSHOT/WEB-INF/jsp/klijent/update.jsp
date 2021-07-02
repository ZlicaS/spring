<%-- 
    Document   : update
    Created on : Jun 28, 2019, 7:54:56 PM
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
        <link rel="stylesheet" type="text/css" href="../../resources/styles/index2.css">
        <title>Klijent update</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">


            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/zaposleni/find">Zaposleni</a> / Izmena </font></b>
                </div>
            </div>

            <br><br>

            <form:form action="/klijent/update/${klijent.idKlijent}" method="post" modelAttribute="klijent">

                <div class="form-group">
                    <form:label path="naziv">Naziv</form:label>
                  
                    <form:input path="naziv" class="form-control" id="naziv"  value="${klijent.naziv}" />
                </div>
                <div class="form-group">
                    <form:label path="pib">Pib</form:label>
                    <form:input path="pib" class="form-control" id="pib" value="${klijent.pib}" />
                </div>
                <div class="form-group">
                    <form:label path="telefon">Telefon</form:label>                                                               
                    <form:input path="telefon" class="form-control" id="telefon" value="${klijent.telefon}" />
                </div>
                <div class="form-group">
                    <form:label path="webStrana">Web strana</form:label>                                                               
                    <form:input path="webStrana" class="form-control" id="webStrana" value="${klijent.webStrana}" />
                </div>

                <div class="form-group">
                    <form:label path="godinaOsnivanja">Godina osnivanja</form:label>                                                               
                    <form:input path="godinaOsnivanja" class="form-control" id="godinaOsnivanja" value="${klijent.godinaOsnivanja}" />
                </div>


                <div class="form-group">
                    <form:label path="delatnost.sifraDelatnosti">Delatnost</form:label>
                    <form:select class="form-control" path="delatnost.sifraDelatnosti">                                          
                        <form:options items="${delatnostList}" itemLabel="naziv" itemValue="sifraDelatnosti" />
                    </form:select>
                </div>
                <br>
                <div class="form-group">
                    <form:label path="mesto.postanskiBroj">Mesto</form:label>
                    <form:select class="form-control" path="mesto.postanskiBroj">                                          
                        <form:options items="${mestaList}" itemLabel="naziv" itemValue="postanskiBroj" />
                    </form:select>
                </div>
                <br>
                <div class="form-group">
                    <form:label path="potencijalniKlijent.idPotKlijenta">Potencijalni klijent</form:label>
                    <form:select class="form-control" path="potencijalniKlijent.idPotKlijenta">                                          
                        <form:options items="${pkList}" itemLabel="naziv" itemValue="idPotKlijenta" />
                    </form:select>
                </div>
                <br>
                <div class='text-right'>
                    <button type="submit" class="btn btn-dark"><span class="fa fa-fw fa-check-square"></span></button>
                    <a href="/klijent/find" class="btn btn-dark"><span class="fa fa-fw fa-remove"></span></a>
                </div>

            </form:form>
        </div>

        <div class="col-6 col-md-1"></div>

    </body>

</html>


