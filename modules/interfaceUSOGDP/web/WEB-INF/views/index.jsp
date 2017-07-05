<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<!--
Smart developers always View Source.

This application was built using Apache Flex, an open source framework
for building rich Internet applications that get delivered via the
Flash Player or to desktops and mobile phones via Adobe AIR.

Learn more about Flex at http://flex.apache.org-->
<head>
    <title>КСУПРБЧ</title>
    <meta name="google" value="notranslate"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- Include CSS to eliminate any default margins/padding and set the height of the html element and
         the body element to 100%, because Firefox, or any Gecko based browser, interprets percentage as
         the percentage of the height of its parent container, which has to be set explicitly.  Fix for
         Firefox 3.6 focus border issues.
         Initially, don't display flashContent div so it won't show if JavaScript disabled. - this changed (dima)
    -->
    <style type="text/css">
        html, body {
            height: 100%;
        }

        body {
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #dcdcdc;
        }

        .hidden {
            display: none;
            overflow: auto;
        }


    </style>

    <!-- Enable Browser History by replacing useBrowserHistory tokens with two hyphens -->
    <!-- BEGIN Browser History required section -->
    <link rel="stylesheet" type="text/css" href="history/history.css"/>
    <script type="text/javascript" src="history/history.js"></script>
    <!-- END Browser History required section -->

    <!-- для работы приложения flex-->
    <link rel="stylesheet" type="text/css" href="history/first.css"/>
    <script type="text/javascript">
        function getUserId() {
            return ${userId};
        }
    </script>

    <!--внедрение динамическим методом, так все равно без JS мы даже не залогинимся-->
    <script type="text/javascript" src="swfobject.js"></script>

</head>
<body>

<!-- SWFObject's dynamic embed method replaces this alternative HTML content with Flash content when enough
     JavaScript and Flash plug-in support is available.
-->
<div id="alternative">
    <h3>Для работы сайта необходим Adobe Flash Player версии 11.1 и включенный JavaScript!</h3>
</div>

<div id="container" class="container hidden">
    <div class="header"><a href="http://www.rw.by/corporate/structure/ktc/"><img src="pic/ktc.jpg" alt="КТЦ"
                                                                                 name="Insert_logo"
                                                                                 id="Insert_logo"/></a>
        <!-- end .header --></div>
    <div id="content" class="content">
        <h2>Внимание!</h2>
        <h4>Для работы сайта необходим Adobe Flash Player версии не ниже 11.1 и включенный JavaScript!</h4>
        <br/>

        <p>Обращаем Ваше внимание, что владельцам Windows 8 нет необходимости скачивать Adobe Flash Player плагин, Вы
            можете просто использовать <b>Internet Explorer 10</b>,так как Adobe Flash Player входит в состав
            операционной системы Windows 8, и получает обновление плэйера автоматически. </p>

        <p>Если у Вас браузер <b>Google Chrome</b> и Вы видите это сообщение - значит по каким-то причинам ваш браузер не получает обновления либо его версия ниже 15. Для решение этой проблеммы обновите вручную Google Chrome либо воспользуйтесь альтернативным браузером для просмотра содержимого сайта</p>

        <p>Для <b>обновления (установки)</b> плэйера воспользуйтесь ссылками в следующей таблице:</p>
        <table class="TableBorderFull Semantic">
            <caption class="TableHead LayoutCellSides LayoutRowTop LayoutRowBottom">
	                        <span class="TextTop">
	                            <span class="TextH3 LayoutSmallRow">
	                                Скачать версию Flash Player <b>11.9</b> с нашего сайта</span>
	                            </span>
            </caption>
            <tbody class="TableBorder Link">
            <tr>
                <td class="TableRowDark">Операционная система</td>
                <td class="TableRowDark">Browsers</td>
                <td class="TableRowDark">Установщик</td>
            </tr>
            <tr>
                <td class="TableRowLight"><p>Windows</p></td>
                <td class="TableRowLight">Internet Explorer</td>
                <td class="TableRowLight">
                    <ul>
                        <li><a href="files/install_flash_player_11_active_x.exe">Скачать EXE установщик</a>,<a
                                href="files/install_flash_player_11_active_x.zip">ZIP</a></li>
                        <li><a href="files/install_flash_player_11_active_x.msi">Скачать MSI установщик</a></li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td class="TableRowDark"><p>Windows</p></td>
                <td class="TableRowDark">Plugin-based browsers <br/>(FireFox, Opera и др.)</td>
                <td class="TableRowDark">
                    <ul>
                        <li><a href="files/install_flash_player_11_plugin.exe">Скачать EXE установщик</a>,<a
                                href="files/install_flash_player_11_plugin.zip">ZIP</a></li>
                        <li><a href="files/install_flash_player_11_plugin.msi">Скачать MSI установщик</a></li>
                    </ul>
                </td>
            </tr>
            </tbody>
        </table>
        <br/>

        <p> Для установки плагина потребуется закрытие всех браузеров.</p>
        <br/>

        <p>Так же Вы можете <b>скачать последнюю версию Flash Player-a с сайта производителя - компании Adobe</b> по ссылкам ниже:

        <p>
        <ul>
            <li><a href="http://get.adobe.com/ru/flashplayer/?Lang=Russian">установка для используемого браузера</a>
            </li>
            <li><a href="http://www.adobe.com/support/flashplayer/downloads.html">страница загрузки всех версий</a></li>
        </ul>
        <!-- end .content --></div>
    <div class="footer">
        <p>dima@ktc.rw</p>
        <!-- end .footer --></div>
    <!-- end .container --></div>


<script type="text/javascript">
    // само внедрение
    if (swfobject.hasFlashPlayerVersion("11.1")) {
        var flashvars = {};
        var params = {};
        params.devicefont = "true";
        params.allowfullscreen = "true";
        var attributes = {};
        attributes.id = "ksuprbch";
        swfobject.embedSWF("Main.swf", "alternative", "100%", "100%", "11.1.0", "expressInstall.swf", flashvars, params, attributes);
    } else {
        document.getElementById("alternative").classList.add("hidden");
        document.getElementById("container").classList.remove("hidden");
    }
</script>

</body>
</html>
