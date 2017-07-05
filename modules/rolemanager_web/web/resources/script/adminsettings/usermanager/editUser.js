/**
 * Created by sedler on 15.12.14.
 */
var responseListUsers;
var submitForm=false;
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
        });
    }
}

var viewUsers={
    init: function(){
        $('#users').append('<option value="newUser">Новый пользователь</option>');
        for(var i=0; i<responseListUsers.length; i++){
            //не выводить пользователя admin
            if(responseListUsers[i].id==2){
                continue;
            }
            $('#users').append('<option value="'+responseListUsers[i].id+'">'+responseListUsers[i].login+'</option>');
        }

        $('#form').submit(function(){
            var newUserLogin=$('#login').val();
            $('#id').val(-1);
            if($('#users option:selected').val()=='newUser'){
                checkUserAjax.init(newUserLogin);
                return submitForm;
            }else{
                $('#id').val($('#users option:selected').val());
                if($('#users option:selected').val()!=$('#login').val()){
                    checkUserAjax.init(newUserLogin);
                    return submitForm;
                }
                return true;
            }
        })

        //событие на список пользователей
        $('#users').change(function(){
            var userId=$(this).val();
            var changeUser;
            var i=0;
            $('#login').val('');
            $('#password').val('');
            $('#confirmPassword').val('');

            $('#fname').val('');
            $('#lname').val('');
            $('#pname').val('');

            $('#workPhone').val('');
            $('#mobilePhone').val('');
            $('#email').val('');
            if(userId!='newUser'){
                for(i=0; i<responseListUsers.length; i++){
                    if(responseListUsers[i].id==userId){
                        changeUser=responseListUsers[i];
                    }
                }
                for(i=0; i<changeUser.userProperties.length; i++){
                    switch (changeUser.userProperties[i].keyt){
                        case "fname":
                            $('#fname').val(changeUser.userProperties[i].value);
                            break;
                        case "lname":
                            $('#lname').val(changeUser.userProperties[i].value);
                            break;
                        case "pname":
                            $('#pname').val(changeUser.userProperties[i].value);
                            break;
                        case"wphone":
                            $('#workPhone').val(changeUser.userProperties[i].value);
                            break;
                        case"mphone":
                            $('#mobilePhone').val(changeUser.userProperties[i].value);
                            break;
                        case"email":
                            $('#email').val(changeUser.userProperties[i].value);
                            break;
                    }
                }
                $('#login').val(changeUser.login);
            }
        });
    }
}

var checkUserAjax={
    init:function(newUserLogin){
        $.ajax({
            url: "/admin/usermanager/checkuserlogin",
            dataType : "json",
            type: "POST",
            async: false,
            data: {userLogin:newUserLogin}
        }).done(function(data) {
            //todo дописать (нет соединения с сервером, продумать алгоритм и убрать async: false)!!!!!
            submitForm=data;
            if(data==true){
                $('#isUserName').css('visibility','hidden');
            }else{
                $('#isUserName').css('visibility','visible');
            }
        });
    }
}

var start={
    init:function(){
        listUsersAjax.init();
    }
}