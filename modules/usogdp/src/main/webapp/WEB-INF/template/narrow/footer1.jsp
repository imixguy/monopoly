<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="date" class="java.util.Date"/>

<c:set var="er" value="<%=System.currentTimeMillis()%>"/>
<c:set var="timeRender" value="${er-ATTR_BEGIN_RENDER}"/>
<script>var timeRender = "${timeRender}"</script>
<div class="page">
<footer class="footer">
    <span>
        <a href="http://www.rw.by/corporate/structure/ktc/" target="_blank">КТЦ</a> |
        <a href="#f4">Контакты</a> |
        <%--<a href="#f6">Вход</a>--%>
        <span class="pull-right">
            <span >сгенерировано <fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH.mm.ss.SSS"/> за ${timeRender} мс</span>
        </span>
    </span>
</footer>
</div>