var taskExecutor={
    timeProv:60*1000,//время проверки нового таска
    date:new Date().getTime(),
    servurl:'http://localhost:8082',
    init:function(options){
        if(options){
            if(options.date){
                this.date=options.date;
            }
            if(options.servurl){
                this.servurl=options.servurl;
            }
        }
        this.createcontainer();
        this.getNextTask();
        this.addinfo('Старт');
    },
    createcontainer:function(){
        jQuery('body').prepend('<div id="panelDf" style="display:block;height:123px;width:115px;left:3px;top:40px;z-index:4;position:fixed;font-size: smaller;"></div>');
        jQuery('#panelDf').prepend('<div id="logP" style="border:1px solid gray;overflow:auto;height:113px;"></div>');
    },
    addinfo:function(info){
        jQuery('#logP').prepend('<div>'+info+'</div>');
    },
    getNextTask:function(){
        var thisEl=this;
        this.addinfo('Получаем следующий таск');
        jQuery.ajax({
            type: "GET",
            url: thisEl.servurl+"/changer/task/getnexttask",
            data: {"executor": jQuery('#myprofile').attr('href')},
            dataType:"json",
            success: function(data){if(data!=null){
                if(location.href==data.pageName){
                    thisEl[data.name](data.content);
                    thisEl.taskComplete(data.id);
                }else{
                    location.href=data.pageName;
                }
            }else{
                setTimeout(function(){thisEl.getNextTask();},thisEl.timeProv);
            }},
            error:function(data){
                thisEl.addinfo("Сервер не отвечает");
                setTimeout(function(){thisEl.getNextTask();},thisEl.timeProv);
            }
        });
    },
    addVKGrAddMessage:function (content){
        this.addinfo('<b>Делаем запись</b>');
        this.addinfo(new Date().toLocaleString());
        jQuery('#post_field').mousedown();
        jQuery('#post_field').val(content);
        if(!jQuery('#official').hasClass('on') ){
            jQuery('#official').click();
        }
        jQuery('#send_post').click();
    },
    taskComplete:function(id_task){
        var thisEl=this;
        this.addinfo("Докладываем о выполнении");
        jQuery.ajax({
            type: "GET",
            url: thisEl.servurl+"/changer/task/markComleted",
            data: {"id_task": id_task,"executor":window.executor},
            success: function(data){if(data){
                thisEl.addinfo("Проверяем следующее задание");
                thisEl.getNextTask();
            }else{
                setTimeout(function(){thisEl.taskComplete(id_task);},30000)
            }},
            error:function(data){thisEl.addinfo("Сервер не отвечает"); setTimeout(function(){thisEl.taskComplete(id_task);},30000)}
        });
    }
}
taskExecutor.init();