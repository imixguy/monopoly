function  getPageSize(){
    var xScroll, yScroll;
    if (window.innerHeight && window.scrollMaxY) {
        xScroll = document.body.scrollWidth;
        yScroll = window.innerHeight + window.scrollMaxY;
    } else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
        xScroll = document.body.scrollWidth;
        yScroll = document.body.scrollHeight;
    } else if (document.documentElement && document.documentElement.scrollHeight > document.documentElement.offsetHeight){ // Explorer 6 strict mode
        xScroll = document.documentElement.scrollWidth;
        yScroll = document.documentElement.scrollHeight;
    } else { // Explorer Mac...would also work in Mozilla and Safari
        xScroll = document.body.offsetWidth;
        yScroll = document.body.offsetHeight;
    }
    var windowWidth, windowHeight;
    if (self.innerHeight) { // all except Explorer
        windowWidth = self.innerWidth;
        windowHeight = self.innerHeight;
    } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
        windowWidth = document.documentElement.clientWidth;
        windowHeight = document.documentElement.clientHeight;
    } else if (document.body) { // other Explorers
        windowWidth = document.body.clientWidth;
        windowHeight = document.body.clientHeight;
    }
    // for small pages with total height less then height of the viewport
    if(yScroll < windowHeight){
        pageHeight = windowHeight;
    } else {
        pageHeight = yScroll;
    }
    // for small pages with total width less then width of the viewport
    if(xScroll < windowWidth){
        pageWidth = windowWidth;
    } else {
        pageWidth = xScroll;
    }
    return [pageWidth,pageHeight,windowWidth,windowHeight];
}

function  getMaxSizeInnerBlock(widthInner, heightInner, elWidth, elHeight){
	if(widthInner<elWidth){
		var t=elWidth/widthInner;
		widthInner = elWidth;
		heightInner = heightInner*t;
	}
	if(heightInner<elHeight){
		var t=elHeight/heightInner;
		heightInner = elHeight;
		widthInner = widthInner*t;
	}
	if(widthInner>elWidth){
		var t=elWidth/widthInner;
		widthInner = elWidth;
		heightInner = heightInner*t;
	}
	if(heightInner>elHeight){
		var t=elHeight/heightInner;
		heightInner = elHeight;
		widthInner = widthInner*t;
	}	
	//decrease on 1%
	return [Math.floor(widthInner-1%widthInner),Math.floor(heightInner-1%heightInner)]	
}

var Class={create:function(){return function(){this.initialize.apply(this, arguments);}}}
Object.extend = function(d,s){for (var property in s) {d[property] = s[property];}return d;}

var predictURL="http://localhost:63342/cms/monogol/web";

var langRu={
    THROW_CUBE:"Бросить кубик",
    BUY_FIRM:"Купить фирму",
    BUY_FILIAL:"Купить филиал",
    TAKE_CREDIT:"Взять кредит",
    GIVE_CREDIT:"Вернуть кредит",
    PUT_FIRM:"Заложить фирму",
    REDEEM_FIRM:"Выкупить фирму",
    CHANGE_FIRM:"Обмен фирмами",
    SELL_FILIAL:"Продать филиал",
    PAY_PENALTY:"Заплатить штраф",
    AUCTION_START:"Объявить аукцион",
    AUCTION_FOLD:"Отказаться",
    AUCTION_BUY:"Поставить",
    GAME_END:"Сдаться",
    GAME_CLOSE:"Закрыть игру",
    SEND_MESSAGE:"Отправить",
    CANCAL:"Отмена",
    YOURS_WIN:"ВЫ ПОБЕДИЛИ!!!!"
}
var langEn={
    THROW_CUBE:"Throw cube",
    BUY_FIRM:"Buy firm",
    BUY_FILIAL:"Buy filial",
    TAKE_CREDIT:"Take credit",
    GIVE_CREDIT:"Give credit",
    PUT_FIRM:"Put firm",
    REDEEM_FIRM:"Redeem Firm",
    CHANGE_FIRM:"Change firm",
    SELL_FILIAL:"Sell filial",
    PAY_PENALTY:"Pay penalty",
    AUCTION_START:"Start auction",
    AUCTION_FOLD:"Fold",
    AUCTION_BUY:"Buy",
    GAME_END:"I loose",
    GAME_CLOSE:"Game close",
    SEND_MESSAGE:"Send",
    CANCAL:"Cancal",
    YOURS_WIN:"YOURS WIN!!!!"
}

var Fishka=Class.create();
Fishka.prototype = {
    colorF:["red","darkblue","yellow","lightblue"],
    colorText:["white;","white;","black","black"],
    initialize:function(numF,posit,name,size){
        this.numF=numF;
        this.posit=posit;
        this.name=name;
        this.size=size;
        this.build();
    },
    build:function(){
        var r=Math.round(this.size.shMc/4);
        this.vid=$('<div style="border-radius:'+r+'px;display:inline;margin:auto;text-align:center;width:'+r+'px;height:'+r+'px; background-color: '+this.colorF[this.numF-1]+';border: 3px solid white;"><span style="color:'+this.colorText[this.numF-1]+'font-weight:bold;font-size:'+this.size.fontsize+'pt;">'+this.numF+'</span></div>')
        this.vid.hide().show();
    }
}      //"money":100000,"penalty":0,"indexPosition":0,"credit":0,"activGamer":false,"goForward":true,"name":"admin"

var Gamer=Class.create();
Gamer.prototype = {
    initialize:function(user,game,num){
        this.user=user;
        this.fishka=new Fishka(num,user.indexPosition,user.name,game.actSize);
        this.game=game;
        this.build();
    },
    build:function(){
        var t=Math.round(this.game.actSize.shCPl/4);
        var v=Math.floor(this.game.actSize.shCPl/4);
        this.vid=$('<div style="border-radius: '+this.game.actSize.fontsize+'px;font-size:'+this.game.actSize.fontsize+'pt;position:relative; display:inline-block;vertical-align:top;width: '+(v-20)+'px;height: '+(t-20)+'px; background-color: '+this.fishka.colorF[this.fishka.numF-1]+';background-image: url(/resources/images/games/'+this.game.room.imageFolder+'/setka.png);margin: 5px;"><div style="color:'+this.fishka.colorText[this.fishka.numF-1]+';margin: 5px;">'+this.fishka.numF+' '+this.user.name+'</div></div>');
        this.bord=$('<img style="border-radius: '+this.game.actSize.fontsize+'px;position:absolute; top:0; left:0;" src="/resources/images/games/'+this.game.room.imageFolder+'/pole.jpg" width="'+(v-20)+'px" height="'+(t-20)+'px"/>');
        this.vid.append(this.bord);
        this.moneyV=$('<div style="color:'+this.fishka.colorText[this.fishka.numF-1]+';margin: 5px;">Деньги: '+this.user.money+'</div>');
        this.penaltyV=$('<div style="color:'+this.fishka.colorText[this.fishka.numF-1]+';margin: 5px;">Штраф: '+this.user.penalty+'</div>');
        if(this.user.penalty==0){
            this.penaltyV.hide();
        }
        //this.moneyV=$('<div>'+this.user.c+'</div>');
        this.vid.append(this.moneyV).append(this.penaltyV);//.append;
        this.updateVid();
    },
    updateVid:function(){
        this.moneyV.text("Деньги: "+this.user.money);
        if(this.user.penalty!=0){
            this.penaltyV.text("Штраф: "+Math.abs(this.user.penalty));
            this.penaltyV.show();
        }else{
            this.penaltyV.text("Штраф: 0");
            this.penaltyV.hide();
        }
        //this.penaltyV()
        if(this.user.activGamer){
            this.game.setMigEl(this.bord);
        }
    }
}

var Card=Class.create();
Card.prototype = {
    initialize: function (gameM,obj,ind,rasp) {
        this.gameM=gameM;
        this.obj=obj;
        this.ind=ind;
        this.buildCard(rasp);
    },
    buildCard:function(rasp){
        this.h=this.gameM.actSize.shBc;
        this.w=this.gameM.actSize.shMc;
        if(rasp=='big'){
            this.w=this.gameM.actSize.shBc;
        }
        this.infocard=$('<div class="infocard" style="position: absolute; z-index: 40; top: 0;left: 0;width:'+this.w+'px;height:'+this.h+'px;">').append('<input type="hidden" value="'+this.ind+'"/>');
        //'height="'+size.shBc+'px" width="auto"'
        var img=$('<img src="/resources/images/games/'+this.gameM.room.imageFolder+'/'+this.obj.image+'"/>').css('width',this.w+'px');
        this.vid=$('<div style="position:relative;text-aligne:center;">').css('float','left').css('height',this.h+'px').append(img).append(this.infocard);
        this.poleForFishka=$('<div style="display:inline-block;vertical-align: middle;"></div>');
        this.vid.append($('<div style="text-align: center;position:absolute;z-index:30;top:0;left:0;width:'+this.w+'px;height:'+this.h+'px;">').append(this.poleForFishka).append('<div style="display:inline-block;height:'+this.h+'px;vertical-align:middle;width:0;">'));
        if(rasp=='left'){
            this.vid.css('height',this.gameM.actSize.shMc+'px');
            this.vid.css('transform','rotate(270deg)').css('transform-origin',Math.round(this.gameM.actSize.shMc/2)+'px '+Math.round(this.gameM.actSize.shMc/2)+'px');// translate('+this.gameM.actSize.shMc/2+'px,'+this.gameM.actSize.shMc/2+'px)');
        }
        if(rasp=='right'){
            this.vid.css('height',this.gameM.actSize.shMc+'px');
            this.vid.css('transform','rotate(90deg)').css('transform-origin',Math.round(this.gameM.actSize.shMc/2)+'px '+Math.round(this.gameM.actSize.shMc/2)+'px').css('float','right');
        }
        var info=$('<div style="color:white;font-size:'+this.gameM.actSize.fontsize+'pt;position: absolute; z-index: -5; top: 0;left: 0;width:100%;height:100%;">').append(this.infoFirm);
        if(this.obj.price){
            this.infoMoneyBG=$('<div style="z-index:-5;position:relative;background-image: url(/resources/images/games/'+this.gameM.room.imageFolder+'/setka.png);">').css('height',Math.round(this.h/4)+'px').css('width',this.w+'px');
            this.infoMoney=$('<div style="position: absolute; z-index: -2; top: 0;left: 0;text-align: center; vertical-align: middle;display: table-cell;">');
            this.infoMoney.css('height',Math.round(this.h/4)+'px').css('width',this.w+'px').text(this.obj.price);
            this.infomoneyBG.append(this.infomoney);
            info.append(this.infoMoneyBG);
        }
        this.infoFirm=$('<div style="text-align: center; vertical-align: middle;">').css('height',Math.round((this.h/4)*3)+'px').css('width',this.w+'px');
        this.infoFirm.text(this.obj.name);
        info.append(this.infoFirm);
        var  bg=$('<div class="infocard" style="background:black;position: absolute; z-index: -10; top: 0;left: 0;">').css('height',this.h+'px').css('width',this.w+'px');
        this.vid.append(info).append(bg);
        this.listVid=$('<div style="color:white; cursor:default;">'+this.obj.name+' - '+this.obj.price+'/'+this.obj.filialPrice+'</div>');
        return this.vid;
    },
    checkedData:function(){
        if(this.obj.put){
            this.putFirm();
        }
        if(this.obj.filialStay>0) {
            for (var i = 0; i < this.obj.filialStay; i++) {
                this.buyFilial();
            }
        }
    },
    sellFirm:function(){
        this.infoMoneyBG.css("background-color","");
        this.infoMoney.text(this.obj.price);
    },
    buyFirm:function(gamer){
        this.infoMoneyBG.css("background-color",gamer.fishka.colorF[gamer.fishka.numF-1]);
        this.obj.userOwner=gamer;
        this.recalculatePenalty();
    },
    recalculatePenalty:function(){
        this.infoMoney.text(this.obj.price);
        if(this.obj.userOwner!=null && !this.obj.put){
            this.infoMoney.text(Math.round(this.obj.price/5+(this.obj.price*(this.obj.filialStay*this.obj.filialStay))/10));
        }
    },
    goOn:function(gamer){ //фишка попала на поле
        this.goOnFishka(gamer.fishka)
    },
    goOnFishka:function(fishka){
        this.poleForFishka.append(fishka.vid);
    },
    canSelect:function(action){
        this.actionUser=action;
        var thisEl=this;
        this.getSelectVid();
        this.vid.append(this.selectVid);
        if(action=='BUY_FILIAL' || action=='SELL_FILIAL'){
            this.infoMoney.text(this.obj.priceFilial);
        }
        if(action=='PUT_FIRM'){
            this.infoMoney.text(this.obj.price/2);
        }
        this.vkldf=function(){
            thisEl.infocard.off('click',thisEl.vkldf).on('click',thisEl.vkldf2);
            thisEl.vkldf2on=true;
            thisEl.listVid.on('click',thisEl.vkldf2);
            if(action!="CHANGE_FIRM"){
                thisEl.gameM.poleF.append(thisEl.listVid);
            }else{
                thisEl.gameM.changePanel.mySelect.append(thisEl.listVid);
            }
            thisEl.gameM.listSelectFirm[thisEl.gameM.listSelectFirm.length] = thisEl.ind;
            thisEl.gameM.updPanelSum(thisEl.actionUser);
            if(thisEl.actionUser=="BUY_FILIAL"){
                for(var i=0;i<thisEl.gameM.listCard.length;i++){
                    if(thisEl.gameM.listCard[i]!=thisEl && thisEl.gameM.listCard[i].obj.numMonopoly==thisEl.obj.numMonopoly){
                        if(thisEl.gameM.listCard[i].vkldf2on){
                            thisEl.gameM.listCard[i].infocard.click();
                        }
                    }
                }
            }
            thisEl.selectVid.css('background','blue');
        };
        this.vkldf2=function(){
            thisEl.listVid.off('click',thisEl.vkldf2);
            thisEl.vkldf2on=false;
            thisEl.infocard.off('click',thisEl.vkldf2).on('click',thisEl.vkldf);
            thisEl.listVid.remove();
            thisEl.selectVid.css('background','white');
//            thisEl.vid.append(thisEl.selectVid);
            thisEl.gameM.listSelectFirm.splice($.inArray(thisEl.ind, thisEl.gameM.listSelectFirm),1);
            thisEl.gameM.updPanelSum(thisEl.actionUser);
        };
        this.infocard.on('click',this.vkldf);
    },
    getSelectVid:function(){
        if(this.selectVid==null){
            this.selectVid=$('<div style="background:white;position:absolute;z-index:20;top:0;left:0;width:'+this.w+'px;height:'+this.h+'px;opacity:0.4"></div>');
        }
        return this.selectVid;
    },
    getSelectVid2:function(){
        if(this.selectVid2==null){
            this.selectVid2=$('<div style="background:yellow;position:absolute;z-index:20;top:0;left:0;width:'+this.w+'px;height:'+this.h+'px;opacity:0.4"></div>');
        }
        return this.selectVid2;
    },
    canSelect2:function(action){
        var thisEl=this;
        this.getSelectVid2();
        this.vid.append(this.selectVid2);
        this.infoMoney.text(this.obj.price);
        this.vkldf3=function(){
            thisEl.infocard.off('click',thisEl.vkldf3).on('click',thisEl.vkldf4);
            thisEl.listVid.on('click',thisEl.vkldf4);
            thisEl.gameM.changePanel.apponentSelect.append(thisEl.listVid);
            thisEl.gameM.listSelectFirm2[thisEl.gameM.listSelectFirm2.length] = thisEl.ind;
            thisEl.gameM.updPanelSum(thisEl.actionUser,1);
            thisEl.selectVid2.css('background','green');
        };
        this.vkldf4=function(){
            thisEl.listVid.off('click',thisEl.vkldf4);
            thisEl.infocard.off('click',thisEl.vkldf4).on('click',thisEl.vkldf3);
            thisEl.listVid.remove();
            thisEl.selectVid2.css('background','yellow');
            //thisEl.vid.append(thisEl.selectVid2);
            thisEl.gameM.listSelectFirm2.splice($.inArray(thisEl.ind, thisEl.gameM.listSelectFirm2),1);
            thisEl.gameM.updPanelSum(thisEl.actionUser,2);
        };
        this.infocard.on('click',this.vkldf3);
    },
    putFirm:function(){
        if(this.putVid==null){
            this.putVid=$('<div style="background:red;position:absolute;z-index:20;top:0;left:0;width:'+this.w+'px;height:'+this.h+'px;opacity:0.4"></div>');
        }
        this.vid.append(this.putVid);
        this.infoMoney.text(this.obj.price);
    },
    redeemFirm:function(){
        this.putVid.remove();
        this.recalculatePenalty();
    },
    canSelectCancal:function(){
        if(this.selectVid && this.selectVid.is(':visible')){
            this.selectVid.css('background','white');
            this.selectVid.remove();
            this.listVid.off('click',this.vkldf).off('click',this.vkldf2);
            this.infocard.off('click',this.vkldf).off('click',this.vkldf2);
            this.actionUser=null;
            this.vkldf2on=null;
            this.recalculatePenalty();
        }
    },
    canSelectCancal2:function(){
        if(this.selectVid2 && this.selectVid2.is(':visible')){
            this.selectVid2.css('background','yellow')
            this.selectVid2.remove();
            this.infocard.off('click',this.vkldf3).off('click',this.vkldf4);
            this.listVid.off('click',this.vkldf3).off('click',this.vkldf4);
            this.recalculatePenalty();
        }
    },
    buyFilial:function(){
        if(this.poleStar==null){
            this.poleStar=$('<div style="position: absolute; z-index: -3; top: 0;left: 0;text-align: center; vertical-align: middle;display: table-cell;">').css('height',Math.round(this.h/4)+'px').css('width',this.w+'px');
            this.infoMoneyBG.append(this.poleStar);
        }
        var imgS='zvezd01.png';
        var razm=Math.round(this.gameM.actSize.shMc/4)-3;
        if(this.obj.filialStay>=5){
            this.poleStar.empty();
            imgS='zvezd02.png';
            razm=Math.round(razm*1.5);
        }
        this.poleStar.append('<img src="/resources/images/games/'+this.gameM.room.imageFolder+'/'+imgS+'" width="'+razm+'px" height="auto"/>');
        this.recalculatePenalty();
    },
    putFilial:function(){
        var razm=Math.round(this.w/4)-3;
        if(this.obj.filialStay==4){
            this.poleStar.empty();
            imgS='zvezd02.png';
            for(var i=0;i<4;i++){
                this.poleStar.append('<img src="/resources/images/games/'+this.gameM.room.imageFolder+'/'+imgS+'" width="'+razm+'px" height="auto"/>');
            }
        }else{
            this.poleStar.find('img:first').remove();
        }
        this.recalculatePenalty();
    }
}

var DataGameLoader=Class.create();
DataGameLoader.prototype = {
    colorF:"",
    initialize:function( ){
        
    },
    build:function(){
       
    },
	loadDataGame:function(obj, collbackNameFunction){
        var thisEl=obj;		
        $.ajax({
            url: "/games/monopoly/gameinfo",
            dataType : "json",
            type: "GET"
        }).done(function(data) {			
            if(data!=null) {
                thisEl.room=data;
                if(thisEl.listCard.length==0){
                    thisEl[functionName]();
                }
            }
        });
    }
}

var DataGameNoLoad=Class.create();
DataGameNoLoad.prototype = DataGameLoader.prototype;
DataGameNoLoad.prototype.loadDataGame=function(obj, collbackNameFunction){
        var thisEl=obj;
		//here we need pull in place and cards
		var data=new Object();		
		var rootPath="file:///D:/test/myproject/monopoly/modules/monogol/web/resources/images/games/imaginarium/";//resources/images/games/imaginarium/
		data.cards=[];
		data.pole=new Object();
		data.pole.src=rootPath+"pole.jpg";
		for(var i=0;i<98;i++){
			var card=new Object();
			card.src=rootPath+"card"+i+".jpg";
			data.cards[i]=card;
		}
		data.cardsVote=[];
		for(var i = 0;i<49;i++){
			var card=new Object();
			card.src=rootPath+"cardVote"+i+".jpg";
			data.cardsVote[i]=card;
		}
		
		
        if(data!=null) {
            thisEl.room=data;
            if(thisEl.listCard.length==0){
			obj[collbackNameFunction](data);
		}
    }        
}
DataGameNoLoad.prototype.loadUser=function(obj, collbackNameFunction){
	var listPlayerEl=$(".playerField");
	for(var i=0; i<listPlayerEl.length; i++){
		
	}
}	
	
var StartGame={
	skeepneed:true,
	showStartWindow:function(){
		this.addOnePlayer();
		this.addOnePlayer();
		if(this.skipNeed()) return;	
		var butMinusPl = $("#minusPlayer");
		var butPlusPl = $("#plusPlayer");
		var thisEl=this;
		butMinusPl.on("click", function(){thisEl.removeOnePlayer()});
		butPlusPl.on("click", function(){thisEl.addOnePlayer()});
		var buttonStart = $("#buttonStart");
		buttonStart.on("click", function(){thisEl.startGame()});
	},
	skipNeed:function(){
		if(this.skeepneed){
			var listPlayerEl=$(".namePlayer");
			for(var i=0; i<listPlayerEl.length; i++){
				$(listPlayerEl[i]).val("Player "+(i+1));
	        }
			this.startGame();
		}
		return this.skeepneed;
	},
	addOnePlayer:function(){
		var el = $(".playerField");
		if(el.length==7){
			this.showError("We have maximum players!");
			return;
		}
		var plF = $(el[0]).clone();				
		plF.find(".numPlayer").text(el.length+1);
		$('#playerPanel').append(plF);
		$('#countPlayers').text(el.length+1);
	},
	removeOnePlayer:function(){
		var el = $(".playerField");
		if(el.length==3){
			this.showError("We have minimum players!");
			return;
		}
		$(el[el.length-1]).remove();
		$('#countPlayers').text(el.length-1);
	},
	showError:function(text){
		var err=$("<div>").text(text);
		$('#errorPlace').append(err);
	    err.fadeOut( 3000, function() {err.remove();});
	},
	startGame:function(){		
	    $('#startWindow').hide();
		gameImaginarium.init(null,false);		
		
	}
}	


var gameImaginarium={
    pageS:getPageSize(),
    gamers:[],
    listCard:[],
    room:{},
    step:0,
    lang:langRu,
    infoGame:'',
    poleGame:$('#poleGame'),
    buttons:[],
    listSelectFirm:[],
    listSelectFirm2:[],
    timeLoad:2000,
    curentUser:null,
    online:true,
	dataGameLoader : new DataGameNoLoad(),
    init:function(lang, online){
        if(lang){
            this.lang=lang;
        }
        if(online){
            this.online=online;
        }
        this.loadDataGame();
		
    },
    migEl:null,
    setMigEl:function(el){
        var t=this.migEl;
        this.migEl=el;
        if(t!=null) {
//            t.attr('src', '/resources/images/games/' + this.room.imageFolder + '/ramka.png');
            t.fadeIn('slow');
        }
//        this.migEl.attr('src','/resources/images/games/'+this.room.imageFolder+'/ramka.gif');
    },
    blink:function(){
        var thisEl=this;
        $(this.migEl).fadeOut('slow',function() {
            $(this).fadeIn('slow',function() {
                thisEl.blink(this);
            });
        });
    },
    loadDataGame:function(){
		this.dataGameLoader.loadDataGame(this, "loadPlace")
		
       /*  var thisEl=this;
        if(this.online){
        $.ajax({
            url: predictURL+"/games/monopoly/gameinfo",
            dataType : "json",
            type: "GET"
        }).done(function(data) {
            if(data!=null) {
                thisEl.room=data;
                if(thisEl.listCard.length==0){
                    thisEl.loadPlace();
                }
            }
        });
        } */
    },
    startloadgamedata:function(){
        var thisEl=this;
        this.loadgamedata();
        setTimeout(function(){thisEl.startloadgamedata()},thisEl.timeLoad);
    },
    loadgamedata:function(){
        var thisEl=this;
        $.ajax({
            url: predictURL+"/games/monopoly/loadgamedata",
            dataType : "json",
            type: "GET"
        }).done(function(data) {
            if(data!=null) {
                if(this.keyLoad!=0){
                    if(thisEl.gamers.length!=thisEl.room.maxCountUser){
                        if(data.listAction.length>0){
                            var t=false;
                            for(var i=0;i<data.listAction.length;i++){
                                if(data.listAction[i].action=="START_GAME"){
                                    t=true;
                                    break;
                                }
                            }
                            if(t){
                                thisEl.getStartGamers();
                            }
                        }
                    }else{}
                    if(thisEl.curentUser==null){
                        thisEl.curentUser=data.userRoom;
                    }
                    thisEl.showButton(data);
                    for(var i=0;i<data.listAction.length;i++) {
                        var gamerA = thisEl.getUserByName(data.listAction[i].user.name);
                        // thisEl.loginfo(data.listAction[i].infoAction+" "+data.listAction[i].user.name);
                        if (gamerA != null) {
                            gamerA.user = data.listAction[i].user;
                            gamerA.updateVid();
                            thisEl.loginfo(gamerA.user.name + ' - ' + data.listAction[i].action+'('+data.listAction[i].infoAction+')');
                            if (data.listAction[i].action == "THROW_CUBE") {
                                thisEl.throw_cubeObr(data.listAction[i].infoAction, gamerA.fishka);
                            }
                            if (data.listAction[i].action == "GO_SELL") {
                                thisEl.startGoSellFunction(data.listAction[i].infoAction, gamerA.fishka);
                            }
                            var card=null;
                            if (data.listAction[i].action == "CHANGE_USER") {
                                var gamer = thisEl.getUserByName(data.listAction[i].infoAction.name);
                                gamer.user = data.listAction[i].infoAction;
                                gamer.updateVid();
                            }
                            if (data.listAction[i].action == "BUY_FIRM") {
                                card=thisEl.listCard[thisEl.getIndexFirm(data.listAction[i].infoAction)];
                                card.obj=data.listAction[i].infoAction;
                                card.buyFirm(gamerA);
                                thisEl.panelAuction.hide();
                            }
                            if(data.listAction[i].action == "NOT_MONEY"){
                                this.loginfo('У вас не достаточно денег. Нужно '+data.listAction[i].infoAction);
                            }
                            if (data.listAction[i].action == "RECEIVE_INCOME") {
                                var gamer = thisEl.getUserByName(data.listAction[i].infoAction.name);
                                gamer.user = data.listAction[i].infoAction;
                                gamer.updateVid();
                            }
                            if (data.listAction[i].action == "PUT_FIRM") {
                                card=thisEl.listCard[thisEl.getIndexFirm(data.listAction[i].infoAction)];
                                card.obj=data.listAction[i].infoAction;
                                card.putFirm();
                                thisEl.cancalSelectFirm();
                            }
                            if (data.listAction[i].action == "BUY_FILIAL") {
                                card=thisEl.listCard[thisEl.getIndexFirm(data.listAction[i].infoAction)];
                                card.obj=data.listAction[i].infoAction;
                                card.buyFilial();
                                thisEl.cancalSelectFirm();
                            }
                            if (data.listAction[i].action == "SELL_FILIAL") {
                                card=thisEl.listCard[thisEl.getIndexFirm(data.listAction[i].infoAction)];
                                card.obj=data.listAction[i].infoAction;
                                card.putFilial();
                                thisEl.cancalSelectFirm();
                            }
                            if (data.listAction[i].action == "REDEEM_FIRM") {
                                card=thisEl.listCard[thisEl.getIndexFirm(data.listAction[i].infoAction)];
                                card.obj=data.listAction[i].infoAction;
                                card.redeemFirm();
                                thisEl.cancalSelectFirm();
                            }
                            if(data.listAction[i].action=="RETURN_IN_BANK"){
                                card=thisEl.listCard[thisEl.getIndexFirm(data.listAction[i].infoAction)];
                                card.obj=data.listAction[i].infoAction;
                                card.sellFirm();
                            }
                            if(data.listAction[i].action=="AUCTION_BRACK"){
                                thisEl.panelAuction.hide();
                            }
                            if(data.listAction[i].action=="WIN"){
                                thisEl.win();
                            }
                            if(data.listAction[i].action=="GO_PRISON"){
                                var gamer = thisEl.getUserByName(data.listAction[i].infoAction.name);
                                gamer.user = data.listAction[i].infoAction;
                                thisEl.startFunctionGoPrison(gamer);
                            }
                            if(data.listAction[i].action=="EXCHANGE_OFFERS"){
                                if(data.listAction[i].infoAction.user.name==data.userRoom.name){
                                    thisEl.razborExchOff(data.listAction[i].infoAction);
                                }
                            }
                        }
                    }
                }
            }
        });
    },
    razborExchOff:function(data){
        this.changePanel.userSelect.append('<option value="' + data.userChanger.name + '">' + data.userChanger.name + '</option>');
        this.changePanel.userSelect.attr("disabled","disabled");
        this.changePanel.myMoney.attr("disabled","disabled");
        this.changePanel.myMoney.val(data.money);
        this.changePanel.apponentMoney.attr("disabled","disabled");
        this.changePanel.apponentMoney.val(data.moneyUserChanger);
        for(var i=0;i<data.indFirmUserChanger.length;i++){
            var card=this.listCard[data.indFirmUserChanger[i]];
            this.changePanel.apponentSelect.append(card.listVid);
            card.vid.append(card.getSelectVid2());
        }
        for(i=0;i<data.indFirm.length;i++){
            var card=this.listCard[data.indFirm[i]];
            this.changePanel.mySelect.append(card.listVid);
            card.vid.append(card.getSelectVid());
        }
        this.changePanel.panelBut.hide();
        var thisEl=this;
        var panelBut=$('<div>');
        panelBut.append($('<button>Принять</button>').click(function(){
            thisEl.cleanChPanel(data,panelBut);
            thisEl.actionsUser("CHANGE_FIRM_OK","change_firm_okObr");
        })).append($('<button>Отказать</button>').click(function(){
            thisEl.cleanChPanel(data,panelBut);
            thisEl.actionsUser("CHANGE_FIRM_CANCAL","change_firm_cancalObr");
        }));
        this.changePanel.panelBut.parent().append(panelBut);
        this.changePanel.vid.show();
    },
    cleanChPanel:function(data,panelBut){
        this.changePanel.userSelect.empty();
        this.changePanel.userSelect.removeAttr("disabled");
        this.changePanel.myMoney.removeAttr("disabled");
        this.changePanel.myMoney.val(0);
        this.changePanel.apponentMoney.removeAttr("disabled");
        this.changePanel.apponentMoney.val(0);
        this.changePanel.mySelect.empty();
        this.changePanel.apponentSelect.empty();
        for(var i=0;i<data.indFirmUserChanger.length;i++){
            var card=this.listCard[data.indFirmUserChanger[i]];
            card.listVid.remove();
            card.selectVid2.remove();
        }
        for(i=0;i<data.indFirm.length;i++){
            var card=this.listCard[data.indFirm[i]];
            card.listVid.remove();
            card.selectVid.remove();
        }
        panelBut.remove();
        panelBut=null;
        this.changePanel.panelBut.show();
        this.changePanel.vid.hide();
    },
    getIndexFirm:function(card){
        for(var i=0;i<this.listCard.length;i++){
            if(this.listCard[i].obj.name==card.name){
                return i;
            }
        }
    },
    loginfo:function(text){
        this.infoGame.prepend('<hr style="color:orange; width:60px;"/>');
        this.infoGame.prepend('<div>'+text+'</div>');
    },
    getUserByName:function(name){
        for(var y=0;y<this.gamers.length;y++){
            if(this.gamers[y].user.name==name){
                return this.gamers[y];
            }
        }
    },
    showHidebut:function(pos,but){
        if(pos){
            but.removeAttr('disabled').css('font-weight','bold');
        }else{
            but.attr('disabled','disabled').css('font-weight','normal');
        }
    },
    hideAllBut:function(){
        for(var b in this.buttons){
            this.buttons[b].attr('disabled','disabled').css('font-weight','normal');
        }
    },
    showButton:function(data){
        //this.hideAllBut();
        for(var b in this.buttons){
            var show=false;
            for(var i=0;i<data.availAction.length;i++){
                if(b==data.availAction[i]){
                    if(b=='AUCTION_BUY' || b=='AUCTION_FOLD'){
                        this.panelAuction.show();
                    }
                    show=true;
                    break;
                }
            }
            this.showHidebut(show,this.buttons[b]);
        }
        if(data.availAction.length>1){
            this.timeLoad=10000;
        }else{
            this.timeLoad=2000;
        }
    },
    loadPlace:function(data){
        var thisEl=this;
        if(data!=null) {
			thisEl.listCard=data.cards;
            thisEl.buildPlace(data, getMaxSizeInnerBlock(567,794,this.pageS[2],this.pageS[3]));
            for(var i=0;i<thisEl.listCard.length;i++){
				thisEl.listCard[i].checkedData();
            }
            thisEl.buildSystemControl();
            thisEl.getStartGamers();
            thisEl.startloadgamedata();            
        }
        /* }else{
               thisEl.listCard=[];
               thisEl.buildPlace(data,thisEl.getSizePl(data.length));
               for(var i=0;i<thisEl.listCard.length;i++){
                    thisEl.listCard[i].checkedData();
               }
               thisEl.buildSystemControl();
               thisEl.getStartGamers();
               thisEl.startloadgamedata();
        } */
    },
    getStartGamers:function(){
        var thisEl=this;
        $.ajax({
            url: predictURL+"/games/monopoly/getStartGamers",
            dataType : "json",
            type: "GET"
        }).done(function(data) {
            if(data!=null) {
                thisEl.synhroGame(data);
                if(thisEl.gamers.length==thisEl.room.maxCountUser){
                    $('#waitGwin').hide();
                    thisEl.blink();
                }
            }
        });
    },
    synhroGame:function(users){
        for(var i=0;i<users.length;i++){
            var gamer=this.getUserByName(users[i].name);
            if(gamer==null){
                var num=this.gamers.length;
                this.gamers[num]=new Gamer(users[i],this,num+1);
                gamer=this.gamers[num];
            }
            this.listCard[gamer.user.indexPosition].goOn(gamer);
            this.userPanel.append(gamer.vid);
        }
        $('.userOwner').remove();
        for(var i=0;i<this.listCard.length;i++){
            if(this.listCard[i].obj.userOwner!=null){
                var g=this.getUserByName(this.listCard[i].obj.userOwner.name);
                this.listCard[i].buyFirm(g);
            }
        }
    },
    buildPlace:function(data,size){
		//?
		$('body').css('background','black');
        var gC=1;
		
        var topline=$('<div>');
        $('#poleGame').css('width', (size[0]-2)+'px');
        $('#poleGame').css('font-size', 10+'pt');
        
        var centerLine=$('<div style="display: inline; clear: left;">');
        centerLine.append('<div id="centerPl" class="centerPlace" style="position:relative;width:'+(size[0]-2)+'px;height:'+(size[1]-2)+'px;display: inline; float: left;"><img src="'+data.pole.src+'" width="'+(size[0]-2)+'px" height="'+(size[1]-2)+'px"/></div>');
        this.poleGame.append($('<div id="place" style="float:left;">').append(centerLine));
    },
    buildCard:function(listCardObj,ind,razm){
        this.listCard[ind]=new Card(this,listCardObj[ind],ind,razm);
        return this.listCard[ind].vid;
    },
    createBut:function(butName){
        this.buttons[''+butName]=this._createBut(butName);
    },
    _createBut:function(butName){
        var thisEl=this;
        return $('<button style="vertical-align: top;font-size:'+this.actSize.fontsize+'pt;width:'+(Math.round(this.actSize.shCPl/4)-2)+'px;height:'+Math.round((this.actSize.shCPl/3)/4)+'px;" value="'+this.lang[butName]+'">'+this.lang[butName]+'</button>').click(function(){
            thisEl.actionsUser(butName,butName.toLowerCase()+"Obr");
        });
    },
    buildSystemControl:function(){
        var thisEl=this;
        this.createBut('THROW_CUBE');
        this.createBut('BUY_FIRM');
        this.createBut('PUT_FIRM');
        this.createBut('REDEEM_FIRM');
        this.createBut('PAY_PENALTY');
        this.createBut('CHANGE_FIRM');
        this.createBut('BUY_FILIAL');
        this.createBut('SELL_FILIAL');
        this.createBut('AUCTION_START');
        this.createBut('AUCTION_BUY');
        this.createBut('AUCTION_FOLD');
        this.createBut('TAKE_CREDIT');
        this.createBut('GIVE_CREDIT');
        //this.createBut('GAME_END');
        this.createBut('GAME_CLOSE');
        this.buttons['GAME_CLOSE'].hide().click(function(){
            document.location.href=predictURL+"/games/monopoly/actions/game_close";
        });

        this.butGE=this._createBut('GAME_END');

        var t=Math.round(this.actSize.shCPl/4);
        this.infoGame=$('<div id="infoGame" style=";margin:3px 3px 0 3px;text-align:center; font-size:'+this.actSize.fontsize+'pt;border:1px solid gray;width:'+Math.round(this.actSize.shCPl/2-12)+'px;height:'+((t*2)-20-this.actSize.fontsize*2)+'px;overflow: auto;color:white;">');

        this.userPanel=$('<div id="userPanel" style="float:left;display:inline-block;text-align:center;width:'+Math.round(this.actSize.shCPl/2-2)+'px;height:'+t*2+'px;">');

        var topPanel=$('<div style="width:'+this.actSize.shCPl+'px;height:'+(t*2-2)+'px;text-align:center;position:relative;">');
        var bottomPanel=$('<div style="background-color:black;opacity:0.85;font-size:'+this.actSize.fontsize+'pt; color: white; width:'+this.actSize.shCPl+'px; height: '+(t*2)+'px;"></div>');
        this.messageField=$('<input type="text" style="font-size:'+this.actSize.fontsize+'pt;color:white;background:black;margin:6px 3px 0 0;border:1px solid gray;width:'+(+Math.round(this.actSize.shCPl/2)-(60+this.actSize.fontsize*4))+'px;"/>');
        var buttonSendMess=$('<button style="font-size:'+this.actSize.fontsize+'pt;float:right;margin:3px 3px 3px 0;width:'+(40+this.actSize.fontsize*4)+'px;">'+this.lang['SEND_MESSAGE']+'</button>').click(function(){
            thisEl.actionsUser('SEND_MESSAGE','send_messageObr');
        });
        var infoPanel=$('<div style="float:left;display:inline-block;text-align:center;width:'+Math.round(this.actSize.shCPl/2-1)+'px;height:'+t*2+'px;">');
        infoPanel.append(this.infoGame).append($('<div style="">').append(this.messageField).append(buttonSendMess));
        bottomPanel.append(this.userPanel).append(infoPanel);
        this.butPanel=$('<div style="width: '+this.actSize.shCPl+'px;height:'+t+'px;text-align:center;position:absolute;z-index:10;top:0;left:0;">');
        for(var b in this.buttons){
            if(b=='AUCTION_BUY' || b=='AUCTION_FOLD'){
                continue;
            }
            this.butPanel.append(this.buttons[b]);
        }
        this.imgName='/resources/images/games/'+this.room.imageFolder+'/';
        this.butPanel.append(this.butGE);
        this.panelSelectFirm=$('<div style="background:black;position:absolute;z-index:10;top:0;left:0;width:'+this.actSize.shCPl+'px;height:'+(t*2-2)+'px;"></div>').hide();
        this.panelAuction=$('<div style="background:black;position:absolute;z-index:10;top:0;left:0;width:'+this.actSize.shCPl+'px;height:'+(t*2-2)+'px;"></div>').hide();
        this.panelAuction.append(this.buttons['AUCTION_BUY']).append(this.buttons['AUCTION_FOLD']);
        this.panelThrowCube=$('<div style="background:black;position:absolute;z-index:10;top:0;left:0;width:'+this.actSize.shCPl+'px;height:'+(t*2-2)+'px;text-aligne:center;vertical-aligne:middle;"></div>').hide();
        this.imgThrowCube1=$('<img style="vertical-align: middle;" src="'+this.imgName+'cube/'+1+'.png" width="'+Math.round(t)+'px" height="'+Math.round(t)+'px"/>');
        this.imgThrowCube2=$('<img style="vertical-align: middle;" src="'+this.imgName+'cube/'+2+'.png" width="'+Math.round(t)+'px" height="'+Math.round(t)+'px"/>');
        this.panelThrowCube.append(this.imgThrowCube1).append(this.imgThrowCube2).append('<div style="display: inline-block; height: 100%; vertical-align: middle;"></div>');
        var butPut=$('<button value="'+this.lang['PUT_FIRM']+'">'+this.lang['PUT_FIRM']+'</button>').click(function(){
            thisEl.actionsUser('PUT_FIRM','put_firmObr');
            thisEl.cancalSelectFirm();
        }).hide();
        var butRed=$('<button value="'+this.lang['REDEEM_FIRM']+'">'+this.lang['REDEEM_FIRM']+'</button>').click(function(){
            thisEl.actionsUser('REDEEM_FIRM','redeem_firmObr');
            thisEl.cancalSelectFirm();
        }).hide();
        var butBuyF=$('<button value="'+this.lang['BUY_FILIAL']+'">'+this.lang['BUY_FILIAL']+'</button>').click(function(){
            thisEl.actionsUser('BUY_FILIAL','buy_filialObr');
            thisEl.cancalSelectFirm();
        }).hide();
        var butPutF=$('<button value="'+this.lang['SELL_FILIAL']+'">'+this.lang['SELL_FILIAL']+'</button>').click(function(){
            thisEl.actionsUser('SELL_FILIAL','sell_filialObr');
            thisEl.cancalSelectFirm();
        }).hide();
        this.buttonsWinBAY={'PUT_FIRM':butPut,'REDEEM_FIRM':butRed,'BUY_FILIAL':butBuyF,'SELL_FILIAL':butPutF};
        var butPutCancal=$('<button value="'+this.lang['CANCAL']+'">'+this.lang['CANCAL']+'</button>').click(function(){
            thisEl.cancalSelectFirm();
        });
        this.poleF=$('<div style="width:'+Math.round(this.actSize.shCPl/2)+'px;height:'+(t*2-30)+'px; overflow: auto; border:1px solid white;margin:1px;"></div>');
        this.panelSum=$('<div style="color: white; width:'+Math.round(this.actSize.shCPl/2)+'px;height:25px; overflow: auto; border:1px solid white;margin:1px;"></div>')
        this.panelSelectFirm.append($('<div style="width:'+Math.round(this.actSize.shCPl/2)+'px;height:'+(t*2-2)+'px;float:left;margin:3px;"></div>').append(this.poleF).append(this.panelSum)).append($('<div></div>').append(butPut).append(butRed).append(butBuyF).append(butPutF).append(butPutCancal));
        topPanel.append(this.buildChangePanel()).append(('<div style="background-color:black;opacity:0.85;width: '+this.actSize.shCPl+'px;height:'+(t*2)+'px;text-align:center;position:absolute;z-index:1;top:0;left:0;">')).append(this.butPanel).append(this.panelSelectFirm).append(this.panelAuction).append(this.panelThrowCube);
        $('#centerPl').append($('<div id="systContr" style="position:absolute;left: 0;top:0; z-index: 10; width: '+this.actSize.shCPl+'px;height:'+this.actSize.shCPl+'px;">').
                append(topPanel).append(bottomPanel));
    },
    buildChangePanel:function(){
        var thisEl=this;
        var ochf={};
        var t=Math.round(this.actSize.shCPl/2);
        ochf.vid=$('<div style="float:clear;background:black;position:absolute;z-index:12;top:0;left:0;width:'+(this.actSize.shCPl)+'px;height:'+t+'px;"></div>').hide();
        var panelChMy=$('<div style="width:'+(this.actSize.shCPl/2)+'px;height:'+(t-2)+'px;float:left;"></div>');
        var panelCh2=$('<div style="width:'+(this.actSize.shCPl/2)+'px;height:'+(t-2)+'px;float:left;"></div>');
        ochf.mySelect=$('<div style="border:1px solid gray;margin: 3px;height:'+(t-100)+'px;overflow:auto;"></div>');
        ochf.myMoney=$('<input type="text" style="background: black; color:#ffffff;">');
        panelChMy.append('<div style="height:22px;">Вы</div>').append(ochf.mySelect).append($('<div style="padding: 3px;">').append(ochf.myMoney));
        ochf.userSelect=$('<select style="background: black; color:#ffffff;margin: 3px;">');
        ochf.userSelect.change(function(){
            for(var i=0;i<thisEl.listCard.length;i++){
                thisEl.listCard[i].canSelectCancal2();
            }
            thisEl.listSelectFirm2=[];
            thisEl.changePanel.apponentSelect.empty();
            thisEl.getPossibleFirm("CHANGE_FIRM",this.value);
        });
        ochf.apponentSelect=$('<div style="border:1px solid gray;margin: 3px;height:'+(t-100)+'px;overflow:auto;">');
        ochf.apponentMoney=$('<input type="text" style="background: black; color:#ffffff;">');
        ochf.panelBut=$('<div>')
                .append($('<button>ok</button>').click(function(){
                    thisEl.actionsUser("CHANGE_FIRM","change_firmObr");
                    thisEl.cancalChangeFirm();
                }))
                .append($('<button>Отмена</button>').click(function(){
                    thisEl.cancalChangeFirm();
                }));
        panelCh2.append($('<div style="height:22px;">').append(ochf.userSelect)).append(ochf.apponentSelect).append($('<div style="padding: 3px;">').append(ochf.apponentMoney)).append(ochf.panelBut);

        ochf.vid.append(panelChMy).append(panelCh2);
        this.changePanel=ochf;
        return ochf.vid;
    },
    updPanelSum:function(action,ind){
        var isum=0;
        if(!ind || ind==1){
            for(var i=0;i<this.listSelectFirm.length;i++){
                if(action=="PUT_FIRM"){
                    isum+=Math.round(this.listCard[this.listSelectFirm[i]].obj.price/2);
                }
                if(action=="REDEEM_FIRM"){
                    isum+=this.listCard[this.listSelectFirm[i]].obj.price;
                }
                if(action=="BUY_FILIAL" || action=="SELL_FILIAL"){
                    isum+=this.listCard[this.listSelectFirm[i]].obj.filialPrice;
                }
                if(action=="CHANGE_FIRM"){
                    isum+=this.listCard[this.listSelectFirm[i]].obj.price;
                }
            }
        }else{
            for(var i=0;i<this.listSelectFirm2.length;i++){
                isum+=this.listCard[this.listSelectFirm2[i]].obj.price;
            }
        }
        if(action!="CHANGE_FIRM"){
            this.panelSum.text(isum);
        }else{
            if(ind==1){

            }else{

            }
        }
    },
    cancalSelectFirm:function(){
        if(this.panelSelectFirm.is(':visible')){
            for(var i=0;i<this.listCard.length;i++){
                this.listCard[i].canSelectCancal();
            }
            for(var b in this.buttonsWinBAY){
                this.buttonsWinBAY[b].hide();
            }
            this.listSelectFirm=[];
            this.poleF.empty();
            this.panelSelectFirm.hide();
        }
    },
    cancalChangeFirm:function(){
        for(var i=0;i<this.listCard.length;i++){
            this.listCard[i].canSelectCancal2();
            this.listCard[i].canSelectCancal();
        }
        this.listSelectFirm=[];
        this.listSelectFirm2=[];
        this.changePanel.apponentSelect.empty();
        this.changePanel.apponentMoney.val("");
        this.changePanel.mySelect.empty();
        this.changePanel.myMoney.val("");
        this.changePanel.vid.hide();
    },
    throw_cubeObr:function(data,fishka,ind){
        var thisEl=this;
        if(!ind){
            ind=0;
            this.panelThrowCube.show();
        }
        this.imgThrowCube1.attr('src',this.imgName+'cube/'+this.getRandomInt(1,6)+'.png');
        this.imgThrowCube2.attr('src',this.imgName+'cube/'+this.getRandomInt(1,6)+'.png');
        if(ind<10){
            ind+=1;
            setTimeout(function(){thisEl.throw_cubeObr(data,fishka,ind)},100);
        }else{
            this.imgThrowCube1.attr('src',this.imgName+'cube/'+data[0]+'.png');
            this.imgThrowCube2.attr('src',this.imgName+'cube/'+data[1]+'.png');
            //this.go_sellObr2(data,fishka);
        }
    },
    getRandomInt:function (min, max){
        return Math.floor(Math.random() * (max - min + 1)) + min;
    },
    startGoSellFunction:function(data,fishka){
        var thisEl=this;
        setTimeout(function(){thisEl.go_sellObr(data,fishka);},2000);
    },
    startFunctionGoPrison:function(gamer){
        var thisEl=this;
        setTimeout(function(){
            gamer.updateVid();
            gamer.fishka.posit=gamer.user.indexPosition;
            card=thisEl.listCard[gamer.user.indexPosition];
            card.goOn(gamer);
            thisEl.panelThrowCube.find('img').hide();
            thisEl.panelThrowCube.append($('<img style="vertical-align: middle;" src="'+thisEl.imgName+'goPrison.png" width="auto" height="'+Math.round(thisEl.actSize.shCPl/3)+'px"/>'));
            setTimeout(function(){
                thisEl.panelThrowCube.find('img:visible').remove();
                thisEl.panelThrowCube.hide();
                thisEl.panelThrowCube.find('img').show();
            },2000);
        },2000);
    },
    go_sellObr:function(data,fishka){
        var step=parseInt(data);
        fishka.posit+=step;
        if(fishka.posit>this.listCard.length-1){
            fishka.posit=fishka.posit-this.listCard.length;
        }
        this.setFishkaI(fishka,step);
    },
    getPossibleFirm:function(action,userName){
        var thisEl=this;
        var urlD=predictURL+"/games/monopoly/actions/getPossibleFirm/"+action;
        if(userName){
            urlD=predictURL+"/games/monopoly/actions/getPossibleFirmCh/"+userName;
        }
        $.ajax({
            url: urlD,
            dataType : "json",
            type: "GET"
        }).done(function(data) {
            if(data) {
                for (var i = 0; i < data.length; i++) {
                    if (!userName) {
                        thisEl.listCard[data[i]].canSelect(action);
                    } else {
                        thisEl.listCard[data[i]].canSelect2(action);
                    }
                }
            }
            if(action=="CHANGE_FIRM"){
                if(!userName) {
                    for (var i = 0; i < thisEl.gamers.length; i++) {
                        if (thisEl.curentUser.name != thisEl.gamers[i].user.name) {
                            thisEl.changePanel.userSelect.append('<option value="' + thisEl.gamers[i].user.name + '">' + thisEl.gamers[i].user.name + '</option>');
                            if(!userName){
                                userName=thisEl.gamers[i].user.name;
                                thisEl.getPossibleFirm(action,userName);
                            }
                        }
                    }
                    thisEl.changePanel.vid.show();
                }
            }else{
                thisEl.buttonsWinBAY[action].show();
                thisEl.panelSelectFirm.show();
            }
            thisEl.updPanelSum(action);
        }).error(function(){

        });
    },
    actionsUser:function(actions, functObr) {
        var thisEl=this;
        var datas = null;
        var typeReq="GET";
        var datat="json";
        this.numError=0;
        jQuery.ajaxSettings.traditional = false;
        if (actions == 'PUT_FIRM' | actions == 'REDEEM_FIRM' | actions == 'BUY_FILIAL' | actions == 'SELL_FILIAL'){
            if (this.listSelectFirm.length == 0) {
                this.getPossibleFirm(actions);
                return;
            } else {
                datas = {'indFirm': this.listSelectFirm};
            }
        }
        if (actions == 'CHANGE_FIRM'){
            if (this.listSelectFirm.length == 0 & this.listSelectFirm2.length == 0) {
                if(this.changePanel.vid.is(':visible')){
                    this.loginfo("Для обмена нужно выбрать хотябы одну фирму с любой стороны");
                    return;
                }
                this.getPossibleFirm(actions);
                return;
            } else {
                jQuery.ajaxSettings.traditional = true;
                typeReq="POST";
                datat="html";
                var money1=parseInt(this.changePanel.myMoney.val());
                if(isNaN(money1)){money1=0;}
                var money2=parseInt(this.changePanel.apponentMoney.val());
                if(isNaN(money2)){money2=0;}
//                'indFirmUserChanger': this.listSelectFirm,'indFirm': this.listSelectFirm2,
                datas = {'indFirmUserChanger': this.listSelectFirm,'indFirm':this.listSelectFirm2,'moneyUserChanger':money1,'money':money2, 'userName':this.changePanel.userSelect.val()};
//                alert(datas);
            }
        }
        if (actions == 'SEND_MESSAGE'){
            datas = {'message':  this.messageField.val()};
            this.messageField.val("");
            typeReq="POST";
        }
        if (actions == 'GAME_END'){
            this.buttons['GAME_CLOSE'].show();
            this.butGE.hide();
        }
        this.hideAllBut();
        this.keyLoad=0;
        $.ajax({
            url: predictURL+"/games/monopoly/actions/"+actions.toLowerCase(),
            dataType : datat,
            data:datas,
//            contentType: "application/json",
            type: typeReq
        }).done(function(data) {
            thisEl.keyLoad=2000;
            thisEl.loadgamedata();
            if (actions == 'GAME_END'){
                thisEl.buttons['GAME_CLOSE'].removeAttr('disabled').css('font-weight','bold');
            }
        }).error(function(){
            if(thisEl.numError<10){
                thisEl.numError+=1;
            }
            setTimeout(function(){thisEl.actionsUser(actions,functObr)},3000*thisEl.numError);
        });
    },
    setFishkaI:function(fishka,step){
        var ind=fishka.posit-step;
        if(ind<0){
            ind=this.listCard.length+ind;
        }
        this.listCard[ind].goOnFishka(fishka);
        if(step>0){
            var thisEl=this;
            setTimeout(function(){thisEl.setFishkaI(fishka,step-1)},200);
        }else{
            this.panelThrowCube.hide();
        }
    },
    win:function(ind){
        var thisEl=this;
        if(!ind){
            ind=0;
            this.panelWin=$('<div style="background:black;position:absolute;z-index:40;top:0;left:0;width:'+this.actSize.shCPl+'px;height:'+(this.actSize.shCPl-2)+'px; text-align:center;"></div>');
            $('#systContr').append(this.panelWin);
            this.imgWin=$('<img style="" src="'+this.imgName+'win/'+1+'.gif" width="'+Math.round(this.actSize.shCPl/2)+'px" height="'+Math.round((this.actSize.shCPl-2)/2)+'px"/>');
            this.panelWin.append($('<div style="display: inline-block; vertical-align: middle; color: WHITE; padding: 60px;color:white; font-weight: bold;font-size: 20pt;"></div>')
                    .append('<div>'+this.lang['YOURS_WIN']+'</div>').append(this.imgWin).append($('<div>'+this.lang['GAME_CLOSE']+'</div>')
                            .click(function(){document.location.href=predictURL+"/games/monopoly/actions/game_close";})))
                    .append('<div style="vertical-align: middle;display:inline-block;height:100%;"></div>');
        }
        this.imgWin.attr('src',this.imgName+'win/'+this.getRandomInt(1,3)+'.gif');
        ind+=1;
        setTimeout(function(){thisEl.win(ind)},2000);
    }
};

function arrToString(namePr,arrm){
    var res="";
    for(var i=0;i<arrm.length;i++){
        res+=namePr
    }
    return
}

$(function(){	
	StartGame.showStartWindow();    
});