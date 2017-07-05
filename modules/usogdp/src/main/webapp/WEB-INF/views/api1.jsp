<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<h2> Технологическая страница</h2><br>

<div>
<table>
    <caption>Ajax api,доступных через get методы</caption>
    <thead>
    <tr>
        <td>ссылка </td>
        <td>описание </td>
        <td>табличный вид</td>
    </tr>

    </thead>
    <tbody>
    <tr>
        <td> <a href="${ctx}/ajax/lastforecast">/ajax/lastforecast</a> </td>
        <td> получение последнего прогноза</td>

    </tr>
    <tr>
        <td> <a href="${ctx}/ajax/forecast?id=402773">/ajax/foreast</a> </td>
        <td> получение прогноза по id. Параметр id обязателен, например ?id=402773</td>
    </tr>
    <tr>
        <td> <a href="${ctx}/ajax/forecast/delete/402773">/ajax/forecast/delete/402773</a> </td>
        <td> Удаление данных из таблиц:op_ipo_p,op_vgo_p,op_lko_p,prognozreal,train_processing по id. <br>Параметр id обязателен, например /402773</td>
    </tr>
    <tr>
        <td> <a href="${ctx}/ajax/forecasts">/ajax/foreasts</a> </td>
        <td>Список всех доступных прогнозов</td>
    </tr>
    <tr>
        <td> <a href="${ctx}/ajax/districts">/ajax/districts</a> </td>
        <td>Список всех доступных участков</td>
        <td> <a href="${ctx}/districts">/districts</a> </td>
    </tr>
    <tr>
        <td> <a href="${ctx}/ajax/simstations">/ajax/simstations</a> </td>
        <td>Список всех доступных станций моделирования</td>
        <td> <a href="${ctx}/simstations">/simstations</a> </td>
    </tr>
    <tr>
        <td> <a href="${ctx}/ajax/station?esr=138507">/ajax/station</a> </td>
        <td>информация о станции. Параметр esr обязателен, например ?esr=138507</td>
    </tr>
    <tr>
        <td> <a href="${ctx}/ajax/stations">/ajax/stations</a> </td>
        <td>информация о всех станциях в НСИ</td>
    </tr>
    <tr>
        <td> <a href="${ctx}/ajax/directions?esr=138507">/ajax/directions</a> </td>
        <td>Список направлений</td>
    </tr>
    <tr>
        <td> <a href="${ctx}/ajax/trains?esr=138507&forecastId=412481">/ajax/trains</a> </td>
        <td>список поездов</td>
    </tr>
    <tr>
        <td> <a href="${ctx}/ajax/gettrainconsist?esr=138507&trId=15012712581914000301302&idTrainHistVag=1422356640000&forecastId=412481&forecastIdParent=&ip=1400%20030%201302&np=2619">/ajax/gettrainconsist</a> </td>
        <td>Список состава поезда</td>
    </tr>
    <tr>
        <td> <a href="${ctx}/ajax/gettrainschedule?trId=15012712581914000301302&forecastId=412481&ip=1400%20030%201302&np=2619">/ajax/gettrainschedule</a> </td>
        <td>Список расписания поезда</td>
    </tr>
    </tbody>
</table>
</div>