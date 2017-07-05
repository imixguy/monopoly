var addThisFr={    
    user:{},
    curEl:{},
    servurl:'http://localhost:8080',
    init:function(options){
        if(options && options.servurl){
			this.servurl=options.servurl;
		}        
        this.user.servid=jQuery('#myprofile').attr('href');
        this.user.userid=jQuery('#myprofile').attr('href');      
        var fr=$('button:contains(Добавить в друзья)');
		if(fr.length>0){
		    fr.click();
			var thisEl=this;
			setTimeout(function(){thisEl._addNextFr1(location.pathname);},6000);
	    }else{
			this.isUserAdd(location.pathname);
		}		
    },
	_addNextFr1:function(us){
        if($('div:contains(Вы подписаны)').length!=0){
            this.addUserToServ(us,false);
        }else{
            this.addUserToServ(us,true);
        }
    },
	isUserAdd:function(fr){        
        this.user.frandid=fr;
        var thisEl=this;
        $.ajax({
            type: "GET",
            url: thisEl.servurl+'/changer/isuseradd',
            data: thisEl.user,
            success: function(data){
                if(data==true){ 
				    window.open(window.location.href, '_self').close();
                }else{
                    thisEl.addUserToServ(fr,false);
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
                window.open(window.location.href, '_self').close();
            }
        });
    }
}

addThisFr.init();