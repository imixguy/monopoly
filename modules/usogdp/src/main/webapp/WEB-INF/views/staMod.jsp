<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/simStations.js?v2"></script>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/error.js"></script>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdp.js?"></script>
<style>
    /*форматирование контейнера таблиц*/
    .tbFormat{
        overflow:auto;
        border: 1px solid #aaaaaa;
        border-top: none;
    }
    /*заголовок таблиц*/
    #caption{
        text-align: center;
    }

    /*форматирование заголовка, контейнера таблиц, хидера и футера*/
    #caption, .tbFormat, header, footer{
        box-sizing: border-box;
        -moz-box-sizing: border-box;
    }
</style>

<div id="information" >
    <%--вывод строки прогноза--%>
    <div  class="forecast_style">
        <Label id="forecast" class="color"></Label>&nbsp;&nbsp;<Label id="selectForecast" class="color" style="display: none">${forecastId}</Label>&nbsp;&nbsp;<button class="" id="newData">Обновить данные</button><br>
    </div>
    <%--вывод селекта со стнанциями и кнопки состава и расписания--%>
    <div>
        <span style="margin-left: 50px">Выбор станции:</span>
        <select id="staMod"  class="ui-corner-all ui-state-default">
        </select>
        <a href="" target="_blank" id="sostav"><button id="but1" class="buttonSost"  disabled="disabled">Состав</button></a>
        <a href="" target="_blank" id="rasp"><button id="but2" class="buttonRasp"  disabled="disabled">Расписание</button></a>
        <a href="" target="_blank" id="brigada"><button id="but3" class="buttonBrigade">Работа станции</button></a>
        <%--<a href="${ctx}/kc/queue" target="_blank" id="queue"><button class="">Очередь накопления вагонов</button></a><br>--%>
    </div>
</div>

<%--заголовок таблиц--%>
<div id="caption" class="ui-jqgrid-titlebar ui-widget-header ui-corner-top ui-helper-clearfix">
    <span class="ui-jqgrid-title">Поездное положение по участкам</span>
</div>
<%--контейнер для общей таблицы--%>
<%--<div oncontextmenu="return usogdp.contextmenu(event, '.tbFormat');"  class="tbFormat">--%>
<div   class="tbFormat">
    <%--общая таблица, включающая таблицы по направлениям, отрисовываемые javascript--%>
    <table id="commonTable" style="width: 100%" class="text">

    </table>
</div>
<!-- Контейнер для собственного контекстного меню. По умолчания - скрыт. -->
<div id="contextMenu" oncontextmenu="return false;" style="position:absolute; top:0; left:0; border:1px solid #666; background-color:white; display:none; float:left;">
    <div>
        <ul class="nav">
            <li><a href="#" id="menuLinkSost" target="_blank"><button disabled="disabled" class="buttonContextMenu">Состав</button></a></li>
            <li><a href="#" id="menuLinkRasp" target="_blank"><button disabled="disabled" class="buttonContextMenu">Расписание</button></a></li>
        </ul>
    </div>
</div>
<%--скрипт--%>
<script>
    $(function() {
        simstations.init();
    });
</script>
