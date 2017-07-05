<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="rw.gcktc.webcms.form.NodeView" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--Блоки входа пользователя и другие--%>
<c:set var="nameBlock"><spring:message code="programm.userauth" /></c:set>
<%
    NodeView n = new NodeView();
    n.setTitles("authblock.jsp");
    n.setName((String) pageContext.getAttribute("nameBlock"));
    request.setAttribute("CURENT_NODEA", n);
    NodeView curN = (NodeView) request.getAttribute("CURENT_NODEA");
%>


<table width="200px" cellspacing="0" cellpadding="0" border="0" >
    <tbody><tr>
        <td align="center" style="border:solid 1px #003988;">
            <%if(curN.getName()!=null){%>
            <div align=center class=menu_right_razdel style='background-color:#003988;color:white;'>
                <div style="font-size:5px">&nbsp;</div>
                <strong class=menu_razdel ><%=curN.getName()%></strong>
                <div style="font-size:5px">&nbsp;</div>
            </div>
            <%}%>
            <%if(curN.getTitles()!=null){%>
                <jsp:include page="<%=curN.getTitles()%>"/>
            <%}else{%>
            <div align=center class=menu_right>
                <div align=center style="width:100%">
                    <strong class=menu_razdel><%=curN.getContent()%></strong>
                </div>
            </div>
            <%}%>
        </td>
    </tr>
    </tbody>
</table>
<div style="font-size:8px" class="shadowblock">&nbsp;</div>