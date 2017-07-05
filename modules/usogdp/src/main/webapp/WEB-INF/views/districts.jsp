<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdpgraph.js?b1"></script>

<style>
    <%--красная строка--%>
    p{
        text-indent: 25px;
    }
</style>
<div style="text-align: center; margin-bottom: 10px;"><h3>Конструирование участков для отображения графика</h3></div>

<div id="ddistricts">
    <select id="mainStation"></select>
    <%--<select id="station1"></select>--%>
    <%--<select id="station2"></select>--%>
    <%--<table style="width: 100%">--%>
        <%--<tr>--%>
            <%--<td style="width: 50%"><table id="tdistricts" ></table></td>--%>
            <%--<td style="vertical-align: top;width: 50%">--%>
                <%--<div style=" width: 100%">--%>
                    <%--<h3 style="text-align: center">Пример</h3>--%>
                    <%--&lt;%&ndash;<p>Соединение последующих участков происходит по первому выбранному участку.</p>&ndash;%&gt;--%>
                    <%--<p>1. Выбираем первый участок из списка</p>--%>
                    <%--<p>2. Выбираем участки из списка, </p>--%>
                    <%--&lt;%&ndash;<p>Если участок не стыкуется, будет нарисован график первого выбранного участка</p>&ndash;%&gt;--%>
                <%--</div>--%>
            <%--</td>--%>
        <%--</tr>--%>
    <%--</table>--%>
</div>
<div style="margin-top: 10px">
    <div><label id="description"></label>&nbsp&nbsp<label id="alldistrict"></label></div>
    <input style="margin-right: 10px; visibility: hidden" id="bDistricts" type="button" value="Показать график">
</div>

<script>
    var districts=[];
    var allDistrict=[];
    $(document).ready(function () {
        // название тега бля вставки таблицы
        usogdp.ajaxLastForecast();

        var idDistricts = "#tdistricts";

        // вешаем обработчик события на кнопку выбора участка
        $( "#bDistricts" ).click(function() {
            var myGrid = $(idDistricts);
            var selRowIds = myGrid.jqGrid('getGridParam', 'selarrrow');
            var dataArray = [];
//            var index;
//            var rowData;
//            for (var i = 0; i < selRowIds.length; i++) {
//                 index = selRowIds[i];
//                 rowData = usogdpgraph.storage.districts[index-1];
//                dataArray.push(rowData['id']);
//            }
            usogdpgraph.openGraph(districts);
        });

        // получение списка участков и показ его в таблице
        $.when(usogdp.ajaxStationsNsi())
                .done(function(dataAjaxSimstations){
                    usogdpgraph.ajaxDistricts(function(data){
                        var listEsr= [];
                        var mapEsr ={};
                        var esr2;
                        var needb = false;
                        for (var i=0; i< data.length; i++){
                            var key = data[i].sta_no;
                            needb = false;
                            for (var k=0;k<listEsr.length; k++){
                                if (listEsr[k] == key){
                                    needb = true;
                                    break;
                                }
                            }
                            if (needb) continue;
                            listEsr.push(key);

                            mapEsr[key] = [];
                            for (var j=0; j < data.length; j++) {
                                if (data[j].sta_no == key){
                                    mapEsr[key].push(
                                            {esr:data[j].sta_no1,
                                                distr:data[j].id});
                                }
                                if (data[j].sta_no1 == key){
                                    mapEsr[key].push(
                                            {esr:data[j].sta_no,
                                                distr:data[j].id});
                                }
                            }

                            var key = data[i].sta_no1;
                            needb = false;
                            for (var k=0;k<listEsr.length; k++){
                                if (listEsr[k] == key){
                                    needb = true;
                                    break;
                                }
                            }
                            if (needb) continue;
                            listEsr.push(key);
                            mapEsr[key] = [];

                            for (var j=0; j < data.length; j++) {
                                if (data[j].sta_no == key){
                                    mapEsr[key].push(
                                            {esr:data[j].sta_no1,
                                                distr:data[j].id});
                                }
                                if (data[j].sta_no1 == key){
                                    mapEsr[key].push(
                                            {esr:data[j].sta_no,
                                                distr:data[j].id});
                                }
                            }

                        }
                        console.log(mapEsr);

                        $('#mainStation').append('<option value="">Первая станция</option>')
//                        for(var station in mapEsr){
                        for(var i=0; i<listEsr.length; i++){
                            var stationName;
                            for(var j=0; j<dataAjaxSimstations.length; j++){
                                if(dataAjaxSimstations[j].esr==listEsr[i]){
                                    stationName=dataAjaxSimstations[j].name;
                                }
                            }
                            $('#mainStation').append('<option value="'+listEsr[i]+'">'+stationName+'</option>')
                        }

                        function selectStationAction(){
                            var stringDistrict='';
                            var objs=$('#'+this.id).nextAll();
                            var selectedStation=$(this).val();
                            var countDistricts=$('#'+this.id).prevAll().length/2;

                            for(i=0; i<objs.length; i++){
                                $('#'+objs[i].id).remove();
                            }

//                            if(objs.length!=0 && this.id!='mainStation'){
                            if(this.id!='mainStation'){
                                districts.splice(countDistricts-1, districts.length-countDistricts+1)
                                allDistrict.splice(countDistricts, allDistrict.length-countDistricts+1)
                            }

                            if(selectedStation==''){
                                if(allDistrict.length==1 || this.id=='mainStation'){
                                    $('#alldistrict').text('')
                                    $('#description').text('')
                                    $('#bDistricts').css('visibility','hidden')
                                    return
                                }
                                stringDistrict=stringDistrict+allDistrict[0]+' – '+allDistrict[allDistrict.length-1]
                                $('#description').text('Участок   ')
                                $('#alldistrict').text(stringDistrict)
                                $('#bDistricts').css('visibility','visible')
                                return;
                            }

//                            for(var station in mapEsr){
                            for(var j=0; j<listEsr.length; j++){
                                if(listEsr[j]==selectedStation){
                                    if(mapEsr[listEsr[j]].length==0) return;

                                    $('#ddistricts').append('<label style="font-size: 1.5em" id="label_'+selectedStation+'">→</label>')
                                    $('#ddistricts').append('<select style="margin-left: 5px; margin-right: 5px" id="'+selectedStation+'">')

                                    $('#'+selectedStation).append('<option value="">Следующая станция</option>')
                                    $('#'+selectedStation).change(selectStationAction);
                                    for(var i=0; i<mapEsr[listEsr[j]].length; i++){
                                        if($('#'+mapEsr[listEsr[j]][i].esr).length!=0){
                                            if(mapEsr[listEsr[j]].length==1){
                                                $('#'+selectedStation).remove()
                                                $('#label_'+selectedStation).remove()
                                            }
                                            continue;
                                        }
                                        var stationName1;
                                        for(var h=0; h<dataAjaxSimstations.length; h++){
                                            if(dataAjaxSimstations[h].esr==mapEsr[listEsr[j]][i].esr){
                                                stationName1=dataAjaxSimstations[h].name;
                                            }
                                        }
                                        $('#'+selectedStation).append('<option district="'+mapEsr[listEsr[j]][i].distr+'" value="'+mapEsr[listEsr[j]][i].esr+'">'+stationName1+'</option>')
                                    }
                                    break;
                                }
                            }
                            if(this.id=='mainStation'){
                                districts=[];
                                allDistrict=[];
                                allDistrict[0]=$('#mainStation :selected').text();
                                $('#alldistrict').text('')
                                $('#description').text('')
                                return;
                            }else{
                                districts[districts.length]=$('#'+this.id+' :selected').attr('district');
                                allDistrict[allDistrict.length]=$('#'+this.id+' :selected').text();
                            }
                            stringDistrict=stringDistrict+allDistrict[0]+' – '+allDistrict[allDistrict.length-1]
                            $('#description').text('Участок   ')
                            $('#alldistrict').text(stringDistrict)
                            $('#bDistricts').css('visibility','visible')
                        }

                        $('#mainStation').change(selectStationAction);
//            usogdpgraph.showDistricts(idDistricts, data);
                    })
                })
                .fail(function(){

                });

    });
</script>


