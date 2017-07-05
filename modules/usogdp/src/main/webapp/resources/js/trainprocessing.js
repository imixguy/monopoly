(function ($) {
    var
        _moduleName = "trainprocessing",
        _description = "Модуль работы станции по бригадам",
        _enabledLog = true,
        _version = "0.0.1-Snapshot",
        _storage = {},

        _urls = {
            ctx: ctx || "",
            linkTrainprocessing: this.ctx + '/ajax/gettrainprocessing' // состав поезда
        };


    if (typeof jQuery === 'undefined') {
        throw new Error(_moduleName + ' JavaScript requires jQuery')
    }

    function Api() {
        this.info();
    };

    Api.prototype = {
        constructor: Api,
        moduleName: _moduleName,
        version: _version,
        storage: _storage,
        description: _description,
        info: function () {
            console.log(this.moduleName);
            console.log(this.version);
        },
        init: init
    };

    function init() {
        document.title='Работа станции по обработке поездов';
//        состав поезда
        $.when(trainprocessingAjax())
            .done(function(doneTrainprocessing){
                $('#info').text('Работа станции по обработке поездов');
                preparateData();
                resizeJqGridWidth();
                $(window).bind('resize', function () {
                    resizeJqGridWidth();
                });
                usogdp.hideLoader();
            })
            .fail(function(jqXHR, textStatus ){
                usogdp.hideLoader();
                window.ajaxError.printError(jqXHR, textStatus, 'Ошибка при получении состава поезда' );
            })
    }

    function trainprocessingAjax(){
        return $.ajax({
            url: _urls.linkTrainprocessing+location.search,
            dataType: "json",
            type: "GET"
        }).done(function (data) {
            _storage.trainprocessing = data;
        }).fail(function(jqXHR, textStatus ) {

        });
    }

    function preparateData(){
        _storage.countBrigade=0;
        for(var i=0; i<_storage.trainprocessing.length; i++){
            if(_storage.countBrigade<_storage.trainprocessing[i].brigade){
                _storage.countBrigade=_storage.trainprocessing[i].brigade;
            }
        }
        var dataArray={};
        for(var i=0; i<_storage.countBrigade; i++){
            $('.tbFormat').append('<div class="float brigateFormat"><table id="brigade_'+i+'"></table></div>');
            dataArray['brig'+(i+1)]=[];
        }
        for(var i=0; i<_storage.trainprocessing.length; i++){
            var obj={
                ip:_storage.trainprocessing[i].ip,
                begin:usogdp.formatDate(_storage.trainprocessing[i].begin),
                end:usogdp.formatDate(_storage.trainprocessing[i].end),
                norm:_storage.trainprocessing[i].norm,
                brigade:_storage.trainprocessing[i].brigade
            };
            switch (obj.brigade){
                case 1:{
                    dataArray['brig'+1][dataArray['brig'+1].length]=obj;
                }break;
                case 2:{
                    dataArray['brig'+2][dataArray['brig'+2].length]=obj;
                }break;
            }
        }

        for(var i=0; i<_storage.countBrigade; i++){
            viewTableGrid(i, dataArray['brig'+(i+1)])
        }
    }

    function viewTableGrid(id, data){
        jQuery("#brigade_"+id).jqGrid({
            datatype: "local",
            data:data,
            rowNum: data.length,
            height: 'auto',
            sortname:'begin',
            sortorder:'asc',
            sortable:true,
            colNames:['Индекс','Начало', 'Конец', 'Норма, мин' ,'Бригада'],
            colModel:[
                {
                    name:'ip',
                    index:'ip',
                    width:95,
                    sortable:true,
                    sorttype:'text',
                    align:'center',
                    classes: 'text',
                    title:false,
                    cellattr: function(rowId, val, rawObject) {
                        var cssoptions=''
                        if (val.split(' ')[1] == '000') {
                            cssoptions = " class='color text'";
                            return cssoptions;
                        }else{
                            return 'class="text"';
                        }
                    }
                },
                {name:'begin',index:'begin',sorttype:'text', sortable:usogdp.castomSertCell, width:80, align:'center',classes: 'text', title:false},
                {name:'end',index:'end', width:80, sortable:true, sorttype:usogdp.castomSertCell, align:'center',classes: 'text',title:false},
                {name:'norm',index:'norm', width:55, sortable:true, sorttype:'number',align:'center',classes: 'text', title:false},
                {name:'brigade',index:'brigade', sortable:false, hidden: true,title:false}
            ],

            multiselect: false,
            caption: 'Бригада '+(id+1),
            viewrecords: true,
            hidegrid:false,
            shrinkToFit:true
        });
    }

    //перерисовка таблицы
    function resizeJqGridWidth(){
        var height=[];
        console.log('перещет resizeJqGridWidth');
        $('.tbFormat').outerHeight(($('.tbFormat').parent().height()-$('header').height()-$('footer').height()-15));
        var widthDefault = 330;
//
        if(_storage.countBrigade>1){
            var newWidth = ($('.tbFormat').width()/2 - 10);
        }else{
            var newWidth = ($('.tbFormat').width() - 10);
        }

        switch (_storage.countBrigade){
            case 1:{
                var newWidth = ($('.tbFormat').width()/3 - 10-10);
            }break;
            case 2:{
                var newWidth = ($('.tbFormat').width()/3 - 10-20);
            }break;
            case 3:{
                var newWidth = ($('.tbFormat').width()/3 - 10-30);
            }break;
        }

        for(var i=0; i< _storage.countBrigade; i++){
            if(newWidth > widthDefault){
                $("#brigade_"+i).setGridWidth(widthDefault, true); //Back to original width
                $("#brigade_"+i).setGridWidth(newWidth, true); //Resized to new width as per window
            }

            jQuery("#brigade_"+i).jqGrid('destroyGroupHeader');

            jQuery("#brigade_"+i).jqGrid('setGroupHeaders', {
                useColSpanStyle: true,
                groupHeaders:[
                    {startColumnName: 'begin', numberOfColumns: 2, titleText: '<em>Обработка поезда</em>'}
                ]
            });
            height[height.length]= $("#brigade_"+i).height();
        }

        var newHeight=0;
        for(var i=0; i<height.length; i++){
            if(newHeight<height[i]){
                newHeight=height[i];
            }
        }
        for(var i=0; i< _storage.countBrigade; i++){
            $("#brigade_"+i).setGridHeight(newHeight);
        }

    }

// default name module
    window.trainprocessing = new Api();
    return trainprocessing;
}(jQuery));




