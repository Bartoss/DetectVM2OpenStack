<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.ibm.service.impl.CheckOpenStackServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: Bartlomiej Sarata
  Date: 12/15/17
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/template/header.jsp" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<main role="main" class="container">
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <a class="btn btn-primary" href="<c:url value="/serverListRefresh"></c:url>" role="button">Refresh</a>
            </div>
            <div class="checkbox">
                <p><label><input type="checkbox" id="myCheck">Hide empty servers</label></p>
            </div>
            <br/>
            <br/>
            <span class="counter pull-right"></span>
            <table class="table-dark table-striped table-bordered" cellspacing="0" width="100%" id="myTable">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name:</th>
                    <th scope="col">Status:</th>
                    <th scope="col">Floating IPs Address:</th>
                    <th scope="col">Who am I:</th>
                    <th scope="col">Db2 Version:</th>
                    <th scope="col">Information Server Version:</th>
                    <th scope="col">Kernel Version:</th>
                    <th scope="col">WebSphere MQ Version:</th>
                    <th scope="col">Netezza Client Release:</th>
                    <th scope="col">OSNAME&OSVERSION:</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${servers}" var="server">
                    <tr>
                        <td>${server.id}</td>
                        <td>${server.name}</td>
                        <td>${server.active}</td>
                        <td>${server.address}</td>
                        <c:forEach items="${properties}" var="property">
                            <c:choose>
                                <c:when test="${property.server.id == server.id}">
                                    <td>${property.value}</td>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>
<!-- /.container -->
<%@include file="/WEB-INF/views/template/footer.jsp" %>