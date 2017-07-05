<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<form:form method="post" action="/managercms/dynamiccontent/savedtemplate.html" modelAttribute="template">
    <div>
        <form:hidden class="" rows="5" cols="42" path="id"/>
        <label>
            Название:
            <span class="form-required" title="Обязательное поле">*</span>
        </label>
        <form:input size="60" maxlength="27" path="name" />
    </div>
    <div>
        <label>
            Содержимое шаблона:
        </label>
        <form:textarea path="file" cssClass="textEdit"/>
    </div>
    <div>
        <label>
            Тип шаблона
        </label>
        <form:select path="typeTemplate">
            <option value="page">page</option>
            <option value="head">head</option>
            <option value="container">container</option>
        </form:select>
    </div>
    <div id="lconteiners">

    </div>
    <div>
        <form:button name="Отправить" value="Отправить" >  Отправить </form:button>
    </div>
</form:form>

