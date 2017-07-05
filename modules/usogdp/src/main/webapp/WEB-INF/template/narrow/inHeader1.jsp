<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="rw.ktc.ksupr.web.app.init.Const" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- use it for correct load static resourses -->
<c:set var="ctx" scope="request" value="${pageContext.request.contextPath}"/>
 <%--for client side , ex. ajax in javaScript--%>
<script>var ctx = "${pageContext.request.contextPath}"</script>

<%--<link rel="stylesheet" type="text/css" href="<c:url value="<%=Const.JQUERY_UI_CSS%>"/>"/>--%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<c:url value="<%=Const.JQUERY_GRID_CSS%>"/>"/>

<script type="text/javascript" src="<c:url value="<%=Const.JQUERY_JS%>" />"></script>
<script type="text/javascript" src="<c:url value="<%=Const.JQUERY__GRID_LOCATION_JS%>" />"></script>
<script type="text/javascript" src="<c:url value="<%=Const.JQUERY__GRID_JS%>" />"></script>
<script type="text/javascript" src="<c:url value="<%=Const.SNAP_JS%>" />"></script>
<%--<script type="text/javascript" src="<c:url value="<%=Const.JQUERY_PLOT_JS%>" />"></script>--%>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdp.js"></script>
