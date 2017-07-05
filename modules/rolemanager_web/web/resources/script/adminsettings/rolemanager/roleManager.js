/**
 * Created by sedler on 10.10.14.
 */

var changeRole; //id роли, которую надо изменить
var table; //таблица
var credentials //список всех допусков (ответ с сервера)
var listRoles; //список ролей (ответ с сервера)

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
            roleTable.init();
        });
    }
}

var viewRoles={
    init:function(){
        var i, j;
        $('#head').append('<th>Доступ</th>');
        for(i=0; i<listRoles.length; i++){
            //запретить вывод роли администратора -> изменять роли одминистратора доступ
            if(listRoles[i].name=='administrator'){
                continue;
            }
            $('#head').append('<th>'+listRoles[i].name+'</th>');
        }
        for(i=0; i<credentials.length; i++){
            $('#tableBody').append('<tr id="row_'+i+'">');
            $('#row_'+i).append('<td>'+credentials[i]+'</td>');
            for(j=0; j<listRoles.length; j++){
                //запретить вывод роли администратора -> изменять роли одминистратора доступ
                if(listRoles[j].name=='administrator'){
                    continue;
                }
                $('#row_'+i).append('<td><div style="text-align: center"><input type="checkbox" id="check_'+i+j+'" value="'+listRoles[j].name+';'+credentials[i]+'"'+'></div></td>');
                if(this.isCredentialInRole(listRoles[j], credentials[i])){
                    $('#check_'+i+j).attr("checked","checked");
                }else{
                    $('#check_'+i+j).removeAttr("checked");
                }
            }
            $('#tableBody ').append('</tr>');
        }

        //подпись на события для флажков
        $(":checkbox").change(function(){
            changeRole=$(this).val();
            changeRolesAjax.init();
        });
    },

    isCredentialInRole:function(role, credential){
        var i;
        for(i=0; i<role.permission.length; i++){
            if(role.permission[i]==credential){
                return true;
            }
        }
        return false;
    }
}

var changeRolesAjax={
    init:function(){
        $.ajax({
            url: "/admin/rolemanager/editroles",
            dataType : "json",
            type: "POST",
            data: { roleName : changeRole }
        }).done(function(data){
            if(data == null){
                alert("нет ответа с сервера");
            }
        });
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

var start={
    init:function(){
        listRolesAjax.init();
    }
}