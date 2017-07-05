var liker={
    timeMin:20,
    timeMax:80,
	timeFPL:1000,
	servurl:'http://localhost:8080',
    init:function(options){
	    this.createcontainer();
		this.startLiker();		
	},
	startLiker:function(){
		var thisEl=this;
		var tNextL=this.getRandom(this.timeMin,this.timeMax);
		this.addinfo("Сейчас "+new Date().toLocaleString());
		thisEl.likeAndTell();
		this.addinfo("След. лайк через "+tNextL+"мин.");
		setTimeout(function(){document.location=document.URL},tNextL*60*1000);
	},
    createcontainer:function(){
        $('body').prepend('<div id="panelDf" style="display:block;height:123px;width:115px;left:3px;top:40px;z-index:4;position:fixed;font-size: smaller;"></div>');
		$('#panelDf').prepend('<div id="logP" style="border:1px solid gray;overflow:auto;height:113px;"></div>');
    },
    addinfo:function(info){
        jQuery('#logP').prepend('<div>'+info+'</div>');
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

liker.init();