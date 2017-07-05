/**
 * Created by sedler on 19.11.14.
 */

var responseListUsers;
var table;
var usersId;  //ид юзеров на удаление
var usersIdJSON;  //ид юзеров на удаление в формате JSON
var reload=false; // флаг для перерисовки таблицы

var tableUser={
    init: function(){
        table=$(document).ready(function() {
            $('#userTable').DataTable({
//                    "data":dataSet,
//                "scrollY": 200,
//                "scrollX": true,
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
            } );
        } );

        $('#userTable').on('click', 'tr', function () {
            if($('td', this).attr('bgcolor')=='#00FFFF'){
                $('td', this).attr('bgcolor', '');
            }else{
                $('td', this).attr('bgcolor', '#00FFFF');
            }
            return;
        });
    }
}

var listUsersAjax={
    init:function(){
        $.ajax({
            url: "/admin/usermanager/listusers",
            dataType : "json",
            type: "GET"
        }).done(function(data) {
            //todo сделать метод обработки ошибки соединения с сервером
            responseListUsers=data;
            viewUsers.init();
        });
    }
}

var viewUsers={
    init:function(){
        var fullName; //ФИО
        if(responseListUsers.length==0 || responseListUsers.length==undefined){
            return;
        }
        $('#userTable').append('<tbody id="tableBody"></tbody>');
        for(var i=0; i<responseListUsers.length; i++){
            //не выводить пользователя admin с id = 2
            if(responseListUsers[i].id==2){
                continue;
            }
            fullName='';
            $("#tableBody").append('<tr id="row_'+i+'"></tr>');
            for(var j=0; j<responseListUsers[i].userProperties.length; j++){
                switch (responseListUsers[i].userProperties[j].keyt){
                    case "fname":
                        fullName=fullName+responseListUsers[i].userProperties[j].value+' ';
                        break;
                    case "lname":
                        fullName=fullName+responseListUsers[i].userProperties[j].value+' ';
                        break;
                    case "pname":
                        fullName=fullName+responseListUsers[i].userProperties[j].value+' ';
                        break;
                }
            }
            $("#row_"+i).append('<td>'+fullName+'</td>');
            $("#row_"+i).append('<td><div style="text-align: center"><input type="checkbox" id="check_'+i+'" style="margin: 0 auto" checked value="'+responseListUsers[i].id+'">'+'</div></td>');
            $('#check_'+i).removeAttr("checked");
        }

        //подпись кнопки удалить  на событие
        if(!reload){
            $("#delete").click(function () {
                usersId=[];
                for(var i=0; i<responseListUsers.length; i++){
                    if($('#check_'+i).prop("checked")){
                        usersId[usersId.length]=$('#check_'+i).attr("value");
                    }
                }
                usersIdJSON=JSON.stringify(usersId);
                deleteUsersAjax.init();
            });

            //отрисовка таблицы
            tableUser.init();
            reload=true;
        }else{
            //найти как перерисовать таблицу!!!
//            table.fnDraw();
//            tableUser.init();
        }

    }
}

var deleteUsersAjax={
    init:function(){
        $.ajax({
            url: "/admin/usermanager/deleteuser",
            dataType : "json",
            type: "POST",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            data: usersIdJSON
        }).done(function(data) {
            //todo сделать метод обработки ошибки соединения с сервером
            if(data==null){
            }else{
                $('#tableBody').remove();
                responseListUsers=null;
                listUsersAjax.init();
            }
        });
    }
}

var start={
    init:function(){
        listUsersAjax.init();
    }
}

