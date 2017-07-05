// возвращает cookie с именем name, если есть, если нет, то null
function getCookied(name) {
    var matches = document.cookie.match(new RegExp("(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
    return matches ? decodeURIComponent(matches[1]) : null;
}

var anekWork={
    countLike:500,
    listAnek:[],
    povtor:600000,//10мин
    listGr:null,
    curInd:0,
    nextInd:0,
	servurl:'http://localhost:8080',
    init:function(options){
        this.createcontainer();
        if(options){
			if(options.listGr){
				this.listGr=options.listGr;
			}
			if(options.servurl){
				this.servurl=options.servurl;
			}
        }
        this.addinfo("location.host:"+location.host);
        this.curInd=getCookied('numgr');

        if(this.curInd!=null){
            this.curInd=parseInt(this.curInd);
            this.addinfo("this.curInd:"+this.curInd);
            if(this.curInd==this.listGr.length){
                this.curInd=0;
            }
        }else{
            this.curInd=0;
        }
        this.addinfo("Пишем куки "+"numGr="+(this.curInd+1)+"ddd:"+this.listGr[this.curInd].countLike);
        document.cookie = "numgr="+(this.curInd+1);
        this.countLike=this.listGr[this.curInd].countLike;
        this.addinfo("countLike "+this.countLike);
        this.nextInd=this.curInd+1;
        if(this.nextInd==this.listGr.length){
            this.nextInd=0;
        }
        this.addinfo("Старт вор");
        this.searthAnek();
    },
    createcontainer:function(){
        $('#page_actions').prepend('<div id="logP" style="border:1px solid gray;overflow: auto;height: 200px;">');
    },
    addinfo:function(info){
        $('#logP').prepend('<div>'+info+'</div>');
    },
    searthAnek:function(){
        // this.updateDataLike();
        this._searthAnek();
        var thisEl=this;
        // setTimeout(function(){thisEl.searthAnek()},this.povtor);
        var path=location.protocol+"//"+location.hostname+this.listGr[this.nextInd].namegr;
        //var path=location.hostname+this.listGr[this.nextInd].namegr;
        if(this.nextInd!=0){
            location.href=path;
        }else{
            setTimeout(function(){location.href=path;},this.povtor);
        }
    },
    updateDataLike:function(){
        $('.post_like').mouseover();
        var thisEl=this;
        setTimeout(function(){$('.like_tt').css("display","none");thisEl._searthAnek();},3000);
    },
    //ищем анекдоты, которые были опубликовоны не больше двух часов назад и не ранее часа назад, и у которых лайков больше countLike,
    _searthAnek:function(){
        var thisEl=this;
        var el=$(':contains(час назад)').parents('.post').find('.post_like_count').filter(function(){return $(this).text()>thisEl.countLike}).parents('.post_full_like_wrap').prevAll('.wall_text').find('.wall_post_text');
        if(el.length==0) this.addinfo('Ничего не найдено');
        el.each(function(ind,el){thisEl.saveAnek(el);});
        //повторяем поиск каждые povtor минут
    },
    //забираем контент анекдота
    saveAnek:function(anek){
        this.addinfo('Нашли анекдот');
        var anekO=null;
        //не берем длинные анекдоты или если есть изображение
        if(jQuery(anek).find('.wall_post_more').length==0 && jQuery(anek).find('.page_post_thumb_sized_photo').length==0){
            anekO={};
            anekO.anek=jQuery(anek).html();
            anekO.id=jQuery(anek).parent().attr('id');
//            for(var ih=0;ih<this.listAnek.length;ih++){
//                if(this.listAnek[ih].id==anekO.id){
//                    anekO=null;
//                    break;
//                }
//            }
        }
        if(anekO!=null){
//            this.listAnek[this.listAnek.length]=anekO;
            //$('#page_actions').append('<div>'+anekO.anek+'</div>');
            this.saveToServer(anekO);
        }
    },
    //сохраняем анекдот на сервере
    saveToServer:function(anek){
        this.addinfo('cтарт аякс');
        var thisEl=this;
        $.ajax({
            type: "GET",
            url: thisEl.servurl+'/changer/saveanekdot',
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