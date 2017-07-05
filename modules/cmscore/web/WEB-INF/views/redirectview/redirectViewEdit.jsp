<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<form:form method="POST" action="/managercms/redirectview/saveredirectview.html" modelAttribute="redirectView">
    <h2>Форма перенаправления адресов</h2>
    <br>
    <div>
        <label>
            URL адрес перенаправления на центральную форму
        </label><br>
        <form:input path="url" size="60"/>
    </div>
    <div>
        <label>
            Узел динамической страницы на которую будет перенаправлен запрос
        </label> <br>
        <form:input size="5" maxlength="10" path="id_nodeView" />
    </div>
    <br>
    <div>
        <form:button name="Отправить" value="Отправить" >  Отправить </form:button>
    </div>
</form:form>
<br>