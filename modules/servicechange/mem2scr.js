$('<div class="row panelmanager"><div class="col-xs-6 form-group"><span class="fashowinput input-group-addon hideRu"><i class="fa fa-angle-double-right"></i><span class="txtF"> Скрыть русский</span></span></div><div class="col-xs-6 form-group hideEng"><span class="fashowinput input-group-addon"><i class="fa fa-angle-double-left"></i><span class="txtF"> Скрыть английский</span></span></div></div>').insertAfter('.headline');
$('.form-control:even').wrap('<div class="input-group2" style="border-collapse: separate;position: relative;display: inline-table;"></div>');
$('.input-group').append('<span class="fashowinput input-group-addon"><i class="fa fa-arrow-left"></i></span>');
$('.input-group2').prepend('<span class="fashowinput input-group-addon input-group-addon2"><i class="fa fa-arrow-right"></i></span>');
$('.hideEng').on('click',function(){
var f1={};
var f2={}; f1=function(){$(this).find('.fa-arrow-right').removeClass('fa-arrow-right').addClass('fa-arrow-left');$(this).parent('.input-group').css('width','550px');$(this).one('click',f2);}; f2=function(){$(this).find('.fa-arrow-left').removeClass('fa-arrow-left').addClass('fa-arrow-right');$(this).parent('.input-group').css('width','1px');$(this).one('click',f1);};
if($('.hideEng .fa-angle-double-left').length>0){
$('.hideEng .fa-angle-double-left').removeClass('fa-angle-double-left').addClass('fa-angle-double-right');
$('.hideEng .txtF').text(' Показать английский');
$('.input-group').css('width','1px');
$('.input-group .fa-arrow-left').removeClass('fa-arrow-left').addClass('fa-arrow-right');
$('.input-group .fashowinput').one('click',f1);
}else{
$('.hideEng .fa-angle-double-right').removeClass('fa-angle-double-right').addClass('fa-angle-double-left');
$('.hideEng .txtF').text(' Скрыть английский');
$('.input-group').css('width','550px');
$('.input-group .fa-arrow-right').removeClass('fa-arrow-right').addClass('fa-arrow-left');
$('.input-group .fashowinput').one('click',f2);
}
});
jQuery.fn.shuffle = function(){
var allElems = this.get();
var getRandom = function(max){
return Math.floor(Math.random() * max);
};
var shuffled = jQuery.map(allElems, function(){
var random = getRandom(allElems.length),
randEl = jQuery(allElems[random]).clone(true)[0];
allElems.splice(random, 1);
return randEl;
});
this.each(function(i){
jQuery(this).replaceWith(jQuery(shuffled[i]));
});
return jQuery(shuffled);
};
$('<div class="shuffler">Перемешать.</div>').insertAfter('.headline');
$('.shuffler').on('click',function(){$('.headline').nextAll('.row[id][id!=endlist]').shuffle();});
$('.headline').nextAll('.row[id][id!=endlist]').find('.input-group2').prepend('<input type="checkbox" class="checkSh"/>');
$('body').append('<div style="display:block;height:300px;width:60px;left:0px;top:0px;z-index:4;position:fixed;font-size: smaller;"><div style="padding:10px 10px;"><i class="fa fa-random" style="width: 40px;font-size: 22px;"></i></div></div>');
$('.fa-random').on('click',function(){$('.checkSh:checked').parents('.row').shuffle();});



//Света, Аня, Катя, ???-море, Ира, Аня, Оля, Кристина, Лена 