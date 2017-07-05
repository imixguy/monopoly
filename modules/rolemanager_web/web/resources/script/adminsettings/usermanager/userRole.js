/**
 * Created by sedler on 24.11.14.
 */

var listUsers;
var listRoles;
var table;
var changeUser;

var start={
    init:function(){
        listUsersAjax.init();
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
            listUsers=data;
            listRolesAjax.init();
        });
    }
}

var listRolesAjax={
    init:function(){
        $.ajax({
            url: "/admin/rolemanager/getroles",
            dataType : "json",
            type: "GET"
        }).done(function(data) {
            //todo сделать метод обработки ошибки соединения с сервером
            listRoles=data[0];
            viewUserRole.init();
            userRoleTable.init();
        });
    }
}

var userRoleTable={
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

var viewUserRole={
    init:function(){
        var i, j;
        var fullName; //ФИО
        $('#head').append('<th>Пользователь</th>');
        for(i=0; i<listRoles.length; i++){
            $('#head').append('<th>'+listRoles[i].name+'</th>');
        }
        for(i=0; i<listUsers.length; i++){
            //не выводить пользователя admin с id = 2
            if(listUsers[i].id==2){
                continue;
            }
            fullName='';
            for(j=0; j<listUsers[i].userProperties.length; j++){
                switch (listUsers[i].userProperties[j].keyt){
                    case "fname":
                        fullName=fullName+listUsers[i].userProperties[j].value+' ';
                        break;
                    case "lname":
                        fullName=fullName+listUsers[i].userProperties[j].value+' ';
                        break;
                    case "pname":
                        fullName=fullName+listUsers[i].userProperties[j].value+' ';
                        break;
                }
            }
            $('#tableBody').append('<tr id="row_'+i+'"></tr>');
            $('#row_'+i).append('<td>'+fullName+'</td>');
            for(j=0; j<listRoles.length; j++){
                $('#row_'+i).append('<td><div style="text-align: center"><input type="checkbox" id="check_'+i+j+'" value="'+listRoles[j].name+';'+listUsers[i].id+'"'+'></div></td>');
                if(this.isRoleInUser(listRoles[j], listUsers[i])){
                    $('#check_'+i+j).attr("checked","checked");
                }else{
                    $('#check_'+i+j).removeAttr("checked");
                }
            }
        }

//        подпись на события для флажков
        $(":checkbox").change(function(){
            changeUser=$(this).val();
            changeUserRolesAjax.init($(this));
        });
    },

    isRoleInUser:function(role, user){
        var i;
        for(i=0; i<user.roles.length; i++){
            if(role.name==user.roles[i].name){
                return true;
            }
        }
        return false;
    }
}


var changeUserRolesAjax={
    init : function(checkbox){
        $.ajax({
            url: "/admin/usermanager/user-role",
            dataType : "json",
            type: "POST",
            data: { userIdRoleName : changeUser }
        }).done(function(data){
            if(data == null){
                //todo дописать метод обработки ошибки с сервером
                alert("нет ответа с сервера");
            }else{
                var UserId=checkbox.val().split(';');
                for(var i=0; i<listUsers.length; i++){
                    if(listUsers[i].id==UserId[1]){
                        listUsers[i].id=data;
                        for(var j=0; j<listRoles.length; j++){
                            $('#check_'+i+j).val(listRoles[j].name+';'+data);
                        }
                    }
                }
            }
        });
    }
}