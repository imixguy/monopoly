/**
 * Created by sedler on 16.12.14.
 */
/**
 * Created by sedler on 15.12.14.
 */
var listRoles;
var credentials;
//var submitForm=false;

var listRolesAjax={
    init:function(){
        $.ajax({
            url: "/admin/rolemanager/getroles",
            dataType : "json",
            type: "GET"
        }).done(function(data) {
            //todo метод обработки ошибки соединения с сервером
            credentials=data[1];
            listRoles=data[0];
            viewRoles.init();
        });
    }
}

var viewRoles={
    init: function(){
        $('#roles').append('<option value="newRoleOpt">Новая роль</option>');
        for(var i=0; i<listRoles.length; i++){
            //не выводить пользователя admin
            if(listRoles[i].name=='administrator'){
                continue;
            }
            $('#roles').append('<option value="'+listRoles[i].name+'">'+listRoles[i].name+'</option>');
        }

//        $('#form').submit(function(){
//            var newUserLogin=$('#login').val();
//            $('#id').val(-1);
//            if($('#users option:selected').val()=='newUser'){
//                checkUserAjax.init(newUserLogin);
//                return submitForm;
//            }else{
//                $('#id').val($('#users option:selected').val());
//                if($('#users option:selected').val()!=$('#login').val()){
//                    checkUserAjax.init(newUserLogin);
//                    return submitForm;
//                }
//                return true;
//            }
//        })

        //событие на список ролей
        $('#roles').change(function(){
            for(var k=0;k< credentials.length; k++){
                $('#check_'+(k+1)).removeAttr("checked");
            }
            if($('#roles option:selected').val()=='newRoleOpt'){
                $('#id').val(-1);
                return;
            }
            var roleName=$('#roles option:selected').val();
            for(var i=0; i<listRoles.length; i++){
                if(roleName==listRoles[i].name){
                    $('#id').val(listRoles[i].id);
                    for(var j=0; j<listRoles[i].permission.length; j++){
                        for(var k=0;k< credentials.length; k++){
                            if(listRoles[i].permission[j]==$('#check_'+(k+1)).val()){
                                $('#check_'+(k+1)).attr("checked","checked");
                                //если установлен креденшнл, то незачем гонять по флажкам, выходим из цикла
                                break;
                            }
                        }
                    }
                }
            }
        });
    }
}

var checkRoleAjax={
    init:function(newRoleName){
        $.ajax({
            url: "/admin/rolemanager/checkrole",
            dataType : "json",
            type: "POST",
            data: {roleName:newRoleName}
        }).done(function(data) {
            if(data==true){
                $('#isRoleName').css('visibility','visible');
            }else{
                $('#isRoleName').css('visibility','hidden');
                $('#form').submit();
            }
        });
    }
}

var start={
    init:function(){
        listRolesAjax.init();
        $('#save').click(function(){
            var newRoleName=$('#name').val();
            if(newRoleName==$('#roles option:selected').val()){
                $('#form').submit();
            }

            checkRoleAjax.init(newRoleName);
        })
    }
}