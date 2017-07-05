<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form:form method="post" id="form" action="/admin/rolemanager/createRole" modelAttribute="newRoleForm">
    <style>
        ul{
            margin:10px 0;
            padding: 0;
            list-style: none;
        }

        li{
            margin:8px 30px;
        }
    </style>

    <div style="text-align: center; margin:20px 0"><h3>Создание роли</h3></div>
    <div>
        <div><form:hidden path="id" id="id" value="-1"/></div>
        <div>Выберите роль:</div>
        <select id="roles"></select>
    </div>
    <div>
        <div><label id="isRoleName" style="visibility: hidden; color:red;">Роль с таким именем уже существует</label></div>
        Имя роли: <form:input id="name" path="name"/>
    </div>
    <div style="padding-top:20px;">
        Допуски:
    </div>
    <div>
        <ul>
        <form:checkboxes element="li" items="${credentials}" path="permissions" id="check_"/>
        </ul>
    </div>
</form:form>
<div><button id="save">Сохранить</button></div>
<div style="margin-top:15px">
    <a href="/admin/rolemanager/settings.html">Настройки</a>
</div>

<script src="/resources/script/adminsettings/rolemanager/editRole1.js"></script>

<script>
    start.init();
</script>