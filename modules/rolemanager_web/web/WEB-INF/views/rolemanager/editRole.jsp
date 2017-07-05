<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div style="text-align: center; margin:20px 0"><h3>Изменение роли</h3></div>
<div id="editRole">
    Выбрать роль:
    <select id="listRoles">
        <option></option>
    </select>
</div>
<div>
    <div><label id="isRoleName" style="visibility: hidden; color:red;">Роль с таким именем уже существует</label></div>
    Имя роли: <input type="text" id="name"/>
</div>


<div>
    <ul id="credentials">

    </ul>
</div>

<div>
    <button id="edit">Изменить</button>
</div>
<div style="margin-top:15px">
    <a href="/admin/rolemanager/settings.html">Настройки</a>
</div>
<script type="application/javascript" src="/resources/script/adminsettings/rolemanager/editRole.js"></script>


<script type="application/javascript">
    start.init();
</script>
