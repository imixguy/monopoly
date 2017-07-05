<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%--<script type="text/javascript" src="<spring:url value="/webjars/jquery/2.1.1/jquery.min.js"/>"></script>--%>
    <%--<script type="text/javascript" src="<c:url value="/webjars/jquery/2.1.1/jquery.min.js" />"></script>--%>
        <%@ include file="/WEB-INF/template/narrow/inHeader1.jsp" %>
    <script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdpgraph.js"></script>
    <title>Станции моделирования</title>
</head>
<body>


<div class="lineMain">
    <div class="square page">
        <div id="t1">
            <table id="list13"></table>
        </div>


    </div>

</div>
</body>
</html>
