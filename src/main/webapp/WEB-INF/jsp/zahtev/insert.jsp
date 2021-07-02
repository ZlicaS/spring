<%-- 
    Document   : insert
    Created on : Jun 29, 2019, 11:46:51 AM
    Author     : Sapsaj
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../../resources/styles/index2.css">
        <title>Zahtev insert</title>
    </head>

    <style>
        .meniItem {
            color: white;
        }
    </style>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">
            <div class="list-group" align="center">
                <div class="list-group-item">
                    <b><font size="4"><a href="/proracun/find">Zahtev</a> / Unos </font></b>
                </div>
            </div>
            <br><br>

            <div class="container-fluid">
                <form:form action="/zahtev/insert" method="post" modelAttribute="zahtev">   

                    <div class="form-group">


                        <div class="form-group">
                            <form:label path="datum">Datum</form:label>
                            <fmt:formatDate value="${zahtev.datum}" var="formattedDate" type="date" pattern="yyyy/MM/dd" />                                           
                            <form:input path="datum" class="form-control" id="datum" />
                        </div>


                        <div class="form-group">
                            <form:label path="odobreno">Odobreno</form:label>
                            <form:input path="odobreno" class="form-control" id="odobreno" />
                        </div>


                        <div class="form-group">
                            <form:label path="zaposleni1.idZaposleni">Zaposleni</form:label>
                            <form:select class="form-control" path="zaposleni1.idZaposleni">                                          
                                <form:options items="${zaposleniList1}" itemLabel="ime" itemValue="idZaposleni" />
                            </form:select>
                        </div>

                        <div class="form-group">
                            <form:label path="zaposleni2.idZaposleni">Account manager</form:label>
                            <form:select class="form-control" path="zaposleni2.idZaposleni">                                          
                                <form:options items="${zaposleniList2}" itemLabel="ime" itemValue="idZaposleni" />
                            </form:select>
                        </div>
                    </div>   
                    <br>

                    <div class='text-right'>
                        <button type="submit" class="btn btn-dark"><span class="fa fa-fw fa-check-square"></span></button>
                        <a href="/zahtev/find" class="btn btn-dark"><span class="fa fa-fw fa-remove"></span></a>
                    </div>
                </form:form>
            </div>
            <div class="col-6 col-md-1"></div>
        </div>
        <script>
            var flag = 0;
            if (localStorage.getItem("idZahteva")) {
                if (localStorage.getItem("idZahteva") === document.getElementById("idZahteva").value) {
                    flag = 1;
                }
                if (flag === 1) {
                    [].forEach.call(document.querySelector('#form-id').elements, function (el) {
                        if (el.id !== "idZahteva") {
                            el.value = localStorage.getItem(el.name);
                        }
                    });
                }
            }
        </script>

        <script>
            function UpdateStorage() {
                localStorage.clear();
                console.log("haj"); //prolazi kroz formu proracuna i cuva sve sto se trenutno nalazi 
                [].forEach.call(document.querySelector('#form-id').elements, function (el) {
                    localStorage.setItem(el.name, el.value);

                })
            }
        </script>


        <div class='form-group'>
            <table class="table table-condensed table-align">
                <thead>
                    <tr>
                        <th>Rbr</th>
                        <th>Opis</th>

                        
                        <th class='text-right'><a href="/stavkaZahteva/find/${zahtev.idZahteva}" class="btn btn-dark"><span class="fa fa-fw fa-plus"></span></a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${zahtev.stavkeZahteva}" var="stavkeZahteva">
                        <tr>
                            <td>${stavkeZahteva.rbr}</td>
                            <td>${stavkeZahteva.opis}</td>


                            <td>
                                <div class='text-right'>
                                    <div class='btn-group'>
                                        <a href="/stavkaZahteva/details/${zahtev.idZahteva}/${stavkeZahteva.rbr}" class="btn btn-dark"><span class='fa fa-fw fa-question'></span></a>
                                        <a href="/stavkaZahteva/update/${zahtev.idZahteva}/${stavkeZahteva.rbr}" class="btn btn-dark"><span class='fa fa-fw fa-pencil'></span></a>
                                        <a href="/stavkaZahteva/delete/${zahtev.idZahteva}/${stavkeZahteva.rbr}" class="btn btn-dark"><span class='fa fa-fw fa-trash'></span></a>
                                    </div>
                                </div>
                            </td>  
                        </tr>
                    </c:forEach>
                </tbody>


            </table>

        </div>



    </body> 
</html>
