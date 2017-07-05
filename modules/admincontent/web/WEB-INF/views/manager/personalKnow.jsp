<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 09.09.2014
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/WEB-INF/views/manager/cssTable.jsp"/>
<form:form method="post" action="/user/content/savepersonalknow.html" modelAttribute="personalKnow">
<div>
    <form:hidden class="" rows="5" cols="79" path="id"/>
    <label> Имя: <span class="form-required" title="Обязательное поле">*</span> </label>
    <form:input size="30" maxlength="27" path="firstName" />
</div>
<div>
    <label> Фамилия: <form:input size="30" maxlength="27" path="lastName" /> </label>
</div>
<div>
    <label> Профессия: <form:input size="30" maxlength="27" path="prof" /> </label>
</div>
<div>
    <label> Телефон: <form:input size="30" maxlength="27" path="tel" /> </label>
</div>
<div>
    <label> E-mail: <form:input size="30" maxlength="27" path="email" /> </label>
</div>

<div>
    <label> Дата рождения:</label><form:input size="2" maxlength="2" path="dayBez" />.<form:input size="2" maxlength="2" path="mounthBez" />.<form:input size="4" maxlength="4" path="yerBez" /> (пример 01.01.2000)
</div>

<table  class="rttable">
<tbody>
<tr>
    <td>Технология</td>
    <td>Лет работы</td>
    <%--<td>Есть желание изучить</td>--%>
</tr>
</tbody>
<tr>
    <td>Java</td>
    <td><form:input size="2" maxlength="27" path="java" /> </td>
    <%--<td><form:checkbox path="javaCh" /> </td>--%>
</tr>
<tr>
    <td>Hibernate</td>
    <td><form:input size="2" maxlength="27" path="hib" /> </td>
    <%--<td><form:checkbox path="hibCh" /> </td>--%>
</tr>
<tr>
    <td>Spring</td>
    <td><form:input size="2" maxlength="27" path="spring" /> </td>
    <%--<td><form:checkbox path="springCh" /> </td>--%>
</tr>
<tr>
    <td colspan="2"><label> Другие библиотеки java: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="libjava" /> </td>
    <%--<td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="libjavaCh" /> </td>--%>
</tr>
<tr>
    <td>Maven</td>
    <td><form:input size="2" maxlength="27" path="maven" /> </td>
</tr>
<tr>
    <td>Ant</td>
    <td><form:input size="2" maxlength="27" path="ant" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие сборщики java: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="othersborsh" /> </td>
</tr>
<tr>
    <td>С#</td>
    <td><form:input size="2" maxlength="27" path="csh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки С#: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="libcsh" /> </td>
</tr>
<tr>
    <td>С</td>
    <td><form:input size="2" maxlength="27" path="cpl" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки С: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="libcpl" /> </td>
</tr>
<tr>
    <td>VC</td>
    <td><form:input size="2" maxlength="27" path="vc" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки VC: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="libvc" /> </td>
</tr>
<tr>
    <td>Delphi</td>
    <td><form:input size="2" maxlength="27" path="delphi" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки delphi: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="libdelphi" /> </td>
</tr>
<tr>
    <td>Assembler</td>
    <td><form:input size="2" maxlength="27" path="assembler" /> </td>
</tr>
<tr>
    <td colspan="2"><label> дополнительные знания Assembler: <br><form:textarea id="contentpage" rows="5" cols="79" path="libassembler" /> </td>
</tr>
<tr>
    <td>PHP</td>
    <td><form:input size="2" maxlength="27" path="php" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки PHP: </label> <br><form:textarea id="contentpage" rows="5" cols="79" path="libphp" /></td>
</tr>
<tr>
    <td>javascript</td>
    <td><form:input size="2" maxlength="27" path="javascript" /> </td>
</tr>
<tr>
    <td>jquery</td>
    <td><form:input size="2" maxlength="27" path="jquery" /> </td>
</tr>
<tr>
    <td>ajax</td>
    <td><form:input size="2" maxlength="27" path="ajax" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие библиотеки JS: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="libjs" /></td>
</tr>


<tr>
    <td colspan="2"><label> Другие языки программирования: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="otherLangPr" /></td>
</tr>

<tr>
    <td>html</td>
    <td><form:input size="2" maxlength="27" path="html" /> </td>
</tr>
<tr>
    <td>xml</td>
    <td><form:input size="2" maxlength="27" path="xml" /> </td>
</tr>
<tr>
    <td>tomcat</td>
    <td><form:input size="2" maxlength="27" path="tomcat" /> </td>
</tr>
<tr>
    <td>jboss</td>
    <td><form:input size="2" maxlength="27" path="jboss" /> </td>
</tr>
<tr>
    <td>websphere</td>
    <td><form:input size="2" maxlength="27" path="websphere" /> </td>
</tr>
<tr>
    <td>resin</td>
    <td><form:input size="2" maxlength="27" path="resin" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие ApplicationServers:</label><br><form:textarea id="contentpage" rows="5" cols="79" path="nameApplicationServer" /> </td>
</tr>
<tr>
    <td>MS SQL</td>
    <td><form:input size="2" maxlength="27" path="mssql" /> </td>
</tr>
<tr>
    <td>My SQL</td>
    <td><form:input size="2" maxlength="27" path="mysql" /> </td>
</tr>
<tr>
    <td>DB2</td>
    <td><form:input size="2" maxlength="27" path="db2sql" /> </td>
</tr>
<tr>
    <td>Oracle SQL</td>
    <td><form:input size="2" maxlength="27" path="oraclesql" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие SQL: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="otherSQL" /></td>
</tr>
<tr>
    <td>Drupal</td>
    <td><form:input size="2" maxlength="27" path="drupal" /> </td>
</tr>
<tr>
    <td>WordPress</td>
    <td><form:input size="2" maxlength="27" path="wordPress" /> </td>
</tr>
<tr>
    <td>Joomla</td>
    <td><form:input size="2" maxlength="27" path="joomla" /> </td>
</tr>
<tr>
    <td>Opencms</td>
    <td><form:input size="2" maxlength="27" path="opencms" /> </td>
</tr>
<tr>
    <td>Magnolia</td>
    <td><form:input size="2" maxlength="27" path="magnolia" /> </td>
</tr>
<tr>
    <td>dotCMS</td>
    <td><form:input size="2" maxlength="27" path="dotCMS" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие CMS:</label><br><form:textarea id="contentpage" rows="5" cols="79" path="otherCMS" /> </td>
</tr>
<tr>
    <td colspan="2"><label> IDE:</label> <br><form:textarea id="contentpage" rows="5" cols="79" path="ide" /> </td>
</tr>
<tr>
    <td>Photoshop</td>
    <td><form:input size="2" maxlength="27" path="photoshop" /> </td>
</tr>
<tr>
    <td>Corel Draw</td>
    <td><form:input size="2" maxlength="27" path="corelDraw" /> </td>
</tr>
<tr>
    <td>Autocad</td>
    <td><form:input size="2" maxlength="27" path="autocad" /> </td>
</tr>
<tr>
    <td>3dmax</td>
    <td><form:input size="2" maxlength="27" path="s3dmax" /> </td>
</tr>
<tr>
    <td>Primere Pro</td>
    <td><form:input size="2" maxlength="27" path="primerePro" /> </td>
</tr>
<tr>
    <td>Ms Office</td>
    <td><form:input size="2" maxlength="27" path="msOffice" /> </td>
</tr>
<tr>
    <td>Open Office</td>
    <td><form:input size="2" maxlength="27" path="openOffice" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие программные пакеты: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="otherProgramm" /></td>
</tr>
<tr>
    <td colspan="2"><label> Дизайн: </label><br><form:textarea id="contentpage" rows="5" cols="79" path="design" /></td>
</tr>
<tr>
    <td colspan="2"><label> Хобби и таланты:</label><br><form:textarea id="contentpage" rows="5" cols="79" path="hobbi" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Спорт:</label><br><form:textarea id="contentpage" rows="5" cols="79" path="sport" /> </td>
</tr>

<tr>
    <td colspan="2"><label> Есть желание изучить:</label><br><form:textarea id="contentpage" rows="5" cols="79" path="desire" /> </td>
</tr>
</table>

<div>
    <form:button name="Отправить" value="Отправить" >  Отправить </form:button>
</div>
</form:form>