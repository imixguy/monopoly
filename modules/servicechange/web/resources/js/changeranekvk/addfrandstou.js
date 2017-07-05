var addFrToU={
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
        this.user.servid=jQuery('#myprofile').attr('href');
        this.user.userid=jQuery('#myprofile').attr('href');
        this.addinfo("serv:"+this.user.servid+"; user:"+this.user.userid);        
    },
	createcontainer:function(){
        $('body').prepend('<div id="panelDf" style="display:block;height:123px;width:115px;left:3px;top:40px;z-index:4;position:fixed;font-size: smaller;"></div>');
		$('#panelDf').prepend('<div id="logP" style="border:1px solid gray;overflow:auto;height:113px;"></div>');
		$('#panelDf').prepend('<button id="stB">Старт</button>');
		var thisEl=this;
		$('#stB').click(function(){thisEl.getListFr();});
    },
    addinfo:function(info){
        $('#logP').prepend('<div>'+info+'</div>');
    },
	getListFr:function(){
        this.addinfo('Получаем список');
        this.ind=0;
        this.list=jQuery('.people_row:not(.ugeprigl)');
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
            this.curEl=jQuery(this.list[this.ind]);
            this.isUserAdd(this.curEl.find('.labeled a').attr('href'));
        }
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
					thisEl.addinfo(new Date().toLocaleString());
                    thisEl.ind+=1;
                    thisEl.addNextFr();
                }else{
                    thisEl.addinfo('<b>Не предлагали</b>');
					thisEl.addinfo(new Date().toLocaleString());
                    thisEl._addNextFr(fr);
                }
            }
        });
    },
	_addNextFr:function(us){
		//alert(us);
        window.open(us);
        var thisEl=this;
        setTimeout(function(){thisEl.addNextFr();},this.time);
    },
	pageTop:function(){
        jQuery('html,body').scrollTop(jQuery(document).height());
        var thisEl=this;
        setTimeout(function(){thisEl.getListFr();},5000);
    }
}

addFrToU.init();