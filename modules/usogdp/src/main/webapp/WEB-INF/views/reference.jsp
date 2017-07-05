<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<h2> Технологический справочник</h2><br>

<div>
    <table style="width: 70%">
        <caption>Список доступных URL</caption>
        <thead>
        <tr>
            <td>ссылка </td>
            <td>описание </td>
        </tr>

        </thead>
        <tbody>
        <tr>
            <td> <a href="${ctx}/index.html" target="_blank">/index.html</a> </td>
            <td> Поездное положение по участкам</td>

        </tr>
        <tr>
            <td> <a href="${ctx}/districts" target="_blank">/districts</a></td>
            <td> Построение графика движения поездов</td>
        </tr>
        <tr>
            <td> <a href="${ctx}/api1" target="_blank">/api1</a></td>
            <td> Ajax api,доступных через get методы</td>
        </tr>
        <tr>
            <td> <a href="${ctx}/reference" target="_blank">/reference</a></td>
            <td> Список доступных URL</td>
        </tr>
        </tbody>
    </table>
</div>