(function ($) {
    var
        _moduleName = "Train consist",
        _description = "Модуль состава поезда",
        _enabledLog = true,
        _version = "0.0.1-Snapshot",
        _storage = {},

        _urls = {
            ctx: ctx || "",
            linkTrainConsist: this.ctx + '/ajax/gettrainconsist' // состав поезда
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
        document.title='Состав '+usogdp.getUrlParameter('ip');
//        состав поезда
        $.when(trainConsistAjax())
            .done(function(doneTrainConsist){
                if(usogdp.getUrlParameter('np')!='null'){
                    $('#info').text('Состав поезда № '+usogdp.getUrlParameter('np')+'   индекс '+usogdp.getUrlParameter('ip')+'   вагонов '+_storage.trainConsist.length);
                }else{
                    $('#info').text('Состав прогнозного поезда '+'   индекс '+usogdp.getUrlParameter('ip')+'   вагонов '+_storage.trainConsist.length);
                }
                viewTableGrid();
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

    function trainConsistAjax(){
        return $.ajax({
            url: _urls.linkTrainConsist+location.search,
            dataType: "json",
            type: "GET"
        }).done(function (data) {
            _storage.trainConsist = data;
        }).fail(function(jqXHR, textStatus ) {

        });
    }

    function viewTableGrid(){
        var data=[];
        for(var i=0; i<_storage.trainConsist.length; i++){
            data[data.length]={
                pnv:_storage.trainConsist[i].pnv,
                nv:_storage.trainConsist[i].nv,
                vsgrvg:_storage.trainConsist[i].vsgrvg,
                ksobt:_storage.trainConsist[i].ksobt,

                nameSt:_storage.trainConsist[i].nameSt,
                kdgr10:_storage.trainConsist[i].kdgr10,
                kdpl:_storage.trainConsist[i].kdpl,
                oov1:_storage.trainConsist[i].oov1,

                oov2:_storage.trainConsist[i].oov2,
                oov3:_storage.trainConsist[i].oov3,
                prim:_storage.trainConsist[i].prim,
                srokd:_storage.trainConsist[i].srokd,

                ip:_storage.trainConsist[i].ip,
                rw_op_id:_storage.trainConsist[i].rw_op_id,
                ksostname:_storage.trainConsist[i].ksostname,
                datop:usogdp.formatDate(_storage.trainConsist[i].datop),

                vgDisl:_storage.trainConsist[i].vgDisl,
                toolTips:_storage.trainConsist[i].stnz.slice(0,4)+'|'+_storage.trainConsist[i].cargoName+'|'+_storage.trainConsist[i].kopdescr
            };
        }

        jQuery("#tableTrain").jqGrid({
            datatype: "local",
            rowNum: _storage.trainConsist.length,
            height: 'auto',
            sortable:true,
            data:data,
            sortname:'pnv',
            sortorder:'asc',
            colNames:['№ в нл','Вагон', 'Масса', 'КС','Станция назначения','Код груза','Код получ.', '1-я', '2-я','3-я','Прим','Срок дост.','Индекс','Операция','Станция', 'время','vgDisl', 'toolTips'],
            colModel:[
                {name:'pnv',index:'pnv',sorttype:'number', sortable:true, width:50, align:'center',classes: 'text', title:false},
                {name:'nv',index:'nv', width:60, sortable:true, sorttype:'number', align:'center',classes: 'text',title:false},
                {name:'vsgrvg',index:'vsgrvg', width:40, sortable:true, sorttype:'number', align:'center',classes: 'text',title:false},
                {name:'ksobt',index:'ksobt', width:40, sortable:true, sorttype:'number', align:'center',classes: 'text',title:false},

                {name:'nameSt',index:'nameSt', width:220, sortable:true, sorttype:'text',classes: 'text', cellattr:function(rowId, val, rawObject){return 'title="'+ usogdp.castomToolTip(rawObject.toolTips,0)+'"'}},
                {name:'kdgr10',index:'kdgr10', width:60, sortable:true, sorttype:'number', align:'center',classes: 'text', cellattr:function(rowId, val, rawObject){return 'title="'+ usogdp.castomToolTip(rawObject.toolTips,1)+'"'}},
                {name:'kdpl',index:'kdpl', width:70, sortable:true, sorttype:'number', align:'center', classes: 'text',title:false},
                {name:'oov1',index:'oov1', width:35, sortable:true, sorttype:'number', align:'center', classes: 'text',title:false},

                {name:'oov2',index:'oov2', width:35, sortable:true, sorttype:'number', align:'center',classes: 'text',title:false},
                {name:'oov3',index:'oov3', width:35, sortable:true, sorttype:'number', align:'center', classes: 'text', title:false},
                {name:'prim',index:'prim', width:50, sortable:true,sorttype:'text', align:'center', classes: 'text',title:false},
                {name:'srokd',index:'srokd', width:70, sortable:true, sorttype:'text', align:'center',classes: 'text',title:false},

                {name:'ip',index:'ip', width:100, sortable:true, sorttype:'number', align:'center',classes: 'text',title:false},
                {name:'rw_op_id',index:'rw_op_id', width:60, sortable:true, sorttype:'text', align:'center',classes: 'text',cellattr:function(rowId, val, rawObject){return 'title="'+ usogdp.castomToolTip(rawObject.toolTips,2)+'"'}},
                {name:'ksostname',index:'ksostname', width:135, sortable:true, sorttype:'text', classes: 'text',title:false},
                {name:'datop',index:'datop', width:100, sortable:true, sorttype:'text', align:'center', classes: 'text',title:false},

                {name: 'vgDisl', index: 'vgDisl', sortable:false, hidden: true, title:false},
                {name: 'toolTips', index: 'toolTips', sortable:false, hidden: true, title:false}

            ],
            rowattr:function(rd){
                if(rd.vgDisl==0){
                    return { "class": "gray" };
                }
            },
            multiselect: false,
            caption: 'Состав поезда',
            viewrecords: true,
            hidegrid:false,
            shrinkToFit:true
        });
    }

    //перерисовка таблицы
    function resizeJqGridWidth(){
        console.log('перещет resizeJqGridWidth');
        $('.tbFormat').outerHeight(($('.tbFormat').parent().height()-$('header').height()-$('footer').height()-5));
        var widthDefault = 1240;
        $('#tableTrain').setGridWidth(widthDefault, true); //Back to original width

        var newWidth = ($('.tbFormat').width() - 20);
        if (newWidth > widthDefault) {
            $('#tableTrain').setGridWidth(newWidth, true); //Resized to new width as per window
        }

        jQuery("#tableTrain").jqGrid('destroyGroupHeader');

        jQuery("#tableTrain").jqGrid('setGroupHeaders', {
            useColSpanStyle: true,
            groupHeaders:[
                {startColumnName: 'oov1', numberOfColumns: 3, titleText: '<em>Особые отметки</em>'},
                {startColumnName: 'ip', numberOfColumns: 4, titleText: '<em>Последняя операция с вагоном</em>'}
            ]
        });
    }

// default name module
    window.train = new Api();
    return train;
}(jQuery));




