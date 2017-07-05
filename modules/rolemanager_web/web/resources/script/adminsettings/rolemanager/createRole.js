/**
 * Created by sedler on 17.11.14.
 */
var table; //таблица
var credentials //список всех допусков (ответ с сервера)
var listRoles; //список ролей (ответ с сервера)
var newRole; //новая роль







var start={
    init:function(){
        $('#save').click(function(){
            var newRoleName=$('#name').val();
            checkRoleAjax.init(newRoleName);
        })
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