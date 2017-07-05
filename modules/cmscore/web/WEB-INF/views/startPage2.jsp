<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 28.01.13
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<h1><spring:message code="programm.inewpage"/></h1>
<br>

<spring:message code="errors.range"
                arguments="'Miha';'sdf';'1232321'"
                htmlEscape="false"
                argumentSeparator=";"/>
<form action="sample_posteddata.jsp" method="get">
    <p>
        <label for="editor1">Editor 1:</label>
        <textarea cols="80" id="editor1" name="editor1" rows="10"></textarea>
    </p>
    <p>
        <input type="submit" value="Submit" />
    </p>
</form>

<ckeditor:replace replace="editor1" basePath="/resources/script/ckeditor/" />

<script type="text/javascript" src="/resources/script/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
    var finder = new CKFinder();
    finder.basePath = '/resources/script/ckfinder/';
    finder.create();
</script>

<div style="display: none;">
<link href="/resources/script/myfinder/skins/kama/appmy.css?t=D7EC21H" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/resources/script/ckeditor/skins/moono/dialog.css?t=DAED">
<script src="/resources/script/jquery/jquery-1.7.1.min.js"></script>
<script>
    (function(){window.ImixFinder=function(a,b,c,d){
        this.lang={};
        this.a=a;
    }}())
    var ImixFinder=new ImixFinder("sdf");
    var lang='ru';


</script>
<script src="/resources/script/myfinder/lang/ru.js"></script>
<script>
    lang=ImixFinder.lang[lang];
    $(document).ready(function(){
        $('#folders_view_label').html(lang.FoldersTitle);
        $('#cke_9_label').text(lang.Upload).parent().attr('title',lang.Upload).attr('href','javascript:void("'+lang.Upload+'")');
        $('#cke_10_label').text(lang.Refresh).parent().attr('title',lang.Refresh).attr('href','javascript:void("'+lang.Refresh+'")');
        $('#cke_11_label').text(lang.Settings).parent().attr('title',lang.Settings).attr('href','javascript:void("'+lang.Settings+'")');
        $('#cke_12_label').text(lang.Maximize.minimize).parent().attr('title',lang.Maximize.minimize).attr('href','javascript:void("'+lang.Maximize.minimize+'")');
        $('#cke_13_label').text(lang.Help).parent().attr('title',lang.Help).attr('href','javascript:void("'+lang.Help+'")');
        $('#cke_16').text(lang.Search.searchPlaceholder);
        $('.message_content').text(lang.FolderLoading);
        $('#status_view').html('<p>'+lang.FilesCountMany.replace('%1',1)+'</p>');


    });
</script>

<div class="cke_dialog_background_cover" style="position: fixed; z-index: 10000; top: 0px; left: 0px; background-color: white; opacity: 0.5; width: 1900px; height: 1200px;"></div>
<div lang="ru" class="cke_reset_all cke_1 cke_editor_editor1_dialog " style="display: block;">
    <table style="position: absolute; top: 200px; left: 180px; z-index: 10010;" class="cke_dialog cke_browser_gecko cke_ltr">
        <tbody>
        <tr>
            <td>
                <div class="cke_dialog_body">
                    <div class="cke_dialog_title" id="cke_dialog_title_69" style="-moz-user-select: none;">Выбор на сервере</div>
                    <a title="Закрыть" href="javascript:void(0)" class="cke_dialog_close_button" id="cke_dialog_close_button_69" style="-moz-user-select: none;">
                        <span class="cke_label">X</span>
                    </a>

                    <table class="cke_dialog_contents" style="margin-top: 0;">
                        <tbody>
                        <tr >
                            <td class="cke_dialog_contents_body" id="cke_dialog_contents_69" style="width:840px; height: 440px;">



                                <div id="imixfinder" style="position: static">
                                    <!-- 1. CKE Skin class. -->
                                    <div class="fake_wrapper cke_skin_kama">
                                        <!-- 2. High contrast class. -->
                                        <div class="fake_wrapper">
                                            <!-- Applicable: hc cke_hc --><!-- 3. Browser class. -->
                                            <div class="fake_wrapper browser_gecko">
                                                <!-- 4. RTL class. -->
                                                <div class="fake_wrapper cke_ltr">
                                                    <!-- Applicable: rtl cke_rtl --><!-- 5. Layout class. -->
                                                    <div class="fake_wrapper">
                                                        <div class="columns_2" id="imixfinder_view">
                                                            <!-- Applicable: columns_1 columns_2 -->
                                                            <div style="width: 152px" class="container" id="sidebar_container">
                                                                <div class="wrapper" id="sidebar_wrapper">
                                                                    <div class="view widget" id="folders_view" style="height: 410px;">
                                                                        <h2 id="folders_view_label"></h2>
                                                                        <div class="folder_tree_wrapper wrapper"><div class="selection" style="height: 17px; top: 0px; display: block;"></div>
                                                                            <ul class="folder_tree no_list">
                                                                                <li id="f1" class="closable selected">
                                                                                    <span onclick="void(0)" class="expander" aria-expanded="true"></span>
                                                                                    <a href="javascript:void(0)">userfiles</a>
                                                                                    <ul>
                                                                                        <!--<li class="openable" id="f4">-->
                                                                                        <!--<span onclick="void(0)" class="expander"></span>-->
                                                                                        <!--<a href="javascript:void(0)">Public Folder</a>-->
                                                                                        <!--<ul></ul>-->
                                                                                        <!--</li>-->
                                                                                    </ul>
                                                                                </li>
                                                                                <!--<li class="openable" id="f2">-->
                                                                                <!--<span onclick="void(0)" class="expander"></span>-->
                                                                                <!--<a href="javascript:void(0)">Images</a>-->
                                                                                <!--<ul></ul>-->
                                                                                <!--</li>-->
                                                                                <!--<li class="openable nochildren" id="f3">-->
                                                                                <!--<span class="expander"></span>-->
                                                                                <!--<a href="javascript:void(0)">Flash</a>-->
                                                                                <!--<ul></ul>-->
                                                                                <!--</li>-->
                                                                                <!--<li class="openable nochildren" id="f0">-->
                                                                                <!--<span class="expander"></span>-->
                                                                                <!--<a href="javascript:void(0)">Файлы</a>-->
                                                                                <!--<ul></ul>-->
                                                                                <!--</li>-->
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="container" id="main_container">
                                                                <div class="view" id="toolbar_view"><div class="cke_toolbox cke_compatibility">
                                                                    <span class="cke_voice_label" id="cke_7"></span>
                                    <span class="cke_toolbar" id="cke_8">
                                        <span class="cke_toolbar_start"></span><span class="cke_toolgroup">
                                        <span class="cke_button">
                                            <a  title="" href="" class="cke_button_upload cke_disabled" id="cke_9" style="">
                                                <span class="cke_icon"></span>
                                                <span class="cke_label" id="cke_9_label"><span class="cke_accessibility"></span></span>
                                            </a>
                                        </span>
                                        <span class="cke_button">
                                            <a title="" href="" class="cke_button_refresh cke_off" id="cke_10" style="">
                                                <span class="cke_icon"></span>
                                                <span class="cke_label" id="cke_10_label"></span>
                                            </a>
                                        </span>
                                        <span class="cke_button">
                                            <a title="" href="" class="cke_button_settings cke_disabled" id="cke_11" style="">
                                                <span class="cke_icon"></span>
                                                <span class="cke_label" id="cke_11_label"></span>
                                            </a></span><span class="cke_button">
                                        <a title="" href="" class="cke_button_maximize cke_disabled" id="cke_12" style="">
                                            <span class="cke_icon"></span>
                                            <span class="cke_label" id="cke_12_label"></span>
                                        </a>
                                    </span>
                                        <span class="cke_button">
                                            <a title="" href="" class="cke_disabled cke_button_help" id="cke_13" style="">
                                                <span class="cke_icon"></span>
                                                <span class="cke_label" id="cke_13_label"></span>
                                            </a>
                                        </span>
                                    </span>
                                        <span class="cke_toolbar_end"></span>
                                    </span>
                                    <span id="cke_14" class="cke_search_box" style="margin-right: 30px;">
                                        <span class="cke_toolbar_start"></span>
                                        <input id="cke_15">
                                        <span class="cke_search_placeholder" id="cke_16" style="display: none;"></span>
                                        <span class="cke_toolbar_end"></span>
                                    </span>
                                                                </div>
                                                                </div>
                                                                <div class="view" id="panel_view" style="display: none;">
                                                                    <div class="panel_widget widget show_border" id="panel_widget"></div>
                                                                </div>
                                                                <div class="view widget files_thumbnails" id="files_view" style="height: 334px;">
                                                                    <h4 class="message_content"></h4>
                                                                    <div class="files_thumbnails fake no_list">
                                                                        <a style="width: 106px" title="Car.jpg" href="javascript:void(0)" class="file_entry" id="r0">
                                                                            <div class="image">
                                                                                <div style="width: 96px; height: 96px; background-image: url('../../avataruser/user0000.gif');"></div>
                                                                            </div>
                                                                            <h5 id="r0_label">Car.jpg</h5>
                                            <span class="details" id="r0_details">
                                                <span class="extra">02.12.2013 7:45</span>
                                                <span>24 Kб</span>
                                            </span>
                                                                        </a>
                                                                        <a style="width: 106px" title="Porsche Approved.pdf" href="javascript:void(0)" class="file_entry" id="r1">
                                                                            <div class="image">
                                                                                <div style="width: 96px; height: 96px; background-image: url('/resources/script/myfinder/skins/kama/images/icons/32/pdf.gif');"></div>
                                                                            </div>
                                                                            <h5 id="r1_label">Porsche Approved.pdf</h5>
                                            <span class="details" id="r1_details">
                                                <span class="extra">02.12.2013 7:45</span>
                                                <span>526 Kб</span></span>
                                                                        </a>
                                                                    </div>
                                                                    <table class="files_details"></table>
                                                                </div>
                                                                <div class="view" id="status_view">

                                                                </div>
                                                                <div style="display: none; position: absolute;" id="dragged_container"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>



                            </td>
                        </tr>
                        <tr>
                            <td role="presentation" class="cke_dialog_footer" id="cke_dialog_footer_69">
                                <div onmousedown="CKEDITOR.tools.callFunction(121, event )" title="Перетащите для изменения размера" class="cke_resizer cke_resizer_ltr">◢</div>
                                <table class="cke_dialog_ui_hbox cke_dialog_footer_buttons" id="cke_133_uiElement" role="presentation">
                                    <tbody>
                                    <tr class="cke_dialog_ui_hbox">
                                        <td role="presentation" class="cke_dialog_ui_hbox_first">
                                            <a id="cke_130_uiElement" class="cke_dialog_ui_button cke_dialog_ui_button_ok" title="ОК" href="javascript:void(0)" style="">
                                                <span class="cke_dialog_ui_button" id="cke_129_label">ОК</span>
                                            </a>
                                        </td>
                                        <td role="presentation" class="cke_dialog_ui_hbox_last">
                                            <a id="cke_132_uiElement" class="cke_dialog_ui_button cke_dialog_ui_button_cancel" title="Отмена" href="javascript:void(0)" style="">
                                                <span class="cke_dialog_ui_button" id="cke_131_label">Отмена</span>
                                            </a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</div>

<%--<div class="buttonPanelR"><div class="buttonPanel"><div class="button">Добавить</div><div class="button">Редактировать</div><div class="button">Удалить</div></div></div>--%>
