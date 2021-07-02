<%-- 
    Document   : delete
    Created on : Jun 29, 2019, 2:18:11 PM
    Author     : Sapsaj
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../../resources/styles/index2.css">
        <title>Zahtev delete</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4"><a href="/proracun/find">Zahtev</a> / Brisanje </font></b>
                </div>
            </div>
            <br><br>

            <form:form action="/zahtev/delete/${zahtev.idZahteva}" method="post">
                <div class="form-group">
                    <label for="idZahteva">Sifra zahteva</label>
                    ${zahtev.idZahteva}
                </div>

                <div class="form-group">
                    <label for="datum">Datum</label>
                    ${zahtev.datum}
                </div>

                <div class="form-group">
                    <label for="odobreno">Odobreno</label>
                    ${zahtev.odobreno}
                </div>

                <div class="form-group">
                    <label for="zaposleni1">Zaposleni</label>
                    ${zahtev.zaposleni1.ime}
                </div>

                <div class="form-group">
                    <label for="zaposleni2">Account manager</label>
                    ${zahtev.zaposleni2.ime}
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

                            <c:forEach items="${zahtev.stavkeZahteva}" var="stavkeProracuna" >
                                <tr>
                                    <td>
                                        <p class='form-control-static'>
                                            ${stavkeZahteva.rbr}
                                        </p>
                                    </td> 
                                    <td>
                                        <p class='form-control-static'>
                                            ${stavkeZahteva.opis}
                                        </p>
                                    </td>  

                                </tr>
                            </c:forEach>


                        </tbody>
                    </table>
                </div> 

                <div class='text-left'>
                    <button type="submit" class="btn btn-dark"><span class="fa fa-fw fa-trash"></span></button>
                    <a href="/zahtev/find" class="btn btn-dark"><span class="fa fa-fw fa-remove"></span></a>
                </div>
                <br><br>
            </form:form>
        </div>
        <div class="col-6 col-md-1"></div>
    </body>
</html>
