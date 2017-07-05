<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<form:form method="post" action="/admin/content/savepage.html" modelAttribute="page">
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
            Содержимое:
        </label>
        <form:textarea id="contentpage" rows="5" cols="42" path="content" cssClass="textEdit"/>
    </div>
    <div>
        <label>
            Размещать на главной:
        </label>
        <form:checkbox class="" path="pastRoot"/>
    </div>
    <div>
        <form:button name="Отправить" value="Отправить" >  Отправить </form:button>
    </div>
</form:form>