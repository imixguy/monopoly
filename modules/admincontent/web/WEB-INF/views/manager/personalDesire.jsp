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
<form:form method="post" action="/user/content/savepersonaldesire.html" modelAttribute="personalDesire">
<div>
    <form:hidden class="" rows="5" cols="50" path="id"/>
    <label> Имя: <span class="form-required" title="Обязательное поле">*</span> </label>
    <form:input size="30" maxlength="27" path="firstName" />
</div>
<br/>
<div>
    <label> Фамилия: <form:input size="30" maxlength="27" path="lastName" /> </label>
</div>
<br/>
<div>
    <label> Профессия: <form:input size="30" maxlength="27" path="prof" /> </label>
</div>
<br/>
<div>
    <label> Телефон: <form:input size="30" maxlength="27" path="tel" /> </label>
</div>
<br/>
<div>
    <label> E-mail: <form:input size="30" maxlength="27" path="email" /> </label>
</div>
<br/>
<div>
    <label> Дата рождения:</label><form:input size="2" maxlength="2" path="dayBez" />.<form:input size="2" maxlength="2" path="mounthBez" />.<form:input size="4" maxlength="4" path="yerBez" /> (пример 01.01.2000)
</div>
<br/>
<table  class="rttable">
<tbody>
<tr>
    <td>Технология</td>
    <td>Лет работы</td>
    <td>Есть желание изучить</td>
</tr>
</tbody>
<tr>
    <td>Java</td>
    <td><form:input size="2" maxlength="27" path="java" /> </td>
    <td><form:checkbox path="javaCh" /> </td>
</tr>
<tr>
    <td>Hibernate</td>
    <td><form:input size="2" maxlength="27" path="hib" /> </td>
    <td><form:checkbox path="hibCh" /> </td>
</tr>
<tr>
    <td>Spring</td>
    <td><form:input size="2" maxlength="27" path="spring" /> </td>
    <td><form:checkbox path="springCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие библиотеки java: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libjava" /> </td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libjavaCh" /> </td>
</tr>
<tr>
    <td>Maven</td>
    <td><form:input size="2" maxlength="27" path="maven" /> </td>
    <td><form:checkbox path="mavenCh" /> </td>
</tr>
<tr>
    <td>Ant</td>
    <td><form:input size="2" maxlength="27" path="ant" /> </td>
    <td><form:checkbox path="antCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие сборщики java: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="othersborsh" /> </td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="othersborshCh" /> </td>
</tr>
<tr>
    <td>С#</td>
    <td><form:input size="2" maxlength="27" path="csh" /> </td>
    <td><form:checkbox path="cshCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки С#: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libcsh" /> </td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libcshCh" /> </td>
</tr>
<tr>
    <td>С</td>
    <td><form:input size="2" maxlength="27" path="cpl" /> </td>
    <td><form:checkbox path="cplCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки С: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libcpl" /> </td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libcplCh" /> </td>
</tr>
<tr>
    <td>VC</td>
    <td><form:input size="2" maxlength="27" path="vc" /> </td>
    <td><form:checkbox path="vcCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки VC: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libvc" /> </td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libvcCh" /> </td>
</tr>
<tr>
    <td>Delphi</td>
    <td><form:input size="2" maxlength="27" path="delphi" /> </td>
    <td><form:checkbox path="delphiCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки delphi: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libdelphi" /> </td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libdelphiCh" /> </td>
</tr>
<tr>
    <td>Assembler</td>
    <td><form:input size="2" maxlength="27" path="assembler" /> </td>
    <td><form:checkbox path="assemblerCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> дополнительные знания Assembler: <br><form:textarea id="contentpage" rows="5" cols="50" path="libassembler" /> </td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libassemblerCh" /> </td>
</tr>
<tr>
    <td>PHP</td>
    <td><form:input size="2" maxlength="27" path="php" /> </td>
    <td><form:checkbox path="phpCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки PHP: </label> <br><form:textarea id="contentpage" rows="5" cols="50" path="libphp" /></td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libphpCh" /> </td>
</tr>
<tr>
    <td>javascript</td>
    <td><form:input size="2" maxlength="27" path="javascript" /> </td>
    <td><form:checkbox path="javascriptCh" /> </td>
</tr>
<tr>
    <td>jquery</td>
    <td><form:input size="2" maxlength="27" path="jquery" /> </td>
    <td><form:checkbox path="jqueryCh" /> </td>
</tr>
<tr>
    <td>ajax</td>
    <td><form:input size="2" maxlength="27" path="ajax" /> </td>
    <td><form:checkbox path="ajaxCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие библиотеки JS: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libjs" /></td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="libjsCh" /> </td>
</tr>


<tr>
    <td colspan="2"><label> Другие языки программирования: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="otherLangPr" /></td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="otherLangPrCh" /> </td>
</tr>

<tr>
    <td>html</td>
    <td><form:input size="2" maxlength="27" path="html" /> </td>
    <td><form:checkbox path="htmlCh" /> </td>
</tr>
<tr>
    <td>xml</td>
    <td><form:input size="2" maxlength="27" path="xml" /> </td>
    <td><form:checkbox path="xmlCh" /> </td>
</tr>
<tr>
    <td>tomcat</td>
    <td><form:input size="2" maxlength="27" path="tomcat" /> </td>
    <td><form:checkbox path="tomcatCh" /> </td>
</tr>
<tr>
    <td>jboss</td>
    <td><form:input size="2" maxlength="27" path="jboss" /> </td>
    <td><form:checkbox path="jbossCh" /> </td>
</tr>
<tr>
    <td>websphere</td>
    <td><form:input size="2" maxlength="27" path="websphere" /> </td>
    <td><form:checkbox path="websphereCh" /> </td>
</tr>
<tr>
    <td>resin</td>
    <td><form:input size="2" maxlength="27" path="resin" /> </td>
    <td><form:checkbox path="resinCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие ApplicationServers:</label><br><form:textarea id="contentpage" rows="5" cols="50" path="nameApplicationServer" /> </td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="nameApplicationServerCh" /> </td>
</tr>
<tr>
    <td>MS SQL</td>
    <td><form:input size="2" maxlength="27" path="mssql" /> </td>
    <td><form:checkbox path="mssqlCh" /> </td>
</tr>
<tr>
    <td>My SQL</td>
    <td><form:input size="2" maxlength="27" path="mysql" /> </td>
    <td><form:checkbox path="mysqlCh" /> </td>
</tr>
<tr>
    <td>DB2</td>
    <td><form:input size="2" maxlength="27" path="db2sql" /> </td>
    <td><form:checkbox path="db2sqlCh" /> </td>
</tr>
<tr>
    <td>Oracle SQL</td>
    <td><form:input size="2" maxlength="27" path="oraclesql" /> </td>
    <td><form:checkbox path="oraclesqlCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие SQL: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="otherSQL" /></td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="otherSQLCh" /> </td>
</tr>
<tr>
    <td>Drupal</td>
    <td><form:input size="2" maxlength="27" path="drupal" /> </td>
    <td><form:checkbox path="drupalCh" /> </td>
</tr>
<tr>
    <td>WordPress</td>
    <td><form:input size="2" maxlength="27" path="wordPress" /> </td>
    <td><form:checkbox path="wordPressCh" /> </td>
</tr>
<tr>
    <td>Joomla</td>
    <td><form:input size="2" maxlength="27" path="joomla" /> </td>
    <td><form:checkbox path="joomlaCh" /> </td>
</tr>
<tr>
    <td>Opencms</td>
    <td><form:input size="2" maxlength="27" path="opencms" /> </td>
    <td><form:checkbox path="opencmsCh" /> </td>
</tr>
<tr>
    <td>Magnolia</td>
    <td><form:input size="2" maxlength="27" path="magnolia" /> </td>
    <td><form:checkbox path="magnoliaCh" /> </td>
</tr>
<tr>
    <td>dotCMS</td>
    <td><form:input size="2" maxlength="27" path="dotCMS" /> </td>
    <td><form:checkbox path="dotCMSCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие CMS:</label><br><form:textarea id="contentpage" rows="5" cols="50" path="otherCMS" /> </td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="otherCMSCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> IDE:</label> <br><form:textarea id="contentpage" rows="5" cols="50" path="ide" /> </td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="ideCh" /> </td>
</tr>
<tr>
    <td>Photoshop</td>
    <td><form:input size="2" maxlength="27" path="photoshop" /> </td>
    <td><form:checkbox path="photoshopCh" /> </td>
</tr>
<tr>
    <td>Corel Draw</td>
    <td><form:input size="2" maxlength="27" path="corelDraw" /> </td>
    <td><form:checkbox path="corelDrawCh" /> </td>
</tr>
<tr>
    <td>Autocad</td>
    <td><form:input size="2" maxlength="27" path="autocad" /> </td>
    <td><form:checkbox path="autocadCh" /> </td>
</tr>
<tr>
    <td>3dmax</td>
    <td><form:input size="2" maxlength="27" path="s3dmax" /> </td>
    <td><form:checkbox path="s3dmaxCh" /> </td>
</tr>
<tr>
    <td>Primere Pro</td>
    <td><form:input size="2" maxlength="27" path="primerePro" /> </td>
    <td><form:checkbox path="primereProCh" /> </td>
</tr>
<tr>
    <td>Ms Office</td>
    <td><form:input size="2" maxlength="27" path="msOffice" /> </td>
    <td><form:checkbox path="msOfficeCh" /> </td>
</tr>
<tr>
    <td>Open Office</td>
    <td><form:input size="2" maxlength="27" path="openOffice" /> </td>
    <td><form:checkbox path="openOfficeCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Другие программные пакеты: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="otherProgramm" /></td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="otherProgrammCh" /> </td>
</tr>
<tr>
    <td colspan="2"><label> Дизайн: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="design" /></td>
    <td colspan="1"><label> Хотелось бы еще узнать: </label><br><form:textarea id="contentpage" rows="5" cols="50" path="designCh" /> </td>
</tr>
<tr>
    <td colspan="3"><label> Хобби и таланты:</label><br><form:textarea id="contentpage" rows="5" cols="50" path="hobbi" /> </td>
</tr>
<tr>
    <td colspan="3"><label> Спорт:</label><br><form:textarea id="contentpage" rows="5" cols="50" path="sport" /> </td>
</tr>

<tr>
    <td colspan="3"><label> Есть желание изучить:</label><br><form:textarea id="contentpage" rows="5" cols="50" path="desire" /> </td>
</tr>
</table>

<div>
    <form:button name="Отправить" value="Отправить" >  Отправить </form:button>
</div>
</form:form>