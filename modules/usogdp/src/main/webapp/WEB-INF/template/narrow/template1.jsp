<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
    <%--<tiles:insertAttribute name="inHeader" />--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%--<meta http-equiv="X-UA-Compatible" content="IE=9,10,11">--%>
    <title><tiles:getAsString name="titleKey"/></title>
    <%@ include file="inHeader1.jsp" %>
    <link href="${ctx}/resources/css/template1.css" rel="stylesheet">
</head>
<body>
<%@ include file="header1.jsp" %>
<div id="main">
    <div id="content" class="page">
        <div class="square">
            <tiles:insertAttribute name="content"/>
        </div>
    </div>
</div>
<%@ include file="footer1.jsp" %>
<%--<script>--%>
<%--$(document).ready(function () {--%>
<%--console.log("тут вызов вашего JS при загрузке странице")--%>
<%--});--%>
<%--</script>--%>

</body>

</html>
