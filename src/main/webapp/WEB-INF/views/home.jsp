<%--
  Created by IntelliJ IDEA.
  User: Bartlomiej Sarata
  Date: 12/15/17
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/template/header.jsp"%>


<main role="main" class="container">

    <div class="starter-template">
        <h1>Virtual Machines And Installed Software Finder</h1>
        <p class="lead"><br></p>
    </div>
    <div>
        <p>
            The goal of the project is to create a tool to discover and collect infrormation about the VMs and software. In addition to that, one should develop a web-based dashboard (any modern Web UI technology) that would list all the virtual machines.
        </p>
        <br/>
    </div>
    <div>
        <table class="table table-dark">
            <thead>
            <td>Task 1</td>
            <td>Task 2</td>
            <td>Task 3</td>
            <td>Task 4</td>
            </thead>
            <tbody>
            <td>Detect VMs using OpenStack Cloud API,</td>
            <td>Develop Java / DB backend to store data about Vms and installed software,</td>
            <td>Develop Web UI dashboard,</td>
            <td>Prepare software detection tools/commands using Linux / Windows shell.</td>
            </tbody>
        </table>
    </div>
    <br/>
    <br/>
    <p>
        Celem projektu jest implementacja narzędzia które pozwoli na wykrycie działających maszyn oraz zainstalowanego oprogramowania a następnie przedstawi te dane w postaci strony webowej (z wykorzystanem dowolnej nowoczesnej techonologi WebUI).
    </p>
    <br/>
    <div>
        <table class="table table-dark">
            <thead>
            <td>Zadanie 1</td>
            <td>Zadanie 2</td>
            <td>Zadanie 3</td>
            <td>Zadanie 4</td>
            </thead>
            <tbody>
            <td>Wykrywanie maszyn wirtualnych z wykorzystaniem OpenStack REST API,</td>
            <td>Implementacja backendu w Javie (plus baza danych) do przechowywanie informacji o maszynach i zainstalowanym oprogramowaniu,</td>
            <td>Implementacja strony www prezentujacej zgromadzone dane,</td>
            <td>Przygotowanie i implementacja skryptów/komend dla systemow Linux i Windows wykrywajacych zainstalowane oprogramowanie.</td>
            </tbody>
        </table>
    </div>

</main><!-- /.container -->
<%@include file="/WEB-INF/views/template/footer.jsp"%>