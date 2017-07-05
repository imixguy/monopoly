(function ($) {
    var
        _moduleName = "Train consist",
        _version = "0.0.1-Snapshot",
        _description = "Модуль расписания поезда",
        _enabledLog = true,
        _storage = {},

        _urls = {
            ctx: ctx || "",
            linkTrainSchedule: this.ctx + '/ajax/gettrainschedule' // состав поезда
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
        description:_description,
        version: _version,
        storage: _storage,
        info: function () {
            console.log(this.moduleName);
            console.log(this.version);
        },
        init: init
    };

    function init() {
        document.title='Расписание '+usogdp.getUrlParameter('ip');
//        состав поезда
        $.when(trainScheduleAjax())
            .done(function(doneTrainShedule){
                if(usogdp.getUrlParameter('np')!='null'){
                    $('#info').text('Расписание поезда № '+usogdp.getUrlParameter('np')+'   индекс '+usogdp.getUrlParameter('ip'));
                }else{
                    $('#info').text('Расписание прогнозного поезда '+'   индекс '+usogdp.getUrlParameter('ip'));
                }
                viewTableGrid();
                resizeJqGridWidth();
                $(window).bind('resize', function () {
                    resizeJqGridWidth();
                })
                usogdp.hideLoader();


            })
            .fail(function(jqXHR, textStatus){
                usogdp.hideLoader();
                window.ajaxError.printError(jqXHR, textStatus, 'Ошибка при получении расписания поезда' );
            })

    }



    function trainScheduleAjax(){
        return $.ajax({
            url: _urls.linkTrainSchedule+location.search,
            dataType: "json",
            type: "GET"
        }).done(function (data) {
            _storage.trainSchedule = data;
        }).fail(function() {

        });
    }

    function viewTableGrid(){
        var data=[];
        for(var i=0; i<_storage.trainSchedule.length; i++){
            data[data.length]={
                kso:_storage.trainSchedule[i].kso,
                staName:_storage.trainSchedule[i].staName,
                thread:_storage.trainSchedule[i].thread,
                kopMnemo:_storage.trainSchedule[i].kopMnemo,
                datop:usogdp.formatDate(_storage.trainSchedule[i].datop),
                id_pr:_storage.trainSchedule[i].id_pr,
                toolTips:_storage.trainSchedule[i].kopDescr+'|'
            }
        }
        jQuery("#schedule").jqGrid({
            datatype: "local",
            rowNum: _storage.trainSchedule.length,
            height: 'auto',
            data:data,
            multiselect: false,
            caption: 'Состав поезда',
            viewrecords: true,
            hidegrid:false,
            shrinkToFit:true,
            colNames:['ECP','Название', 'Нитка НГДП', 'Мнемокод','Время','id_pr', 'toolTips'],
            colModel:[
                {name:'kso',index:'kso', width:200,sortable:false, align:'center',sorttype:'text', classes: 'text', title:false},
                {name:'staName',index:'staName', width:200, sortable:false, sorttype:'text', classes: 'text', title:false},
                {name:'thread',index:'thread', width:120, sortable:false, align:'center',sorttype:'text', classes: 'text', title:false},

                {name:'kopMnemo',index:'kopMnemo', width:200, sortable:false, align:'center',sorttype:'text', classes: 'text',cellattr:function(rowId, val, rawObject){return 'title="'+ usogdp.castomToolTip(rawObject.toolTips,0)+'"'}},
                {name:'datop',index:'datop', width:200, sortable:false, align:'center',sorttype:'text', classes: 'text',title:false},
                {name: 'id_pr', index: 'id_pr', sortable:false, hidden: true, title:false},
                {name: 'toolTips', index: 'toolTips', sortable:false, hidden: true, title:false}

            ],
            rowattr:function(rd){
                if(Number(usogdp.getUrlParameter('forecastId'))==rd.id_pr){
                    return { "class": "color" };
                }
            }
        });
    }

    //перерисовка таблицы
    function resizeJqGridWidth(){
        $('.tbFormat').outerHeight(($('.tbFormat').parent().height()-$('header').height()-$('footer').height()-5));
        var widthDefault = 945;
        $('#schedule').setGridWidth(widthDefault, true); //Back to original width

        var newWidth = ($('.tbFormat').width() - 30);
        if (newWidth > widthDefault) {
            $('#schedule').setGridWidth(newWidth - 2, true); // Resized to new width as per winschedule}
        }

        jQuery("#schedule").jqGrid('destroyGroupHeader');

        jQuery("#schedule").jqGrid('setGroupHeaders', {
            useColSpanStyle: true,
            groupHeaders:[
                {startColumnName: 'kso', numberOfColumns: 2, titleText: '<em>Станция</em>'},
                {startColumnName: 'kopMnemo', numberOfColumns: 2, titleText: '<em>Операция</em>'}
            ]
        });
    }



// default name module
    window.schedule = new Api();
    return schedule;
}(jQuery));




