<%@ page import="rw.ktc.ksupr.web.app.init.Const" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<script type="text/javascript" charset="utf8" src="${ctx}/webjars/Snap.svg/0.3.0/snap.svg.js"></script>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdp.js"></script>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdpgraph.js?<%=Const.APP_VERSION%>"></script>
<style>
    /*Данные стили касаются только графика */
    /*Это стили для всего полотна рисования*/
    svg {
        background: #F9F9FF;
        /*border: 1px dashed black;*/
        margin-top: 28px;
    }
/* линии прогнозных поездов*/
    g.forecast > line {
        stroke: #6290ff;
        stroke-width: 2px;
    }

    /* линии реальных поездов*/
    g.real > line {
        stroke: black;
        stroke-width: 2px;
    }

    /* линии реальных и прогнозных поездов при выделении*/
    g.real:hover > line {
        stroke: red;
    }

    g.forecast:hover > line {
        stroke: red;
    }

    /* дублированные линии у всех поездов для более удобноого выделения. поэтому сделаны невидимыми*/
    g > line.forselect {
        stroke-width: 20px;
        opacity: 0;
    }

    /* Ось Y*/
    /* горизонтальные линии оси у*/
    line.axisy {
        stroke: #b6b1be;
        stroke-width: 1px;
        /*stroke-dasharray: 10 10;*/
    }

    g#axisY > text {
        /*font-size: 14px;*/
    }

    /* станции границ участков*/
    text.axisy-st {
        font-size: 14px;
        font-weight: bold;
    }
    line.axisy-st {
        stroke: black;
        stroke-width: 2px;
    }

    /* Граничные станции с другими государствами - стыки*/
    text.axisy-styk {
        font-size: 16px;
        fill: #ff9972;
        /*font-weight: bold;*/
    }
    line.axisy-styk {
        stroke: #ff9972;
        stroke-width: 2px;
    }

    /* Ось X*/
    line.axisx {
        stroke: #959595;
        stroke-width: 1.5px;
    }
    line.minutes {
        stroke: #959595;
        stroke-width: 0.5px;
    }
    line.middlehour {
        stroke: #959595;
        stroke-width: 1px;
        stroke-dasharray: 4 4;
    }
    /* метка времени на графике*/
    g > line.metka {
        stroke: black;
        stroke-width: 2px;
    }
    /* ось Х, текстовая метка часа и даты */
    text.timestamp {
        /*transform: translate(0px,18px);*/
        text-anchor: middle;
    }
    text.datestamp {
        /*transform: translate(0px,18px);*/
        text-anchor: middle;
        fill: red;
        font-weight: bold;
    }

    /* Точка пересечения линии поезда с линиями станций  - черточка и метка времени*/
    text.metka {
        text-anchor: middle;
        font-size: 12px;
        font-family: Tahoma;
        color: #000000;
    }

    /* номер поезда*/
    text.numbertrain {
        /*text-anchor: middle;*/
        font-size: 14px;
        /*font-weight: bold;*/
        font-family: Tahoma;
        /*stroke: #ff6e0c;*/
        /*stroke: #097909;*/
    }

    /* ип */
    text.iptrain {
        /*text-anchor: middle;*/
        font-size: 14px;
        font-family: Tahoma;
        font-weight: normal;
        /*font-style: italic;*/
        /*color: #ff6e0c;*/
        /*stroke: #097909;*/
    }

    g > a > circle.metka {
        display: none;
    }

    /* Стиль для выделенного поезда */
    g.seltrain > line {
        stroke: red;
        /*fill: red;*/
    }
    g.seltrain > text {
        /*stroke: red;*/
        /*fill: red;*/
        /*font-size: 12px;*/
    }
    /*g.seltrain > a > circle.metka {*/
    /*display: inherit;*/
    /*fill: red;*/
    /*}*/
    g.seltrain.forecast > a > circle.metka {
        display: inherit;
        fill: #6290ff;
    }
    g.seltrain.real > a > circle.metka {
        display: inherit;
        fill: black;
    }
    /* Линия расчета прогноза*/
    #axisStart > line {
        stroke: blue;
        stroke-width: 3px;
    }



    /* стилизация html */
    label#distrinfo {
        margin-left: 30px;
    }
    div.distrinfo {
        text-align: center;
        font-weight: bold;
        width: 100%;
    }
    label.trainInfo{
        margin-left: 20px;
    }
    form#rasp{
        display: inline;
    }
</style>
<div>
    <div>
        <label id="forecast" class="forecast_style color"></label>
    </div>
    <div class="distrinfo">
        <label id="distrinfo"></label>
    </div>
    <div>
        <button id="bSchedule" class="buttonRasp" disabled="disabled">Расписание</button>
        <label id="trainInfo"></label>
        <label id="trainInfo2" class="hidden"></label>
    </div>
    <%--<input id="btY" type="button" value="Y">--%>
    <%--<input id="btX" type="button" value="X">--%>
    <div>
        <nobr>
            <div style="float:left">
                <table id="tstadisctricts"></table>
            </div>
            <div style="">
                <svg id="svg" class="hidden" width="0" height="0">

                </svg>
            </div>
        </nobr>
    </div>
</div>
<script>

    var forecastId = ${forecastId };
    usogdpgraph.districts = ${districts};
    var showT = ${showT};

    $(document).ready(function () {


        usogdp.ajaxForecasts(function () {
            var forecast = usogdp.getForecastByIdOrLast(forecastId);
            $('#forecast').text('Обновление данных на   ' + usogdp.formatDate(forecast.startDate, 'forecast'));
            $('#forecast').attr("forecastId", forecast.id);

            $.when(usogdpgraph.ajaxStaDistricts(usogdpgraph.districts), usogdpgraph.ajaxTrainOperations(forecast.id))
                    .done(function (dataStaDistricts, dataTrainOperations) {
                        var staDistricts = dataStaDistricts[0].slice();
                        var trainOperations = dataTrainOperations[0];

                        $('#distrinfo').text('Участок:' + staDistricts[0].name + " - " + staDistricts[staDistricts.length - 1].name);
                        usogdpgraph.showgraf("#svg", trainOperations, forecast);

                        $("#btY").click(function () {
                            $("#axisY").toggle();
                        });
                        $("#btX").click(function () {
                            $("#axisX").toggle();
                        });
                        $("#bSchedule").click(function () {
                            var url = usogdpgraph.links.schedule;
                            window.open(url);
                        });
                        if (showT) {
                            var i = 1;
                            while (i < staDistricts.length) { // убираем повторения
                                if (i > 0 && staDistricts[i - 1].esr == staDistricts[i].esr) { //  если esr равен предыдущему esr
                                    staDistricts.splice(i, 1); // удаляем текущий
                                } else {
                                    i++;
                                }
                            }
                            usogdpgraph.showStaDistricts("#tstadisctricts", staDistricts);
                            $("#trainInfo2").toggle();
                        }

                        usogdp.hideLoader(); // скрыть "ожидание"

                    }).fail(function (jqXHR, textStatus) {
                        if (window.ajaxError) {
                            usogdp.hideLoader();
                            window.ajaxError.printError(jqXHR, textStatus, 'ошибка при получении данных с модуля usogdpgraph');
                        }
                    });

        });

    });
</script>
