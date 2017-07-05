<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
</style>
<div style="width: 80%; margin: 0 auto">
    <table id="userTable" class="cell-border"  width="100%" cellspacing="0" cellpadding="0"  align="center" >
        <caption>Список пользователей</caption>
        <thead>
        <tr id="head">
        </tr>
        </thead>
        <tbody id="tableBody">
        </tbody>
    </table>
</div>
<div style="margin-top:15px">
    <a href="/admin/rolemanager/settings.html">Настройки</a>
</div>
<link rel="stylesheet" type="text/css" href="/resources/script/DataTables-1.10.2/media/css/jquery.dataTables.css">
<script type="application/javascript" src="/resources/script/DataTables-1.10.2/media/js/jquery.dataTables.js"></script>
<script type="application/javascript" src="/resources/script/adminsettings/usermanager/userRole.js"></script>


<script type="application/javascript">
    start.init();
</script>