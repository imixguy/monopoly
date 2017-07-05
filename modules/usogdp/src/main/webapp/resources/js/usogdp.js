(function ($) {
    var
        _moduleName = "usogdp.js",
        _description = "Общий модуль для общих запросов ajax",
        _version = "0.0.2",
        _enabledLog = true,
        _storage = {
            forecast:{}},
        context = ctx || "",// default context is determined dynamically from inHeader.jsp
        prefixGraphController = "/ajax/",
        _urls = {
            simstations: context + prefixGraphController + "simstations", // урл для получение списка станций моделирования
            forecast: context + prefixGraphController + "forecast", // урл для получение прогноза по его ид
            forecasts: context + prefixGraphController + "forecasts", // урл для получение списка всех прогнозов
            lastforecast: context + prefixGraphController + "lastforecast", // урл для получение последнего прогноза
            stationnsi:context + prefixGraphController + "station", // урл для получения станции nsi,
            stationsnsi: context + prefixGraphController + "stations" // урл для получения списка станциий nsi
        }
        ;
    //---------- проверка наличия jquery переопределение logging -----
    if (typeof jQuery === 'undefined') {
        throw new Error(_moduleName + ' JavaScript requires jQuery')
    };

    window.log = function () {
        if (_enabledLog) {
            try {
                return console.log.apply(console, arguments);
            } catch (_error) {
            }
        }
    };
    //if (_enabledLog) {
    //    log("логирование в консоль включено");
    //};
    //-------- конструктор объекта usogdp -----
    function Api() {
        //ajaxSimStations();
        //ajaxForecasts();
    };

    Api.prototype = {
        constructor: Api,
        moduleName: _moduleName,
        version: _version,
        description: _description,
        disableLog: function(){
          _enabledLog = false;
        },
        info: function () {
            console.log(this.moduleName);
            console.log(this.version);
        },

//        sortDateComporator:sortDateComporator,
        getForecastByIdOrLast:getForecastByIdOrLast,
        castomSertCell:castomSertCell,
        formatDate: formatDate,
        getUrlParameter:getUrlParameter,
        showLoader:showLoader,
        hideLoader:hideLoader,
        castomToolTip:castomToolTip,
        contextmenu:contextmenu,
        closeContextMenu:closeContextMenu,
        ajaxSimStations: ajaxSimStations,
        ajaxLastForecast: ajaxLastForecast,
        ajaxForecasts: ajaxForecasts,
        ajaxForecast: ajaxForecast,
        ajaxStationNsi:ajaxStationNsi,
        ajaxStationsNsi:ajaxStationsNsi,

        storage: _storage
    };

    function getUrlParameter(sParam)
    {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++)
        {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] == sParam)
            {
                return decodeURIComponent(sParameterName[1]);
            }
        }
    }
    //--------- запрос на станции моделирования ----
    function ajaxSimStations(callback) {
        return $.ajax({
            url: _urls.simstations,
            dataType: 'json',
            type: "GET"
        }).done(function (data) {
                _storage.simstations = data;
                if (callback) callback(data);
            }
        );
    }
    //------- запрос на последний forecast --------
    function ajaxLastForecast(callback) {
        return $.ajax({
            url: _urls.lastforecast,
            timeout: 70000,
            dataType: "json",
            type: "GET"
        }).done(function (data) {
            _storage.lastforecast = data;
            if (callback) callback(data);
        });
    }
    //   ------- запрос на получение всех forecasts --------
    function ajaxForecasts(callback) {
        return $.ajax({
            url: _urls.forecasts,
            dataType: "json",
            type: "GET"
        }).done(function (data) {
            _storage.forecasts = data;
            if (callback) callback(data);
        });
    }

    function getForecastByIdOrLast(id){
        for(var i = 0; i<_storage.forecasts.length; i++){
            if (_storage.forecasts[i].id == id) {
                return _storage.forecasts[i];
            }
        }
        return _storage.forecasts[_storage.forecasts.length-1];
    }
    //   ------- запрос на forecast по id --------
    function ajaxForecast(id, callback) {
        return $.ajax({
            url: _urls.forecast,
            dataType: "json",
            type: "GET",
            data: {id: id}
        }).done(function (data) {
            _storage.forecast[id.toString()] = data;
            if (callback) callback(data);
        });
    }
    //   ------- запрос на станцию nsi по известном esr --------
    function ajaxStationNsi(esr){
        return $.ajax({
            url: _urls.stationnsi,
            dataType: "json",
            type: "GET",
            data: {esr: esr}
        }).done(function (data) {
            log(_moduleName + "ajaxStationNsi:done");
        }).fail(function(){

        });
    }
    //   ------- запрос на все станции nsi --------
    function ajaxStationsNsi(){
        return $.ajax({
            url: _urls.stationsnsi,
            dataType: "json",
            type: "GET"
        }).done(function (data) {
            _storage.stationsNsi=data;
            log(_moduleName + "ajaxStationsNsi:done");
        }).fail(function(){

        });
    }

    //переопределение функции для сортировки даты (jqgrid)
    function castomSertCell(vall){
        if(vall==''){
            return 999999999;
        }else{
            var date=vall.split(' ')[0].split('/');
            var time=vall.split(' ')[1].split(':');
            return Number('1'+date[1]+date[0]+time[0]+time[1]);
        }
    }

    //----------- форматирование даты -----------
    function formatDate(ms, format) {
        if (ms == null) {
            return '';
        }
        var formatDate;
        var date = new Date(ms);
        switch (format){
            case "forecast":
                formatDate = ('0' + date.getDate()).slice(-2) + '.' + ('0' + (date.getMonth() + 1)).slice(-2)+'.' + date.getFullYear() + ' время ' + ('0' + date.getHours()).slice(-2) + ':' + ('0' + date.getMinutes()).slice(-2)+':' + ('0' + date.getSeconds()).slice(-2);
                break;
            case "DD/MM":
                formatDate = ('0' + date.getDate()).slice(-2) + '/' + ('0' + (date.getMonth() + 1)).slice(-2);
                break;
            default: // "DD/MM HH:MM"
                formatDate = ('0' + date.getDate()).slice(-2) + '/' + ('0' + (date.getMonth() + 1)).slice(-2) + ' ' + ('0' + date.getHours()).slice(-2) + ':' + ('0' + date.getMinutes()).slice(-2);
                break;
        }
        return formatDate;
    }

    // Функция для определения координат указателя мыши
    function defPosition(event, element) {
        var x = 0, y = 0;
        x = event.clientX + (document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft);
        y = event.clientY + (document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop);
        var elementWidth, elementHeight, elementPosition, menuWidth, menuHeight;
        menuWidth=$('#contextMenu').width();
        menuHeight=$('#contextMenu').height();
        if(element){
             elementWidth = $(element).width();
             elementHeight = $(element).height();
             elementPosition=$(element).offset();
        }
        else{
            elementWidth =$('body').width();
            elementHeight = $('body').height();
            elementPosition=$('body').offset();
        }
        if(x+menuWidth>elementWidth || (elementWidth-(x+menuWidth))<15){
            x=(elementWidth-menuWidth-15);
        }
        if(y+menuHeight>(elementPosition.top + elementHeight-8)){
            y=(elementPosition.top + elementHeight)-menuHeight-8;
        }
        return {x:x, y:y};
    }

//    функция обработчик контекстного меню (element - тот элемент, относительно которого будет вызываться контекстное меню)
    function contextmenu(evt, element) {
        // Блокируем всплывание события contextmenu
        evt = evt || window.event;
        evt.cancelBubble = true;
        // Показываем собственное контекстное меню
        var menu = document.getElementById("contextMenu");
        var position=defPosition(evt, element);
        menu.style.top = position.y + "px";
        menu.style.left = position.x + "px";
        menu.style.display = "";
        // Блокируем всплывание стандартного браузерного меню
        return false;
    }

    // Закрываем контекстное при клике левой или правой кнопкой по документу
    // Функция для добавления обработчиков событий
    function closeContextMenu(){
        function addHandler(object, event, handler, useCapture) {
            if (object.addEventListener) {
                object.addEventListener(event, handler, useCapture ? useCapture : false);
            } else if (object.attachEvent) {
                object.attachEvent('on' + event, handler);
            } else alert("Add handler is not supported");
        }

        addHandler(document, "contextmenu", function() {
            document.getElementById("contextMenu").style.display = "none";
        });
        addHandler(document, "click", function() {
            document.getElementById("contextMenu").style.display = "none";
        });
    }

    //----------- custom подсказка tooltip ---------------------
    function castomToolTip(str, count){
        return str.split('|')[count];
    }

    //------- показать загрузчик (крутящаяся GIF картинка) -------
    function showLoader(){
        $('.loading').show();
    }

    //------- скрыть загрузчик (крутящаяся GIF картинка) -------
    function hideLoader(){
        $('.loading').hide();
    }

// default name module
    window.usogdp = new Api();
    return usogdp;
}(jQuery));



