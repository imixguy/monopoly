<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="date" class="java.util.Date"/>

<c:set var="er" value="<%=System.currentTimeMillis()%>"/>
<c:set var="timeRender" value="${er-ATTR_BEGIN_RENDER}"/>
<script>var timeRender = "${timeRender}"</script>
<footer class="ui-jqgrid-titlebar ui-widget-header ui-corner-top">
    <span>
        <a href="http://www.rw.by/corporate/structure/ktc/" target="_blank">КТЦ</a>
        <%--<a href="#f4">Контакты</a> |--%>
        <span class="pull-right">Copyright (c) 2014 Конструкторско-технический центр. Cгенерировано <fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH.mm.ss.SSS"/> за ${timeRender} мс</span>
    </span>
</footer>
