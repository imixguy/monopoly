var setinokanek={   
    timeFNA:20*60*1000,//время для нового анекдота и для лайка старого
    //timeFNA:10000,
    numVh:true,
    numVr:0,
    stopSA:true,
    numOOSight:0,
    date:new Date().getTime(),
    servurl:'http://localhost:8080', 
    init:function(options){
	    
	if(options){			
		if(options.servurl){
		this.servurl=options.servurl;
		}
        }
        this.createcontainer();
	//$('#panelService').append('<button id="bStart">Старт</button>');
	//var thisEl=this;
	//$('#bStart').click(function(){thisEl.getNewAnek(); thisEl.addinfo('Старт');});    
	this.getNewAnek(); 
	this.addinfo('Старт');    
    },
    createcontainer:function(){	    
        $('body').prepend('<div id="panelService" style="position:fixed;z-index:100;top:80px;left:70px;border:1px solid gray;height: 180px;width:200px;background:white;"><div id="logP" style="height: 180px;overflow: auto;"></div></div>');
    },
    addinfo:function(txt){
        var el= $('#logP');
        el.append('<div>'+txt+'</div>');
        el.scrollTop(el[0].scrollHeight-el[0].offsetHeight);
    },
    getNewAnek:function(){
        var thisEl=this;        
        this.addinfo('Получаем анекдот');
	//setTimeout(function(){thisEl.getNewAnek();},this.timeFNA);
        jQuery.ajax({
            type: "GET",
            url: thisEl.servurl+"/changer/nextanekdotok",            
            success: function(data){if(data){
				thisEl.addAnek(data);							
			}},
            error:function(data){thisEl.addinfo("Сервер не отвечает");}
        });
    },
    addAnek:function (anek){
        this.addinfo('<b>Записываем анекдот</b>');
	this.addinfo(new Date().toLocaleString());
        //if(this.stopSA) return;
        //this.date=anek.dateCreate;
        anek=anek.anekdot.replace('<br>', '\x0D\x0A', 'g');
        this.addinfo(anek);
	var thisEl=this;
	setTimeout(function(){thisEl.writeAnek(anek);},this.timeFNA);
		//this.addinfo('Готово');
        //jQuery('#post_field').mousedown();
        //jQuery('#post_field').val(anek);
        //if(!jQuery('#official').hasClass('on') ){
        //    jQuery('#official').click();
        //}
        //jQuery('#send_post').click();		
		//if(this.getRandom(1,4)==1){
		//	this.addinfo('расскажем об этом');
		//	this.likeAndTell();
		//}else{
		//	this.addinfo('не расскажем об этом');
		//}
    },
    writeAnek:function(anek){
	jQuery('#field_text').focus().val(anek);
	jQuery('input.pbtn.__grn.js-dyn-edit-submit').click();
    }
    
}
setinokanek.init();