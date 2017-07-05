<%--
  Created by IntelliJ IDEA.
  User: sedler
  Date: 17.11.14
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="width: 60%; margin: 0 auto">
    <table id="roleTable" class="cell-border"  width="100%" cellspacing="0" cellpadding="0"  align="center" >
        <caption>Список ролей</caption>
        <thead>
        <tr id="head">
        </tr>
        </thead>

    </table>
</div>
<div>
    <button id="delete" style="margin-left: 100px; margin-top: 15px;">Удалить</button>
</div>
<div style="margin-top:15px; margin-left: 100px; pargin-top:10px;">
    <a href="/admin/rolemanager/settings.html">Настройки</a>
</div>
<link rel="stylesheet" type="text/css" href="/resources/script/DataTables-1.10.2/media/css/jquery.dataTables.css">
<script type="application/javascript" src="/resources/script/DataTables-1.10.2/media/js/jquery.dataTables.js"></script>
<script type="application/javascript" src="/resources/script/adminsettings/rolemanager/deleteRole.js"></script>

<script type="application/javascript">
    $(function(){
        start.init();
    })
</script>