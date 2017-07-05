<%@ page import="rw.gcktc.cms.usermanager.User" %>
<%@ page import="rw.gcktc.webcms.form.ConteinerForSession" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page import="rw.gcktc.webcms.security.UserWeb" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 25.11.13
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="centerLayout">

    <style type="text/css">
        .dataCentr{
            padding-top:30px;
        }
        .imageBlock{
            text-align:center
        }
        .dataBlock{
            text-align:left;

        }
        .dataBlock .body{
            padding-left:10px;
        }

        .dataBlock .hederData {
            border-top:1px solid #B1BDD6;
            background:#DAE2E8;
            padding-left:25px
        }
        .dataBlock .flexHeader h2 {
            color:#45688E;
            display:inline;
        }
        h2 {
            font-size:13px;
            margin:0;
            padding:0;
        }
        .dataBlock .bopen .flexHeader {
            /*background-image:url(images/flex_arrow_open.gif);*/
            background-position:left center;
            background-repeat:no-repeat;
            border-top:1px solid #B1BDD6;
        }

        .formRegistr{
            text-align:center;font-family:Arial, Helvetica, sans-serif; font-size:15px; font-weight:bold; color:#903;
        }

        .rootFrame {
            text-align: center;
        }
        .rootFrame tr td {
            font-weight: bold;
        }

        .buttSave{
            cursor:pointer;
        }


    </style>

    <%--<%--%>
        <%--Authentication a= null;--%>
        <%--String userName="";--%>
        <%--User us=null;--%>
        <%--try {--%>
            <%--a = SecurityContextHolder.getContext().getAuthentication();--%>
            <%--us=((UserWeb)a.getPrincipal()).getUserw();--%>
            <%--userName+=" "+us.getPropertysValue(us,"fname").get(0).getValue()+" "+us.getPropertysValue(us,"lname").get(0).getValue()+" "+us.getPropertysValue(us,"pname").get(0).getValue();--%>
        <%--} catch (Exception e) {--%>

        <%--}--%>
    <%--%>--%>

    <div id="userForm" style="">
        <table width="100%" cellspacing="0" cellpadding="0" border="0" class="rootFrame">
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td width="10%">&nbsp;</td>
                <td width="40%">
                    <%--<div class="imageBlock"><img class="imageUserb" width="180" src="/resources/avataruser/<%=((ConteinerForSession)request.getSession().getAttribute("contS")).getAvatarPath()%>"></div>--%>
                    <div class="imageBlock"><img class="imageUserb" width="180" src="/resources/avataruser/${avatarPath}"></div>
                    <div><div>&nbsp;</div><div class="buttSave" id="butChIm">Изменить изображение</div><div id="butChPass" class="buttSave">Изменить логин или пароль</div></div>
                </td>
                <td width="40%"><div class="dataBlock"><div class="bopen"><div class="hederData flexHeader">
                    <table width="100%" cellspacing="0" cellpadding="0" border="0">
                        <tr>
                            <td><h2>Основные данные</h2></td>
                            <td style="text-align:right;"><span style="padding-right:20px" class="buttSave">изменить</span></td>
                        </tr>
                    </table>
                </div>
                </div>
                    <%--<div>&nbsp;</div><div class="body"><div><%=userName%></div><div>(должность)</div><%= us.getPropertysValue(us,"wphone").get(0).getValue()%><div><%= us.getPropertysValue(us,"mphone").get(0).getValue()%></div><div><%= us.getPropertysValue(us,"email").get(0).getValue()%></div><div></div></div>--%>
                    <div>&nbsp;</div><div class="body"><div>${user.getPropertysValue(user, "fname").get(0).getValue()}  ${user.getPropertysValue(user, "lname").get(0).getValue()}  ${user.getPropertysValue(user, "pname").get(0).getValue()}</div><div>${user.getPropertysValue(user, "wphone").get(0).getValue()}</div><div>${user.getPropertysValue(user, "mphone").get(0).getValue()}</div><div>${user.getPropertysValue(user, "email").get(0).getValue()}</div><div></div></div>
                    <div>&nbsp;</div>
                    <div class="bopen"><div class="hederData flexHeader">
                        <table width="100%" cellspacing="0" cellpadding="0" border="0"><tr><td><h2>Программные данные</h2></td><td style="text-align:right;"></td></tr></table>
                    </div></div>
                    <div>&nbsp;</div>
                    <div><div class="body"><div>пользователь АСУ РБ</div><div>программист</div><div style="display: none;">неопределено</div><div>Управление</div>
                        <div style="display: none;">неопределено</div><div>Администратор</div></div></div></div>
                </td>
                <td width="10%">&nbsp;</td>
            </tr>
        </table>
    </div>
</div>

<div id="userImage" style="">
    <div style="padding-top:70px;" class="rootFrame formRegistr">
        <table width="180" height="290" border="0" align="center">
            <tr>
                <td>
                    <%--<div><img id="imageUser" class="imageUserb" width="180" src="/resources/avataruser/<%=((ConteinerForSession)request.getSession().getAttribute("contS")).getAvatarPath()%>"></div>--%>
                    <div><img id="imageUser" class="imageUserb" width="180" src="/resources/avataruser/${avatarPath}"></div>
                </td>
            </tr>
        </table>
        <div style="padding-top:70px;">
            <iframe style="width:0px; height:0px; border:0px" name="hidenimagefr"></iframe>
            <div id="placeForForm">
                <form:form method="POST" action="/usermanager/saveimage.html" enctype="multipart/form-data" id="formIm">
                    <input type="file" name="userImage" class="formRegistr"><div style="padding-top:70px;">
                    <span class="buttSave" id="butOkim"><spring:message code="loginform.save2"/></span><span style="padding-left:60px;" class="buttSave" id="butCancim"><spring:message code="loginform.cancal"/></span>
                </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div id="userFormChPass" style="">
    <div style="padding-top:70px;" class="rootFrame formRegistr">
        <div><div>ЛОГИН</div><div><input type="text" style="text-align:left;" class="formRegistr"></div></div>
        <div><div>СТАРЫЙ ПАРОЛЬ</div><div><input type="password" style="text-align:left;" class="formRegistr"></div></div>
        <div><div>НОВЫЙ ПАРОЛЬ</div><div><input type="password" style="text-align:left;" class="formRegistr"></div></div>
        <div><div>ПОВТОР НОВОГО ПАРОЛЯ</div><div><input type="password" style="text-align:left;" class="formRegistr"></div></div>
        <div>&nbsp;</div><div><span class="buttSave">ОТМЕНА</span><span style="padding-left:60px;" class="buttSave">COХРАНИТЬ</span></div>
    </div>
</div>

<script type="text/javascript">
    var usF;
    $(document).ready(function(){
        $('#userImage').hide();
        $('#userFormChPass').hide();
        $('#butChIm').click(function(){$('#userForm').hide();$('#userImage').show()});
        $('#butChPass').click(function(){$('#userForm').hide();$('#userFormChPass').show()});
        usF={
            options: {
                oknoInfo: $('#placeForForm').html()
            },
            imageLoad:function(key,fileName){
                if(!key){alert('Ошибка записи изображения');return;}
                $('#placeForForm').append(this.options.oknoInfo);
                this.observeEvent();
                $('.imageUserb').attr('src','/resources/avataruser/'+fileName+"?"+Math.ceil(Math.random()*1000));
                this.hideSaveIm();
            },
            observeEvent:function(){
                $('#butOkim').click(function(){
                    var t=window.frames["hidenimagefr"];
                    t=t.document;
                    var ok=$('#formIm');
                    //t.getElementsByTagName('head')[0].innerHTML='<script src="script/prototype.js" type="text/javascript"><'+'/script>'
                    var fd=t.getElementsByTagName('body')[0];
                    $(fd).append(ok);
                    ok.submit();});
                var thisEl=this;
                $('#butCancim').click(function(){thisEl.hideSaveIm();});
            },
            hideSaveIm:function(){
                $('#userImage').hide(); $('#userForm').show()
            }
        };
        usF.observeEvent();
    });
</script>
