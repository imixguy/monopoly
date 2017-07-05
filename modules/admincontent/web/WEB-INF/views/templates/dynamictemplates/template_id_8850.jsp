<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 03.12.14
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
        <%--Если есть атрибут titlePage - то вставляется он иначе вставляется атрибут titleKey --%>
        <%--<c:set var="container" value="containers2"/>--%>
    </title>
    <!--#begin head #-->
    <c:set var="nameContainer" scope="request" value="${'head'}"/>
    <jsp:include page="../../manager/dynamicpage/containerSelHead.jsp"/>
    <!--# head end#-->
</head>
<body class="bodyCl">
    <!--#begin bodycontainer #-->
    <c:set var="nameContainer" scope="request" value="${'bodycontainer'}"/>
    <jsp:include page="../../manager/dynamicpage/containerSel.jsp"/>
    <!--# bodycontainer end#-->
</body>
</html>