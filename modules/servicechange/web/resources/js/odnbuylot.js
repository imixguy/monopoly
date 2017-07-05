var licfplaulot='<p><b>Лицензионное соглашение с конечным пользователем программы автопокупка лотов</b></p><p>Данный документ является лицензионным соглашением (далее Соглашение) между пользователем (физическое или юридическое лицо) (далее Пользователь) и распространителем программного обеспечения (Правообладатель), дающим право на использование программы автопокупка лотов (Программы).</p><p><b>Установка, регистрация, использование</b></p><p>Начав установку либо использование Программы, Пользователь тем самым подтверждает свое согласие соблюдать условия данного Соглашения. В случае несогласия Пользователь должен отказаться от дальнейшей установки либо использования Программы.</p><p><b>Ограниченная гарантия</b></p><p>Программа предназначается и предоставляется в качестве программного обеспечения общего назначения, а не для какой-либо конкретной цели пользователя. Пользователь соглашается с тем, что любое программное обеспечение может содержать ошибки и не будет иметь никаких притензий к правообладателю.</p><p>Правообладатель не несет ответственности за потерю, повреждение, издержки и затраты, понесенные Пользователем или третьим лицом в результате использования Программы, за торговые расходы, простой оборудования, потери, понесенные Пользователем или третьим лицом в результате любой деятельности Программы. Правообладатель не несет ответственности за косвенный, особый, случайный ущерб, а также его последствия, связанные или возникающие в связи с предметом настоящего Соглашения.</p><p><b>Дополнение</b></p><p>Пользователь понимает, что программа может изменяться, обновляться и расширять функционал правообладателем без предупреждения пользователя. Пользователь принимает все изменения Программы по условиям данной лицензии.</p><p><b>Сохранение права собственности на Программу</b></p><p>Программа защищена действующим законодательством и международными конвенциями об авторском праве и других правах на интеллектуальную собственность. Правообладателю принадлежат все авторские права на Программу. Программа не продается, а лицензируется как охраняемый результат интеллектуальной деятельности. </p><p><b>Разрешение споров</b></p><p>Пользователь отказывается от любых споров с правообладателем а также принимает все притензии (споры, судебные разбирательства и т.д и т.п.)   направленные на правооблодателя от третьих сторон на себя, от любых действий вызванных данной программой и запущенной им на любом устройстве.</p><p>Данное Соглашение регулируется и подлежит толкованию в соответствии с законодательством Республики Беларусь.</p><p>Все споры, имеющие отношение к данному Соглашению или к использованию Программы, включая споры в отношении законности, толкования, нарушения, расторжения данного Соглашения, должны решаться в Международном Коммерческом Арбитражном Суде при Торгово-Промышленной Палате Республики Беларусь (далее Арбитражный Суд), который является единственным местом и средством правовой защиты в рамках данного Соглашения. Решение, вынесенное Арбитражным Судом, будет обязательным для Правообладателя и Пользователя, и может быть незамедлительно принудительно исполнено на основании исполнительно листа, выданного компетентной судебной инстанцией. Решение Арбитражного Суда может быть оспорено в судебной инстанции Республики Беларусь в соответствии с положениями Гражданского Процессуального Кодекса Республики Беларусь. Оспаривание решения не приостанавливает его исполнения.</p>';

var pgl={
    masVV:[/С.*5/,/С.*7/,/5\+.*2/,/5\+.*5/,/5\+.*7/,/5\+.*10/,/П.*1/,/П.*2/,/Н.*2/,/Н.*5/,/Н.*7/],
    maspr:[],
    ltorg:[],
    tik:100,
    tt:52000,
    wait:3000,
    mwait:50,
    masspr:[250,350,50,125,175,250,2500,5000,10,10,10],
    pik:0,
    puk:0,
	licensia:licfplaulot,
	lp:1,
	ntorg:0,
    init:function(){
	    this.win(this.masspr);  
    },
	addifr:function(){
	    $('#autobuylot').before('<iframe width="100%" height="0" style="border:0;" src="http://imix.by/redpage2"><iframe>')
    },
    win:function(m){
	    if(this.lp==0){
			this.wlic();
			return;
		}
	    if($('#autobuylot').length!=0){			
			return;
		}
        $('#aboutPanel').before('<div id="autobuylot"><style></style><table style="text-align: center;width:100%;"><tr><td><nobr>№ п/п</nobr></td><td>Смайл</td><td>дн/шт</td><td>цена</td></tr><tr><td>1</td><td rowspan="2"><div class="auct_ser-ic auct_ser-ic__service-19"></div></td><td>×5</td><td><input type="text" class="pricer" name="s1kd1" value="'+m[0]+'"/></td></tr><tr><td>2</td><td>×7</td><td><input type="text" class="pricer" name="s1kd2" value="'+m[1]+'"/></td></tr><tr><td>3</td><td rowspan="4"><div class="auct_ser-ic auct_ser-ic__service-1"></div></td><td>×2</td><td><input type="text" class="pricer" name="s2kd1" value="'+m[2]+'"/></td></tr><tr><td>4</td><td>×5</td><td><input type="text" class="pricer" name="s2kd2" value="'+m[3]+'"/></td></tr><tr><td>5</td><td>×7</td><td><input type="text" class="pricer" name="s2kd3" value="'+m[4]+'"/></td></tr><tr><td>6</td><td>×10</td><td><input type="text" class="pricer" name="s2kd4" value="'+m[5]+'"/></td></tr><tr><td>7</td><td rowspan="2"><div class="auct_ser-ic auct_ser-ic__service-11"></div></td><td>×1</td><td><input type="text" class="pricer" name="s3kd1" value="'+m[6]+'"/></td></tr><tr><td>8</td><td>×2</td><td><input type="text" class="pricer" name="s3kd2" value="'+m[7]+'"/></td></tr><tr><td>9</td><td rowspan="3"><div class="auct_ser-ic auct_ser-ic__service-3"></div></td><td>×2</td><td><input type="text" class="pricer" name="s4kd1" value="'+m[8]+'"/></td></tr><tr><td>10</td><td>×5</td><td><input type="text" class="pricer" name="s4kd2" value="'+m[9]+'"/></td></tr><tr><td>11</td><td>×7</td><td><input type="text" class="pricer" name="s4kd3" value="'+m[10]+'"/></td></tr></table><div align="right"><input id="bsprin" type="button" value="Принять"/></div><br><div id="logP" style="border:1px solid gray;overflow: auto;height: 200px;"></div></div>');
		$('.pricer').css('width','50px');
        var thisEl=this;
        $('#bsprin').click(function(){		    
            thisEl.changePraice();			
        });
		thisEl.changePraice();
		//this.addifr();
    },
	wlic:function(){
		if($('#licwin').length!=0){
			$('#licwin').show();
			return;
		}
	    $('#aboutPanel').before('<div id="licwin" style="border:1px solid gray;padding:10px 10px 10px 10px"><div id="licalot" style="overflow:scroll;width:207px;height:300px;border:1px solid gray;"></div><div style="font-size:0.7em;padding-top:10px;"><label><input type="checkbox" id="chlcl">Я принимаю это лицензионное соглашение</label></div><div style="text-align:right;padding-top:10px;"><input id="acceptlal" type="button" value="Ок"></div></div>');
		$('#licalot').prepend('<div>'+this.licensia+'</div>');
		thisEl=this;
		$('#acceptlal').click(function(){
			if($('#chlcl').attr('checked')=='checked'){
				thisEl.lp=1;	
                thisEl.init();			
			}
			$('#licwin').hide();
		});		
	},
    changePraice:function(){
        var thisEl=this;		
        $('.pricer').each(function(ind,el){thisEl.maspr[ind]=parseInt($(el).attr('value'))});
        if(this.puk==0){
            this.puk=1;
            this.searthIG();
        }else{
            this.wrlog('Цены изменены');
        }
    },
    wrlog:function(txt){
        var el= $('#logP');
        el.append('<div>'+txt+'</div>');
        el.scrollTop(el[0].scrollHeight-el[0].offsetHeight);
    },

    searthIG:function(){
        $($('div.sCard')[0]).append('<div id="dfgIn"></div>');
        $('.panelRounded_body a.button-pro').click();
        //this.wrlog('Запускаем обновление');
        this._seartIG();
    },
    _seartIG:function(){
        if($('#dfgIn').length==0){
            //this.wrlog('Обновление выполнено');
            this.searthIGs();
        }else{
            //this.wrlog('...');
            var thisEl=this;
            setTimeout(function(){thisEl._seartIG()},100);
        }
    },
    searthIGs:function(){
        var thisEl=this,mr=[],tNU=60000;this.pik+=1;thisEl.wrlog('шаг:'+this.pik);
		try{tNU=(parseInt(/\d/.exec($('.sep-list tbody tr:last').find('td:eq(1)').text())[0])-1)*60000; if(tNU==0){tNU=60000;}}catch(e){}
        try{
            $('.sep-list tbody tr').each(function(i,el) {
                var c = $(el).find('.sCard_add');
                var ls='';
                mr[i] = [];
                for (var iw = 0; iw < thisEl.masVV.length; iw++) {
                    if (thisEl.masVV[iw].test(c.text())) {
                        mr[i][0] = iw;
                        ls += '№' + iw + '; ';
                        break;
                    }
                }
                var tte = /\d/.exec($(el).find('td:eq(1)').text());
                tte=((tte==null)?0:tte[0]);
                ls += 't='+tte+'; ';
                mr[i][1]=tte;
                mr[i][2]=parseInt($(el).find('.fi').attr('value'));
                ls += 'p=' + mr[i][2] + '; ';
                if(thisEl.isInteres(mr[i])){
                    thisEl.wrlog('Вызвало интерес '+(mr[i][0]+1)+' за '+mr[i][2]);
                    $(el).css('background','lightblue');
                    mr[i][3] = $(el).find('.auction_infoPanel_bidPanel .fi').attr('name');
                    ls += 'id=' + mr[i][3] + '; ';
                    var tw=thisEl.getTime(mr[i]);
                    if (tNU > tw) {
                        tNU=tw;
                        thisEl.wrlog('Изменили время на '+thisEl.prSec(tNU));
                        $(el).css('background','pink');
                    }
                    //return false;
                }
            });
            //this.wrlog(mr[0][0]+';'+mr[1][0]+';'+mr[2][0]+';'+mr[3][0]+';');
            this.wrlog('спим '+this.prSec(tNU)+' проснемся в '+new Date(new Date().getTime()+tNU).toLocaleTimeString());
        } catch(e) {this.wrlog('Ошибка!!! '+e);}
        setTimeout(function(){thisEl.searthIG()},tNU);
    },
    prSec:function(t){
        if(t/1000>59) return t/60000+' мин';
        return t/1000+' сек';
    },
    isInteres:function(mr){
        if(isNaN(mr[2]) || mr[2]==0){
            mr[2]=NaN;
            this.wrlog('много'+'<'+this.maspr[mr[0]]+'='+(mr[2]<this.maspr[mr[0]]));
            return false;
        }
        this.wrlog(mr[2]+'<'+this.maspr[mr[0]]+'='+(mr[2]<this.maspr[mr[0]]));
        return mr[2]<this.maspr[mr[0]];
    },
    getTime:function(mr){
        if(mr[1]==0){this.startTorg(mr,this.ntorg+=1);return 600000};
        if(mr[1]==1){return this.wait};
        if(mr[1]>1){return (mr[1]-1)*60000};
    },
    startTorg:function (mr,ntorg){
        for(var df=0;df<this.ltorg.length;df++){
            if(this.ltorg[df][3]==mr[3]){
                return;
            }
        }		
        this.wrlog('Начали торги №'+ntorg+' за '+(mr[0]+1));
		$('[name="b'+mr[3]+'"]').parent('tr').css('border','1px solid red');
        this.ltorg[this.ltorg.length]=mr;
        var thisEl=this;
        setTimeout(function(){thisEl._startTorg(mr,ntorg)},this.tt);
    },
    _startTorg:function (mr,ntorg){
        var thisEl=this;
        mr[2]=parseInt($('[name="'+mr[3]+'"]').attr('value'));
        if($('[name="b'+mr[3]+'"]').val()=='wait...'){
            this.wrlog(ntorg+' не обновились');
            setTimeout(function(){thisEl._startTorg(mr,ntorg)},this.mwait);
            return;
        }
//        this.wrlog('->.');
        if(this.isInteres(mr)){
		    $('[name="b'+mr[3]+'"]').val('wait...');
            if($('[name="b'+mr[3]+'"]').attr('disabled')==null){
				this.wrlog(ntorg+' cтавим '+mr[2]);
                $('[name="b'+mr[3]+'"]').click().attr('disabled','disabled');				
            }else{
			    this.wrlog(ntorg+' обновляемся...');
                $('.panelRounded_body a.button-pro').click();				
			}			
            setTimeout(function(){thisEl._startTorg(mr,ntorg)},this.mwait);
        }else{
//            this.wrlog('->...');
            $('.panelRounded_body a.button-pro').click();
            this.delLot(mr,ntorg);
//            this.wrlog('->....');
        }
    },
    delLot:function(mr,ntorg){
        this.wrlog('Закончили торги '+ntorg+' за '+(mr[0]+1)+' за '+mr[2]);
        setTimeout(function(){$('.panelRounded_body a.button-pro').click();},62000-this.tt);
        for(var df=0;df<this.ltorg.length;df++){
            if(this.ltorg[df][3]==mr[3]){
                this.ltorg.splice(df,1)
            }
        }
    }
}
pgl.init();