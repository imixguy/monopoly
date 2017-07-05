<%--
  Created by IntelliJ IDEA.
  User: sedler
  Date: 26.02.15
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<style>

    .ui-autocomplete {
        /*width:70px;*/
        /*height: 40px;*/
        max-height: 200px;
        overflow-y: auto;
        font-family: Tahoma;
        font-size: 0.7em;
        font-weight: normal;
         /*prevent horizontal scrollbar */
        overflow-x: hidden;
    }

     #emptyVagons tr td.colorEditCell,#emptyVagons tr td.colorEditCell:focus, #emptyVagons tr.colorEditCell{
        background-color: #0a68ab;
    }

    .colorEditCellError{
        background-color: red;
    }

    .tbFormat{
        width: 100%;
        /*overflow: auto;*/
        text-align: center;
    }
    .tbFormat, header, footer{
        box-sizing: border-box;
        -moz-box-sizing: border-box;
    }
    .center{
        display: inline-block;
    }

    /*#modal_form {*/
        /*width: 300px;*/
        /*height: 300px; *//* Размеры должны быть фиксированы */
        /*border-radius: 5px;*/
        /*border: 1px #000 solid;*/
        /*background: #fff;*/
        /*position: fixed; *//* чтобы окно было в видимой зоне в любом месте */
        /*top: 45%; *//* отступаем сверху 45%, остальные 5% подвинет скрипт */
        /*left: 50%; *//* половина экрана слева */
        /*margin-top: -150px;*/
        /*margin-left: -150px; *//* тут вся магия центровки css, отступаем влево и вверх минус половину ширины и высоты соответственно =) */
        /*display: none; *//* в обычном состоянии окна не должно быть */
        /*opacity: 0; *//* полностью прозрачно для анимирования */
        /*z-index: 5; *//* окно должно быть наиболее большем слое */
        /*padding: 20px 10px;*/
        /*box-shadow: 0px 0px 7px 0px #000;*/
    /*}*/
    /* Кнопка закрыть для тех кто в танке) */
    /*#modal_form #modal_close {*/
        /*width: 21px;*/
        /*height: 21px;*/
        /*position: absolute;*/
        /*top: 10px;*/
        /*right: 10px;*/
        /*cursor: pointer;*/
        /*display: block;*/
    /*}*/

    /*#modal_close:hover{*/
        /*font-weight: bold;*/
        /*text-shadow: 5px 5px 10px 0px #000;*/
        /*color: red;*/
    /*}*/
    /* Подложка */
    /*#overlay {*/
        /*z-index: 3; *//* подложка должна быть выше слоев элементов сайта, но ниже слоя модального окна */
        /*position: fixed; *//* всегда перекрывает весь сайт */
        /*background-color: #000; *//* черная */
        /*opacity: 0.5; *//* но немного прозрачна */
        /*width: 100%;*/
        /*height: 100%; *//* размером во весь экран */
        /*top: 0;*/
        /*left: 0; *//* сверху и слева 0, обязательные свойства! */
        /*cursor: pointer;*/
        /*display: none; *//* в обычном состоянии её нет) */
    /*}*/
</style>

<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/emptyvagons.js?v=6"></script>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/error.js"></script>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdp.js?v=1"></script>


<%--<div id="modal_form"><!-- Само окно -->--%>
    <%--<span id="modal_close">X</span> <!-- Кнопка закрыть -->--%>
    <%--<div style="margin-top: 50px;">--%>
        <%--<p>Введите название станции или код esr</p>--%>
        <%--<input type="text" id="station"/>--%>
    <%--</div>--%>
    <%--<div style="margin-top:50px; margin-left: 50px">--%>
        <%--<button id="confirmAddStation">Добавить станцию</button>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="overlay"></div><!-- Подложка -->--%>

<div class="tbFormat">
    <%--вывод информации о прогнозе и о составе--%>
    <%--<div  class="">--%>
        <%--<Label id="info" class="color" style="display: inline-block; margin-top: 5px; margin-left: 20px"></Label><br>--%>
    <%--</div>--%>
    <div class="center">

        <span>
            <div style="text-align: left; margin-bottom: 3px">
                <select id="vagonType"></select>
                <button id="save">Сохранить</button>
                <button id="cancel">Отменить</button>
            </div>
            <table id="emptyVagons"></table>
            <div style="text-align: left; margin-top: 3px"><button id="addStation">Добавить станцию</button></div>
        </span>
    </div>
</div>




<script>
    $(function() {
        emptyVagons.init();
    });
</script>