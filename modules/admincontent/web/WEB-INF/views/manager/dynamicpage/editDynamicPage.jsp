<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<form:form method="POST" action="/managercms/dynamiccontent/savedcontent.html" modelAttribute="dynamicContent">
    <div>
        <form:hidden class="" rows="5" cols="42" path="id" id="idCont"/>
        <form:hidden class="" rows="5" cols="42" path="clone"/>
        <label>
            Название:
            <span class="form-required" title="Обязательное поле">*</span>
        </label>
        <form:input size="60" maxlength="60" path="name" />
    </div>
    <div>
        <label>
            Тип динамического контента:
        </label>
        <form:select id="typeDynCont" path="typeDynCont">
            <form:option value="page" label="page"/>
            <form:option value="head" label="head"/>
            <form:option value="container" label="container"/>
        </form:select>
    </div>
    <div id="redirURL" style="display: none;">
        <label>RegEdit шаблон URL-а страницы (/node/.*/viewpage.html)</label>
        <div>
            <form:select path="redirectUrl" items="${dynamicContent.redirectUrl}" multiple="true" size="3" style="height:100%;min-width: 330px;"/>
        </div>
        <div><input type="button" id="butAddUrl" value="Добавить URL"/> <input type="button" id="butRemUrl" value="Удалить выделенные URL"/></div>
        <div id="winEnterUrl">
            <div>
                <label>URL: </label><input type="text" value="" id="urlnew">
            </div>
        </div>
    </div>
    <div>
        <label>
            Описание:
        </label>
        <br/>
        <form:textarea id="contentpage" rows="5" cols="42" path="content"/>
    </div>
    <div>
        <label>
            Шаблон:
        </label>
        <form:select id="shName" path="shablonName">
        </form:select>
        <script type="application/javascript">
            var fLC={
                id_sh:'${dynamicContent.shablonName}',
                init:function(){
                    $('#typeDynCont').change(function(){fLC.loadtypetemplate();});
                    $('#cancalEdit').click(function(){window.history.back();return false;});
                    $('#shName').change(function(){fLC.loadconteiners()});

                    jQuery( "#winEnterUrl" ).dialog({
                        resizable: false,
                        height:140,
                        modal: true,
                        autoOpen: false,
                        buttons: {
                            "Ок": function() {
                                var url= jQuery("#urlnew").val();
                                jQuery("#redirectUrl").append('<option value="'+url+'">'+url+'</option>');
                                jQuery( this ).dialog( "close" );
                            },
                            "Отмена": function() {
                                jQuery( this ).dialog( "close" );
                            }
                        }
                    });

                    $('#butAddUrl').click(function(){jQuery("#winEnterUrl").dialog("open");return false;});
                    $('#butRemUrl').click(function(){jQuery("#redirectUrl option:selected").remove();return false;});
                    $('#dynamicContent').submit(function(){jQuery("#redirectUrl option").attr('selected','selected');});
                    jQuery("#redirectUrl option").attr('selected',false);
                    this.loadtypetemplate();
                },
                loadtypetemplate:function(){
                    if($('#typeDynCont').val()=='page'){
                        jQuery('#redirURL').show();
                    }else{
                        jQuery('#redirURL').hide();
                    }
                    $( "#shName").empty();
                    $( "#shName" ).append('<option value="null">&lt;Шаблон не выбран&gt;</option>');
                    var thisEl=this;
                    $.ajax({
                        url: "/managercms/dynamiccontent/getTemplates",
                        dataType : "json",
                        type: "GET",
                        data: { typet : $('#typeDynCont').val() }
                    }).done(function(data) {
                        $( "#shName").empty();
                        if(data.length<1){
                            $( "#shName" ).append('<option value="null">&lt;Шаблоны отсутствуют&gt;</option>');
                            $( "#dfs").empty();
                        }
                        for(var d=0;d<data.length;d++){
                            $( "#shName" ).append('<option value="'+data[d].id_node+'">'+data[d].value+'</option>');
                        }
                        thisEl.checkShabl();
                        thisEl.loadconteiners();
                    });
                } ,
                checkShabl:function(){
                    $( "#shName option[value="+this.id_sh+"]").attr("selected","selected");
                },
                loadconteiners:function(){
                    $( "#dfs").empty();
                    var thisEl=this;
                    $.ajax({
                        url: "/managercms/dynamiccontent/getContainers",
                        dataType : "json",
                        type: "GET",
                        data: { id_cont : $("#shName").val() }
                    }).done(function(data) {
                        for(var d=0;d<data.length;d++){
                            $( "#dfs" ).append('<div><label><input type="hidden"  name="cont['+d+'].name" value="'+data[d]+'">'+data[d]+' :</label>' +
                                    '<span id="nSel_'+d+'"></span><input class="c_content" type="text" name="cont['+d+'].content" value=""/></div>');
                            $('#nSel_'+d).append($('#hsfC')[0].innerHTML);
                            $('#nSel_'+d+' select').attr('name','cont['+d+'].type').attr('id','').addClass("c_type");
                        }
                        thisEl.loaddataconteiners();
                    });
                },
                loaddataconteiners:function(){
                    if($( "#idCont").val()==""){
                        return;
                    }
                    var thisEl=this;
                    $.ajax({
                        url: "/managercms/dynamiccontent/getDataContainers",
                        dataType : "json",
                        type: "GET",
                        data: { id_cont : $("#idCont").val() }
                    }).done(function(data) {
                        for(var i=0;i<data.length;i++){
                            var el=$("#dfs [value="+data[i].name+"]").parent().parent();
                            el.find('select[class=c_type] option[value='+data[i].type+']')[0].selected=true;
                            el.find("[class=c_content]").val(data[i].content);
                        }
                        thisEl.seartTextSel();
                        $('.c_type').change(function(){
                            thisEl.checkText(this);
                        });
                    });
                },
                replaceTextEl:function(el){
                    var thisEl=this;
                    var inp=$($(el).parents('div')[0]).find('input[type=text]');
                    $($(el).parents('div')[0]).find('input[type=text]').replaceWith("<div class='textCh'><textarea id='ta_"+inp.attr('name')+"' name='"+inp.attr('name')+"' rows='5' cols='42' class='textEdit'>"+inp.val()+"</textarea></div>");
                    injeditor.init();
                },
                replaceTextEl2:function(el){
                    var inp=$($(el).parents('div')[0]).find('textarea');
                    $($(el).parents('div')[0]).find('.textCh').replaceWith('<input class="c_content" type="text" name="'+inp.attr('name')+'" value="'+inp.text()+'"/>')
                },
                checkText:function(el){
                    if(el.value=='text'){
                        this.replaceTextEl(el);
                    }else{
                        this.replaceTextEl2(el);
                    }
                },
                seartTextSel:function(){
                    var thisEl=this;
                    $('option:selected[value=text]').each(function(){
                       thisEl.replaceTextEl($(this).parents('select')[0]);
                    });
                }
            }
            $(function(){
                fLC.init();
            })
        </script>
    </div>
    <div style="display: none" id="hsfC">
        <form:select path="typeDef">
            <form:options items="${dynamicContent.typeAtribute}" />
        </form:select>
    </div>
    <div id="dfs">
    </div>
    <div>
        <form:button name="Отправить" value="Отправить">  Отправить </form:button> <button name="Отмена" value="Отмена" id="cancalEdit"> Отмена </button>
    </div>
</form:form>