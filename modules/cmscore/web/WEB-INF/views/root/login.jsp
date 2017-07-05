<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 16.12.13
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<p>
    Вы попали на эту страничку, так как вы не зарегестрированы или прав вашей учетной записи не достаточно для просмотра запрашиваемого содержимого.
    Пожалуйста введите логин и пароль с необходимыми правами.
</p>
<br><br>
<div align="center">
    <form action="j_spring_security_check" method="post" >
        <table border="0" cellspacing="5" cellpadding="0">
            <tr>
                <td colspan="2"><nobr><img src="/resources/images/users.png" width="25" height="25" title="Логин" style="vertical-align:middle"/>&nbsp;<input type="text" name="j_username" class="owntext" style="width:130px;"/></nobr></td>
            </tr>
            <tr>
                <td colspan="2"><nobr><img src="/resources/images/key_48.png" width="25" height="25" title="Пароль" style="vertical-align:middle"/>&nbsp;<input type="password" name="j_password" class="owntext" style="width:130px;"/></nobr></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td align="right" style="padding-right:10">
                    <input type="image" src="/resources/images/btn_signin_<spring:message code="local"/>.png" name="sub" alt="<spring:message code="programm.enter"/>"/>
                </td>
            </tr>
            <tr><td colspan="2" style="font-size:x-small;" align="center"><a href="/usermanager/createnewuser.html"><spring:message code="programm.usernewregistr"/></a></td></tr>
        </table>
    </form>
</div>