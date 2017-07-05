<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 28.01.13
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<div id="wrapper">
    <div class="container">
        <div id="centerpanel">
            <div id="leftbar">
                <!--#begin container1 #-->
                <c:set var="nameContainer" scope="request" value="${'container1'}"/>
                <jsp:include page="../../manager/dynamicpage/containerSel.jsp"/>
                <!--# container1 end#-->
            </div>
            <%--Центральная страница--%>
            <div  id="content">
                <%--Ошибки системы--%>
                <jsp:include page="../../template/error.jsp"/>
                <!--#begin centerPage #-->
                <jsp:include page="../../manager/dynamicpage/centerPage.jsp"/>
                <!--# centerPage end#-->
                <!--#begin container2 #-->
                <c:set var="nameContainer" scope="request" value="${'container2'}"/>
                <jsp:include page="../../manager/dynamicpage/containerSel.jsp"/>
                <!--# container2 end#-->
            </div>
        </div>
    </div>
</div>