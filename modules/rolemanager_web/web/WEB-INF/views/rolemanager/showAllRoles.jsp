<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 09.09.2014
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/WEB-INF/views/manager/cssTable.jsp"/>
<div style="overflow: auto">
    <script type="application/javascript">
        var obj={
            df:function () {
                var thisEl=this;
                jQuery.ajax({
                    url:"/admin/rolemanager/getAllRoles3",
                    dataType:"json",
                    type:"GET"
                })
                        .done(function (data) {
                            var table=jQuery("#tableRole").addClass("rttable");

                            for(var i=0;i<data.length;i++){
                                var vo=data[i];
                                var tr= jQuery("<tr>").addClass("id_"+vo.id);
                                tr.append(jQuery("<td>").text(i+1).append("<input type='hidden' value='"+vo.id+"'/>"));
                                tr.append(jQuery("<td>").text(vo.name));
                                var td=jQuery("<td>");
                                for(var y=0;y<vo.permission.length;y++){
                                    td.append("<div>"+vo.permission[y]+"<div>");
                                }
                                tr.append(td);
                                tr.append(jQuery("<td>").append("<div><a href='/admin/rolemanager/"+vo.id+"/editRole.html'>edit</a></div>").append("<div><a href='#'>delete</a></div>"));
                                table.append(tr);
                               // thisEl.pad(tr,vo);
                            }
                        })
                        .fail(function () {
                            alert("error");
                        });
            },
            pad:function (tr,vo){
                tr.click(function(){
                    var vod=vo;
                    alert(vod.id+"|"+vod.name);
                })
            }
        }
        obj.df();
    </script>
    <div id="tableRole">

    </div>
    <%--<table class="rttable">--%>
    <%----%>
    <%--</table>--%>
</div>
