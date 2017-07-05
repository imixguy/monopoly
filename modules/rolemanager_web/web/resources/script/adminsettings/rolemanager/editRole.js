/**
 * Created by sedler on 18.11.14.
 */
var credentials //список всех допусков (ответ с сервера)
var listRoles; //список ролей (ответ с сервера)
var editRole;    //измененнная роль
var changeRole   //флаг на изменение роли

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

var editRoleAjax={
    init:function(){
        $.ajax({
            url: "/admin/rolemanager/editrole",
            dataType : "json",
            type: "POST",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            data: JSON.stringify(editRole)
        }).done(function(data) {
            if(data=null){
                alert('нет связи с сервером');
            }else{
                $('#listRoles option:selected').text(editRole.name);
            }
        });
    }
}




var viewRoles={
    init:function(){
        for(var i=0; i<listRoles.length; i++){
            //запретить вывод роли администратора -> запретить удаление роли администратора
            if(listRoles[i].name=='administrator'){
                continue;
            }
            $('#listRoles').append('<option value="'+listRoles[i].id+'">'+listRoles[i].name+'</option>');
        }

        for(var i=0; i<credentials.length;i++){
            $('#credentials').append('<li><input type="checkbox" value="'+credentials[i]+'" id="cred_'+i+'"><label for="cred_'+i+'">'+credentials[i]+'</label></li>');
        }

        $('#listRoles').change(function () {
            var optionSelected = $('#listRoles option:selected').text();
            $('#name').val(optionSelected);
            var i , j;
            var role='';
            var check=false;
            for(i=0; i<listRoles.length; i++){
                if(optionSelected==listRoles[i].name){
                    role=listRoles[i];
                }
            }
            if(role==''){
                for(i=0; i<credentials.length; i++){
                    $('#cred_'+i).removeAttr("checked");
                }
            }else{
                for(i=0; i<credentials.length; i++){
                    for(j=0; j<role.permission.length; j++){
                        if(role.permission[j]==credentials[i]){
                            check=true;
                            break;
                        }
                    }
                    if(check==true){
                        $('#cred_'+i).attr("checked","checked");
                        check=false;
                    }else{
                        $('#cred_'+i).removeAttr("checked");
                    }
                }
            }
        });

        $('#edit').click(function(){
            changeRole=false;
            var newRoleName=$('#name').val();
            var roleName=$('#listRoles option:selected').text();

            if(newRoleName=='' || roleName==''){
                alert('не выбрана роль, или новое имя роли не корректно');
            }else{
                if(roleName!=newRoleName){
                    changeRole=true;
                }
                if(changeRole){
                    checkRoleAjax.init(newRoleName);
                }else{
                    addEditRole.init();
                    editRoleAjax.init();
                }
            }

        })
    }
}

var addEditRole={
    init:function(){
        var newRoleName=$('#name').val();
        var roleName=$('#listRoles option:selected').text();
        for(var i=0; i< listRoles.length; i++){
            if(listRoles[i].name==roleName){
                editRole=listRoles[i];
                editRole.name=newRoleName;
            }
        }
        editRole.permission=[];
        for(var i=0; i<credentials.length; i++){
            if($('#cred_'+i).is(':checked')){
                editRole.permission[editRole.permission.length]=$('#cred_'+i).val();
            }
        }
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
                addEditRole.init();
                editRoleAjax.init();
            }
        });
    }
}