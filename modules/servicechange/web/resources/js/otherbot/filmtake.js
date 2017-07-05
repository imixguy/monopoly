var filmTaskS={
    countLike:500,
    listAnek:[],
    povtor:18000000,//5ч
    servurl:'http://localhost:8080',
    url:'http://www.megacritic.ru/films.html',
    nexter:function(listtask){
        this.createcontainer();
        this.addinfo("Запускаем задиание");
        this.searthFilm();
       // curtask.func();
        var path=this.url;
        this.addinfo(new Date().toLocaleString()+"<br>Слудующее задание ");
        this.addinfo("через "+this.povtor+" мсек");
        setTimeout(function(){location.href=path;},this.povtor);
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
        this.nexter();
    },
    createcontainer:function(){
        $('body').prepend('<div id="panelDf" style="display:block;height:123px;width:115px;left:3px;top:40px;z-index:4;position:fixed;font-size: smaller;"></div>');
        $('#panelDf').prepend('<div id="logP" style="border:1px solid gray;overflow:auto;height:113px;"></div>');
    },
    addinfo:function(info){
        jQuery('#logP').prepend('<div>'+info+'</div>');
    },
    searthFilm:function(){
        this._searthFilm();
    },
    updateDataLike:function(){
//        jQuery('.post_like').mouseover();
//        var thisEl=this;
//        setTimeout(function(){jQuery('.like_tt').css("display","none");thisEl._searthAnek();},3000);
    },
    //ищем анекдоты, которые были опубликовоны не больше двух часов назад и не ранее часа назад, и у которых лайков больше countLike,
    _searthFilm:function(){
        var thisEl=this;
        jQuery('.jr_blogview .listItem').each(function(ind,el){var el1=jQuery(el);var t={'name':el1.find(".contentTitle").text(),
            'img':el1.find('.contentThumbnail img').attr('src'),'film':el1.find(".jr_customFields").text(),
            'discription':el1.find(".contentIntrotext").text()};thisEl.saveFilm(t);});
//        this.addinfo("Найдено "+thisEl.tR.length+" фильмов");
//        if(thisEl.tR.length==0) this.addinfo('Ничего не найдено');
//        thisEl.tR.each(function(ind,el){thisEl.saveFilm(el);});
        //повторяем поиск каждые povtor минут
    },
    //забираем контент анекдота
    saveFilm:function(film){
        this.addinfo('Нашли фильм');
        this.saveToServer(film);
    },
    //сохраняем фильм на сервере
    saveToServer:function(film){
        film.sight=location.href;
        this.addinfo('cтарт аякс');
        var thisEl=this;
        jQuery.ajax({
            type: "POST",
            url: thisEl.servurl+'/changer/savefilm2',
            data: film,
            success: function(data){
                if(data==true){
                    thisEl.addinfo('<b>Добавлен</b><br>'+film.name);
                }else{
                    thisEl.addinfo('<b>Уже есть</b><br>'+film.name);
                }
            }
        });
    }
}
jQuery(function(){  filmTaskS.init();})

