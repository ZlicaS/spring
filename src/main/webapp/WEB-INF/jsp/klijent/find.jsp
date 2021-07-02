<%-- 
    Document   : find
    Created on : Jun 28, 2019, 5:12:39 PM
    Author     : Sapsaj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "/resources/js/bootstrap.js" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../../resources/styles/index2.css">
        <title>Klijent</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4">Klijenti</font></b>
                </div>
            </div>
            <br><br>

            <p><b></b><input type="text" id="myInput" onkeyup="searchTable()"  placeholder="Unesi naziv klijenta">
            </p>
            <table class='table table-condensed table-align' id="myTable">
                <thead>
                    <tr>

                        <th>PIB</th>
                        <th>Naziv</th>
                        <th>Telefon</th>
                        <th>Web strana</th>
                        <th>Godina osnivanja</th>

  <!--<a href="<c:url value="/klijent/insert"/>"><h4 class="meniItem">Dodaj</h4></a>-->

                        <th class='text-right'><a href="/klijent/insert" class="btn btn-dark"><span class='fa fa-fw fa-user-plus'></span></a></th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${klijentList}" var="klijent" >
                        <tr>
                            <td>

                                <p class='form-control-static'>
                                    ${klijent.pib}
                                </p>

                            </td>

                            <td>

                                <p class='form-control-static'>
                                    ${klijent.naziv}
                                </p>
                            </td>   


                            <td>
                                <p class='form-control-static'>
                                    ${klijent.telefon}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${klijent.webStrana}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${klijent.godinaOsnivanja}
                                </p>
                            </td>
                            <td>
                                <div class='text-right'>
                                    <div class='btn-group'>
                                        <a href="/klijent/details/${klijent.idKlijent}" class="btn btn-dark"><span class='fa fa-fw fa-question'></span></a>
                                        <a href="/klijent/update/${klijent.idKlijent}" class="btn btn-dark"><span class='fa fa-fw fa-pencil'></span></a>
                                        <a href="/klijent/delete/${klijent.idKlijent}" class="btn btn-dark"><span class='fa fa-fw fa-trash'></span></a>
                                    </div>
                                </div>
                            </td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>      
        </div>
        <script>
            function searchTable() {
                var input, filter, found, table, tr, td, i, j;
                input = document.getElementById("myInput");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");
                for (i = 1; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td");



                    for (j = 0; j < td.length; j++) {
                        if (td[1].innerHTML.toUpperCase().indexOf(filter) > -1) {
                            found = true;
                        }
                    }
                    if (found) {
                        tr[i].style.display = "";
                        found = false;
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        </script>

        <div class="col-6 col-md-1"></div>
    </body>

</html>
