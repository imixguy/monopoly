<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/WEB-INF/views/manager/cssTable.jsp"/>
<div style="overflow: auto">
    <script type="application/javascript">
        var allTemplObj={
            df:function () {
                jQuery( "#dialog-confirm" ).dialog({
                    resizable: false,
                    height:140,
                    modal: true,
                    autoOpen: false,
                    buttons: {
                        "Удалить все": function() {
                            jQuery("#tableRole [type=checkbox]:checked").each(function(){
                                thisEl.remDCByID(thisEl.getId(this));
                            });
                            jQuery( this ).dialog( "close" );
                        },
                        "Отмена": function() {
                            jQuery( this ).dialog( "close" );
                        }
                    }
                });
                jQuery( "#dialog-normal" ).dialog({
                    resizable: false,
                    height:140,
                    modal: true,
                    autoOpen: false,
                    buttons: {
                        "Ok": function() {
                            jQuery( this ).dialog( "close" );
                        }
                    }
                });
                var thisEl=this;
                jQuery.ajax({
                    url:"/managercms/dynamiccontent/getAllContainers",
                    dataType:"json",
                    type:"GET"
                }).done(function (data) {
                    var table=jQuery("#tableRole tbody");

                    for(var i=0;i<data.length;i++){
                        var vo=data[i];
                        var tr= jQuery("<tr>");
                        tr.append(jQuery("<td>").text(i+1).append("<input class='idDC' type='hidden' value='"+vo.id+"'/>"));
                        tr.append(jQuery("<td>").text(vo.name));
                        tr.append(jQuery("<td>").text(vo.content));
                        tr.append(jQuery("<td>").text(vo.typeDynCont));
                        tr.append(jQuery("<td>").append("<input type='checkbox' class='chSel'/>"));
                        tr.append(jQuery("<td>").append("<nobr><a href=\"/content/dynamic/"+vo.id+"/view.html\"><input type='button' class='butOp' value='откр.'/></a>" +
                                "<a href=\"/managercms/dynamiccontent/"+vo.id+"/editdc.html\"><input type='button' class='butEd' value='ред.'/></a>" +
                                "<a href=\"/managercms/dynamiccontent/"+vo.id+"/clonedc.html\"><input type='button' class='butClone' value='клон.'/></a>" +
                                "<input type='button' class='butRem' value='удал.'/></nobr>"));
//                                var td=jQuery("<td>");
//                                tr.append(td);
//                                tr.append(jQuery("<td>").append("<div><a href='/admin/rolemanager/"+vo.id+"/editRole.html'>edit</a></div>").append("<div><a href='#'>delete</a></div>"));
                        table.append(tr);
                        // thisEl.pad(tr,vo);
                    }
//                    managercms/dynamiccontent/removedc
                    jQuery('.butRem').click(function(el){thisEl.remDCByID(thisEl.getIdByAction(el));});
//                    jQuery('.butOp').click(function(el){thisEl.openCont(el);});
                }).fail(function () {
                    alert("error");
                });

                jQuery('#butAddNew').click(function(){document.location.href = "/managercms/dynamiccontent/newdc.html";});
                jQuery('#butRemSel').click(function(){
                    jQuery("#dialog-confirm").dialog("open");
                });
            },
            getIdByAction:function(act){
                return this.getId(act.target);
            },
            getId:function(el){
                return jQuery(jQuery(el).parents("tr")[0]).find('input.idDC').val();
            },
            remDCByID:function(id){
                jQuery.ajax({
                    url:"/managercms/dynamiccontent/removedc/"+id,
                    dataType:"json",
                    type:"DELETE"
                }).done(function (data) {
                    if(data){
                        var p=jQuery(jQuery(".idDC[value="+id+"]").parents("tr")[0]).remove();
                        jQuery("#dialog-normal p").text("Удаление выполнено.");
                        jQuery("#dialog-normal").dialog({"title":"Удаление"}).dialog("open");
                    }else{
                        jQuery("#dialog-normal p").text("Не удалось удалить элемент.");
                        jQuery("#dialog-normal").dialog({"title":"Удаление"}).dialog("open");
                    }
                }).fail(function () {
                    jQuery("#dialog-normal p").text("Ошибка, возможно сервер временно не доступен, повторите попытку позже, при повторной ошибке обратитесь к администратору.");
                    jQuery("#dialog-normal").dialog({"title":"Ошибка"}).dialog("open");
                });
            },
            openCont:function (el){
                alert(document.location.origin+"/content/dynamic/"+this.getIdEl(el)+"/view.html");
                document.location.href=document.location.origin+"/content/dynamic/"+this.getIdEl(el)+"/view.html";
            },
            getIdEl:function(el){
                return jQuery(el.target).parents('#tableRole tr').find('.idDC').val();
            }
        }
        jQuery(function(){
            allTemplObj.df();
            <!--<intercept-url pattern="/managercms/dynamiccontent/newdc.html" access="hasRole('ROLE_CREATE_DC')" />-->
        })
    </script>
    <div>
        <table id="tableRole" class="rttable">
            <thead>
            <tr>
                <th>№</th>
                <th>Имя</th>
                <th>Описание</th>
                <th>Тип</th>
                <th><input type='checkbox' class='chSelAll'/></th>
                <th>Действие</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <div id="panSett">
        <input type='button' id='butAddNew' value='Добавить новый'/>
        <input type='button' id='butRemSel' value='Удалить отмеченные'/>
    </div>
    <div id="dialog-confirm" title="Удалить выбранные странички?">
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Вы уверены, что хотите удалить все выбранные странички, без возможности востановления?</p>
    </div>
    <div id="dialog-normal" title="">
        <p></p>
    </div>
</div>
