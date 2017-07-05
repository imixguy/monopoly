<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/WEB-INF/views/manager/cssTable.jsp"/>
<div id="notscript_allredirect" style="display: none; color: red;">
    Внимание!!! JQuery отключен, некоторые функции будут не доступны.
</div>
<div>
    <table class="rttable tableredirect">
        <thead>
        <tr>
            <th>URL</th>
            <th>№ узла динам. стр.</th>
            <th><input type='checkbox' class='chSelAll'/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allredirect}"  var="curentred">
            <tr><td><input class='idRed' type='hidden' value='<c:out value="${curentred.id}"/>'/><c:out value="${curentred.url}"/> </td><td> <c:out value="${curentred.id_nodeView}"/></td><td><input type='checkbox' class='chSel'/></td></tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div id="panSett">
    <input type='button' id='butAddNew' value='Добавить новый'/>
    <input type='button' id='butRemSel' value='Удалить отмеченные'/>
</div>
<script type="text/javascript">
    if(typeof jQuery === 'undefined'){
         document.getElementById('notscript_allredirect').style.display='';
    }else{
        $(function(){
            $('#butAddNew').click(function(){
                document.location.href="/managercms/redirectview/addredirectview.html";
            });
            $('.tableredirect tbody tr').dblclick(function(){
                var id=$(this).parents('tr').find('input.idRed').val();
                document.location.href="/managercms/redirectview/"+id+"/editredirectview.html";
            })
        })
    }
</script>