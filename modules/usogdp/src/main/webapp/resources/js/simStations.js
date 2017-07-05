(function ($) {
    var
        _moduleName = "simStations.js",
        _description = "Модуль для вывода станций моделирования",
        _version = "0.0.1-Snapshot",
        _storage = {},
        context = ctx || "",// default context is determined dynamically from inHeader.jsp
        prefixGraphController = "/ajax/",

        _urls = {
            listDirections: context + prefixGraphController + "directions", // урл для получение списка направления по станциям
            listTrains: context + prefixGraphController + "trains", // урл для получение списка поездов
            linkTrainConsist: context  + '/trainconsist?', // состав поезда
            linkTrainSchedule: context + '/trainschedule?' // расписание
        };

    //---------- проверка наличия jquery переопределение logging -----
    if (typeof jQuery === 'undefined') {
        throw new Error(_moduleName + ' JavaScript requires jQuery')
    }

    function Api() {
        this.info();
    };

    //-------- конструктор объекта simstations -----
    Api.prototype = {
        constructor: Api,
        moduleName: _moduleName,
        version: _version,
        description:_description,
        storage: _storage,
        info: function () {
            console.log(this.moduleName);
            console.log(this.version);
        },
        init: init
    };

    //-------- функция запуска ajax запросов на получение данных с сервера для отрисовки в таблицах -----
    function init() {
        $('.tbFormat').bind('contextmenu', function(e){
            return usogdp.contextmenu(e, '.tbFormat');
        })
        usogdp.closeContextMenu();
        $.when(usogdp.ajaxSimStations(), usogdp.ajaxLastForecast(), usogdp.ajaxForecasts(), usogdp.ajaxStationsNsi())
            .done(function(doneSimSt, doneLforecast, doneForecasts, doneStationsNsi){
                log(_moduleName + " - ajaxSimStations(), ajaxLastForecast(), ajaxForecasts() :done");
                _storage.selectedSimstation=doneSimSt[0][0];
                document.title=_storage.selectedSimstation.stationNsi.name;
                _storage.simstations=doneSimSt[0];
                _storage.forecasts=doneForecasts[0];
                _storage.lastforecast=doneLforecast[0];
                _storage.stationsNsi=doneStationsNsi[0];
                if($('#selectForecast').text()==''){
                    _storage.selectedForecast=doneLforecast[0];
                }else{
                    for(var i =0; i<_storage.forecasts.length; i++){
                        if(Number($('#selectForecast').text())==_storage.forecasts[i].id){
                            _storage.selectedForecast=_storage.forecasts[i];
                            _storage.lastforecast=_storage.forecasts[i];
                        }
                    }
                    if(_storage.selectedForecast==undefined || _storage.selectedForecast==null){
                        _storage.selectedForecast=doneLforecast[0];
                    }
                }

                viewSimstations();

                $.when(ajaxDirections(_storage.selectedSimstation.stationNsi.esr), ajaxTrains(_storage.selectedSimstation.stationNsi.esr, _storage.selectedForecast.id))
                    .done(function(doneDirections, doneTrains){
                        log(_moduleName + " - ajaxDirections(), ajaxTrains() :done");
                        viewTables();
                        usogdp.hideLoader();

                        _storage.intervalID = window.setInterval(function(){
                            $.when(usogdp.ajaxLastForecast())
                                .done(function(doneLastForecast){
                                    if(_storage.lastforecast.startDate<doneLastForecast.startDate){
                                        _storage.lastforecast=doneLastForecast;
                                        $('#newData').show();
                                    }
                                })
                                .fail(function(jqXHR, textStatus){
                                    window.ajaxError.printError(jqXHR, textStatus, 'ошибка при попытке обновления запроса');
                                })
                        }, 60000);

                    })
                    .fail(function(jqXHR, textStatus){
                       if(window.ajaxError){
                           usogdp.hideLoader();
                           window.ajaxError.printError(jqXHR, textStatus, 'ошибка при получении списка направлений и списка поездов');
                       }
                    });

            })
            .fail(function(jqXHR, textStatus){
                if(window.ajaxError){
                    usogdp.hideLoader();
                    window.ajaxError.printError(jqXHR, textStatus, 'ошибка при получении данных с модуля usogdp');
                }
            });
    }


    //------- запрос направлений для выбранной станции ------
    function ajaxDirections(esr) {
        return $.ajax({
            url: _urls.listDirections,
            dataType: "json",
            type: "GET",
            data: {esr: esr}
        }).done(function (data) {
            _storage.directions = data;
            log(_moduleName + " ajaxDirections.count:" + data.length);
        }).fail(function() {

        });
    }

    //-------- запрос поездов для выбранной станции ---------
    function ajaxTrains(esr, forecastId) {
        return $.ajax({
            url: _urls.listTrains,
            dataType: "json",
            type: "GET",
            data: {
                esr: esr,
                forecastId: forecastId
            }
        }).done(function (data) {
            _storage.trains = data;
            log(_moduleName + " ajaxTrains.count:" + data.length);
        }).fail(function() {

        });
    }

    //------------- отрисовка select со станциями -------
    function viewSimstations() {
        log(_moduleName + " viewSimstations(): begin");
        $('#commonTable').empty();
        $('#staMod').empty();
        $('#forecast').text('Обновление данных на   ' +usogdp.formatDate(_storage.selectedForecast.startDate, 'forecast'));
        $('#forecast').attr('forecastId', _storage.selectedForecast.id);
        for (var i = 0; i < _storage.simstations.length; i++) {
            $('#staMod').append('<option style=" ui-selectmenu-button " value="' + i + '">' + _storage.simstations[i].stationNsi.name + '</option>');
        }
        $('#brigada').attr('href', context+'/trainprocessing?esr='+_storage.simstations[0].stationNsi.esr+'&forecastId='+_storage.selectedForecast.id);
        //подпись <select> на событие
        $('#staMod').change(function () {
            log(_moduleName + " функция события выбора станции: begin");
            //показать элемент загрузки данных
            usogdp.showLoader();
            $('#commonTable').empty();
            $('#but1').prop('disabled', true);
            $('#but2').prop('disabled', true);
            $('#brigada').attr('href', context+'/trainprocessing?esr='+_storage.simstations[$(this).val()].stationNsi.esr+'&forecastId='+_storage.selectedForecast.id);
            $('.buttonContextMenu').prop('disabled', true);
            _storage.selectedSimstation = _storage.simstations[$(this).val()];
            document.title=_storage.selectedSimstation.stationNsi.name;
            $.when(ajaxDirections(_storage.selectedSimstation.stationNsi.esr), ajaxTrains(_storage.selectedSimstation.stationNsi.esr, _storage.selectedForecast.id))
                .done(function(doneDirections, doneTrains){
                    log(_moduleName + " - ajaxDirections(), ajaxTrains() :done");
                    viewTables();
                    usogdp.hideLoader();
                })
                .fail(function(jqXHR, textStatus){
                    if(window.ajaxError){
                        window.ajaxError.printError(jqXHR, textStatus, 'ошибка получения данных при выборе станции');
                    }
                });
            log(_moduleName + " функция события выбора станции: end");
        });

        $('#newData').click(reloadData);

        log(_moduleName + " viewSimstations(): end");
    }

    //------- шаблон форматирования данных ------
    function formatData(arg1, arg2){
        var result;
        if (arg1 && arg2) {
            if (arg1 == arg2) {
                result = arg1;
            } else {
                result = arg1 + '/' + arg2;
            }
        } else {
            if (arg1) {
                result = arg1;
            } else {
                result = arg2;
            }
        }
        return result;
    }

    //-------- отрисовать таблицы в браузере -------
    function viewTables(){
        log(_moduleName + " viewTables(): begin");
        var i = 0, j = 0, k = 0,
            data1, data2, styk, data3=[];
        for (i = 0; i < (_storage.directions.length * 2); i += 2) {
            data1 = [];
            data2 = [];
            styk = _storage.directions[j].styk;
            $('#commonTable').append('<tr><td><div id="cont_' + i + '" style="width:100%"><table style="width:100%" id="table_' + i + '"></table></div></td><td><div id="cont_' + (i + 1) + '" style="width:100%"><table style="width:100%" id="table_' + (i + 1) + '"></table></div></td></tr>')
            for (k = 0; k < _storage.trains.length; k++) {
                if (styk == _storage.trains[k].iz) {
                    addTableModel(data1, _storage.trains[k]);
                }
                if (styk == _storage.trains[k].na) {
                    addTableModel(data2, _storage.trains[k]);
                }
            }
            viewTableGrid(i, data1, _storage.directions[j].name1);
            viewTableGrid(i + 1, data2, _storage.directions[j].name2);
            j++;
        }
//        передаточные поезда
        $('#commonTable').append('<tr><td><div id="cont_' + i + '" style="width:100%"><table style="width:100%" id="table_' + i + '"></table></div></td><td><div id="cont_' + (i + 1) + '" style="width:100%"></div></td></tr>')
        for(k = 0; k < _storage.trains.length; k++){
            if ( _storage.trains[k].na==null && _storage.trains[k].iz==null) {
                addTableModel(data3, _storage.trains[k]);
            }
        }
        viewTableGrid(i, data3, 'Передаточные поезда');
//        -------------------
        resizeJqGridWidth();
        $(window).bind('resize', function () {
            resizeJqGridWidth();
        })
        log(_moduleName + " viewTables(): end");
    }

    //-------- заполнить таблицы данными ------
    function viewTableGrid(id, data, direction){
        log(_moduleName + " viewTableGrid(): begin | id = "+id);
        var table = jQuery("#table_" + id).jqGrid({
            height: 250,
            data:data,
            sortname:'otpr',
            sortorder:'asc',
            sortable:true,
            rowNum: data.length,
            datatype: "local",
            multiselect: false,
            scroll:true,
            scrollOffset:18,
            caption: '<div style="text-align: center">'+direction+'</div>',
            viewrecords: true,
            hidegrid: false,
            shrinkToFit: true,
            colNames: ['Поезд', 'Индекс', 'Прибытие', 'Готовность', 'Отправление', 'Ваг', 'Длина', 'Вес', 'Ссылка на состав', 'Ссылка на расп','tr_id', 'tooltips'],
            colModel: [
                {name: 'np', index: 'np', width: 60,sorttype:'number', sortable:true, align: 'center',title:false,classes: 'text'},

                {name: 'ip',
                 index: 'ip',
                 width: 95,
                sorttype:'text',
                sortable:true,
                 align: 'center',
                     cellattr: function(rowId, val, rawObject) {
                         var esrNsi=rawObject.toolTips.split(' ');
                         var sfp='', snp='';
                         for(var j=0; j<_storage.stationsNsi.length; j++){
                             if(esrNsi[0]==_storage.stationsNsi[j].esr){
                                 sfp=_storage.stationsNsi[j].name;
                             }
                             if(esrNsi[1]==_storage.stationsNsi[j].esr){
                                 snp=_storage.stationsNsi[j].name;
                             }
                         }
                         var tooltip='title="'+sfp+' - '+snp+'"'
                         var cssoptions=''
                         if (val.split(' ')[1] == '000') {
                             cssoptions = " class='color text'";
                             return cssoptions+' '+tooltip;
                         }
                         return 'class="text" '+tooltip;
                     }
                 },

                {name: 'prib', index: 'prib', width: 80,sorttype:usogdp.castomSertCell, sortable:true, align: 'center', classes: 'color text', title:false},
                {name: 'got', index: 'got', width: 80,sorttype:usogdp.castomSertCell, sortable:true,  align: 'center', classes: 'color text', title:false},
                {name: 'otpr', index: 'otpr', width: 80, sorttype:usogdp.castomSertCell,sortable:true, align: 'center', classes: 'color text', title:false},
                {name: 'vag', index: 'vag', width: 60,sorttype:'text', sortable:true,  align: 'center', title:false, classes: 'text'},
                {name: 'dlina', index: 'dlina', width: 60,sorttype:'text', sortable:true,  align: 'center', title:false, classes: 'text'},
                {name: 'ves', index: 'ves', width: 70,sorttype:'text', sortable:true,  align: 'center', title:false, classes: 'text'},
                {name: 'urlConsist', index: 'urlConsist',  hidden: true, title:false, classes: 'text'},
                {name: 'urlSchedule', index: 'urlSchedule',  hidden: true, title:false, classes: 'text'},
                {name: 'tr_id', index: 'tr_id',  hidden: true, title:false},
                {name: 'toolTips', index: 'toolTips',  hidden: true, title:false}
            ],
            onSelectRow: function (rowid, status) {
                if (status) {
                    var id = $(this).getCell(rowid, 10);
                    var tableId = this.id;
                    var urlSost = $(this).getCell(rowid, 8);
                    var urlRasp = $(this).getCell(rowid, 9);
                    $('#sostav').attr('href', _urls.linkTrainConsist + urlSost);
                    $('#menuLinkSost').attr('href', _urls.linkTrainConsist + urlSost);
                    $('#rasp').attr('href', _urls.linkTrainSchedule + urlRasp);
                    $('#menuLinkRasp').attr('href', _urls.linkTrainSchedule + urlRasp);

                    $('#but1').prop('disabled', false);
                    $('#but2').prop('disabled', false);
                    $('.buttonContextMenu').prop('disabled', false)
                    for (i = 0; i < (_storage.directions.length * 2+1); i++) {
                        if (tableId == ('table_' + i)) continue; //если это таже таблица, в которой пользователь выбрал поезд, то незачем ходить по строкам и еще раз выделять строку
                        jQuery("#table_" + i).resetSelection();
                        var colModel = jQuery("#table_" + i).getRowData();
                        var colModelId = jQuery("#table_" + i).jqGrid('getDataIDs');
                        for (j = 0; j < colModel.length; j++) {
                            if (colModel[j].tr_id == id) {
                                jQuery("#table_" + i).jqGrid('setSelection', colModelId[j], false);
                            }
                        }
                    }
                }
            },
            //todo дописать!
            beforeSelectRow: function () {
                return true;
            }
        });
        log(_moduleName + " viewTableGrid(): end");
    }

    //---------- создание объекта поезда для представления в jqgrid и добавление его в массив --------
    function addTableModel(data, train){
        var consist = 'esr=' + _storage.selectedSimstation.stationNsi.esr + '&trId=' + train.trId + '&idTrainHistVag=' + train.idTrainHistVag + '&forecastId=' + _storage.selectedForecast.id +'&ip='+train.ip+'&np='+train.np;
        var schedule = 'trId=' + train.trId + '&forecastId=' + _storage.selectedForecast.id +'&ip='+train.ip+'&np='+train.np;
        data[data.length] = {
            np: train.np,
            ip: train.ip,
            prib: usogdp.formatDate(train.prib),
            got: usogdp.formatDate(train.got),
            otpr: usogdp.formatDate(train.otpr),
            vag: formatData(train.klvpPrib, train.klvpOtpr),
            dlina: formatData(train.dlvsPrib, train.dlvsOtpr),
            ves: formatData(train.qbrPrib, train.qbrOtpr),
            urlConsist: consist,
            urlSchedule: schedule,
            tr_id:train.trId,
            toolTips:train.sfp+' ' + train.snp
        }
    }

    //-------- изменение ширины таблиц ------------
    function resizeJqGridWidth() {
        $('.tbFormat').outerHeight(($('.tbFormat').parent().height()-$('header').height()-$('footer').height()-38-$('#information').height()));
        $('#commonTable').height($('#cont_0').height()*_storage.directions.length);
        var widthDefault = 625;
        for (var i = 0; i < (_storage.directions.length * 2+1); i++) {
            $('#table_' + i).setGridWidth(widthDefault, true); //Back to original width
        }
        var newWidth = ($('#commonTable').width() -6) / 2;
        if (newWidth > widthDefault) {
            for (var i = 0; i < _storage.directions.length * 2; i++) {
                $('#table_' + i).setGridWidth(newWidth - 2, true); //Resized to new width as per window
            }
        }
    }

    function reloadData(){
        usogdp.showLoader();
        $.when(usogdp.ajaxLastForecast(), usogdp.ajaxForecasts())
            .done(function(doneLforecast, doneForecasts){
                log(_moduleName + " - ajaxSimStations(), ajaxLastForecast(), ajaxForecasts() :done");
                _storage.selectedForecast=doneLforecast[0];
                _storage.forecasts=doneForecasts[0];
                _storage.lastforecast=doneLforecast[0];
                $('#forecast').text('Обновление данных на   ' +usogdp.formatDate(_storage.selectedForecast.startDate, 'forecast'));
                $('#forecast').attr('forecastId', _storage.selectedForecast.id);
                $('#staMod').val($('#staMod').val()).change();
                $('#newData').hide();
            })
            .fail(function(jqXHR, textStatus){
                if(window.ajaxError){
                    window.ajaxError.printError(jqXHR, textStatus, 'ошибка при обновлении данных');
                }
            })
    }

    window.simstations = new Api();
    return simstations;
}(jQuery));