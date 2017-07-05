<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 06.05.14
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
        <c:set var="titleKey"><tiles:getAsString name="titleKey"/></c:set>
        <spring:message code="${titleKey}" />
    </title>

</head>
<body>

<p>Вы попали на эту страницу, так как это первый запуск системы.</p>
<p>Для дальнейшего запуска нужно выполнить следующие настройки.</p>
<p>Создайте базу данных на любом SQL сервере</p>

<form action="settings.html" method="POST" >
    <p>Заполните параметры для доступа в Базу данных</p>
    <div><label><input type="radio" name="database"/>JNDI имя</label></div>
    <table>
        <tr>
            <td><label>JNDI: </label></td><td><input name="jndi" type="text" size="50"/></td><td><label>Пример (java:comp/env/jdbc)</label></td>
        </tr>
    </table>
    <div><label><input type="radio" name="database"/>Настройки базы данных</label></div>
    <table>
        <tr>
            <td><label>Драйвер </label></td><td><input name="drivername" type="text" size="50"/></td><td><label>Пример (com.mysql.jdbc.Driver)</label></td>
        </tr>
        <tr>
            <td><label>URL базы данных: </label></td><td><input name="urlBD" type="text" size="50"/></td><td><label>Пример (jdbc:mysql://localhost:3306/testbd?characterEncoding=UTF-8)</label></td>
        </tr>
        <tr>
            <td> <label>Пользователь: </label></td><td><input name="userBD" type="text" size="30"/></td><td><label>Пример (user)</label></td>
        </tr>
        <tr>
            <td><label>Пароль: </label></td><td><input name="passBD" type="password" size="30"/></td><td><label>Пример (password)</label></td>
        </tr>
    </table>
    <p>Укажите логин и пароль главного администратора системы</p>
    <table>
        <tr>
            <td> <label>Логин: </label></td><td><input name="loginAdm" type="text" size="30"/></td><td><label>Пример (user)</label></td>
        </tr>
        <tr>
            <td><label>Пароль: </label></td><td><input name="passAdm" type="password" size="30"/></td><td><label>Пример (password)</label></td>
        </tr>
    </table>
    <p>Укажите параметры hibernate</p>
    <table>
        <tr>
            <td> <label>Диалект: </label></td><td><input name="dialect" type="text" size="50"/></td><td><label>Пример (org.hibernate.dialect.MySQLDialect)</label></td>
        </tr>
    </table>
    <table>
        <tr>
            <td></td><td></td><td><input type="submit" value="Отправить"></td>
        </tr>
    </table>
</form>
</body>
</html>