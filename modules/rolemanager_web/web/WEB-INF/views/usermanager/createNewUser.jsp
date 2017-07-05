<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 22.11.13
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="userForm" style="">
<div>
    Выбрать пользователя:<br>
    <select id="users" style="margin:7px"></select>

</div>

<form:form id="form" method="post" action="/admin/usermanager/createnewuser.html" modelAttribute="newUserForm">
    <form:hidden path="id" value="-1" id="id"/>
<table width="100%" cellspacing="0" cellpadding="0" border="0" class="rootFrame">
    <tr>
        <td width="5%">&nbsp;</td>
        <td width="30%"><spring:message code="loginform.authddata"/></td>
        <td width="30%"><spring:message code="loginform.persondata"/></td>
        <td width="5%">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="4">&nbsp;</td>
    </tr>
    <tr>
    <td>&nbsp;</td>
    <td style="vertical-align:top">
        <div><div><label id="isUserName" style="visibility: hidden; color:red;">Пользователь с таким логином уже существует</label></div><div><spring:message code="loginform.logins"/>*</div><div><form:input id="login" path="login"/></div><br></div>
        <div><div><spring:message code="loginform.pass"/>*</div><div><form:password id="password" path="password"/></div></div>
        <div><div><spring:message code="loginform.repeatpass1"/>*</div><div><form:password id="confirmPassword" path="confirmPassword"/></div></div>
    </td>
    <td style="vertical-align:top">
        <div><div><spring:message code="loginform.fname"/>*</div><div><form:input id="fname" path="fname"/></div></div>
        <div><div><spring:message code="loginform.lname"/>*</div><div><form:input id="lname" path="lname"/></div></div>
        <div><div><spring:message code="loginform.pname"/>*</div><div><form:input id="pname" path="pname"/></div></div>
        <div>&nbsp;</div>
        <div><div><spring:message code="loginform.phonework3"/>*</div><div><form:input id="workPhone" path="workPhone"/></div></div>
        <div><div><spring:message code="loginform.phonemob"/></div><div><form:input id="mobilePhone" path="mobilePhone"/></div></div>
        <div><div><spring:message code="loginform.email"/></div><div><form:input id="email" path="email"/></div></div>
    </td>
    <td>&nbsp;</td>
    </tr>
    <tr>
        <td colspan="4">&nbsp;</td>
    </tr>
    <tr>
        <td align="left" colspan="2"><spring:message code="registrationUser.rootText"/><br> <span style="color:red;"><spring:message code="loginform.infopasslog"/></span></td>
        <td><input type="submit" id="save" value="Cохранить" style="font-weight:bold; padding: 5px 20px;"></td>
        <td>&nbsp;</td>
    </tr>
</table>
</form:form>
</div>
<script src="/resources/script/adminsettings/usermanager/editUser.js"></script>
<script>
    $(function() {
        start.init();
    });
</script>