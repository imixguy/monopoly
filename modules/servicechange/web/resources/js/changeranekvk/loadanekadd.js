var addAnek={
    timeFNA:29*60*1000,//время для нового анекдота и для лайка старого
    timeFPL:14*60*1000,//время через которое нужно рассказать о анекдоте
    numVh:true,
    listAnekR:[],//список контента для добавления
    numVr:0,
    stopSA:false,
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
        $('body').prepend('<div id="panelDf" style="display:block;height:123px;width:115px;left:3px;top:40px;z-index:4;position:fixed;font-size: smaller;"></div>');
		$('#panelDf').prepend('<div id="logP" style="border:1px solid gray;overflow:auto;height:113px;"></div>');
    },
    addinfo:function(info){
        jQuery('#logP').prepend('<div>'+info+'</div>');
    },
    createStartAct:function(){
        var butStartAddAnek=jQuery('<button id="butStAddA">Стоп</button>');
        var thisEl=this;
        butStartAddAnek.click(function(){if(thisEl.stopSA){butStartAddAnek.text("Стоп");thisEl.stopSA=false;thisEl.getNewAnek();}else{thisEl.addinfo('Публикация анекдотов остановлена.');butStartAddAnek.text("Старт");thisEl.stopSA=true;}});
        jQuery('#panelDf').prepend(butStartAddAnek);
        this.getNewAnek();
    },
    getNewAnek:function(){
        var thisEl=this;
        setTimeout(function(){thisEl.getNewAnek();},this.timeFNA);
        if(new Date().getHours()<6) {
            this.addinfo('До 6 утра не печатаем анекдоты');
            return;
        }
        this.addinfo('Получаем анекдот');
        jQuery.ajax({
            type: "GET",
            url: thisEl.servurl+"/changer/nextanekdot3",
            data: {date: this.date},
            success: function(data){if(data){thisEl.addAnek(data)}},
            error:function(data){thisEl.addinfo("Сервер не отвечает");}
        });
    },
    addAnek:function (anek){
        this.addinfo('<b>Записываем анекдот</b>');
		this.addinfo(new Date().toLocaleString());
        if(this.stopSA) return;
        this.date=anek.dateCreate;
        anek=anek.anekdot.replace('<br>', '\x0D\x0A', 'g');
        //this.addinfo(anek);
		this.writeAnekToImix(anek);
        jQuery('#post_field').mousedown();
        jQuery('#post_field').val(anek);
        if(!jQuery('#official').hasClass('on') ){
            jQuery('#official').click();
        }
        jQuery('#send_post').click();		
		if(this.getRandom(1,4)==1){
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

addAnek.init();