<%-- 
    Document   : find
    Created on : Jun 29, 2019, 11:59:27 AM
    Author     : Sapsaj
--%>

<%@page import="com.liza.domain.Zahtev"%>
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
        <title>Zahtev</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        <br><br><br><br><br><br>

        <div class="col-6 col-md-1"></div>
        <div class="col-6 col-md-10">

            <div class="list-group">
                <div class="list-group-item" align="center">
                    <b><font size="4">Zahtev</font></b>
                </div>
            </div>
            <br><br>

            <p><b></b><input type="text" id="myInput" onkeyup="searchTable()"  placeholder="Pretraga zahteva">
            </p>

            <table class="table table-hover">

                <thead>
                    <tr>
                        <th scope="col">Odobreno</th>
                        <th scope="col">Sifra zahteva</th>
                         
                        <th scope="col">Datum</th>
                       
                        <th scope="col">Zaposleni</th>
                        <th scope="col">Account manager</th>

                        <th class='text-right'><a href="/zahtev/insert" class="btn btn-dark"><span class='fa fa-fw fa-plus'></span></a></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${zahtevList}" var="zahtev" >
                        <tr>
                            <td>
                                <p class='form-control-static'>
                                    ${zahtev.odobreno}
                                </p>
                            </td>
                            <td>
                                <p class='form-control-static'>
                                    ${zahtev.idZahteva}
                                </p>
                            </td> 
                             
                            
                            
                            <td>
                                <p class='form-control-static'>
                                    ${zahtev.datum}
                                </p>
                            </td> 
                           

                            <td>
                                <p class='form-control-static'>
                                    ${zahtev.zaposleni1.ime}
                                </p>
                            </td>

                            <td>
                                <p class='form-control-static'>
                                    ${zahtev.zaposleni2.ime}
                                </p>
                            </td>
                            <td>
                                <div class='text-right'>
                                    <div class='btn-group'>
                                        <a href="/zahtev/details/${zahtev.idZahteva}" class="btn btn-dark"><span class='fa fa-fw fa-question'></span></a>
                                        <a href="/zahtev/update/${zahtev.idZahteva}" class="btn btn-dark"><span class='fa fa-fw fa-pencil'></span></a>
                                        <a href="/zahtev/delete/${zahtev.idZahteva}" class="btn btn-dark"><span class='fa fa-fw fa-trash'></span></a>
                                    </div>
                                </div>
                            </td>


                        </tr>
                    </c:forEach>

                </tbody>
            </table>  
            <script>
                function searchTable() {
                    var input, filter, found, table, tr, td, i, j;
                    input = document.getElementById("myInput");
                    filter = input.value.toUpperCase();
                    table = document.getElementById("myTable");
                    tr = table.getElementsByTagName("tr");
                    for (i = 1; i < tr.length; i++) {
                        td = tr[i].getElementsByTagName("td");

                        if (td[5].innerHTML.toUpperCase().indexOf(filter) > -1) {
                            found = true;
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
        </div>
        <div class="col-6 col-md-1"></div>
    </body>
</html>

