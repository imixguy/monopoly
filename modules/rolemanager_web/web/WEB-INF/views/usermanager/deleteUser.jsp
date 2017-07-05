<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div style="width: 90%; margin: 0 auto">
    <table id="userTable" class="cell-border"  width="100%" cellspacing="0" cellpadding="0"  align="center" >
        <caption>Список зарегестрированных пользователей</caption>
        <thead>
        <tr>
            <th>ФИО</th>
            <th>Удалить</th>
        </tr>
        </thead>
    </table>
</div>
<div>
    <button id="delete" style="margin:10px 0;">Удалить</button>
</div>
<div style="margin-top:15px">
    <a href="/admin/rolemanager/settings.html">Настройки</a>
</div>

<link rel="stylesheet" type="text/css" href="/resources/script/DataTables-1.10.2/media/css/jquery.dataTables.css">
<script type="application/javascript" src="/resources/script/DataTables-1.10.2/media/js/jquery.dataTables.js"></script>
<script type="application/javascript" src="/resources/script/adminsettings/usermanager/deleteUser.js"></script>

<script type="application/javascript">
    $(function(){
        start.init();
    });

</script>