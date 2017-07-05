var addFr={
    list:[],
    ind:0,
    time:2150000, //каждые 36 мин
    user:{},
    curEl:{},
    servurl:'http://localhost:8080',
    init:function(options){
        if(options && options.servurl){
			this.servurl=options.servurl;
		}
        this.createcontainer();
        this.user.servid=jQuery('#top_back_link').attr('href');
        this.user.userid=jQuery('#myprofile').attr('href');
        this.addinfo("serv:"+this.user.servid+"; user:"+this.user.userid);
        this.getListFr();
    },
    createcontainer:function(){
	     $('body').prepend('<div id="panelDf" style="display:block;height:123px;width:115px;left:3px;top:40px;z-index:4;position:fixed;font-size: smaller;"></div>');
		$('#panelDf').prepend('<div id="logP" style="border:1px solid gray;overflow:auto;height:113px;"></div>');
        //$('.side_panel').prepend('<div id="logP" style="border:1px solid gray;overflow:auto;height:200px;">');
    },
    addinfo:function(info){
        $('#logP').prepend('<div>'+info+'</div>');
    },
    getListFr:function(){
        this.addinfo('Получаем список');
        this.ind=0;
        this.list=jQuery('.friends_act:not(.ugeprigl):contains(Пригласить в группу)');
        this.addinfo("Получен список "+this.list.length+" эл.");
        if(this.list.length==0) {
            this.pageTop();
        } else {
            this.list.addClass('ugeprigl');
            this.addNextFr();
        }
    },
    addNextFr:function(){
        if(this.list.length==this.ind) {
            this.getListFr();
        }else{
            this.addinfo('Берем следующего друга');
            this.curEl=jQuery(this.list[this.ind]).parents('.user_block');
            this.isUserAdd(this.curEl.attr('id').replace('user_block',''));
        }
    },
    _addNextFr:function(us){
        jQuery(this.list[this.ind]).click();
        var thisEl=this;
        setTimeout(function(){thisEl._addNextFr1(us);},6000);
    },
    _addNextFr1:function(us){
        if(this.curEl.find('.res').length==0){
            this.addUserToServ(us,false);
        }else{
            this.addUserToServ(us,true);
        }
    },
    _addNextFr2:function(){
        this.ind+=1;
        var thisEl=this;
        setTimeout(function(){thisEl.addNextFr();},this.time);
    },
    isUserAdd:function(fr){
        this.addinfo('Проверяем добавлен ли друг '+fr);
        this.user.frandid=fr;
        var thisEl=this;
        $.ajax({
            type: "GET",
            url: thisEl.servurl+'/changer/isuseradd',
            data: thisEl.user,
            success: function(data){
                if(data==true){
                    thisEl.addinfo('<b>Уже предлагали</b>');
                    thisEl.ind+=1;
                    thisEl.addNextFr();
                }else{
                    thisEl.addinfo('<b>Не предлагали</b>');
                    thisEl._addNextFr(fr);
                }
            }
        });
    },
    addUserToServ:function(fr, ogr){
        this.user.frandid=fr;
        this.user.ogr=ogr;
        var thisEl=this;
        $.ajax({
            type: "GET",
            url: thisEl.servurl+'/changer/addusertogroup',
            data: thisEl.user,
            success: function(data){
                if(data==true){
                    if(ogr){
                        thisEl.addinfo('<b>Занесен</b> '+thisEl.curEl.find('.friends_field').text()+"<br>"+new Date().toLocaleString());
                        thisEl.ind+=1;
                        thisEl.addNextFr();
                    }else{
                        thisEl.addinfo('<b>Добавлен</b> '+thisEl.curEl.find('.friends_field').text()+"<br>"+new Date().toLocaleString());
                        thisEl._addNextFr2();
                    }
                }else{
                    thisEl.addinfo('<b>Ошибка подключения</b><br>');
                }
            }
        });
    },
    pageTop:function(){
        jQuery('html,body').scrollTop(jQuery(document).height());
        var thisEl=this;
        setTimeout(function(){thisEl.getListFr();},5000);
    }
}

addFr.init();