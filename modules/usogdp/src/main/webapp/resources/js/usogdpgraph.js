(function ($, usogdp) {
    //      Заясь?
    //      (\_/)
    //     (='.'=)
    //     (")_(")
    var
        _moduleName = "usogdpgraph.js",
        _version = "0.0.3",
        _storage = {stadistrict: undefined},
        context = (ctx || "") + "/",// default test from inHeader.jsp
        prefixGraphController = "ajax/",
        _urls = {
            listStaMod: context + prefixGraphController + "simstations", // урл для получение списка станций моделирования
            districts: context + prefixGraphController + "districts", // урл для получение списка станций моделирования
            lAstForecast: context + prefixGraphController + "lastforecast", // урл для получения последнего прогноза
            stadistrict: context + "graph/" + "stadistrict", // получения соединенно списка станций для массива ид участков
            mapdistrict: context + "graph/" + "mapdistrict", // получения карты ключ - ид участка, значение - список станций участка
            operations: context + "graph/" + "operations", // получения всех операций для конкретного прогноза
            graph: context + "graph/do", // урл для рисования
            linkTrainSchedule: context + 'trainschedule?', // расписание
            linkTrainConsist: context + 'trainconsist?' // состав поезда
        },
        links={schedule:""},
        txtconst ={labelip:"ИП:"},
    // для графика
        usedforecast,
        mapAllTrIds = {},
        listTrIds = [], // список поездов только для данного участка stadistrict
        showenTrIds = [],
        msInHour = 60 * 60 * 1000,
        graf = {
            hoursBeforeStart:1, // показать часов перед точкой расчета
            startDate: undefined, //дата расчета прогноза
            pixelsInHours: 60, // ось х
            pixelBetwenSta: 25, // ось y
            offsetX: 60, // смещение оси х
            offsetY: 32, // смещение оси у
            addexY: 20, // дополнительное место по оси у
            countHours: 25, // количество отображаемых часов на графике
            width: undefined, // расчетные ширина и высота графика
            height: undefined
        },
        axisX = {
            dateMin: undefined,
            dateMax: undefined,
            minline: {point1: undefined, point2: undefined},
            maxline: {point1: undefined, point2: undefined},
            //point1: {},
            //point2: {},
            //pixelsInHours: pixelsInHours,
            //countHours: countHours,
            max: 0
        },
        axisY = {
            coordinates: [],
            lastEsrCoordinate: 0
        },
        cssclass = {
            axisx: "axisx", // вертикальные линии оси х
            minutes: "minutes",// линии минут
            middlehour: "middlehour",// линия серидины часа
            datestamp: "datestamp", // текс даты
            timestamp: "timestamp", // текст метки часа
            axisy: "axisy", // горизонтальные линии станций
            axisystamod: "axisy-st", // горизонтальные линии станций моделирования
            axisystyk: "axisy-styk", // горизонтальные линии стыковочных станций
            forecast: "forecast", // группа прогнозного поезда
            real: "real",  // группа реального поезда
            metka: "metka",  // метка времени поезда при пересечении с линией станции
            seltrain: "seltrain", // задается группе поезда , при выборе поезда (клики на любом объекте группы)
            forselect: "forselect", // дублирующие линии для выделения поезда
            np: "numbertrain", // номер поезда
            ip: "iptrain" // номер поезда
        }
        ;

    if (typeof jQuery === 'undefined') {
        throw new Error(_moduleName + ' JavaScript requires jQuery')
    }
    if (typeof usogdp === 'undefined') {
        throw new Error(_moduleName + ' JavaScript requires usogdp.js')
    }
    usogdp.disableLog();

    if (SVGElement && SVGElement.prototype) {

        SVGElement.prototype.hasClass = function (className) {
            return new RegExp('(\\s|^)' + className + '(\\s|$)').test(this.getAttribute('class'));
        };

        SVGElement.prototype.addClass = function (className) {
            if (!this.hasClass(className)) {
                this.setAttribute('class', this.getAttribute('class') + ' ' + className);
            }
        };

        SVGElement.prototype.removeClass = function (className) {
            var removedClass = this.getAttribute('class').replace(new RegExp('(\\s|^)' + className + '(\\s|$)', 'g'), '$2');
            if (this.hasClass(className)) {
                this.setAttribute('class', removedClass);
            }
        };

        SVGElement.prototype.toggleClass = function (className) {
            if (this.hasClass(className)) {
                this.removeClass(className);
            } else {
                this.addClass(className);
            }
        };

    }

    function Api() {
    };

    Api.prototype = {
        constructor: Api,
        moduleName: _moduleName,
        version: _version,
        storage: _storage,
        links:links,
        districts: [],  // массив для хранения участков
        info: function () {
            log("module name:%s",this.moduleName);
            log("module ver:%s",this.version);
        },
        showDistricts: showDistricts,
        showStaDistricts: showStaDistricts,
        showgraf: showgraf,
        showStaMod: showStaMod,

        openGraph: openGraph,

        ajaxDistricts: ajaxDistricts,
        ajaxStaDistricts: ajaxStaDistricts,
        //ajaxMapDistricts: ajaxMapDistricts,
        ajaxTrainOperations: ajaxTrainOperations


        //staConsistInDistrict: staConsistInDistrict,

        //getYforEsr: getYbyEsr

    };


    function ajaxDistricts(callback) {
        log('вызов ajaxDistricts');
        return $.ajax({
            url: _urls.districts,
            dataType: 'json',
            type: "GET"
        }).done(function (data) {
                _storage.districts = data;
                log(_moduleName + " districts.count:" + data.length);
                if (callback) callback(data);
            }
        );
    }

    function ajaxTrainOperations(forecastId, callback) {
        return $.ajax({
            url: _urls.operations,
            dataType: 'json',
            type: "GET",
            data: {forecastId: forecastId}
        }).done(function (data) {
                _storage.operations = data;
                if (callback) callback(data);
            }
        );
    }

    function openGraph(datas, callback) {
        var d = {};
        d.districts = datas;
        d.forecastId = usogdp.storage.lastforecast.id;

        var newTabUrl = _urls.graph + '?' + decodeURIComponent($.param(d));
        window.open(newTabUrl, '_blank');
        window.focus();
    }

    function ajaxStaDistricts(districts, callback) {
        return $.ajax({
            url: _urls.stadistrict,
            dataType: 'json',
            type: "GET",
            data: {districts: districts}
        }).done(function (data) {
                _storage.stadistrict = data;
                if (callback) callback(data);
            }
        );
    }

    //function ajaxMapDistricts(districts, callback) {
    //    return $.ajax({
    //        url: _urls.mapdistrict,
    //        dataType: 'json',
    //        type: "GET",
    //        data: {districts: districts}
    //    }).done(function (data) {
    //            _storage.mapdistrict = data;
    //            if (callback) callback(data);
    //        }
    //    );
    //}

    function commonDistrict(esr1, esr2) {// функция определение общего участка для двух станций
        var s1, s2;
        if (esr1 == esr2) return {code:-2, msg:"ЕСР равны, участок не возможно определить"}; // че вы от меня вообще хотите?
        for (var i = 0; i < _storage.stadistrict.length; i++) {
            s1 = _storage.stadistrict[i];
            if (s1.esr == esr1) {
                for (var j = 0; j < _storage.stadistrict.length; j++) {
                    s2 = _storage.stadistrict[j];
                    if (s1.distr == s2.distr && s2.esr == esr2) {
                        return {code:s1.distr, msg:"ok - общий участок для станций найден"};
                    }
                }
            }
        }
        return {code:-1, msg:"станции не приналежат ни одному обрабатываемому участку"};
    }

    function staConsistInDistrict(esr) {
        for (var i = 0; i < _storage.stadistrict.length; i++) {
            if (esr == _storage.stadistrict[i].esr) {
                return true;
            }
        }
        return false;
    }

    function showStaMod(tagId, datas) {
        $(tagId).jqGrid({
            data: datas,
            rowNum: 200,
            datatype: "local",
            rownumbers: true,
            hidegrid: false,
            height: 'auto',
            //weidt:800,
            //rowNum: 9990,
            //rowList: [100,200,300],
            colNames: ['esr', 'Name'],
            colModel: [
                {name: 'stationNsi.esr', index: 'stationNsi.esr', width: 120, align: "center", sorttype: "string"},
                {name: 'stationNsi.name', index: 'stationNsi.name', width: 250, align: "left", sorttype: "string"}
            ],
            //pager: "#pager13",
            viewrecords: true,
            caption: "Станции моделирования"
        });
    }

    function showDistricts(tagId, datas) {
        $(tagId).jqGrid({
            data: datas,
            rowNum: 200,
            datatype: "local",
            rownumbers: true,
            hidegrid: false,
            multiselect: true,
            multiselectWidth: 55,
            height: 'auto',
            //weidt:800,
            //rowNum: 9990,
            rowList: [100, 200, 300],
            colNames: ['id', 'sta_no', 'sta_no1', 'name'],
            colModel: [
                //{  width: 80, align: "center",formatter: "checkbox", formatoptions: { disabled: false}},
                {name: 'id', width: 40, align: "center", hidden: true},
                {name: 'sta_no', width: 70, align: "center"},
                {name: 'sta_no1', width: 70, align: "center"},
                {name: 'name', width: 200, align: "left"}
            ],
            //pager: "#pager13",
            //viewrecords: true,
            caption: "Станции моделирования"
        });
        $(tagId).jqGrid("setLabel", "cb", "Выбрать");
    }

    function showStaDistricts(tagId, datas) {
        $(tagId).jqGrid({
            data: datas,
            rowNum: 200,
            datatype: "local",
            rownumbers: true,
            hidegrid: false,
            height: 'auto',
            //weidt:800,
            //rowNum: 9990,
            //rowList: [100,200,300],
            colNames: ['esr', 'name', 'distr', 'st_pr'],
            colModel: [
                {name: 'esr', width: 50, align: "center", sortable: false},
                {name: 'name', width: 185, align: "left", sortable: false},
                {name: 'distr', width: 50, align: "center", sortable: false, hidden: false},
                {name: 'st_pr', width: 50, align: "center", sortable: false, hidden: false}
            ],
            //pager: "#pager13",
            viewrecords: true,
            caption: "Станции обобщенного участка",
            loadComplete: function () {
                var grid = $(tagId),
                    ids = grid.getDataIDs();
                for (var i = 0; i < ids.length; i++) {
                    grid.setRowData(ids[i], false, {height: graf.pixelBetwenSta});
                }
            }
        });
    }

    function dateFormatterOne(cellvalue, options, rowObject) {
        if (!cellvalue) return "";
        log("csdcsd");
        s = formatDate(cellvalue);
        return s;
    }


    function groupOnTrId(trainOperations) {
        //Группировка строк с базы по поездам (trId) c дальнейшей фильтрацией - остаются только поезда для данного участка
        // На выходе карта mapAllTrIds и лист ключей listTrIds[]

        // {trId, [{esr, datop, kop, ip, np, idtrainhistvag}]}
        var processed,
            train,
            trId,
            index = 0;
        //Группируем по TrId
        for (var i = 0; i < trainOperations.length; i++) {
            trId = trainOperations[i].trId.trim();
            train = mapAllTrIds[trId + ""];
            if (!train) {
                train = mapAllTrIds[trId + ""] = {trId: trId, events: []};
                listTrIds[listTrIds.length] = trId;
                for (var j = i; j < trainOperations.length; j++) {
                    processed = trainOperations[j];
                    if (train.trId === processed.trId.trim()) {
                        index = train.events.length;
                        processed.trId = processed.trId.trim();
                        train.events[index] = processed;
                    }
                }
            }
        }
        log("Всего поездов:%c%s", "color:red", listTrIds.length);
        // filtering trIds for this district
        var retainTrain = false, esr;
        i = 0;
        while (i < listTrIds.length) {  // фильтруем поезда
            trId = listTrIds[i];
            train = mapAllTrIds[trId + ""];
            retainTrain = false;
            for (var j = 0; j < train.events.length; j++) {
                esr = train.events[j].esr;
                if (train.events.length == 1 && train.events.length == 0) {
                    log("у поезда событий не достаточно для показа:"+trId);
                    break;
                }
                if (staConsistInDistrict(esr)) {
                    retainTrain = true;
                    break;
                }
            }
            if (retainTrain) {
                i++;
            } else {
                listTrIds.splice(i, 1);
            }
        }
        log("Необходимо обработать,смежных для данного участка, %c%s поезд(ов):", "color:red", listTrIds.length);
    }

    function initGraph(paper) {
        var startDateMS = usedforecast.startDate;
        graf.startDate = startDateMS;
        startDateMS += 5 * 60 * 1000; // добавляем 5 минут для точности часа -> 10:55 - тоже 11
        var date, dif, date2, x, timeOffset;
        date = new Date(startDateMS);
        date.setMinutes(0); // обнуляем все после часа
        date.setSeconds(0);
        date.setMilliseconds(0);
        date = new Date(date.getTime() - graf.hoursBeforeStart*msInHour); // отнимаем час
        // теперь это время начала отсчета для оси х
        var dateMax = new Date(date.getTime() + graf.countHours * msInHour);

        var dif = dateMax.getTime() - date.getTime();
        var timeOffset = dif * graf.pixelsInHours / (msInHour); // msInHour
        var x = graf.offsetX + timeOffset;
        axisX.dateMin = date;
        axisX.dateMax = dateMax;
        axisX.max = x;
        //graf.width = graf.pixelsInHours * (graf.countHours - 1) + graf.addedX + graf.offsetX;
        //graf.height = _storage.stadistrict.length * graf.pixelBetwenSta + graf.addexY + graf.offsetY;
        graf.width = axisX.max;
        graf.height = _storage.stadistrict.length * graf.pixelBetwenSta + graf.addexY + graf.offsetY;
        paper.attr('width', graf.width);
        paper.attr('height', graf.height);
    }


    function drawAxisX(paper) {
        var paperAxisX = paper.group();
        var tenMin = 10 * 60 * 1000;
        var x1, x2, i, date, minutes, textHour, svgtext, first = true;
        paperAxisX.attr('id', 'axisX');
        date = new Date(axisX.dateMin);
        for (i = 0; i < graf.countHours + 1; i++) {
            x1 = getXbyDate(date.getTime());
            var line = paperAxisX.line(x1, graf.offsetY, x1, axisY.lastEsrCoordinate);
            if (first) {
                axisX.minline.point1 = {x: x1, y: graf.offsetY};
                axisX.minline.point2 = {x: x1, y: axisY.lastEsrCoordinate};
                first = false;
            }
            line.attr("class", cssclass.axisx);
            line.attr('id', 'hour' + date);
            textHour = date.getHours();

            if (textHour == 0) {
                //textHour = (date.getDay() + 1) + "/" + (date.getMonth() + 1);
                textHour = usogdp.formatDate(date.getTime(), "DD/MM");
                x2 = x1;
                svgtext = paperAxisX.text(x1, axisY.lastEsrCoordinate + 17, textHour);
                svgtext.attr("class", cssclass.datestamp);
                svgtext = paperAxisX.text(x1, graf.offsetY - 17, textHour);
                svgtext.attr("class", cssclass.datestamp);

            } else {
                svgtext = paperAxisX.text(x1, axisY.lastEsrCoordinate + 17, textHour);
                svgtext.attr("class", cssclass.timestamp);
                svgtext = paperAxisX.text(x1, graf.offsetY - 17, textHour);
                svgtext.attr("class", cssclass.timestamp);
            }
            minutes = date.getTime();
            for (var j = 0; j < 6; j++) {
                minutes = minutes + tenMin;
                x1 = getXbyDate(minutes);
                if (x1 >= graf.width) break;
                var line = paperAxisX.line(x1, graf.offsetY, x1, axisY.lastEsrCoordinate);
                if (j != 2) {
                    line.attr("class", cssclass.minutes);
                } else {
                    line.attr("class", cssclass.middlehour);
                }
            }
            date = new Date(date.getTime() + msInHour);
        }
        axisX.maxline.point1 = {x: graf.width, y: graf.offsetY};
        axisX.maxline.point2 = {x: graf.width, y: axisY.lastEsrCoordinate};

    }

    function drawStartLine(paper) {
        var lineTime = paper.group();
        lineTime.attr('id', 'axisStart');
        var x1 = getXbyDate(graf.startDate);
        lineTime.line(x1, graf.offsetY, x1, axisY.lastEsrCoordinate);

    }


    function drawAxisY(paper) {
        var paperAxisY = paper.group(); // создание слоя для оси
        var x1, y1, x2, y2, i, label;
        paperAxisY.attr('id', 'axisY'); // атрибут для слоя
        var ycor = axisY.coordinates = []; // сохраним координаты
        var numberline = 0; // номер линии
        for (i = 0; i < _storage.stadistrict.length; i++) {
            if (i > 0 && _storage.stadistrict[i - 1].esr != _storage.stadistrict[i].esr) {
                numberline++;
            }
            x1 = graf.offsetX;
            y1 = numberline * graf.pixelBetwenSta + graf.offsetY;
            x2 = graf.width;
            y2 = y1;
            label = _storage.stadistrict[i].esr + "_" + _storage.stadistrict[i].distr;
            ycor[label] = y1;
            if (i > 0 && _storage.stadistrict[i - 1].esr == _storage.stadistrict[i].esr) { // предыдущая esr равна этой - не рисуем ничего
                continue;
            }
            var line = paperAxisY.line(x1, y1, x2, y2);
            line.attr("class", cssclass.axisy);
            line.attr('id', _storage.stadistrict[i].esr);
            var text = paperAxisY.text(3, y1 + 5, _storage.stadistrict[i].esr);
            if (_storage.stadistrict[i].st_pr == 1 || _storage.stadistrict[i].st_pr == 4 || _storage.stadistrict[i].st_pr == 5) {
                //line.attr("class", cssclass.axisystamod);
                line.attr("class", cssclass.axisystamod);
                text.attr("class", cssclass.axisystamod);
            }
            if (_storage.stadistrict[i].st_pr == 3 ) {
                //line.attr("class", cssclass.axisystamod);
                line.attr("class", cssclass.axisystyk);
                text.attr("class", cssclass.axisystyk);
            }
        }
        axisY.lastEsrCoordinate = y1;
    }


    function getXbyDate(timestamp) {
        var dif = (new Date(timestamp)).getTime() - axisX.dateMin.getTime();
        var timeOffset = dif * graf.pixelsInHours / (msInHour);
        var x = 0 + graf.offsetX + timeOffset;
        return x;
    }

    function getYbyEsr(esr, distr) {
        return axisY.coordinates[esr + "_" + distr];
    }

    function isDateOnRange(timestamp) {
        if (axisX.dateMin.getTime() < timestamp && timestamp < axisX.dateMax.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    // Самый главный метод для отрисовки линий поезда и всего сотально (касающегося поезда)
    function drawThreads(paper) {
        var graphiks = paper.group();
        graphiks.attr('id', 'слой графиков');
        var trId, ip, train, threadTrain, point1, point2, tevent1, tevent2, tevent3, lastevent, esr1, esr2, esr3, addThread = false, distrEvents = 0;
        for (var i = 0; i < listTrIds.length; i++) {
            addThread = false;
            trId = listTrIds[i];
            train = mapAllTrIds[trId + ""]; // тоьлко после этой строки
            train.numberTrain = {}; // инициализация карты , где будем помечать на каком участке уже сделали пометку с номером поезда
            ip = train.events[0].ip;
            threadTrain = graphiks.group();
            threadTrain.attr('id', train.trId);
            threadTrain.attr('class', getClassByIp(ip));
            point1 = {} , point2 = {};
            for (var j = 0; j < train.events.length - 1; j++) {
                tevent1 = train.events[j];
                tevent1.showDatop = true;
                tevent2 = train.events[j + 1];
                tevent2.showDatop = true;
                esr1 = tevent1.esr;
                esr2 = tevent2.esr;
                tevent1.onRange = isDateOnRange(tevent1.datop);
                tevent2.onRange = isDateOnRange(tevent2.datop);
                if ((tevent1.onRange || tevent2.onRange) && esr1 != esr2) {
                    distrEvents = commonDistrict(esr1, esr2);
                    if (distrEvents.code <= 0 && esr1 != esr2) { //станции не принадлежат ни к одному участку
                        //log("esr1:%s, esr2:%s, msg:", esr1, esr2, distrEvents.msg);
                        continue;
                    }
                    // рисуем косую
                    if (tevent1.datop == tevent2.datop) {
                        log("!");
                    }
                    point1.x = getXbyDate(tevent1.datop);
                    point1.y = getYbyEsr(esr1, distrEvents.code);
                    point2.x = getXbyDate(tevent2.datop);
                    point2.y = getYbyEsr(esr2, distrEvents.code);
                    if (point1.x < graf.offsetX) {
                        point1 = cross(point1, point2, axisX.minline.point1, axisX.minline.point2);
                        tevent1.showDatop = false;
                    }
                    if (point2.x >= graf.width) {
                        point2 = cross(point1, point2, axisX.maxline.point1, axisX.maxline.point2);
                        tevent2.showDatop = false;
                    }
                    //lastevent = tevent2;
                    lastevent = jQuery.extend({}, tevent2);
                    //log("%cкосая x1:%d ,y1:%d, x2:%s, y2:%s ", "color:green;", point1.x, point1.y, point2.x, point2.y);
                    var line = threadTrain.line(point1.x, point1.y, point2.x, point2.y);
                    var line2 = threadTrain.line(point1.x, point1.y, point2.x, point2.y); // для выделения линии увеличение площади , через css
                    line2.attr("class", cssclass.forselect);
                    addThread = true;
                    drawDatop(threadTrain, point1, tevent1, trId);
                    if (!train.numberTrain[distrEvents.code+""]) {
                        drawNumberTrain(threadTrain, point1, point2, tevent1.np, tevent1);
                        train.numberTrain[distrEvents.code+""] = true;
                    }

                }
                if (addThread && esr1 == esr2) {
                    // горизонтальная прямая
                    // если события на одной станции, то нужно посмотреть следующее событие после tevent2
                    // чтобы рисовать горизонтальную только тогда, если есть дальнейшее движение на этом участке
                    var k = j + 2;
                    if (k >= train.events.length) continue;
                    tevent3 = train.events[k];
                    esr3 = tevent3.esr;
                    distrEvents = commonDistrict(esr2, esr3);
                    //log("%cevent esr:%s, kop:%s, datop:%s", "color:gray;", tevent1.esr, tevent1.kop, new Date(tevent1.datop));
                    if (distrEvents.code > 0 && tevent2.onRange) {
                        if (distrEvents.code <= 0) {
                            //log("esr1:%s, esr2:%s, msg:", esr1, esr2, distrEvents.msg);
                            continue;
                        }  //станции не принадлежат ни к одному участку
                        // если события 2 и 3 на разных станциях , то рисуем горизонтальную линию для собтий 1 и 2
                        point1.x = getXbyDate(tevent1.datop);
                        point1.y = getYbyEsr(esr1, distrEvents.code);
                        point2.x = getXbyDate(tevent2.datop);
                        point2.y = getYbyEsr(esr2, distrEvents.code);
                        var line = threadTrain.line(point1.x, point1.y, point2.x, point2.y);
                        var line2 = threadTrain.line(point1.x, point1.y, point2.x, point2.y);
                        line2.attr("class", cssclass.forselect);
                        drawDatop(threadTrain, point1, tevent1, trId);
                        //log("%cгоризонт x1:%d ,y1:%d, x2:%s, y2:%s ", "color:green;", point1.x, point1.y, point2.x, point2.y);
                    }
                }
            }
            if (addThread) {
                drawDatop(threadTrain, point2, lastevent, trId);
            }
            if (!addThread) {
                $("#" + trId).remove();
//                        log("%cНитка не будет отображена", "color:blue");
            } else {
                showenTrIds.push(trId);
                logTrainEvents(train);
            }
        }
        log("показаны:%s\n", showenTrIds.length, showenTrIds);
    }

    function logTrainEvents(train, color) {
        if (!color) color = "gray";
        log("%ctrain:%s", "color:" + color, train.trId);
        for (var j = 0; j < train.events.length; j++) {
            var tevent = train.events[j];
            var strDate = usogdp.formatDate(tevent.datop); // new Date(tevent.datop)
            log("%cevent esr:%s, kop:%s, datop:%s, ip:%s, np:%s", "color:gray;", tevent.esr, tevent.kop, strDate, tevent.ip, tevent.np);
        }
    }

    function setHandlers(forecast) {
        var trId, train;
        for (var i = 0; i < showenTrIds.length; i++) {
            trId = showenTrIds[i];
            train = mapAllTrIds[trId + ""];
            //var tr = {trId:train.trId, ip:train.events[0].ip, np:train.events[0].np};
            $('#' + train.trId).click(function (e) {
                var th;
                for (var i = 0; i < showenTrIds.length; i++) {
                    trId = showenTrIds[i];
                    th = document.getElementById("" + trId);
                    th.removeClass(cssclass.seltrain);
                }
                var t = mapAllTrIds[this.id + ""];
                $('#trainInfo').text(txtconst.labelip+t.events[0].ip);
                $('#trainInfo2').text("Идентификатор поезда:"+ t.trId);
                th = document.getElementById("" + this.id);
                links.schedule =_urls.linkTrainSchedule+ 'trId=' + t.trId + '&forecastId=' + forecast.id + '&ip=' + t.events[0].ip + '&np=' + t.events[0].np;
                //$('#rasp').attr('action', _urls.linkTrainSchedule + params.schedule);
                $('#bSchedule').prop('disabled', false);
                th.toggleClass(cssclass.seltrain);
                th.parentNode.appendChild(th); //выброс на передний план
            });

        }
    }

    function getIdVag(esr, trId, kop) {
        var train = mapAllTrIds[trId];
        var ev = [];
        var oper = ["P0002", "P0003", "P0102", "P0042", "P0043"];
        for (var i = 0; i < train.events.length; i++) {
            if (esr == train.events[i].esr) {
                var index = ev.length;
                ev[index] = train.events[i];
            }
        }
        var index = -1;
        for (i = 0; i < ev.length; i++) {
            index = oper.indexOf(ev[i].kop);
            if (index >= 0) {
                index = i;
                break;
            }
        }
        if (index >= 0) { // коп в списке
            if (ev[index].idtrainhistvag) {
                return ev[index].idtrainhistvag;
            }
        } else {
            for (i = 0; i < ev.length; i++) {
                if (ev[i].kop == kop) {
                    if (ev[i].idtrainhistvag) {
                        return ev[i].idtrainhistvag;
                    } else {
                        return ev[i].datop;
                    }
                }
            }
        }
    }

    function drawNumberTrain(paper, point1, point2, np, tevent){
        var k = (point2.y - point1.y)/(point2.x-point1.x); //тангенс наклона к оси х
        var atan = Math.atan(k);
        var angle = atan * 180/Math.PI;
        var x, y, x2 ;
        x = point1.x+15;
        y = point1.y-3;
        x2 = x;
        if (angle < 0){
            x=x+10;
        }
        if (np){
            x2 = x +40;
            var text = paper.text(x, y, np);
            text.attr('transform', "rotate("+angle+" "+point1.x+","+point1.y+")");
            text.attr('class', cssclass.np);
        }
        //var text = paper.text(x2, y, tevent.ip);
        //text.attr('transform', "rotate("+angle+" "+point1.x+","+point1.y+")");
        //text.attr('class', cssclass.ip);
    }

    function drawDatop(paper, point, tevent) {
        if (!tevent.showDatop) return;
        var minute = getMinute(tevent.datop);
        if (minute != 0) {
            var line = paper.line(point.x, point.y - 7, point.x, point.y + 7);
            line.attr('class', cssclass.metka);
            var text = paper.text(point.x, point.y - 8, minute);
            text.attr('kop', tevent.kop);
            text.attr('esr', tevent.esr);
            text.attr('class', cssclass.metka);
        }
        var idvag = getIdVag(tevent.esr, tevent.trId, tevent.kop);
        var consist = 'esr=' + tevent.esr + '&trId=' + tevent.trId + '&idTrainHistVag=' + idvag + '&forecastId=' + usedforecast.id + '&ip=' + tevent.ip + '&np=' + tevent.np;
        var hrefconsist = _urls.linkTrainConsist + consist;
        var asvg = paper.el("a").attr({"xlink:href": hrefconsist, "target": "_blank"});
        var circle = paper.circle(point.x, point.y, 6);
        circle.attr('kop', tevent.kop);
        circle.attr('esr', tevent.esr);
        circle.attr('class', cssclass.metka);
        asvg.append(circle);
    }

    function getMinute(ms) {
        return new Date(ms).getMinutes() % 10;
    }

    function showgraf(grafTagId, trainOperations, forecast) { // Основной метод отрисовки
        groupOnTrId(trainOperations);
        var paper = Snap(grafTagId); // создание холста
        usedforecast = forecast;
        initGraph(paper);
        drawAxisY(paper);
        drawAxisX(paper);
        drawStartLine(paper);
        drawThreads(paper);
        setHandlers(forecast);
        $(grafTagId).show();
    }

    function getClassByIp(ip) {
        if (ip.substring(5, 8) == "000") {
            return cssclass.forecast;
        }
        return cssclass.real;
    }

    function cross(p1, p2, p3, p4) //точки  отрезков, вертикаль\горизонталь точки p3,p4
    {
        if (p3.x == p4.x) { // вертикаль
            var y = p1.y + ((p2.y - p1.y) * (p3.x - p1.x)) / (p2.x - p1.x);
            if (y > Math.max(p3.y, p4.y) || y < Math.min(p3.y, p4.y) || y > Math.max(p1.y, p2.y) || y < Math.min(p1.y, p2.y))   // если за пределами отрезков
                return {x: 0, y: 0};
            else
                return {x: p3.x, y: y};
        } else { // горизонталь
            var x = p1.x + ((p2.x - p1.x) * (p3.y - p1.y)) / (p2.y - p1.y);
            if (x > Math.max(p3.x, p4.x) || x < Math.min(p3.x, p4.x) || x > Math.max(p1.x, p2.x) || x < Math.min(p1.x, p2.x))   // если за пределами отрезков
                return {x: 0, y: 0};
            else
                return {x: x, y: p3.y};
        }
    }

// default name module
    window.usogdpgraph = new Api();
    //window.pgdp.init();
    return usogdpgraph;
}(jQuery, usogdp));


