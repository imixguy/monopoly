<%--
  Created by IntelliJ IDEA.
  User: sedler
  Date: 20.12.14
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--элемент загрузки данных--%>
<div class="loading">
    <img id="loader" src="${ctx}/resources/img/loading.gif">
</div>

<style>
    #error{
        display: none;
        position: absolute;
        width :100%;
        background-color: rgba(95, 95, 96, 0.24);
        top:0;
        left:0;
        height: 100%;
        z-index: 2;
    }
</style>
<div id="error">

</div>

<header>
    <%--<div class="whiteblock page">--%>
            <%--<span class="pull-right">--%>
                <%--<a href="javascript: void(0)">Добро пожаловать</a>--%>
                <%--|--%>
                <%--<a href="javascript: void(0)" >Руководство пользователя</a>--%>
            <%--</span>--%>
        <%--</span>--%>
    <%--</div>--%>
   <div style="float: right">
       <a href="${ctx}/emptyvagons" target="_blank">Регулировка порожних вагонов</a>&nbsp;&nbsp;
       <a href="${ctx}/districts" target="_blank">Показать график</a>
   </div>
</header>

