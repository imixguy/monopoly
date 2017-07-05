var getAllSelWords = function () {
    var listOb = [];
    $('.checkSh:checked').parent().parent().parent().each(function (ind, el) {
        var ob = new Object();
        el = $(el);
        ob.r = el.find('.input-group2 input[type=text]').val();
        ob.e = el.find('.input-group input[type=search]').val();
        if (ob.r != "")
            listOb.push(ob);
    });
    return listOb;
}


var prStr = {"STATE_PR_DEF" : ["Menu", "Slovar"],"statePr" : this.STATE_PR_DEF[1],"numlib" : "0"};

function startWorkCard() {
    var dT = $('#main .node-form #edit-body').val();
    if (dT.length > 0) {
        prStr = JSON.parse(dT);
    }
    jQuery('#main').css({"z-index" : "20","position" : "absolute","background" : "white","left" : "0px","top" : "200px","padding" : "0px 75px 0px 75px"});

    jQuery('#node-form div.standard').hide();
    jQuery('#node-form div.admin').hide();
    jQuery('#edit-preview').hide();
    jQuery('#main .tabs').hide();
    console.log('i did it');

    if (prStr.statePr == STATE_PR_DEF[0]) {
        //Р·РґРµСЃСЊ Р±СѓРґРµС‚ РїРѕСЃС‚СЂРѕРµРЅРёРµ РјРµРЅСЋ
        var t="";
    } else {
        startlib(prStr.numlib);
    }

    $(window).resize(function () {
        if ($('.hideEng .fa-angle-double-left').length > 0) {
            $('.input-group').css('width', ($('.hideEng').width() - $($('.wordcard')[0]).width() * 3) + 'px');
        }
    });
}

function startlib(indLib, settLib) {
    $('#main .node-form').prepend($('label:contains(elements)').parent().find('textarea').val());

    var wordsEl = JSON.parse($('label:contains(words)').parent().find('textarea').val());

    var elEx = $('.exampleEl').html();
    var reReplacePatternRu = /rusword/g;
    var reReplacePatternEn = /enword/g;

    $(wordsEl[indLib].d).each(function (ind, el) {
        var t = elEx.replace(/rusword/g, el.r);
        t = t.replace(/enword/g, el.e);
        $('.wordsEl').append(t);
    });

    $('.hideEng').on('click', function () {
        var f1 = {};
        var f2 = {};
        f1 = function () {
            $(this).find('.fa-arrow-right').removeClass('fa-arrow-right').addClass('fa-arrow-left');
            $(this).parent('.input-group').css('width', ($('.hideEng').width() - $($('.wordcard')[0]).width() * 3) + 'px');
            $(this).one('click', f2);
        };
        f2 = function () {
            $(this).find('.fa-arrow-left').removeClass('fa-arrow-left').addClass('fa-arrow-right');
            $(this).parent('.input-group').css('width', '1px');
            $(this).one('click', f1);
        };
        if ($('.hideEng .fa-angle-double-left').length > 0) {
            $('.hideEng .fa-angle-double-left').removeClass('fa-angle-double-left').addClass('fa-angle-double-right');
            $('.hideEng .txtF').text(' РџРѕРєР°Р·Р°С‚СЊ Р°РЅРіР»РёР№СЃРєРёР№');
            $('.input-group').css('width', '1px');
            $('.input-group .fa-arrow-left').removeClass('fa-arrow-left').addClass('fa-arrow-right');
            $('.input-group .fashowinput').one('click', f1);
        } else {
            $('.hideEng .fa-angle-double-right').removeClass('fa-angle-double-right').addClass('fa-angle-double-left');
            $('.hideEng .txtF').text(' РЎРєСЂС‹С‚СЊ Р°РЅРіР»РёР№СЃРєРёР№');
            $('.input-group').css('width', ($('.hideEng').width() - $($('.wordcard')[0]).width() * 3) + 'px');
            $('.input-group .fa-arrow-right').removeClass('fa-arrow-right').addClass('fa-arrow-left');
            $('.input-group .fashowinput').one('click', f2);
        }
    });
    jQuery.fn.shuffle = function () {
        var allElems = this.get();
        var getRandom = function (max) {
            return Math.floor(Math.random() * max);
        };
        var shuffled = jQuery.map(allElems, function () {
            var random = getRandom(allElems.length);
            randEl = jQuery(allElems[random]).clone(true)[0];
            allElems.splice(random, 1);
            return randEl;
        });
        this.each(function (i) {
            jQuery(this).replaceWith(jQuery(shuffled[i]));
        });
        return jQuery(shuffled);
    };
    $('.shuffler input').on('change', function () {
        if ($('.checkSh:checked').length > 0) {
            $('.checkSh:checked').prop("checked", "");
            $('.shuffler label span').text("Р’С‹РґРµР»РёС‚СЊ РІСЃРµ.");
        } else {
            $('.checkSh').prop("checked", "checked");
            $('.shuffler label span').text("РЎРЅСЏС‚СЊ РІС‹РґРµР»РµРЅРёРµ.");
        }
    });
    $('.headline').nextAll('.row[id][id!=endlist]').find('.input-group2').prepend('<input type="checkbox" class="checkSh"/>');
    $('body').append('<div style="display:block;height:300px;width:60px;left:200px;top:0px;z-index:21;position:fixed;font-size: smaller;"><div style="padding:10px 10px;"><i class="fa fa-random" style="width: 40px;font-size: 22px;"/></div><div style="padding:10px 10px;"><i class="fa fa-level-down" style="width: 40px;font-size: 22px;"/></div><div style="padding:10px 10px;"><i class="fa fa-external-link" style="width: 40px;font-size: 22px;"/></div></div>');
    $('.fa-random').on('click', function () {
        $('.checkSh:checked').parents('.row').shuffle();
    });
    $('.fa-level-down').on('click', function () {
        $('.checkSh:checked').parents('.row').insertBefore($('.checkSh:checked:last').parents('.row').next());
    });
    $('.checkSh').on('change', function () {
        if ($('.checkSh:checked').length > 0) {
            $('.shuffler input').prop("checked", "checked");
            $('.shuffler label span').text("РЎРЅСЏС‚СЊ РІС‹РґРµР»РµРЅРёРµ.");
        } else {
            $('.shuffler input').prop("checked", "");
            $('.shuffler label span').text("Р’С‹РґРµР»РёС‚СЊ РІСЃРµ.");
        }
    });
    $('.fa-external-link').on('click', function () {
        var d = JSON.stringify(getAllSelWords());
        console.log(d);
    });
    $('.fa-times').on('click', function () {
        var thisEl = $(this).parents('div.row');
        var idel = thisEl.find('input:hidden').val();
        $.ajax({
            method : "GET",
            url : "/new/profile/del/word/" + idel + "/" + $('input[name=dic_id]:first').val() + "/"
        }).done(function (msg) {
            thisEl.remove();
        });
    });
}

jQuery(function () {
    startWorkCard();
});