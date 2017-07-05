<%@ page import="org.springframework.context.i18n.LocaleContextHolder" %>
<%@ page import="rw.gcktc.webcms.form.NodeView" %>
<%@ page import="java.util.Locale" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 28.01.13
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="http://struts.apache.org/tags-titles" prefix="titles"%>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%--<base href="/webcmse/" />--%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>
    <%--Если есть атрибут titlePage - то вставляется он иначе вставляется атрибут titleKey --%>
    <c:choose>
        <c:when test="${titlePage!=null}">
            ${titlePage}
        </c:when>
        <c:otherwise>
            <c:set var="titleKey"><tiles:getAsString name="titleKey"/></c:set>
            <spring:message code="${titleKey}" />
        </c:otherwise>
    </c:choose>
</title>
<meta content="" name="description">
<meta content="" name="keywords">
<link href="http://www.rw.by/css/main.css?v2" rel="stylesheet" type="text/css">
<link href="http://www.rw.by/css/pages.css" rel="stylesheet" type="text/css">
<link href="http://www.rw.by/css/somepages.css" rel="stylesheet" type="text/css">
<link href="http://www.rw.by/css/mystyle.css" type="text/css" rel="stylesheet">
<link href="http://www.rw.by/css/styles.css?v2" type="text/css" rel="stylesheet">
<link href="http://www.rw.by/css/styles2.css" type="text/css" rel="stylesheet">
<script src="http://mc.yandex.ru/metrika/watch.js" async="" type="text/javascript"></script><script src="http://www.google-analytics.com/ga.js" async="" type="text/javascript"></script><script type="text/javascript" src="http://www.rw.by/feedback.php?get_lang=1"></script><link href="http://www.rw.by/css/fotorama.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="http://www.rw.by/js/jquery.js"></script><script type="text/javascript" src="http://www.rw.by/js/jquery.smooth-scroll.min.js"></script><script type="text/javascript" src="http://www.rw.by/js/jquery.carouFredSel-5.5.0-packed.js"></script><script type="text/javascript" src="http://www.rw.by/js/jquery.example.min.js"></script><script type="text/javascript" src="http://www.rw.by/js/jquery-ui-1.8.20.custom.min.js"></script><script src="http://www.rw.by/js/jquery.stickr.js" type="text/javascript"></script><script type="text/javascript" src="http://www.rw.by/js/main.js"></script><script src="http://www.rw.by/js/mymain.js?v4" type="text/javascript"></script><script src="http://www.rw.by/js/max-js.js" type="text/javascript"></script><script src="http://www.rw.by/js/fotorama.js" type="text/javascript"></script><link rel="alternate" type="application/rss+xml" title="Все новости" href="http://www.rw.by/rss/?section=1">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «Новости пассажирам»" href="http://www.rw.by/rss/?section=13">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «Новости грузоотправителям»" href="http://www.rw.by/rss/?section=14">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «Корпоративные новости»" href="http://www.rw.by/rss/?section=15">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «БелЖД и молодежь»" href="http://www.rw.by/rss/?section=60">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «Новый формат»" href="http://www.rw.by/rss/?section=65">
<link rel="alternate" type="application/rss+xml" title="Новости Минского отделения" href="http://www.rw.by/rss/?section=71">
<link rel="alternate" type="application/rss+xml" title="Новости Барановичского отделения" href="http://www.rw.by/rss/?section=72">
<link rel="alternate" type="application/rss+xml" title="Новости Брестского отделения" href="http://www.rw.by/rss/?section=73">
<link rel="alternate" type="application/rss+xml" title="Новости Гомельского отделения" href="http://www.rw.by/rss/?section=74">
<link rel="alternate" type="application/rss+xml" title="Новости Могилевского отделения" href="http://www.rw.by/rss/?section=75">
<link rel="alternate" type="application/rss+xml" title="Новости Витебского отделения" href="http://www.rw.by/rss/?section=76">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «Репортажи, интервью, статьи»" href="http://www.rw.by/rss/?section=11">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «Репортажи, интервью, статьи». «Минское отделение Белорусской железной дороги»" href="http://www.rw.by/rss/?section=121">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «Репортажи, интервью, статьи». «Барановичское отделение Белорусской железной дороги»" href="http://www.rw.by/rss/?section=122">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «Репортажи, интервью, статьи». «Брестское отделение Белорусской железной дороги»" href="http://www.rw.by/rss/?section=123">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «Репортажи, интервью, статьи». «Гомельское отделение Белорусской железной дороги»" href="http://www.rw.by/rss/?section=124">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «Репортажи, интервью, статьи». «Могилевское отделение Белорусской железной дороги»" href="http://www.rw.by/rss/?section=125">
<link rel="alternate" type="application/rss+xml" title="Новости раздела «Репортажи, интервью, статьи». «Витебское отделение Белорусской железной дороги»" href="http://www.rw.by/rss/?section=126">
<link rel="alternate" type="application/rss+xml" title="Городские линии" href="http://www.rw.by/rss/?section=131">
<link rel="alternate" type="application/rss+xml" title="Региональные линии" href="http://www.rw.by/rss/?section=132">
<link rel="alternate" type="application/rss+xml" title="Межрегиональные линии" href="http://www.rw.by/rss/?section=134">
<link rel="alternate" type="application/rss+xml" title="Международные линии" href="http://www.rw.by/rss/?section=133">
<link rel="alternate" type="application/rss+xml" title="Коммерческие линии" href="http://www.rw.by/rss/?section=135">
<link rel="alternate" type="application/rss+xml" title="Городские линии. Репортажи, интервью, статьи" href="http://www.rw.by/rss/?section=161">
<link rel="alternate" type="application/rss+xml" title="Региональные линии. Репортажи, интервью, статьи" href="http://www.rw.by/rss/?section=162">
<link rel="alternate" type="application/rss+xml" title="Межрегиональные линии. Репортажи, интервью, статьи" href="http://www.rw.by/rss/?section=164">
<link rel="alternate" type="application/rss+xml" title="Международные линии. Репортажи, интервью, статьи" href="http://www.rw.by/rss/?section=165">
<link rel="alternate" type="application/rss+xml" title="Коммерческие линии. Репортажи, интервью, статьи" href="http://www.rw.by/rss/?section=163">
<script type="text/javascript" src="http://www.rw.by/js/jquery.autocomplete_v1.js"></script>
<link rel="stylesheet" type="text/css" href="http://www.rw.by/css/jquery.autocomplete.css" media="all">
<link rel="stylesheet" type="text/css" media="print" href="http://www.rw.by/css/print.css">
<link href="http://www.rw.by/favicon.ico" rel="shortcut icon">
<script type="text/javascript">

    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-31356575-1']);
    _gaq.push(['_setDomainName', 'rw.by']);
    _gaq.push(['_addOrganic', 'akavita.by', 'z']);
    _gaq.push(['_addOrganic', 'tut.by', 'query']);
    _gaq.push(['_addOrganic', 'search.tut.by', 'query']);
    _gaq.push(['_addOrganic', 'all.by', 'query']);
    _gaq.push(['_addOrganic', 'go.mail.ru',  'q']);
    _gaq.push(['_addOrganic', 'nova.rambler.ru',  'query']);
    _gaq.push(['_addOrganic', 'nigma.ru', 's']);
    _gaq.push(['_addOrganic', 'webalta.ru', 'q']);
    _gaq.push(['_addOrganic', 'aport.ru', 'r']);
    _gaq.push(['_addOrganic', 'poisk.ru', 'text']);
    _gaq.push(['_addOrganic', 'km.ru', 'sq']);
    _gaq.push(['_addOrganic', 'liveinternet.ru', 'ask']);
    _gaq.push(['_addOrganic', 'quintura.ru', 'request']);
    _gaq.push(['_addOrganic', 'search.qip.ru', 'query']);
    _gaq.push(['_addOrganic', 'gde.ru', 'keywords']);
    _gaq.push(['_addOrganic', 'gogo.ru', 'q']);
    _gaq.push(['_addOrganic', 'ru.yahoo.com', 'p']);
    _gaq.push(['_addOrganic', 'meta.ua', 'q']);
    _gaq.push(['_addOrganic', 'bigmir.net', 'q']);
    _gaq.push(['_trackPageview']);
    _gaq.push(['_trackPageLoadTime']);

    (function() {
        var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
    })();

</script>
</head>
<body marginwidth="0" marginheight="0"  leftmargin="0" topmargin="0" class="bodyCl">
<div class="main-wrappper">
<div class="div-ie6"><div class="div-ie6-wrpr">
    <p>Вы используете устаревший браузер. Чтобы использовать все возможности сайта, загрузите и установите один из этих браузеров:</p>
    <div class="browser"><a href="http://www.mozilla-europe.org/" class="firefox-a"><span>Mozilla Firefox</span></a></div>
    <div class="browser"><a href="http://www.google.com/chrome/" class="chrome-a"><span>Google Chrome</span></a></div>
    <div class="browser"><a href="http://www.apple.com/safari/" class="safari-a"><span>Apple Safari</span></a></div>
    <div class="browser"><a href="http://www.opera.com/" class="opera-a"><span>Opera</span></a></div>
    <div class="browser"><a href="http://www.microsoft.com/" class="ie-a"><span>Internet Explorer</span></a></div>
</div></div>
<div class="promo">
    <div class="caroufredsel_wrapper" style="display: block; text-align: start; float: none; position: relative; top: 0px; right: 0px; bottom: 0px; left: 0px; width: 1903px; height: 600px; margin: 0px; overflow: hidden;"><ul class="slides" style="text-align: left; float: none; position: absolute; top: 0px; left: 0px; margin: 0px; width: 17127px; height: 600px;">
        <li style="background-image: url('http://www.rw.by/uploads/promo/1920x600/passengers.png');">
        </li><li class="white" style="background-image: url('http://www.rw.by/uploads/promo/1920x600/tme3_slider.png');">
    </li><li class="white" style="background-image: url('http://www.rw.by/uploads/promo/1920x600/cargo_2.png'); ">
    </li><li class="white" style="background-image: url('http://www.rw.by/uploads/promo/1920x600/format.png');">
    </li></ul></div>
    <ul class="nav">
        <li><img alt="Слайд_new: Пассажирские перевозки" src="http://www.rw.by/uploads/promo/68x32_tt/passengers.png"></li>
        <li><img alt="Маневровый тепловоз ТМЭ3" src="http://www.rw.by/uploads/promo/68x32_tt/tme3_slider.png"></li>
        <li><img alt="Слайд_new: Грузовые перевозки" src="http://www.rw.by/uploads/promo/68x32_tt/cargo_2.png"></li>
        <li><img alt="Слайд_new: Новый формат" src="http://www.rw.by/uploads/promo/68x32_tt/format.png"></li>
    </ul>
    <div class="shadow"></div>
    <div class="pattern"></div>
</div>
<div class="main-wrapper index">
<div class="index-top">
<div class="index-top__w">
<div class="popup-site-map"><div class="wrapper">
<a href="#" class="site-map"><span><u>Весь сайт Белорусской железной дороги</u></span><i></i></a><a class="close"><u>Закрыть</u></a><a href="http://www.rw.by/" class="home"><u>Главная страница сайта БелЖД</u></a><div class="tabs-wrapper"><ul class="tabs with-clear">
    <li class="active"><span><i>Расписание</i><img src="http://www.rw.by/images/site-map-popup-tab-active.png" alt=""></span></li>
    <li><b>Билеты</b></li>
    <li><b>Услуги пассажирам</b></li>
    <li><b>Туризм и отдых</b></li>
    <li><b>Грузовые перевозки</b></li>
    <li><b>Корпоративный</b></li>
</ul></div>
<div class="menu-section-wrapper"><ul class="menu-contents">
    <li><a href="http://www.rw.by/schedule/suburban/">Главная страница раздела&nbsp;«Расписание»
    </a></li>
    <li><ul class="menu-contents-cols with-clear"></ul></li>
</ul></div>
<div class="menu-section-wrapper" style="display: none;"><ul class="menu-contents">
    <li><a href="http://www.rw.by/tickets/">Главная страница раздела&nbsp;«Билеты»
    </a></li>
    <li><ul class="menu-contents-cols with-clear"></ul></li>
</ul></div>
<div class="menu-section-wrapper" style="display: none;"><ul class="menu-contents">
    <li><a href="http://www.rw.by/passengers_services/">Главная страница раздела&nbsp;«Услуги пассажирам»
    </a></li>
    <li><ul class="menu-contents-cols with-clear">
        <li><ul class="menu-contents-sub"><li><a href="http://www.rw.by/help/">Справочная информация</a></li></ul></li>
        <li><ul class="menu-contents-sub"><li>
            <a href="http://www.rw.by/passengers_services/information_services/">Информационные сервисы</a><ul class="menu-contents-pages">
            <li><a href="http://www.rw.by/schedule/suburban/">Расписание движения поездов</a></li>
            <li><a href="http://www.rw.by/passengers_services/information_services/tickets_order/">Заказ билетов</a></li>
            <li><a href="http://www.rw.by/">Календарь пассажира</a></li>
            <li><a href="http://www.rw.by/passengers_services/information_services/points_of_sale/">Пункты продажи проездных документов</a></li>
            <li><a href="http://poezd.rw.by" target="_blank">Покупка билетов онлайн</a></li>
        </ul>
        </li></ul></li>
        <li><ul class="menu-contents-sub"><li>
            <a href="http://www.rw.by/passengers_services/trains/">Поезда</a><ul class="menu-contents-pages">
            <li><a href="http://www.rw.by/passengers_services/trains/branded_trains/">Фирменные поезда</a></li>
            <li><a href="http://www.rw.by/passengers_services/trains/vagon_types/">Категории вагонов</a></li>
            <li><a href="http://www.rw.by/passengers_services/trains/vagons_for_disabled_person/">Вагоны для пассажиров с ограниченными физическими возможностями</a></li>
            <li><a href="http://www.rw.by/passengers_services/trains/train_services/">Услуги в поездах</a></li>
            <li><a href="http://www.rw.by/passengers_services/trains/new_format/">Новый формат пассажирских перевозок</a></li>
        </ul>
        </li></ul></li>
        <li><ul class="menu-contents-sub"><li>
            <a href="http://www.rw.by/passengers_services/railway_stations/">Железнодорожные вокзалы</a><ul class="menu-contents-pages"><li><a href="http://www.rw.by/passengers_services/railway_stations/">Вокзалы</a></li></ul>
        </li></ul></li>
    </ul></li>
</ul></div>
<div class="menu-section-wrapper" style="display: none;"><ul class="menu-contents">
    <li><a href="http://www.rw.by/tourism_and_recreation/">Главная страница раздела&nbsp;«Туризм и отдых»
    </a></li>
    <li><ul class="menu-contents-cols with-clear">
        <li><ul class="menu-contents-sub">
            <li><a href="http://www.rw.by/tourism_and_recreation/about/">О нас</a></li>
            <li>
                <a href="http://www.rw.by/tourism_and_recreation/services/">Услуги</a><ul class="menu-contents-pages">
                <li><a href="http://www.rw.by/tourism_and_recreation/services/belarus/">Беларусь</a></li>
                <li><a href="http://www.rw.by/tourism_and_recreation/services/outbound_tourism/">Выездной туризм</a></li>
                <li><a href="http://www.rw.by/tourism_and_recreation/services/forms_and_contracts/">Бланки и договоры</a></li>
                <li><a href="http://www.rw.by/tourism_and_recreation/services/medical_insurance/">Медицинское страхование</a></li>
            </ul>
            </li>
        </ul></li>
        <li><ul class="menu-contents-sub"><li>
            <a href="http://www.rw.by/tourism_and_recreation/countries/">Страны</a><ul class="menu-contents-pages">
            <li><a href="http://www.rw.by/tourism_and_recreation/tours/">Беларусь</a></li>
            <li><a href="http://www.rw.by//tourism_and_recreation/countries/bulgaria/">Болгария</a></li>
            <li><a href="http://www.rw.by/tourism_and_recreation/countries/lithuania/">Литва</a></li>
            <li><a href="http://www.rw.by/tourism_and_recreation/countries/russia/">Россия</a></li>
            <li><a href="http://www.rw.by/tourism_and_recreation/countries/ukraine/">Украина</a></li>
            <li><a href="http://www.rw.by/tourism_and_recreation/countries/chehija/">Чехия</a></li>
            <li><a href="http://www.rw.by/tourism_and_recreation/countries/egypt/">Египет</a></li>
        </ul>
        </li></ul></li>
        <li><ul class="menu-contents-sub">
            <li>
                <a href="http://www.rw.by/tourism_and_recreation/countries/belarus/">Приглашаем в Беларусь</a><ul class="menu-contents-pages">
                <li><a href="http://www.rw.by/tourism_and_recreation/countries/belarus_excursions/">Экскурсии по Беларуси</a></li>
                <li><a href="http://www.rw.by//tourism_and_recreation/countries/belarus_tours/">Туры в Беларусь</a></li>
            </ul>
            </li>
            <li><a href="http://www.rw.by/tourism_and_recreation/schedule_of_tours/">График экскурсий</a></li>
            <li><a href="http://www.rw.by/tourism_and_recreation/otdih_i_lechenie/">Отдых и лечение</a></li>
        </ul></li>
        <li><ul class="menu-contents-sub"><li><a href="http://www.rw.by/vt/vt.html" target="_blank">Виртуальное путешествие</a></li></ul></li>
    </ul></li>
</ul></div>
<div class="menu-section-wrapper" style="display: none;"><ul class="menu-contents">
    <li><a href="http://www.rw.by/cargo_transportation/">Главная страница раздела&nbsp;«Грузовые перевозки»
    </a></li>
    <li><ul class="menu-contents-cols with-clear">
        <li><ul class="menu-contents-sub"><li>
            <a href="http://www.rw.by/cargo_transportation/services/">Сервисы</a><ul class="menu-contents-pages">
            <li><a href="http://www.rw.by/cargo_transportation/services/normative_reference_information/">Нормативно-справочная информация</a></li>
            <li><a href="http://www.rw.by/cargo_transportation/services/tariffs/">Тарифы на грузоперевозки</a></li>
            <li><a href="/calculation_rates/">Расчет грузовых тарифов</a></li>
            <li><a href="http://www.rw.by/cargo_transportation/services/rental_of_railway_transporters/">Аренда железнодорожных транспортеров</a></li>
            <li><a href="http://www.rw.by/cargo_transportation/services/information_services/">Информационные услуги</a></li>
            <li><a href="http://www.rw.by/cargo_transportation/services/e_transportation/">АC «Электронная перевозка» и АПЗ</a></li>
        </ul>
        </li></ul></li>
        <li><ul class="menu-contents-sub"><li>
            <a href="http://www.rw.by/cargo_transportation/">Информация</a><ul class="menu-contents-pages">
            <li><a href="http://www.rw.by/cargo_transportation/rates_changes/">Информация об изменении тарифов</a></li>
            <li><a href="http://www.rw.by/corporate/press_center/news_of_cargo_carriers/">Новости грузоперевозчикам</a></li>
            <li><a href="http://www.rw.by/cargo_transportation/customer_reviews/">Отзывы клиентов</a></li>
        </ul>
        </li></ul></li>
        <li><ul class="menu-contents-sub">
            <li>
                <a href="http://www.rw.by/cargo_transportation/freight_forwarding_company/">Экспедиторские компании</a><ul class="menu-contents-pages">
                <li><a href="http://www.rw.by/corporate/structure/belint/">«БЕЛИНТЕРТРАНС —  транспортно-логистический центр»</a></li>
                <li><a href="">ТЭРДУП «Гомельжелдортранс»</a></li>
                <li><a href="http://www.rw.by/cargo_transportation/freight_forwarding_company/the_selection_of_the_forwarder/">Подбор экспедитора</a></li>
            </ul>
            </li>
            <li>
                <a href="http://www.rw.by/cargo_transportation/container_transportation/">Контейнерные перевозки</a><ul class="menu-contents-pages">
                <li><a href="http://www.rw.by/cargo_transportation/container_transportation/container_work_stations/">Станции открытые для работы с контейнерами</a></li>
                <li><a href="http://www.rw.by/cargo_transportation/container_transportation/accelerated_trains/">Специализированные ускоренные поезда</a></li>
            </ul>
            </li>
        </ul></li>
    </ul></li>
</ul></div>
<div class="menu-section-wrapper" style="display: none;">
    <ul class="menu-contents">
        <li><a href="http://www.rw.by/corporate/">Главная страница раздела&nbsp;«Корпоративный»
        </a></li>
        <li><ul class="menu-contents-cols with-clear">
            <li><ul class="menu-contents-sub">
                <li>
                    <a href="http://www.rw.by/corporate/belarusian_railway/">О Белорусской железной дороге</a><ul class="menu-contents-pages">
                    <li><a href="http://www.rw.by/corporate/belarusian_railway/technical_characteristics/">Техническая характеристика</a></li>
                    <li><a href="http://www.rw.by/corporate/belarusian_railway/investment_policy/">Инвестиционная политика</a></li>
                    <li><a href="http://www.rw.by/corporate/belarusian_railway/priority_directions/">Развитие приоритетных направлений</a></li>
                    <li><a href="http://www.rw.by/corporate/belarusian_railway/statistics/">Статистика</a></li>
                    <li><a href="http://www.rw.by/corporate/belarusian_railway/international_activities/">Международная деятельность</a></li>
                    <li><a href="http://www.rw.by/corporate/belarusian_railway/partners/">Наши партнеры</a></li>
                    <li><a href="http://www.rw.by/corporate/belarusian_railway/corporate_style/">Фирменный стиль</a></li>
                    <li><a href="http://150let.rw.by/" target="_blank">История</a></li>
                </ul>
                </li>
                <li><a href="http://www.rw.by/corporate/related_structures/">Смежные структуры</a></li>
                <li><a href="http://www.rw.by/corporate/tenders_and_procurement/">Тендеры и закупки</a></li>
                <li><a href="http://www.rw.by/corporate/normative_documents/">Нормативные документы</a></li>
                <li><a href="http://www.rw.by/corporate/real_estate/">Недвижимость (продажа и аренда)</a></li>
            </ul></li>
            <li><ul class="menu-contents-sub"><li>
                <a href="http://www.rw.by/corporate/structure/">Структура</a><ul class="menu-contents-pages">
                <li><a href="http://www.rw.by/corporate/structure/guide/">Руководство</a></li>
                <li><a href="http://www.rw.by/corporate/structure/management/">Управление Белорусской железной дороги</a></li>
                <li><a href="http://www.rw.by/corporate/structure/">Организации и обособленные структурные подразделения</a></li>
                <li><a href="http://www.rw.by/corporate/structure/dorprofsoj/">Профсоюз железнодорожников и транспортных строителей</a></li>
            </ul>
            </li></ul></li>
            <li><ul class="menu-contents-sub"><li>
                <a href="http://www.rw.by/corporate/social_sphere/">Социальная сфера</a><ul class="menu-contents-pages">
                <li><a href="http://www.rw.by/corporate/social_sphere/culture_and_sport/">Культура и cпорт</a></li>
                <li><a href="http://www.rw.by/corporate/social_sphere/museums/">Музеи</a></li>
                <li><a href="http://www.rw.by/corporate/social_sphere/sanatorium/">Санатории</a></li>
                <li><a href="http://www.rw.by/corporate/social_sphere/education/">Образование</a></li>
                <li><a href="http://www.rw.by/corporate/social_sphere/health/">Здравоохранение</a></li>
            </ul>
            </li></ul></li>
            <li><ul class="menu-contents-sub">
                <li>
                    <a href="http://www.rw.by/corporate/press_center/">Пресс-центр</a><ul class="menu-contents-pages">
                    <li><a href="http://www.rw.by/corporate/press_center/">Главные новости</a></li>
                    <li><a href="http://www.rw.by/corporate/press_center/reportings_interview_article/2014/">Репортажи, интервью, статьи</a></li>
                    <li><a href="http://www.rw.by/corporate/press_center/videos/">Видеоматериалы</a></li>
                    <li><a href="http://www.rw.by/corporate/press_center/photo_reports/">Фоторепортажи</a></li>
                    <li><a href="http://www.rw.by/corporate/press_center/news_of_passengers/2014/">Новости пассажирам</a></li>
                    <li><a href="http://www.rw.by/corporate/press_center/news_of_cargo_carriers/2014/">Новости грузоперевозчикам</a></li>
                    <li><a href="http://www.rw.by/corporate/press_center/corporate_news/2014/">Корпоративные новости</a></li>
                </ul>
                </li>
                <li>
                    <a href="http://www.rw.by/corporate/youth/">Молодежный</a><ul class="menu-contents-pages">
                    <li><a href="http://www.rw.by/corporate/youth/press_center/news/2013/">Новости</a></li>
                    <li><a href="http://www.rw.by/corporate/youth/projects/">Проекты</a></li>
                    <li><a href="http://www.rw.by/corporate/youth/youth_policy/">Молодежная политика</a></li>
                    <li><a href="http://www.rw.by/corporate/youth/photo_gallery/">Фотогалерея</a></li>
                    <li><a href="http://www.rw.by/corporate/youth/the_sectoral_committee_of_the_brsm/">Отраслевой комитет БРСМ</a></li>
                    <li><a href="http://www.rw.by/corporate/youth/the_sectoral_committee_of_the_brsm/our_history/">Наша история</a></li>
                    <li><a href="http://www.rw.by/corporate/youth/the_sectoral_committee_of_the_brsm/contacts/">Контакты</a></li>
                </ul>
                </li>
                <li>
                    <a href="http://www.rw.by/corporate/contacts/">Контакты</a><ul class="menu-contents-pages">
                    <li><a href="http://www.rw.by/corporate/contacts/treatment_of_citizens/">Обращения</a></li>
                    <li><a href="http://www.rw.by/corporate/contacts/administrative_pricedures/">Административные процедуры</a></li>
                    <li><a href="http://www.rw.by/corporate/contacts/advertisement/">Реклама</a></li>
                </ul>
                </li>
                <li><a href="http://www.rw.by/corporate/web_resources/">Каталог веб-ресурсов</a></li>
            </ul></li>
        </ul></li>
    </ul>
    <div class="menu-units-wrapper">
        <h3><a href="http://www.rw.by/corporate/structure/">Структура</a></h3>
        <ul class="menu-units-cols with-clear">
            <li class="menu-units-list"><div class="wrap">
                <a href="http://www.minsk.rw.by/"><img src="http://www.rw.by/uploads/content/annotation/60x45/minsk.png" alt=""><u>Минское отделение</u></a><a href="http://www.baranovichi.rw.by/"><img src="http://www.rw.by/uploads/content/annotation/60x45/baranovichi.png" alt=""><u>Барановичское отделение</u></a>
            </div></li>
            <li class="menu-units-list"><div class="wrap">
                <a href="http://www.brest.rw.by/"><img src="http://www.rw.by/uploads/content/annotation/60x45/brest.png" alt=""><u>Брестское отделение</u></a><a href="http://www.gomel.rw.by/"><img src="http://www.rw.by/uploads/content/annotation/60x45/gomel_.png" alt=""><u>Гомельское отделение</u></a>
            </div></li>
            <li class="menu-units-list"><div class="wrap">
                <a href="http://www.mogilev.rw.by/"><img src="http://www.rw.by/uploads/content/annotation/60x45/mogilev.png" alt=""><u>Могилевское отделение</u></a><a href="http://www.vitebsk.rw.by/"><img src="http://www.rw.by/uploads/content/annotation/60x45/vitebskoe_otdelenie.png" alt=""><u>Витебское отделение</u></a>
            </div></li>
            <li><div class="wrap"><a href="http://www.rw.by/corporate/structure/">Все структурные подразделения</a></div></li>
        </ul>
    </div>
</div>
</div></div>
<div class="popup-105"><div class="wrapper">
<a class="close"></a><div class="station-phone">
    <h3>Справочное бюро вокзала станции</h3>
    <ul class="stations-list">
        <li><span class="active">МИНСК</span></li>
        <li><u>БАРАНОВИЧИ</u></li>
        <li><u>БРЕСТ</u></li>
        <li><u>ВИТЕБСК</u></li>
        <li><u>ГОМЕЛЬ</u></li>
        <li><u>МОГИЛЕВ</u></li>
        <li><u>Бобруйск</u></li>
        <li><u>Борисов</u></li>
        <li><u>Гродно</u></li>
        <li><u>Жлобин</u></li>
        <li><u>Калинковичи</u></li>
        <li><u>Кричев</u></li>
        <li><u>Лида</u></li>
        <li><u>Лунинец</u></li>
        <li><u>Молодечно</u></li>
        <li><u>Орша</u></li>
        <li><u>Осиповичи</u></li>
        <li><u>Пинск</u></li>
        <li><u>Полоцк</u></li>
        <li><u>Слуцк</u></li>
    </ul>
    <div class="phones-container">
        <div class="phones">
            <span class="num">105</span><span class="alt">или</span><span class="code">(+375 17)</span><span class="num">225 70 00, 225 54 10</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/minsk_passenger/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="num">105</span><span class="alt">или</span><span class="code">(+375 163)</span><span class="num">49 21 77</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/baranovichi/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="num">105</span><span class="alt">или</span><span class="code">(+375 162)</span><span class="num">26 40 77</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/brest/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="num">105</span><span class="alt">или</span><span class="code">(+375 212)</span><span class="num">37 82 37</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/vitebsk/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="num">105</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/gomel/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="num">105</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/mogilev/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="num">105</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/bobruisk/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="code">(+375 177)</span><span class="num">73 51 51</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/borisov/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="code">(+375 152)</span><span class="num">73 45 00, 74 45 56</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/grodno/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="num">105</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/zhlobin/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="code">(+375 2345)</span><span class="num">9 54 28</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/kalinkovichi/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="num">105</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/railway_station_krichev/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="code">(+375 1561)</span><span class="num">3 61 06</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/lida/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="code">(+375 1647)</span><span class="num">4 11 28</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/luninets/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="code">(+375 176)</span><span class="num">74 24 23</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/molodechno/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="num">105</span><span class="alt">или</span><span class="code">(+375 216)</span><span class="num">29 32 42</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/orsha/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="num">105</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/osipovichi/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="code">(+375 165)</span><span class="num">35 54 53</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/pinsk/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="code">(+375 214)</span><span class="num">45 62 37</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/polotsk/">Полная справочная информация</a></div>
        </div>
        <div class="phones" style="display:none">
            <span class="code">(+ 375 1795)</span><span class="num">9 85 19</span><div class="full-info"><a href="http://www.rw.by/passengers_services/railway_stations/slutsk/">Полная справочная информация</a></div>
        </div>
    </div>
</div>
<div class="depos-phones-container">
<div class="depos-phones with-clear wysiwyg">
    <ul>
        <li>
            <strong>Бронирование мест на поезда в прямом и местном сообщениях</strong><br>
            Тел.: (+375 17) 151, 225 70 51</li>
        <li>
            <strong>Доставка билетов на дом и предприятия</strong> <em><strong>(временно не осуществляется!)</strong></em><br>
            Тел. (+375 17) 225 62 46</li>
        <li>
            <strong>Прием заявок на оформление проездных документов группам<br>
                за наличный и безналичный расчет по адресу: ул. Бобруйская, 4</strong><br>
            Тел./факс (+375 17) 225 51 46</li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            Тел. (+375 17) 225 62 46, факс 225 51 46</li>
    </ul>
    <div>
        <ul>
            <li>
                <strong>Справочное бюро международных билетных касс</strong><br>
                Тел. (+375 17) 213 17 19</li>
            <li>
                <strong>Прием и оформление к перевозке багажа и грузобагажа</strong><br>
                Тел. (+375 17) 225 67 04</li>
            <li>
                <strong>Хранение ручной клади в автоматических и стационарной камерах хранения</strong><br>
                Тел. (+375 17) 225 65 39</li>
            <li>
                <strong>Администратор билетных касс на вокзале</strong><br>
                Тел. (+375 17) 225 51 42</li>
            <li>
                <strong>Пригородные билетные кассы</strong><br>
                Тел. (+375 17) 225 17 07<br>
                <br>
                &nbsp;</li>
        </ul>
        <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
            Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
            <br>
            <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
            Тел. (+375 17) 225 44 77<br>
            (время работы с 13.00 до 15.00 в рабочие дни)</p>
    </div>
    <p>&nbsp;</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Предварительные кассы, международная касса</strong><br>
            Тел. (+375 163) 49 22 68</li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            Тел. (+375 163) 49 21 73, факс 49 22 62</li>
        <li>
            <p><strong>Бронирование билетов</strong><br>
                Тел. (+375 163) 49 30 05</p>
        </li>
        <li>
            <strong>Багажное отделение</strong><br>
            Тел. (+375 163) 49 37 58</li>
        <li>
            <strong>Комнаты отдыха</strong><br>
            Тел. (+375 163) 49 32 47</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
    <p>&nbsp;</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Международные кассы</strong><br>
            Тел. (+375 162) 26 35 29</li>
        <li>
            <strong>Пригородные кассы</strong><br>
            Тел. (+375 162) 26 28 39</li>
        <li>
            <strong>Бронирование билетов</strong><br>
            Тел. (+375 162) 26 11 22</li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            (+375 162) 26 35 73, факс 26 01 09</li>
        <li>
            <strong>Багажная касса</strong><br>
            Тел. (+375 162) 26 21 42</li>
        <li>
            <strong>Кассы камеры хранения</strong><br>
            Тел. (+375 162) 26 36 02</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Багажное отделение</strong><br>
            тел. (+375&nbsp; 212) 37 84 66</li>
        <li>
            <strong>Заказ билетов с доставкой на дом</strong><br>
            тел. (+375 212) 42 62 05</li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            тел. (+375 212) 42 62 05</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Заказ и бронирование мест на пассажирские поезда</strong><br>
            Тел. (+375 232) 77 30 30</li>
        <li>
            <strong>Заказ и бронирование мест на пассажирские поезда<br>
                через Интернет</strong> <a href="mailto:zakaz@mail.gomel.by">zakaz@mail.gomel.by</a>
        </li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            Тел. (+375 232) 95 26 03, факс 95 26 03</li>
        <li>
            <strong>Багажная касса</strong><br>
            Тел. (+375 232) 95 23 10</li>
        <li>
            <strong>Заказ и бронирование мест в комнатах отдыха</strong><br>
            Тел.: (+375 232) 95 30 72, 95 37 39</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Заведующий билетной кассой</strong><br>
            Тел. (+375 222) 39 21 29</li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            Тел. (+375 222) 39 37 05, факс 39 37 05</li>
        <li>
            <strong>Заказ и бронирование мест на поезда&nbsp;с нумерованными местами</strong><br>
            Тел.105<br>
            <br>
            &nbsp;</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Заведующий билетной кассой</strong><br>
            Тел. (+375 225) 46 53 70</li>
        <li>
            <strong>Заказ и бронирование мест</strong><br>
            Тел. 105</li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            Тел. (+375 225) 46 53 84</li>
        <li>
            <strong>Багажная касса&nbsp;</strong><br>
            Тел.(+ 375 225) 46 53 11<br>
            <br>
            &nbsp;</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>Международная касса<br>
            Тел. (+375 177) 70 44 40</li>
        <li>Багажное отделение<br>
            Тел. (+375 177) 70 42 73</li>
    </ul>
    <p>&nbsp;</p>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Заведующая билетными кассами</strong><br>
            Тел. (+375 152) 73 44 11</li>
        <li>
            <strong>Заказ билетов по телефону (бронирование)</strong><br>
            Тел. (+375 152) 73 44 70</li>
        <li>
            <strong>Багажное отделение</strong><br>
            Тел. (+375 152) 73 43 29</li>
        <li>
            <strong>Комнаты отдыха</strong><br>
            Тел. (+375 152) 73 44 96</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Контактная информация</strong><br>
            Тел./факс (+ 375 2334)&nbsp; 6 23 78</li>
        <li>
            <strong>Заказ и бронирование мест</strong>&nbsp; <strong>на пассажирские поезда</strong><br>
            Тел./факс (+ 375 2334) 6 24 93</li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            Тел. (+375 2334) 6 24 29</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Заказ и бронирование мест на пассажирские поезда &nbsp;</strong>&nbsp; &nbsp;&nbsp;&nbsp;<br>
            Тел. (+375 2345) 9 52 93</li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            Тел. (+375 2345 ) 9 57 29, факс 9 51 00</li>
        <li>
            <strong>Багажная касса</strong><br>
            Тел. (+375 2345) 9 53 55</li>
    </ul>
    <p>&nbsp;</p>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Заведующий билетной кассой</strong><br>
            Тел. (+375 2241) 5 28 17</li>
        <li>
            <strong>Заказ и бронирование мест</strong><br>
            Тел. 105</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Старший билетный кассир</strong><br>
            Тел. (+375 1561) 3 63 92</li>
        <li>
            <strong>Билетные кассы</strong><br>
            Тел.: (+375 1561) 3 60 62, 3 60 68, 3 63 03</li>
        <li>
            <strong>Багажное отделение</strong><br>
            Тел. (+375 1561) 3 61 15</li>
        <li>
            <strong>Комнаты отдыха</strong><br>
            Тел. (+375 1561) 3 61 05</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)<br>
        &nbsp;</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Билетные кассы</strong><br>
            Тел. (+375 1647) 4 11 29</li>
        <li>
            <strong>Cтарший билетный кассир</strong><br>
            Тел. (+375 1647) 4 11 39</li>
        <li>
            <strong>Бронирование мест на пассажирские поезда</strong><br>
            Тел. (+375 1647)&nbsp; 4 11 15</li>
        <li>
            <strong>Багажное отделение</strong><br>
            Тел. (+375 1647) 4 12 58</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Старший билетный кассир</strong><br>
            Тел. (+375 176) 74 24 12</li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            Тел. (+375 176) 74 24 12, факс 74 28 26<br>
            &nbsp;</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Продажа проездных документов</strong><br>
            Тел. (+375 216) 29 20 77</li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            Тел. (+375 216) 29 20 77</li>
        <li>
            <strong>Бронирование мест на пассажирские поезда</strong><br>
            Тел. (+375 216) 29 20 75</li>
        <li>
            <strong>Камеры хранения</strong><br>
            Тел. (+375 216) 29 51 93</li>
        <li>
            <strong>Приём к перевозке багажа и грузобагажа граждан и организаций</strong><br>
            Тел. (+375 216) 29 21 68</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <p><strong>Заведующий билетной кассой</strong><br>
                тел. (+375 2235) 6 24 29</p>
        </li>
        <li>
            <strong>Заказ и бронирование мест</strong><br>
            Тел. 105<br>
            &nbsp;</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <p><strong>Контактная информация</strong>&nbsp;&nbsp;&nbsp;<br>
        Тел.: (+375 165) 35 56 23, факс (+375 165) 35 54 51</p>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Заказ билетов с доставкой на дом</strong><br>
            Тел. (+375 214) 45 68 75</li>
        <li>
            <strong>Оформление проездных документов по безналичному расчету</strong><br>
            Тел. (+375 214) 45 68 75<br>
            &nbsp;</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
<div class="depos-phones with-clear wysiwyg" style="display:none">
    <ul>
        <li>
            <strong>Заведующий билетной кассой</strong><br>
            Тел. (+ 375 1795) 9 85 33</li>
        <li>
            <strong>Заказ и бронирование мест</strong><br>
            Тел. (+ 375 1795) 9 85 19</li>
    </ul>
    <p><strong>Пассажирская служба Белорусской железной дороги</strong><br>
        Адрес: 220030, г. Минск, ул. Ленина, 17, факс 225 46 21<br>
        <br>
        <strong>Горячая линия по вопросам обслуживания пассажиров в поездах</strong><br>
        Тел. (+375 17) 225 44 77<br>
        (время работы с 13.00 до 15.00 в рабочие дни)</p>
</div>
</div>
</div></div>
<div class="popup-106 no-tickets-popup"><div class="wrapper">
    <a class="close"></a><form id="no_tickets"></form>
</div></div>
</div>
<div class="index-top__i"><div class="promo-nav"><ul class="" style="display: inline-block;"><li class=""><span><img src="http://www.rw.by/uploads/promo/68x32_tt/passengers.png" alt="Слайд_new: Пассажирские перевозки"></span></li><li class="selected"><span><img src="http://www.rw.by/uploads/promo/68x32_tt/tme3_slider.png" alt="Маневровый тепловоз ТМЭ3"></span></li><li class=""><span><img src="http://www.rw.by/uploads/promo/68x32_tt/cargo_2.png" alt="Слайд_new: Грузовые перевозки"></span></li><li class=""><span><img src="http://www.rw.by/uploads/promo/68x32_tt/format.png" alt="Слайд_new: Новый формат"></span></li></ul></div>
    <div class="big-links-wrap"><div style="text-align: start; float: none; position: absolute; top: 0px; right: 0px; bottom: 0px; left: 0px; width: 1360px; height: 600px; margin: 0px; overflow: hidden;" class="caroufredsel_wrapper"><div style="text-align: left; float: none; position: absolute; top: 0px; left: 0px; margin: 0px; width: 12240px; height: 600px;" class="big-links-slides">

        <a href="http://www.rw.by/tme3/"><div class="slogan"><div class="slogan-ins">
            <p>Маневровый тепловоз ТМЭ3</p>
            <u></u>
        </div></div></a><a href="/cargo_transportation/"><div class="slogan"><div class="slogan-ins">
        <p>Доставим железно!</p>
        <u></u>
    </div></div></a><a href="/passengers_services/trains/new_format/"><div class="slogan"><div class="slogan-ins">
        <p>Мобильность, экологичность, комфорт</p>
        <u></u>
    </div></div></a><a href="/passengers_services/"><div class="slogan"><div class="slogan-ins">
        <p>Это твой поезд!</p>
        <u></u>
    </div></div></a></div></div></div>
    <div class="top-tools">
        <div class="top-lang">
            <span class="langText"><u>Language</u></span><ul style="display: none;" class="lang-select">
            <li class="active"><span>Русский язык</span></li>
            <li class=""><a href="/be/">Беларуская мова</a></li>
            <li class=""><a href="/en/">English</a></li>
        </ul>
        </div>
        <div class="top-search"><form onsubmit="var str=document.getElementById('searchinp'); if (!str.value || str.value == str.title) return false;" method="get" action="http://www.rw.by/search/">
            <input id="searchinp" class="example gray" title="Поиск" name="search" type="text"><button type="submit"></button>
        </form></div>
        <div class="top-help-online">
            <span class="text"><u>Чем вам помочь?</u></span><ul class="ins">
            <li class="item help"><a href="http://www.rw.by/help/"><u>Справочная информация</u></a></li>
            <li class="item cit"><a href="http://www.rw.by/corporate/contacts/treatment_of_citizens/"><i></i><u>Обращения</u></a></li>
        </ul>
        </div>
        <div class="top-help-105"><a href="#"><u>Справочное бюро</u></a></div>
    </div>
    <div style="" id="main_menu" class="main-menu">
        <a class="site-map" href="#"><span><u>Весь сайт</u></span><i></i></a><div class="menu-items-wrap"><table style="width: auto;" class="menu-items" cellspacing="0"><tbody><tr>
        <td style="width: 194px;"><a href="http://www.rw.by/schedule/suburban/"><em><u><b>Расписание</b></u></em><i></i></a></td>
        <td style="width: 146px;"><a href="http://www.rw.by/tickets/"><em><u><b>Билеты</b></u></em><i></i></a></td>
        <td style="width: 245px;"><a href="http://www.rw.by/passengers_services/"><em><u><b>Услуги пассажирам</b></u></em><i></i></a></td>
        <td style="width: 211px;"><a href="http://www.rw.by/tourism_and_recreation/"><em><u><b>Туризм и отдых</b></u></em><i></i></a></td>
        <td style="width: 252px;"><a href="http://www.rw.by/cargo_transportation/"><em><u><b>Грузовые перевозки</b></u></em><i></i></a></td>
        <td style="width: 214px;" class="last"><a href="http://www.rw.by/corporate/"><em><u><b>Корпоративный</b></u></em><i></i></a></td>
    </tr></tbody></table></div>
    </div>
    <script type="text/javascript">
        document.getElementById('main_menu').style.display='none';
    </script><div class="logo-promo logo-promo-ru"><div style="text-align: start; float: none; position: relative; top: auto; right: auto; bottom: auto; left: auto; width: 226px; height: 42px; margin: 0px; overflow: hidden;" class="caroufredsel_wrapper"><ul style="text-align: left; float: none; position: absolute; top: 0px; left: 0px; margin: 0px; width: 2034px; height: 42px;">
        <li class="white">
        </li><li class="white">
    </li><li class="white">
    </li><li>
    </li></ul></div></div>
</div>
</div>
<div class="content">
    <div class="content__i">

        <div style="padding:50px">
            <div><a href="/index.html">Вернуться на главную страницу работы с анкетами</a></div>
            <br/></br>
            <tiles:insertAttribute name="content" />
        </div>
    </div>
</div>
<div class="footer-extra">
    <div class="copyright">©&nbsp;2014&nbsp;Белорусская железная дорога<br>
        Создание сайта <a href="http://www.db.by/">Cтудия Борового</a><!--Создание сайта:Студия Борового-->
    </div>
    <div class="gov_links">
        <a class="gov-link" href="/ncpi/" target="_blank"><img src="http://www.rw.by/uploads/linkfooter/0x40/gov2.png" alt="Национальный правовой портал РБ"></a><a class="gov-link" href="http://investinbelarus.by/" target="_blank"><img src="http://www.rw.by/uploads/linkfooter/0x40/national_agency.png" alt="Инвестируй в Беларусь"></a><a class="gov-link" href="http://www.government.by/ru/events_d4/" target="_blank"><img src="http://www.rw.by/uploads/linkfooter/0x40/gov3.png" alt="Информация о либерализации"></a><a class="gov-link" href="http://www.fisinter.ru/evrazes.htm" target="_blank"><img src="http://www.rw.by/uploads/linkfooter/0x40/gov1.png" alt="Товары Евразэс"></a><a class="gov-link" href="http://mintrans.gov.by/" target="_blank"><img src="http://www.rw.by/uploads/linkfooter/0x40/mintrans1.png" alt="Министерство транспорта и коммуникаций Республики Беларусь"></a><a class="gov-link" href="http://www.center.gov.by/biznes/" target="_blank"><img src="http://www.rw.by/uploads/linkfooter/0x40/gov4.png" alt="Улучшение бизнес-климата в РБ"></a>
    </div>
</div>
<script type="text/javascript">
    (function (d, w, c) {
        (w[c] = w[c] || []).push(function() {
            try {
                w.yaCounter14212258 = new Ya.Metrika({id:16948750, enableAll: true, webvisor:true});
            } catch(e) { }
        });

        var n = d.getElementsByTagName("script")[0],
                s = d.createElement("script"),
                f = function () { n.parentNode.insertBefore(s, n); };
        s.type = "text/javascript";
        s.async = true;
        s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";

        if (w.opera == "[object Opera]") {
            d.addEventListener("DOMContentLoaded", f);
        } else { f(); }
    })(document, window, "yandex_metrika_callbacks");
</script><noscript><div><img src="http://www.rw.by//mc.yandex.ru/watch/16948750" style="position:absolute; left:-9999px;" alt=""></div></noscript>
</div>
</div>

<div id="ui-datepicker-div" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all"></div>





<%--<div>--%>
<%--<tiles:insertAttribute name="header" />--%>
<%--</div>--%>
<%--<div>--%>
<%--&lt;%&ndash;Блоки входа пользователя и другие&ndash;%&gt;--%>
<%--<c:set var="nameBlock"><spring:message code="programm.userauth" /></c:set>--%>
<%--<%--%>
<%--Locale l=LocaleContextHolder.getLocale();--%>
<%--NodeView n = new NodeView();--%>
<%--n.setTitles("authblock.jsp");--%>
<%--n.setName((String) pageContext.getAttribute("nameBlock"));--%>
<%--request.setAttribute("CURENT_NODEA", n);--%>
<%--%>--%>
<%--<jsp:include page="../../template/blockview.jsp"/>--%>
<%--</div>--%>
<%--<div>--%>
<%--&lt;%&ndash;Ошибки системы&ndash;%&gt;--%>
<%--<jsp:include page="../../template/error.jsp"/>--%>
<%--&lt;%&ndash;Центральная страница&ndash;%&gt;--%>
<%--<tiles:insertAttribute name="content" />--%>
<%--</div>--%>
<%--<div>--%>
<%--<tiles:insertAttribute name="footer" />--%>
<%--</div>--%>
</body>
</html>