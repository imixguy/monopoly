var addAnek={
    timeFNA:29*60*1000,//время для нового анекдота и для лайка старого
    timeFPL:14*60*1000,//время через которое нужно рассказать о анекдоте
    numVh:true,
    listAnekR:[],//список контента для добавления
    numVr:0,
    stopSA:false, //false-запущено
    numOOSight:0,
    date:new Date().getTime(),
    servurl:'http://localhost:8080',
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
        this.addinfo('Старт');
        this.createStartAct();
    },
    createcontainer:function(){
        $('body').prepend('<div id="panelDf" style="display:block;height:123px;width:115px;left:3px;top:40px;z-index:40;position:fixed;font-size: smaller;"></div>');
        $('#panelDf').prepend('<div id="logP" style="border:1px solid gray;overflow:auto;height:113px;"></div>');
    },
    addinfo:function(info){
        jQuery('#logP').prepend('<div>'+info+'</div>');
    },
    createStartAct:function(){
        var butStartAddAnek=jQuery('<button id="butStAddA">Стоп</button>');
        var thisEl=this;
        butStartAddAnek.click(function(){if(thisEl.stopSA){butStartAddAnek.text("Стоп");thisEl.stopSA=false;thisEl.getNewAnek();}else{butStartAddAnek.text("Старт");thisEl.stopSA=true;}});
        jQuery('#panelDf').prepend(butStartAddAnek);
        this.getNewAnek();
    },
    getNewAnek:function(){
        var thisEl=this;
        //setTimeout(function(){thisEl.getNewAnek();},this.timeFNA);
        if(new Date().getHours()<8) {
            setTimeout(function(){location.reload(true);},(6-new Date().getHours())*60*60*1000);
            return;
        }
        this.addinfo('Получаем фильм');
        jQuery.ajax({
            type: "GET",
            url: thisEl.servurl+"/changer/nextfilm3",
            data: {date: this.date},
            success: function(data){if(data){thisEl.addAnek(data)}else{
                thisEl.addinfo("Список пустой, ждем 2 часа до следующей проверки "+new Date());
                setTimeout(function(){location.reload(true);},2*60*60*1000);
            }},
            error:function(data){thisEl.addinfo("Сервер не отвечает");setTimeout(function(){location.reload(true);},2*60*1000);}
        });
    },
    _addImage:function(anek){
        if(anek.img==null){
            this._addText(anek);
        }
        try {
            var comp = Composer.init(document.getElementById('post_field'), {
                lang: {
                    introText: getLang('profile_mention_start_typing'),
                    noResult: getLang('profile_mention_not_found')
                },
                checkLen: Wall.postChanged,
                onValueChange: Wall.onPostValChange
            });
        } catch (e) {
            var thisEl=this;
            setTimeout(function(){thisEl._addImage(anek);},1000);
            return;
        }
        Wall.showEditPost();
        var elD=document.getElementById('post_field');
        elD.value=anek.img;
        elD.focus();
        jQuery('#post_field').mousedown();
        var thisEl=this;
        this.addinfo('Вставляем фотку');
        setTimeout(function(){thisEl._addImageCheck(comp,anek,0);},2000);
    },
    _addImageCheck:function(comp,anek,count){
        console.log(count);
        if(jQuery('.preview').length<1 && count<5){
            var thisEl=this;
            var elD=document.getElementById('post_field');
            comp.options.onValueChange(elD.value,true);
            setTimeout(function(){thisEl._addImageCheck(comp,anek,count++);},2000);
        }else{
            console.log(anek.name);
            this._addText(anek);
        }
    },
    addAnek:function (anek){
        setTimeout(function(){location.reload(true);},30*60*1000);
        this.addinfo('<b>Записываем фильм</b>');
        this.addinfo(new Date().toLocaleString());
        if(this.stopSA) return;
        this.date=anek.dateCreate;
        this._addImage(anek);
    },
    _addText:function(anek){
        jQuery('#post_field').val(anek.name+'\x0D\x0A'+anek.film+'\x0D\x0A'+anek.discription);
        if(!jQuery('#official').hasClass('on') ){
            jQuery('#official').click();
        }
        document.getElementById('post_field').blur();
        jQuery('#send_post').click(); //Wall.sendPost();
        if(this.getRandom(1,5)==1){
            this.addinfo('расскажем об этом');
            this.likeAndTell();
        }else{
            this.addinfo('не расскажем об этом');
        }
    },
    writeAnekToImix:function(anek){
        var thisEl=this;
        jQuery.ajax({
            type: "GET",
            url: "http://imix.by/parse/saveanekdot",
            data: {anek: anek},
            success: function(data){thisEl.addinfo(data);},
            error:function(data){thisEl.addinfo('Не удалось соединится с imix.by');}
        });
    },
    getRandom:function(min,max){
        return Math.round(min-0.5+ Math.random()*(max-min+1));
    },
    likeAndTell:function(){
        setTimeout(function(){jQuery('.post_full_like div.post_like:first').click();},this.timeFPL);
        setTimeout(function(){jQuery('.post_full_like div.post_like:first').mouseover();},this.timeFPL+30*1000);
        setTimeout(function(){jQuery('.like_tt').filter(function(){return $(this).css("display")=="block"}).find('.checkbox').click()},this.timeFPL+35*1000);
        setTimeout(function(){jQuery('.like_tt').filter(function(){return $(this).css("display")=="block"}).css("display","none");},this.timeFPL+40*1000);
    }
}

function InsertChar (el,key) {
    try {
        var pressEvent = document.createEvent ("KeyboardEvent");
        pressEvent.initKeyEvent ("keydown", true, true, window,
            false, false, false, false,
            0, key.charCodeAt (0));
        el.dispatchEvent (pressEvent);
    }
    catch (e) {
        alert ("Your browser does not support this example!");
    }
}

window.addf=function (){
    Wall.showEditPost();

    var elD=document.getElementById('post_field');
//    elD.value="http://serialochka.ru/images/medium/superdevushka_serialochka_9.jpg";

    elD.focus();
    jQuery('#post_field').mousedown();
    var comp=Composer.init(document.getElementById('post_field'), {
        lang: {
            introText: getLang('profile_mention_start_typing'),
            noResult: getLang('profile_mention_not_found')
        },
        checkLen: Wall.postChanged,
        onValueChange: Wall.onPostValChange
    });


    //var evt= new KeyboardEvent("past", {bubbles : true, cancelable : true, key : " ", char : " ", shiftKey : true});
    //setTimeout(function(){ comp.options.onValueChange(elD.value,true);},3000);
    comp.options.onValueChange(elD.value,true);

    //Composer.updateAutoComplete(comp,evt);

}

addAnek.init();