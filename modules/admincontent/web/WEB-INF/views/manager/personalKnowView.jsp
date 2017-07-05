<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 09.09.2014
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/WEB-INF/views/manager/cssTable.jsp"/>
<div>

    <label> Имя: </label> ${pk.firstName}

</div>
<div>
    <label> Фамилия: ${pk.lastName} </label>
</div>
<div>
    <label> Профессия: ${pk.prof} </label>
</div>
<div>
    <label> Телефон: ${pk.tel} </label>
</div>
<div>
    <label> E-mail: ${pk.email}</label>
</div>

<div>
    <label> Дата рождения:  ${pk.dayBez}. ${pk.mounthBez}. ${pk.yerBez}</label>
</div>

<table class="rttable">
<tbody>
<tr>
    <th>Технология</th>
    <th>Лет работы</th>
</tr>
</tbody>
<tr>
    <td>Java</td>
    <td>${pk.java} </td>
</tr>
<tr>
    <td>Hibernate</td>
    <td>${pk.hib} </td>
</tr>
<tr>
    <td>Spring</td>
    <td>${pk.spring} </td>
</tr>
<tr>
    <td colspan="2"><label> Другие библиотеки java: </label><br>${pk.libjava} </td>
</tr>
<tr>
    <td>Maven</td>
    <td>${pk.maven} </td>
</tr>
<tr>
    <td>Ant</td>
    <td>${pk.ant} </td>
</tr>
<tr>
    <td colspan="2"><label> Другие сборщики java: </label><br>${pk.othersborsh} </td>
</tr>
<tr>
    <td>С#</td>
    <td>${pk.csh} </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки С#: </label><br>${pk.libcsh} </td>
</tr>
<tr>
    <td>С</td>
    <td>${pk.cpl} </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки С: </label><br>${pk.libcpl} </td>
</tr>
<tr>
    <td>VC</td>
    <td>${pk.vc} </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки VC: </label><br>${pk.libvc} </td>
</tr>
<tr>
    <td>Delphi</td>
    <td>${pk.delphi} </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки delphi: </label><br>${pk.libdelphi} </td>
</tr>
<tr>
    <td>Assembler</td>
    <td>${pk.assembler} </td>
</tr>
<tr>
    <td colspan="2"><label> дополнительные знания Assembler: <br>${pk.libassembler} </td>
</tr>
<tr>
    <td>PHP</td>
    <td>${pk.php} </td>
</tr>
<tr>
    <td colspan="2"><label> Библиотеки PHP: </label> <br>${pk.libphp}</td>
</tr>
<tr>
    <td>javascript</td>
    <td>${pk.javascript} </td>
</tr>
<tr>
    <td>jquery</td>
    <td>${pk.jquery} </td>
</tr>
<tr>
    <td>ajax</td>
    <td>${pk.ajax} </td>
</tr>
<tr>
    <td colspan="2"><label> Другие библиотеки JS: </label><br>${pk.libjs}</td>
</tr>


<tr>
    <td colspan="2"><label> Другие языки программирования: </label><br>${pk.otherLangPr}</td>
</tr>

<tr>
    <td>html</td>
    <td>${pk.html} </td>
</tr>
<tr>
    <td>xml</td>
    <td>${pk.xml} </td>
</tr>
<tr>
    <td>tomcat</td>
    <td>${pk.tomcat} </td>
</tr>
<tr>
    <td>jboss</td>
    <td>${pk.jboss} </td>
</tr>
<tr>
    <td>websphere</td>
    <td>${pk.websphere} </td>
</tr>
<tr>
    <td>resin</td>
    <td>${pk.resin} </td>
</tr>
<tr>
    <td colspan="2"><label> Другие ApplicationServers:</label><br>${pk.nameApplicationServer} </td>
</tr>
<tr>
    <td>MS SQL</td>
    <td>${pk.mssql} </td>
</tr>
<tr>
    <td>My SQL</td>
    <td>${pk.mysql} </td>
</tr>
<tr>
    <td>DB2</td>
    <td>${pk.db2sql} </td>
</tr>
<tr>
    <td>Oracle SQL</td>
    <td>${pk.oraclesql} </td>
</tr>
<tr>
    <td colspan="2"><label> Другие SQL: </label><br>${pk.otherSQL}</td>
</tr>
<tr>
    <td>Drupal</td>
    <td>${pk.drupal} </td>
</tr>
<tr>
    <td>WordPress</td>
    <td>${pk.wordPress} </td>
</tr>
<tr>
    <td>Joomla</td>
    <td>${pk.joomla} </td>
</tr>
<tr>
    <td>Opencms</td>
    <td>${pk.opencms} </td>
</tr>
<tr>
    <td>Magnolia</td>
    <td>${pk.magnolia} </td>
</tr>
<tr>
    <td>dotCMS</td>
    <td>${pk.dotCMS} </td>
</tr>
<tr>
    <td colspan="2"><label> Другие CMS:</label><br>${pk.otherCMS} </td>
</tr>
<tr>
    <td colspan="2"><label> IDE:</label> <br>${pk.ide} </td>
</tr>
<tr>
    <td>Photoshop</td>
    <td>${pk.photoshop} </td>
</tr>
<tr>
    <td>Corel Draw</td>
    <td>${pk.corelDraw}</td>
</tr>
<tr>
    <td>Autocad</td>
    <td>${pk.autocad}</td>
</tr>
<tr>
    <td>3dmax</td>
    <td>${pk.s3dmax}</td>
</tr>
<tr>
    <td>Primere Pro</td>
    <td>${pk.primerePro} </td>
</tr>
<tr>
    <td>Ms Office</td>
    <td>${pk.msOffice} </td>
</tr>
<tr>
    <td>Open Office</td>
    <td>${pk.openOffice} </td>
</tr>
<tr>
    <td colspan="2"><label> Другие программные пакеты: </label><br>${pk.otherProgramm}</td>
</tr>
<tr>
    <td colspan="2"><label> Дизайн: </label><br>${pk.design}</td>
</tr>
<tr>
    <td colspan="2"><label> Хобби и таланты:</label><br>${pk.hobbi} </td>
</tr>
<tr>
    <td colspan="2"><label> Спорт:</label><br>${pk.sport}</td>
</tr>
<tr>
    <td colspan="2"><label> Есть желание изучить:</label><br>${pk.desire}</td>
</tr>


</table>