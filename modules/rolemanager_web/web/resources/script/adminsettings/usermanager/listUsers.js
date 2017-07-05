/**
 * Created by sedler on 10.10.14.
 */

var responseListUsers;
var table;

var listUsersAjax={
    init:function(){
        $.ajax({
            url: "/admin/usermanager/listusers",
            dataType : "json",
            type: "GET"
        }).done(function(data) {
            //todo дописать ошибку связи с сервером
            responseListUsers=data;
            viewUsers.init();
            tableUser.init();
        });
    }
}

var viewUsers={
    init:function(){
        var fullName; //ФИО
        var dataRegistr; //дата регистрации
        var dep;  //принадлежность
        var ecUn; //предприятие
        var proff; //должность
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
            dataRegistr='';
            dep='';
            ecUn='';
            proff='';
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
                    case"dep":
                        dep=dep+responseListUsers[i].userProperties[j].value;
                        break;
                    case"ecUn":
                        ecUn=ecUn+responseListUsers[i].userProperties[j].value;
                        break;
                    case"proff":
                        proff=proff+responseListUsers[i].userProperties[j].value;
                        break;
                }
            }
            dataRegistr=responseListUsers[i].dateCreate;
            $("#row_"+i).append('<td>'+fullName+'</td>'+'<td>'+dep+'</td>'+'<td>'+ecUn+'</td>'+'<td>'+proff+'</td>'+'<td>'+dataRegistr+'</td>');
            $("#row_"+i).append('<td><div style="text-align: center"><input type="checkbox" id="check_'+i+'" checked value="'+responseListUsers[i].id+'">'+'</div></td>');
            if(responseListUsers[i].active==true){
                $('#check_'+i).attr("checked","checked");
            }else{
                $('#check_'+i).removeAttr("checked");
            }
        }

        //подпись флажков на события
        $(":checkbox").change(function () {
            changeUser.init($(this));
        });
    }
}

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

var changeUser={
    init:function(checkbox){
        $.ajax({
            url: "/admin/usermanager/changeuser",
            dataType : "json",
            type: "POST",
            data: { userId : checkbox.attr("value")}
        }).done(function(data){
            //todo дописать ошибку связи с сервером
            if(data==null){
                alert('нет ответа от сервера');
            }else{
                checkbox.val(data);
            }
        })
    }
}

var start={
    init:function(){
        listUsersAjax.init();
    }
}