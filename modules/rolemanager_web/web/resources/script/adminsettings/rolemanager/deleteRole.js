/**
 * Created by sedler on 11.11.14.
 */
var credentials //список всех допусков (ответ с сервера)
var listRoles; //список ролей (ответ с сервера)
var rolesId;    //ид ролей на удаление
var table;
var checkDraw=false;  //флаг на перерисовку таблицы


var start={
    init:function(){
        listRolesAjax.init();
    }
}

var listRolesAjax={
    init:function(){
        $.ajax({
            url: "/admin/rolemanager/getroles",
            dataType : "json",
            type: "GET"
        }).done(function(data) {
            credentials=data[1];
            listRoles=data[0];
            viewRoles.init();
        });
    }
}

var deleteRoleAjax={
    init:function(){
        $.ajax({
            url: "/admin/rolemanager/deleterole",
            dataType : "json",
            type: "POST",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            data: JSON.stringify(rolesId)
        }).done(function(data) {
            if(data=null){
                alert('нет связи с сервером');
            }else{
                $('#tableBody').remove();
                table=null;
                listRoles=null;
                listRolesAjax.init();
            }
        });
    }
}


var viewRoles={
    init:function(){
        var i, j;
        for(i=0; i<listRoles.length; i++){
            //не выводит роль администратора
            if(listRoles[i].name=='administrator'){
                continue;
            }
            $('#roleTable').append('<tbody id="tableBody"></tbody>');
            $('#tableBody').append('<tr id="row_'+i+'">');
            $('#row_'+i).append('<td>'+listRoles[i].name+'</td>');
            $('#row_'+i).append('<td><div style="text-align: center"><input type="checkbox" id="check_'+i+'" value="'+listRoles[i].id+'"'+'></div></td>');
            $('#check_'+i).removeAttr("checked");
            $('#tableBody').append('</tr>');
        }
        if(!checkDraw){
            $('#head').append('<th>Роль</th>');
            $('#head').append('<th>Удалить</th>');
            roleTable.init();
            checkDraw=true;
            $('#delete').click(function(){
                rolesId=[];
                for(var i=0; i<listRoles.length; i++){
                    if($('#check_'+i).prop("checked")){
                        rolesId[rolesId.length]=$('#check_'+i).attr("value");
                    }
                }
                deleteRoleAjax.init();
            });
        }else{
            //отрисовать таблицу заново (перерисовать)
        }
    }
}

var roleTable={
    init: function(){
        table=$(document).ready(function() {
            $('#roleTable').DataTable({
//                    "data":dataSet,
//                "scrollY": 200,
//                "scrollX": true,
                "paging":   false,
                "ordering": false,
                "info":     false,
                "language": {
                    "lengthMenu": "Выводить по  _MENU_ шт.",
                    "search":"Поиск",
                    "paginate": {
                        "first":      "Первая",
                        "last":       "Последняя",
                        "next":       "Следующая",
                        "previous":   "Предыдущая"
                    },
                    "info": "Показано с _START_ по _END_ из _TOTAL_ пользователей",
                    "infoEmpty":      "Показано с 0 по 0 из 0 пользователей",
                    "zeroRecords":"Данные не найдены",
                    "emptyTable": "Данные отсутствуют"
                }
            });
        } );

        $('#roleTable').on('click', 'tr', function () {
            if($('td', this).attr('bgcolor')=='#00FFFF'){
                $('td', this).attr('bgcolor', '');
            }else{
                $('td', this).attr('bgcolor', '#00FFFF');
            }
        } );
    }
}