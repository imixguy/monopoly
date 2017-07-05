function getCountLike(){
    var pn=location.pathname;
	if(pn=='/evil_incorparate') return 4000;
    if(pn=='/anekdot') return 1000;
    if(pn=='/o_smile_o') return 450;
    if(pn=='/ostrjak') return 200;
    if(pn=='/i.love.smile') return 250;
    return 0;
}

// возвращает cookie с именем name, если есть, если нет, то null
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
    }
    return "";
}

var anekWork={
    countLike:500,
    listAnek:[],
    povtor:1200000,//20мин
	servurl:'http://localhost:8080',
    nexter:function(listtask){
        this.createcontainer();
        var	wait=10000;
        var indtask=getCookie('numgr');
        if(indtask==""){
            indtask=0;
        }else{
            indtask=parseInt(indtask);
        }
        this.addinfo("Запускаем задиание №"+indtask);
        var curtask=listtask[indtask];
        this.addinfo("curtask.url="+curtask.url);
        var indnexttask=indtask+1;
        if(indnexttask==listtask.length){
            indnexttask=0;
            wait=this.povtor;
        }
        this.addinfo("indnexttask="+indnexttask);
        curtask.func();
        var path=location.protocol+"//"+location.hostname+listtask[indnexttask].url;
        this.addinfo(new Date().toLocaleString()+"<br>Слудующее задание "+listtask[indnexttask].url);
        this.addinfo("через "+wait+" мсек");
        setTimeout(function(){setCookie('numgr',indnexttask,1);location.href=path;},wait);
    },
    init:function(options){
        if(options){
			if(options.countLike){
				this.countLike=options.countLike;
			}
			if(options.servurl){
				this.servurl=options.servurl;
			}
        }
        this.searthAnek();
    },
    createcontainer:function(){
        $('body').prepend('<div id="panelDf" style="display:block;height:123px;width:115px;left:3px;top:40px;z-index:4;position:fixed;font-size: smaller;"></div>');
		$('#panelDf').prepend('<div id="logP" style="border:1px solid gray;overflow:auto;height:113px;"></div>');
    },
    addinfo:function(info){
        jQuery('#logP').prepend('<div>'+info+'</div>');
    },
    searthAnek:function(){
        // this.updateDataLike();
        this._searthAnek();
        //var thisEl=this;
        // setTimeout(function(){thisEl.searthAnek()},this.povtor);
        // setTimeout(function(){location.reload();},this.povtor);
    },
    updateDataLike:function(){
        jQuery('.post_like').mouseover();
        var thisEl=this;
        setTimeout(function(){jQuery('.like_tt').css("display","none");thisEl._searthAnek();},3000);
    },
    //ищем анекдоты, которые были опубликовоны не больше двух часов назад и не ранее часа назад, и у которых лайков больше countLike,
    _searthAnek:function(){
        var thisEl=this;
        var el=jQuery(':contains(час назад)').parents('.post').find('.post_like_count').filter(function(){return jQuery(this).text()>thisEl.countLike}).parents('.post_full_like_wrap').prevAll('.wall_text').find('.wall_post_text');
        if(el.length==0) this.addinfo('Ничего не найдено');
        el.each(function(ind,el){thisEl.saveAnek(el);});
        //повторяем поиск каждые povtor минут
    },
    //забираем контент анекдота
    saveAnek:function(anek){
        var fanek=jQuery(anek).parents('.post_table');
        this.addinfo('Нашли анекдот');
        var anekO=null;
        //не берем длинные анекдоты или если есть изображение
        if(jQuery(anek).find('.wall_post_more').length==0 && jQuery(anek).find('.page_post_thumb_sized_photo').length==0){
		    var t=0;
			if(location.pathname=='/palnom6' && jQuery(anek).text().toLowerCase().indexOf('палат')>-1){
				this.addinfo('этот анек пропускаем');	
				t=1;				
			}
			if(t==0){
				anekO={};
				anekO.cadded=fanek.find("[id^='share_count']").text();
				anekO.clike=fanek.find("[id^='like_count']").text();
				anekO.anek=jQuery(anek).html();
				anekO.anek=anekO.anek.replace(/<a.*a>/,'');
				anekO.id=jQuery(anek).parent().attr('id');
			}
//            for(var ih=0;ih<this.listAnek.length;ih++){
//                if(this.listAnek[ih].id==anekO.id){
//                    anekO=null;
//                    break;
//                }
//            }
        }
        if(anekO!=null){
//            this.listAnek[this.listAnek.length]=anekO;
            //jQuery('#page_actions').append('<div>'+anekO.anek+'</div>');
            this.saveToServer(anekO);
        }
    },
    //сохраняем анекдот на сервере
    saveToServer:function(anek){
        anek.group=location.pathname;
        this.addinfo('cтарт аякс');
        var thisEl=this;
        jQuery.ajax({
            type: "POST",
            url: thisEl.servurl+'/changer/saveanekdot2',
            data: anek,
            success: function(data){
                if(data==true){
                    thisEl.addinfo('<b>Добавлен</b><br>'+anek.anek);
                }else{
                    thisEl.addinfo('<b>Уже есть</b><br>'+anek.anek);
                }
            }
        });
    }
}
function anekTask(){    
	anekWork.init({'countLike':getCountLike()});	
}
var listtask=[{url:'/evil_incorparate',func:anekTask},{url:'/anekdot',func:anekTask},{url:'/o_smile_o',func:anekTask},{url:'/ostrjak',func:anekTask},{url:'/i.love.smile',func:anekTask}];
anekWork.nexter(listtask);