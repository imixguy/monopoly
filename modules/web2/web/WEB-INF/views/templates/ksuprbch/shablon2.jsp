<%@ page import="org.springframework.context.i18n.LocaleContextHolder" %>
<%@ page import="rw.gcktc.webcms.form.NodeView" %>
<%@ page import="java.util.Locale" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 28.01.13
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="http://struts.apache.org/tags-titles" prefix="titles"%>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>
        <%--Если есть атрибут titlePage - то вставляется он иначе вставляется атрибут titleKey --%>
        <c:choose>
            <c:when test="${titlePage!=null}">
                ${titlePage}
            </c:when>
            <c:otherwise>
                <c:set var="titleKey"><tiles:getAsString name="titleKey"/></c:set>
                <spring:message code="${titleKey}" />
            </c:otherwise>
        </c:choose>
    </title>
    <jsp:include page="../../templates/ksuprbch/script.jsp"/>
</head>
<body marginwidth="0" marginheight="0"  leftmargin="0" topmargin="0" class="bodyCl">
<div>
    <tiles:insertAttribute name="header" />
</div>
<div>
    <div>
        <%--Блоки входа пользователя и другие--%>
        <c:set var="nameBlock"><spring:message code="programm.userauth" /></c:set>
        <%
            Locale l=LocaleContextHolder.getLocale();
            NodeView n = new NodeView();
            n.setTitles("authblock.jsp");
            n.setName((String) pageContext.getAttribute("nameBlock"));
            request.setAttribute("CURENT_NODEA", n);
        %>
        <jsp:include page="../../template/blockview.jsp"/>
    </div>
    <div>
        <%--Ошибки системы--%>
        <jsp:include page="../../template/error.jsp"/>
        <%--Центральная страница--%>
        <tiles:insertAttribute name="content" />
    </div>
</div>
<div>
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>