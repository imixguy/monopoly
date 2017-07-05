//-------------------вставка контейнеров с помощью кода-----------------------
<%@ page import="rw.gcktc.cms.material.dynamiccontent.Container" %>
<%@ page import="rw.gcktc.cms.material.dynamiccontent.DynamicContent" %>
<%
    Object oldContainer=null;
    DynamicContent dC=null;
%>
<!--#begin conteiner1 #-->
<%
    oldContainer=request.getAttribute("container");
    dC=(DynamicContent)request.getAttribute("page");
    request.setAttribute("container",dC.getContainerByName("spokBlock2"));
%>
<jsp:include page="../../manager/dynamicpage/containerSel.jsp"/>
<%
    request.setAttribute("container",oldContainer);
%>
<!--# conteiner1 end#-->

//--------------------вставка контейнеров с помощью jstl------------------------

<!--#begin conteiner1 #-->
<c:set var="oldContainer" scope="request" value="${container}"/>
<c:set var="container" scope="request" value="${page.containerByName('container1')}"/>
<jsp:include page="../../manager/dynamicpage/containerSel.jsp"/>
<c:set var="container" scope="request" value="${oldContainer}"/>
<!--# conteiner1 end#-->

//-------------------Вставка шаблона в первичный шаблон---------------------------------
<%
    Object oldPage=request.getAttribute("page");
    DynamicContent dC=((DynamicContent) oldPage).getDynamicContent(Long.parseLong(((Container)request.getAttribute("container")).getContent()));
    request.setAttribute("page",dC);
    String fileN="/WEB-INF/views/templates/dynamictemplates/template_id_" + dC.getShablonName()+".jsp";
%>
