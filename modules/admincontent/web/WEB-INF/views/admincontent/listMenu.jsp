<%@ page import="rw.gcktc.cms.menu.Menu" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 10.12.13
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <p>
        Меню — это набор ссылок (пунктов меню), используемых для навигации по сайту. Меню, которые сейчас доступны для вашего сайта, показаны ниже. Выберите меню из этого списка для управления его содержимым.
    </p>
</div>
<div>
    <a href="/admin/menu/redactMenu.html">Добавить меню</a>
</div>
<br>
<div>
    Список меню
</div>
<div class="listMenu">
    <%
        List<Menu> lm= (List<Menu>) request.getAttribute("listMenuI");
        for(Menu menuIt:lm){
    %>
    <div>
        <a href="/admin/menu/redactMenu.html?menu=<%=menuIt.getId()%>"><h3 style="margin-bottom: 0;"><%=menuIt.getTitle()%></h3></a> (<a href="/admin/menu/removemenu.html?menu=<%=menuIt.getId()%>">удалить</a>)
        <p style="margin-left:40px;margin-top: 0;"><%=menuIt.getDescription()%></p><p>
    </div>
    <%}%>
    <%--<c:forEach var="menuIt" items="${listMenuI}">--%>
    <%--<div>--%>
    <%--<h3><c:out value="${menuIt.title}"/></h3>--%>
    <%--<p<c:out value=">${menuIt.content}"/><p>--%>
    <%--</div>--%>
    <%--</c:forEach>--%>
</div>
