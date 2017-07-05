<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 10.12.13
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<form:form method="post" action="/admin/menu/redactMenu.html" modelAttribute="menuForm">
    <form:hidden path="id" dir="auto" />
    <div>
        <label>
            Имя меню:
            <span class="form-required" title="Обязательное поле">*</span>
        </label>
        <form:input size="60" maxlength="27" path="title" />
    </div>
    <div>
        <label>
            Описание:
        </label>
        <form:textarea class="" rows="5" cols="42" path="description"/>
    </div>
    <div>
        <form:button name="Отправить" value="Отправить" >  Отправить </form:button>
    </div>
</form:form>