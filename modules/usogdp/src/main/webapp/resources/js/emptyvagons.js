/**
 * Created by sedler on 26.02.15.
 */
(function ($) {
    var
        _moduleName = "empty_vagons.js",
        _description = "Модуль для регулировки парожних вагонов",
        _version = "0.0.1",
        _enabledLog = true,
        _storage = {},
        context = ctx || "",// default context is determined dynamically from inHeader.jsp
        prefixController = "/modules/ajax/",
        _urls = {
            stavagons: context + prefixController + "stavagons", // урл для получение списка станций для таблицы порожних вагонов
            allraspvagons: context + prefixController + "allraspvagons", // урл для получение порожних вагонов
            savevagons: context + prefixController + "savevagons" // урл для сохранения порожних вагонов
//            lastforecast: context + prefixGraphController + "lastforecast", // урл для получение последнего прогноза
//            stationnsi:context + prefixGraphController + "station", // урл для получения станции nsi,
//            stationsnsi: context + prefixGraphController + "stations" // урл для получения списка станциий nsi
        };
    //---------- проверка наличия jquery переопределение logging -----
    if (typeof jQuery === 'undefined') {
        throw new Error(_moduleName + ' JavaScript requires jQuery')
    };

    //-------- конструктор объекта usogdp -----
    function Api() {
    };

    Api.prototype = {
        constructor: Api,
        moduleName: _moduleName,
        version: _version,
        description: _description,
        info: function () {
            console.log(this.moduleName);
            console.log(this.version);
        },
        init:init,
        storage: _storage
    };


    function init (){
        $.when(ajaxStavagons(),allRaspvagons(), usogdp.ajaxStationsNsi())
            .done(function(ajaxStavagons,allRaspvagons,ajaxStationsNsi){
                _storage.ajaxStationsNsi=ajaxStationsNsi[0];
                _storage.data=[];
                _storage.autocompleteData=[];
                _storage.errrors=[];
                addAutocomplete();
                if(!_storage.flag){
                    $('#emptyVagons').on('focus', '.esr', function(){

                        $(this).autocomplete({
                            select: function( event, ui ) {
                                $(this).val( ui.item.label );
                                return false;
                            },
                            minLength:3,
                            focus: function( event, ui ) {
                                $(this).val( ui.item.label );
                                return false;
                            },
                                source: function (request, response) {
                                var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(request.term), "i");
    //                            var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
                                response($.grep(_storage.autocompleteData, function(value) {
                                    return matcher.test(value.value)
                                        || matcher.test(value.label);
                                }));
                            }
                        }).autocomplete( "instance" )._renderItem = function( ul, item ) {
                            return $( "<p>" )
                                .append(item.label +' '+ item.value)
                                .appendTo( ul );
                        };
                    });

                    $('#addStation').click(function(){
                        var rowIds=jQuery('#emptyVagons').jqGrid('getDataIDs');
                        jQuery('#emptyVagons').jqGrid('addRowData', Number(rowIds[rowIds.length-1])+1,createObject(),'last')
                    });

                    $('#cancel').click(function(){
                        jQuery('#emptyVagons').jqGrid('clearGridData');
                        jQuery('#emptyVagons').jqGrid('setGridParam', {data: _storage.data});
                        jQuery('#emptyVagons').trigger('reloadGrid');
                    });

                    $('#save').click(function(){
                        _storage.flag=true;
                        usogdp.showLoader()
                        var rowIds=jQuery('#emptyVagons').jqGrid('getDataIDs');

                        if(_storage.errrors.length>0){
    //                        todo дописать всплывающее окно
                        }else{
                            var results= jQuery('#emptyVagons').jqGrid('getChangedCells', 'dirty');
                            var resultsRows= jQuery('#emptyVagons').jqGrid('getChangedCells', 'all');
                            createSaveObjects(results,resultsRows)
                            $.when(ajaxSaveVagons())
                                .done(function(doneSaveVagons){
                                    init();
    //                                $.when()
    //                                    .done(function(){
    //
    //                                    })
    //                                    .fail(function(){
    //
    //                                    })
                                })
                                .fail(function(){

                                })

                        }

                    });
                    $('#vagonType').change(selectType);
                }
                for (var i = 0; i < types.length; i++) {
                    $('#vagonType').append('<option style=" ui-selectmenu-button " value="' + types[i].id + '">' + types[i].name + '</option>');
                }
                if(!_storage.flag){
                    $('#vagonType').val(types[0].id).change();
                }else{
                    $('#vagonType').val(_storage.selectedType).change();
                }

                if(!_storage.flag){viewTable();}

                usogdp.hideLoader();
            })
            .fail(function(){

            })

    }

    function addAutocomplete(){
        for(var i=0; i<_storage.ajaxStationsNsi.length; i++){
            _storage.autocompleteData[_storage.autocompleteData.length]={
                                                                            value:_storage.ajaxStationsNsi[i].name,
                                                                            label:_storage.ajaxStationsNsi[i].esr
                                                                        }
        }
    }

    function createSaveObjects(results, resultsRows){
        _storage.saveVagonNazn=[];
        var newEsrObjects=[]
        var newStnzObjects=[]
        var newEsrRows=[]
//        разделение на измененные станции назначения, и станции назначения и станции esr
        for(var i=0; i<results.length; i++){
            if(results[i].esr=='') continue
            if(results[i].esr){
                newEsrObjects[newEsrObjects.length]=results[i];
                newEsrRows[newEsrRows.length]=resultsRows[i];
            }else{
                newStnzObjects[newStnzObjects.length]=results[i];
            }
        }
//        создание-изменение объектов с введенными станциями назначения
        for(var i=0; i<newStnzObjects.length; i++){
            var data=_storage.data[newStnzObjects[i].id-1]
            for (var element in newStnzObjects[i]){
                if(element!='id'){
                    var adm=element;
                    var newStnz=newStnzObjects[i][element]
                    var oldStnz=data[element];
                    var type=_storage.selectedType
                    var esr=data.esr;
                    var oldVagonNazn=null
                    if(newStnz==oldStnz) break;
                    for(var j=0; j<_storage.allraspvagons.length; j++){
                        if(_storage.allraspvagons[j].esr==esr && _storage.allraspvagons[j].adm==adm && _storage.allraspvagons[j].type==type && _storage.allraspvagons[j].stnz==oldStnz){
                            oldVagonNazn=_storage.allraspvagons[j];
                            break;
                        }
                    }
                    if(oldVagonNazn){
                        oldVagonNazn.stnz=newStnz;
                        _storage.saveVagonNazn[_storage.saveVagonNazn.length]=oldVagonNazn;
                    }else{
                        _storage.saveVagonNazn[_storage.saveVagonNazn.length]={
                                                                                   adm:adm,
                                                                                   esr:esr,
                                                                                   id:0,
                                                                                   stnz:newStnz,
                                                                                   stpkl:null,
                                                                                   type:type
                                                                              }
                    }
                }
            }
        }
//        создание объектов с измененными станциями назначения и станциями esr
        newEsrObjects.sort(compareId);
        newEsrRows.sort(compareId);
        for(var i=0; i<newEsrObjects.length; i++){
            if((i+1)<newEsrObjects.length && newEsrObjects[i].esr==newEsrObjects[i+1].esr) continue;
            data=null;
            for(var j=0; j<_storage.stavagons.length; j++){
                if(newEsrObjects[i].esr==_storage.stavagons[j].esr){
                    data=_storage.data[j];
                    break;
                }
            }
            if(data){
                var newData=newEsrRows[i];
                for(var element in data){
                    if(element=='esr' || element=='nod' || element=='name' || element=='id') continue
                    if(data[element]!='' && newData[element]==''){
                        oldVagonNazn=null;
                        for(var z=0; z<_storage.allraspvagons.length; z++){
                            if(_storage.allraspvagons[z].esr==data.esr && _storage.allraspvagons[z].adm==element && _storage.allraspvagons[z].type==_storage.selectedType && _storage.allraspvagons[z].stnz==data[element]){
                                oldVagonNazn=_storage.allraspvagons[z];
                                break;
                            }
                        }
                        oldVagonNazn.stnz='';
                        _storage.saveVagonNazn[_storage.saveVagonNazn.length]=oldVagonNazn;
                    }else
                    if(data[element]=='' && newData[element]!=''){
                        var newObj={
                            adm:element,
                            esr:newData.esr,
                            id:0,
                            stnz:newData[element],
                            type:_storage.selectedType,
                            stpkl:null
                        }
                        _storage.saveVagonNazn[_storage.saveVagonNazn.length]=newObj;
                    }else
                    if(data[element]!='' && newData[element]!=''){
                        oldVagonNazn=null;
                        for(var z=0; z<_storage.allraspvagons.length; z++){
                            if(_storage.allraspvagons[z].esr==data.esr && _storage.allraspvagons[z].adm==element && _storage.allraspvagons[z].type==_storage.selectedType && _storage.allraspvagons[z].stnz==data[element]){
                                oldVagonNazn=_storage.allraspvagons[z];
                                break;
                            }
                        }
                        oldVagonNazn.stnz=newData[element];
                        _storage.saveVagonNazn[_storage.saveVagonNazn.length]=oldVagonNazn;
                    }
                }
            }else{
                for(var element in newEsrRows[i]){
                    if(element=='id' || element=='esr' || element=='nod' || element=='name' || newEsrRows[i][element]==''){
                        continue;
                    }else{
                        var newObj={
                            adm:element,
                            esr:newEsrObjects[i].esr,
                            id:0,
                            stnz:newEsrObjects[i][element],
                            type:_storage.selectedType,
                            stpkl:null
                        }
                        _storage.saveVagonNazn[_storage.saveVagonNazn.length]=newObj;
                    }
                }
//                if(newEsrObjects[i].id<=_storage.data.length){
//                    data=_storage.data[newEsrObjects[i].id-1];
//
//                }
//                resultsRows[i]
////                    todo дописать изменеие только esr в старых записях
            }
        }
    }

    function compareId(obj1, obj2) {
        return obj1.esr - obj2.esr;
    }

    function createListObbjects(){
        for(var i=0; i<_storage.stavagons.length; i++){
            obj=createObject(_storage.stavagons[i]);
            for(var j=0; j<_storage.allraspvagons.length; j++){
                if(_storage.allraspvagons[j].esr==_storage.stavagons[i].esr && _storage.allraspvagons[j].type==_storage.selectedType){
                    for(m=0; m<adm.length; m++){
                        if(adm[m].id==_storage.allraspvagons[j].adm){
                            obj[adm[m].id]=_storage.allraspvagons[j].stnz;
                            break;
                        }
                    }
                }
            }
            _storage.data[_storage.data.length]=obj;
        }
    }

    function createObject(station){
        obj={}
        if(station){
            obj.nod='';
            obj.esr=station.esr;
            obj.name=station.name;
            if(station.railDiv){
                obj.nod=station.railDiv.divId
            }
        }else{
            obj.nod='';
            obj.esr='';
            obj.name='';
        }
        for(var k=0; k<adm.length; k++){
            obj[adm[k].id]=''
        }
        return obj;
    }

    function selectType(){
        _storage.data=[];
        _storage.selectedType=$(this).val();
        createListObbjects()
        jQuery('#emptyVagons').jqGrid('clearGridData');
        jQuery('#emptyVagons').jqGrid('setGridParam', {data: _storage.data});
        jQuery('#emptyVagons').trigger('reloadGrid');
    }

    function ajaxStavagons(callback){
        return $.ajax({
            url: _urls.stavagons,
            dataType: "json",
            type: "GET"
        }).done(function (data) {
            _storage.stavagons = data;
            if (callback) callback(data);
        });
    }

    function ajaxSaveVagons(callback){
        return $.ajax({
            url: _urls.savevagons,
            dataType: "json",
            type: "POST",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            data:JSON.stringify(_storage.saveVagonNazn)
        }).done(function (data) {
            _storage.stavagons = data;
            if (callback) callback(data);
        });
    }

    function allRaspvagons(callback){
        return $.ajax({
            url: _urls.allraspvagons,
            dataType: "json",
            type: "GET"
        }).done(function (data) {
            _storage.allraspvagons = data;
            if (callback) callback(data);
        });
    }

    function viewTable(){
        var colNames=['НОД','ЕСР', 'название']
        for(var i=0; i<adm.length; i++){
            colNames[colNames.length]=adm[i].name
        }
        var colModel=[
            {name:'nod',index:'nod', width:50,editable:false,sortable:false, align:'center',sorttype:'text', classes: 'text', title:false},
            {name:'esr',index:'esr', width:50,editable:true,edittype:'text',editoptions: {size:5,maxlength: 6, class:'esr'}, sortable:false, sorttype:'text', classes: 'text', title:false},
//            {name:'esr',index:'esr', width:50, sortable:false, sorttype:'text', classes: 'text', title:false},
//            {name:'name',index:'name', width:170,editable:true,editable:true,edittype:'text',editoptions: {size:25}, sortable:false, align:'center',sorttype:'text', classes: 'text', title:false}
            {name:'name',index:'name', width:170, sortable:false, align:'center',sorttype:'text', classes: 'text', title:false}
        ]
        for(var i=0; i<adm.length; i++){
            colModel[colModel.length]={name:adm[i].id,index:adm[i].id, width:50,editable:true, edittype:'text',editoptions: {size:5, maxlength: 6,class:'esr'}, sortable:false, align:'center',sorttype:'text', classes: 'text', title:false}
        }
        jQuery("#emptyVagons").jqGrid({
            datatype: "local",
            rowNum: _storage.data.length,
            height: 'auto',
            data:_storage.data,
            multiselect: false,
            caption: 'Таблица регулировки задания',
            viewrecords: true,
            hidegrid:false,
            shrinkToFit:true,
            colNames:colNames,
            cellEdit:true,
            cellsubmit:'clientArray',
            colModel:colModel,
            afterSaveCell: function(rowid,celname,value,iRow,iCol) {
                    for(var i=0; i<_storage.ajaxStationsNsi.length; i++){
                        if(value==_storage.ajaxStationsNsi[i].esr){
                            if(iCol==1){
                                if(_storage.ajaxStationsNsi[i].railDiv){
                                    $("#emptyVagons").jqGrid('setCell',rowid, 'nod',_storage.ajaxStationsNsi[i].railDiv.divId);
                                }
                                $("#emptyVagons").jqGrid('setCell',rowid, 'name',_storage.ajaxStationsNsi[i].name);
                                return;
                            }
                            return;
                        }
                    }
                    if(value=='' && iCol!=1){
                        return;
                    }
                    if(iCol==1){
                        $("#emptyVagons").jqGrid('setCell',rowid, 'nod',' ');
                        $("#emptyVagons").jqGrid('setCell',rowid, 'name',' ');
                    }
//                    _storage.errrors[_storage.errrors.length]={
//                        rowid:rowid,
//                        celname:celname,
//                        value:value,
//                        iRow:iRow,
//                        iCol:iCol
//                    }
//                    $(this.rows[iRow].cells[iCol]).addClass('colorEditCellError');
            }
//            rowattr:function(rd){
//                if(Number(usogdp.getUrlParameter('forecastId'))==rd.id_pr){
//                    return { "class": "color" };
//                }
//            }
        });

        jQuery("#emptyVagons").jqGrid('setGroupHeaders', {
            useColSpanStyle: true,
            groupHeaders:[
                {startColumnName: 'nod', numberOfColumns: 3, titleText: '<em>Станция нахождения порожних вагонов</em>'},
                {startColumnName: adm[0].id, numberOfColumns: 15, titleText: '<em>Собственник вагонов</em>'}
            ]
        });
    }
// default name module
    window.emptyVagons = new Api();
    return emptyVagons;
}(jQuery));

var results=[]

var adm=[
    {
        id:20,
        name:'РЖД'
    },
    {
        id:21,
        name:'БЧ'
    },
    {
        id:22,
        name:'УЗ'
    },
    {
        id:23,
        name:'ЧФМ'
    },
    {
        id:24,
        name:'ЛГ'
    },
    {
        id:25,
        name:'ЛДЗ'
    },
    {
        id:26,
        name:'ЭВР'
    },
    {
        id:29,
        name:'УТИ'
    },
    {
        id:57,
        name:'АЗ'
    },
    {
        id:58,
        name:'АРМ'
    },
    {
        id:59,
        name:'КРГ'
    },
    {
        id:66,
        name:'ТЖД'
    },
    {
        id:67,
        name:'ТРК'
    },
    {
        id:27,
        name:'КЗХ'
    },
    {
        id:28,
        name:'ГР'
    }
]

var types=[
    {
        id:20,
        name:'КРЫТЫЕ'
    },
    {
        id:40,
        name:'ПЛАТФОРМЫ'
    },
    {
        id:60,
        name:'ПОЛУВАГОНЫ'
    },
    {
        id:70,
        name:'ЦИСТЕРНЫ'
    },
    {
        id:71,
        name:'ЦИСТЕРНЫ СВЕТЛ НАЛИВА'
    },
    {
        id:72,
        name:'ЦИСТЕРНЫ ТЕМН НАЛИВА'
    },
    {
        id:83,
        name:'АВТОНОМНЫЕ РЕФ ВАГОНЫ'
    },
    {
        id:87,
        name:'РЕФ ПОЕЗДА И СЕКЦИИ'
    },
    {
        id:90,
        name:'ПРОЧИЕ'
    },
    {
        id:92,
        name:'МИНЕРАЛОВОЗЫ'
    },
    {
        id:93,
        name:'ЦЕМЕНТОВОЗЫ'
    },
    {
        id:95,
        name:'ЗЕРНОВОЗЫ'
    },
    {
        id:96,
        name:'ФИТИНГОВЫЕ ПЛАТФОРМЫ'
    }
]