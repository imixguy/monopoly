<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>


<%--Ошибки системы--%>
<jsp:include page="../../template/error.jsp"/>
<div>
    <!--#begin spokBlock2 #-->
    <c:set var="nameContainer" scope="request" value="${'spokBlock2'}"/>
    <jsp:include page="../../manager/dynamicpage/containerSel.jsp"/>
    <!--# spokBlock2 end#-->
</div>
<div>
    <!--#begin spokBlock #-->
    <c:set var="nameContainer" scope="request" value="${'spokBlock'}"/>
    <jsp:include page="../../manager/dynamicpage/containerSel.jsp"/>
    <!--# spokBlock end#-->
</div>
