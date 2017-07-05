<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="rw.gcktc.cms.usermanager.User" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page import="rw.gcktc.webcms.security.UserWeb" %>

<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 20.11.13
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<%--%>
<%--ConteinerForSession contFs=(ConteinerForSession)request.getSession().getAttribute("contS");--%>
<%--User us = (contFs!=null)?contFs.getUser():null;--%>
<%--if (us == null) {--%>
<%--%>--%>
<%--<form action="/usermanager/authentication.html" method="post" name="activationUserForm">--%>
<%--<table border="0" cellspacing="5" cellpadding="0">--%>
<%--<tr>--%>
<%--<td colspan="2"><nobr><img src="/resources/images/users.png" width="25" height="25" title="Логин" style="vertical-align:middle"/>&nbsp;<input type="text" name="login" class="owntext" style="width:130px;"/></nobr></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td colspan="2"><nobr><img src="/resources/images/key_48.png" width="25" height="25" title="Пароль" style="vertical-align:middle"/>&nbsp;<input type="password" name="password" class="owntext" style="width:130px;"/></nobr></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>&nbsp;</td>--%>
<%--<td align="right" style="padding-right:10">--%>
<%--<input type="image" src="/resources/images/btn_signin_<spring:message code="local"/>.png" name="sub" alt="<spring:message code="programm.enter"/>"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr><td colspan="2" style="font-size:x-small;" align="center"><a href="/usermanager/createnewuser.html"><spring:message code="programm.usernewregistr"/></a></td></tr>--%>
<%--</table>--%>
<%--</form>--%>
<%--<%}else{%>--%>
<%--<table border="0" cellspacing="5" cellpadding="0">--%>
<%--<tr>--%>
<%--<td width="37" height="36" rowspan="2"><img height=45 border=0 src="/resources/avataruser/<%=((ConteinerForSession)request.getSession().getAttribute("contS")).getAvatarPath()%>"/> </td>--%>
<%--<td width="119" style="font-size:x-small;text-align: center;">--%>
<%--<spring:message code="programm.hello"/>--%>
<%--&lt;%&ndash;<bean:message key="programm.hello"/>&ndash;%&gt;--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td style="font-size:x-small;text-align: center;"><%=us.getName()%></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>&nbsp;</td>--%>
<%--<td align="right" style="text-align: center;">--%>
<%--<a href="/usermanager/logout.html"><input type="image" alt="<spring:message code="programm.exit"/>" src="/resources/images/log_out_<spring:message code="local"/>.png"/></a>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr><td colspan="2" style="font-size:x-small;" align="center"><a href="/usermanager/gousercabinet.html"><spring:message code="programm.cabinetuser"/></a></td></tr>--%>
<%--<tr><td colspan="2" style="font-size:x-small;" align="center"></td></tr>--%>
<%--</table>--%>
<%--<%}%>--%>



<%
    boolean key=false;
    Authentication a= null;
    String userName="";
    try {
        a = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : a.getAuthorities()) {
            if(authority.getAuthority().equals("ROLE_AUTH_DATA")) {
                key=true;
                break;
            }
        }

        User us=((UserWeb)a.getPrincipal()).getUserw();
        userName+=" "+us.getPropertysValue(us,"fname").get(0).getValue()+" "+us.getPropertysValue(us,"lname").get(0).getValue()+" "+us.getPropertysValue(us,"pname").get(0).getValue();
//
//        for(NodeProperty pr:user.getNodeProperties()){
//            if(pr.getKeyt().equals("fname")){
//                userName+=" "+pr.getValue();
//            }
//            if(pr.getKeyt().equals("lname")){
//                userName+=" "+pr.getValue();
//            }
//            if(pr.getKeyt().equals("pname")){
//                userName+=" "+pr.getValue();
//            }
//        }
    } catch (Exception e) {

    }
    if (!key) {
%>
<form action="j_spring_security_check" method="post">
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
<%}else{%>
<table border="0" cellspacing="5" cellpadding="0">
    <tr>
        <td width="37" height="36" rowspan="2"><img height=45 border=0 src="/resources/images/users.png"/> </td>
        <td width="119" style="font-size:x-small;text-align: center;">
            <spring:message code="programm.hello"/>
            <%--<bean:message key="programm.hello"/>--%>
        </td>
    </tr>
    <tr>
        <td style="font-size:x-small;text-align: center;"><%=userName%></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td align="right" style="text-align: center;">
            <a href="j_spring_security_logout"><input type="image" alt="<spring:message code="programm.exit"/>" src="/resources/images/log_out_<spring:message code="local"/>.png"/></a>
            <%--/usermanager/logout.html--%>
        </td>
    </tr>
    <tr><td colspan="2" style="font-size:x-small;" align="center"><a href="/usermanager/gousercabinet.html"><spring:message code="programm.cabinetuser"/></a></td></tr>
    <tr><td colspan="2" style="font-size:x-small;" align="center"></td></tr>
</table>
<%}%>