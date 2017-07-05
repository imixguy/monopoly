<%--
  Created by IntelliJ IDEA.
  User: sedler
  Date: 10.10.14
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
    .settNav{
        width:100%;
        padding-left:10px;
        padding-top: 15px;
        padding-bottom: 15px;
    }

    .settNavColumn{
        margin:0;
        padding:0;
        background: -webkit-linear-gradient(top,#AFB0B2,#AFB0B2 60%);
        width:50%;
        border:solid 2px #737374;
        border-radius: 5px;
        box-shadow: inset 0 0 5px #737374;
    }

    .settNavColumn h2{
        background: -webkit-linear-gradient(top, #e2e3e5, #7b7c7e 60%);
        margin:0;
        padding:0;
        color:#ffffff;
        text-align: center;
    }

    .mainMeny{
        list-style: none;
        border-bottom: 1px groove #838384;
        margin: 15px 10px;
        padding: 0;

    }

    .settUl{
        background-color: #a5a5a7;
        border: 2px groove #808090;
        border-radius: 7px;
        box-shadow: inset 0 0 15px #737374;
        width: 80%;
        margin: 15px auto;
    }

    .mainMeny li{
        border: 2px groove #8e8e8f;
        border-bottom: none;
    }

    ul a{
        padding: 2px 5px;
        display: block;
        text-decoration: none;
        color:#ffffff;
    }

    #menu1, #menu2{
        display: none;
    }
    .subMenu1, .subMenu2{

        margin:0;
        padding: 0;
        list-style: none;

    }

    .subMenu1 li, .subMenu2 li{
        border:none;
        border-bottom: 2px groove #8e8e8f;;
    }

    .subMenu1 li:last-of-type, .subMenu2 li:last-of-type{
        border-bottom: none;
    }

    .subMenu1 a, .subMenu2 a{
        padding-left:30px;
    }

    a:hover{
        box-shadow: inset 0 0 15px #636364;
        background-color: #959597;
    }
</style>
<security:authorize access="hasRole('ROLE_CREATE_ROLE')">
<div class="settNav">
    <div class="settNavColumn">
        <h2>Навигация</h2>
        <div class="settUl">
            <ul class="mainMeny">
                <li><a href="" id="user">Управление пользователями</a></li>
                <li id="menu1">
                    <ul class="subMenu1">
                        <li><a href="/admin/usermanager/listusers.html">Список пользователей</a></li>
                        <li><a href="/admin/usermanager/createnewuser.html">Создать/Редактировать пользователя</a></li>
                        <%--<li><a href="/admin/usermanager/edituser.html">Редактировать пользователя</a></li>--%>
                        <security:authorize access="hasRole('ROLE_CRUD_ROLEMANAGER')">
                            <li><a href="/admin/usermanager/deleteuser.html">Удалить пользователя</a></li>
                        </security:authorize>
                        <li><a href="/admin/usermanager/user-role.html">Назначение ролей</a></li>
                    </ul>
                </li >
                <li><a href="" id="role">Управление ролями</a></li>
                <li id="menu2">
                    <ul class="subMenu2">
                        <li><a href="/admin/rolemanager/credentialManager.html">Управление доступами ролей</a></li>
                        <li><a href="/admin/rolemanager/createRole.html">Создать/Редактировать роль</a></li>
                        <%--<li><a href="/admin/rolemanager/editRole.html">Изменить роль</a></li>--%>
                        <security:authorize access="hasRole('ROLE_CRUD_ROLEMANAGER')">
                            <li><a href="/admin/rolemanager/deleterole.html">Удалить роль</a></li>
                        </security:authorize>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
</security:authorize>
<script type="text/javascript">
    $(document).ready(function(){
        $('#user').click(function(){
            if($('#menu1').css("display")=='none'){
                $('#menu1').toggle('normal');
                $('#menu2').hide('normal');
            }else{
                $('#menu1').toggle('normal');
            }
            return false;
        });
        $('#role').click(function(){
            if($('#menu2').css("display")=='none'){
                $('#menu2').toggle('normal');
                $('#menu1').hide('normal');
            }else{
                $('#menu2').toggle('normal');
            }
            return false;
        });
    });
</script>