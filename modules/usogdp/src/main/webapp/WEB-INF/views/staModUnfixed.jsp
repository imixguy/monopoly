<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/stationsR.js"></script>
<style>
    /*форматирование контейнера таблиц*/
    .tbFormat{
        overflow:auto;
        width: 100%;
        /*overflow-y: auto;*/
        border: 1px solid #aaaaaa;
        border-top: none;
    }
    /*заголовок таблиц*/
    #caption{
        text-align: center;
    }

    /*форматирование заголовка, контейнера таблиц, хидера и футера*/
    #caption, .tbFormat, header, footer{
        /*width: 100%;*/
        box-sizing: border-box;
        -moz-box-sizing: border-box;
    }
</style>

<%--заголовок таблиц--%>
<div id="caption" class="ui-jqgrid-titlebar ui-widget-header ui-corner-top ui-helper-clearfix">
    <span class="ui-jqgrid-title">Поездное положение по участкам</span>
</div>
<%--контейнер для общей таблицы--%>
<div class="tbFormat">
    <%--общая таблица, включающая таблицы по направлениям, отрисовываемые javascript--%>
    <table id="commonTable" style="width: 100%">

    </table>
</div>
<%--скрипт--%>
<script>
    $(function() {
        station.init();
    });
</script>
