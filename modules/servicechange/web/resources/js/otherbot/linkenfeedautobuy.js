function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
// возвращает cookie с именем name, если есть, если нет, то null
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

var lfabuy={	
	timeout:10*60*1000,//10 мин
	curind:0,
	init:function(){
		this.searthEl();
	},
	searthEl:function(){
		if(document.getElementById('main_content_around')==null){
			this.nextS();
			return;
		}
		this.curind=parseInt(getCookie('nextS'))||0;	
		//alert('sdf='+this.curind);		
		//устанавливаем уменьшение цены в пределах 30%
		if(parseInt($('percent').selectedIndex)!=2){
			document.getElementById('percent').selectedIndex=2; 
		}
		if($('center_text').innerHTML.unescapeHTML().indexOf('Заявки не найдены')==-1 && $$('.re_data').size()==0){
			this.publicD();
			return;
		}
		if($$('.alert_block').length>0){
			$$('.re_data input')[0].checked=true;
			$$('[value=add_to_bl_web]')[0].selected=true;
			$$('[value=Ok]')[0].onclick();
			return;
			
		}
		if($$('.re_data').size()>0){
			check_all(document.links_form);       
			document.getElementsByName('links_form')[0].submit();
		}else{
			this.nextS();
		}
	},
	nextS:function(){
		var nextInd=this.curind+1;
		if(nextInd>=$('platform_id').options.length){
		    nextInd=0;		    
		}
		setCookie('nextS',nextInd,5);
		if(nextInd==0){
		     var thisEl=this;
			 //this.timeout=5000;
		     setTimeout(function(){thisEl.startNS(nextInd)},this.timeout);
		}else{
			this.startNS(nextInd);
		}
	},
	startNS:function(nextInd){
		//alert(nextInd);
		$('platform_id').options[nextInd].selected=true;
		$('platform_id').onchange();
	},
	publicD:function(){
		document.getElementsByName('search')[0].submit(); 
	}
}

lfabuy.init();