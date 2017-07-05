var Queue = function(){
    var queue = [];
    this.add = function(element) {queue.push(element);return this;};
    this.get = function() {return queue.shift();};
    this.remove = function() {queue.shift(); return this; };
    this.size = function() {return queue.length;};
    this.isEmpty = function() {return (queue.length == 0);};
    this.getQueue = function() {return queue;};
};
var ted=false;
setTimeout(function(){if(typeof jQuery=="undefined" | !ted) location.href=location.href},60*10*1000);

function getFlash(name) { return window.document[name] || window[name] || document.embeds[name]; }
console.log("taskCreator");
var taskCreator= {
    timeProv: 20 * 1000,//время проверки нового таска
    timeLoadera: 10 * 1000,//время для проверки задания вктаргетом
    timeReSearchTask: 60*10*1000,//время перезагрузки страницы и поиска заданий
    date: new Date().getTime(),
    servurl: 'https://task2-nulay.rhcloud.com',
    queue:null,
    countund:0,
    timecheckComplete:5000,
    init: function (options) {
        var thisEl=this;
        if(document.URL.indexOf("vktarget.ru/list")==-1){
            log.console("it is not vktarget.");
            //если это ютуб фрейм то пытаемся запустить видео
            if(document.URL.indexOf("youtube.com")>=0){
                setTimeout(function(){
                    console.log('clickYoutoub');
                    var t=document.getElementsByClassName('ytp-large-play-button');
                    for(var i=0;i<t.length;i++){
                        t[i].click();
                        console.log('clickYoutoub '+i);
                    }
                },10000);
            }
            return;
        }
        //document.getElementsByClassName("vkt-menu__item")[8].text=this.countund;
        if(document.getElementById("header")==null){
            if(this.countund>10){
                document.location.href=document.URL;
            }
            this.countund++;
            setTimeout(function(){thisEl.init(options)},1000);
            return;
        }
        this.countund=0;
        if(document.getElementById("whiteness").style.display!="none"){
            if(this.countund>50){
                document.location.href=document.URL;
            }
            this.countund++;
            setTimeout(function(){thisEl.init(options)},2000);
            return;
        }
        this.countund=0;
        if(typeof jQuery == "undefined"){
            if(this.countund>20){
                document.location.href=document.URL;
            }
            this.countund++;
            setTimeout(function(){thisEl.init(options)},1000);
            return;
        }
        //check loging in vktarget
        if($('.vkt-head .vkt-menu__item.login[data-show=login-popup]').length>0){
            console.log("try enter to sistem");
            if(execPermission.enter!=null && execPermission.enter=='login'){
                console.log("try enter to sistem with login");
                $('.vkt-head .vkt-menu__item.login[data-show=login-popup]').click();
                $('.vkt-login__form input[data-login=email]').val(execPermission.enterlogin);
                $('.vkt-login__form input[data-login=password]').val(execPermission.enterpass);
                setTimeout(function(){
                    $('.vkt-login__form div.vkt-menu__item.login').click();
                },5*1000);
            }else{
                $('.vkt-head .vkt-menu__item.login[data-show=login-popup]').click();
                setTimeout(function(){
                    $('.vkt-login__buttons .vk-square [data-uloginbutton=vkontakte]').click();
                },5*1000);
            }
            setTimeout(function(){
                document.location.href=document.URL;
            },2*60*1000);
            return;
        }
        this.countund=0;
        if (options) {
            if (options.date) {
                this.date = options.date;
            }
            if (options.servurl) {
                this.servurl = options.servurl;
            }
        }
        this.createcontainer();
        setTimeout(function(){thisEl.searthTask();},2000);
    },
    createcontainer:function(){
        if(jQuery('#panelDf').length<1){
            jQuery('body').prepend('<div id="panelDf" style="background:white;display:block;height:123px;width:115px;left:3px;top:40px;z-index:4;position:fixed;font-size: smaller;"></div>');
            jQuery('#panelDf').prepend('<div id="logP" style="border:1px solid gray;overflow:auto;height:113px;font-size: xx-small;"></div>');
        }
    },
    addinfo:function(info){
        console.log(info);
        jQuery('#logP').prepend('<div>'+info+'</div>');
    },
    //ищем задания и вызваем его добавление для пользователя
    searthTask:function(){
        var thisEl=this;
        if(this.queue==null){
            this.addinfo('Ищем задания');
            this.queue=new Queue();
            if(jQuery('.vkt-content__list-item .vkt-content__list-item-type:first').text().length<3){
                if(this.countund<10){
                    this.countund++;
                    this.queue=null;
                    setTimeout(function(){thisEl.searthTask();},2000);
                    return;
                }
            }
        }
        jQuery('.vkt-content__list-item:not(.checkedEl)').each(function(ind,el){jQuery(el).addClass("checkedEl");thisEl.queue.add(el);});
        this.addinfo("Количество эл. "+thisEl.queue.size());
        this.countund=0;
        if(this.queue.size()>0){
            this.addinfo('Выполняем следующее задание');
            var task=jQuery(this.queue.get());
            this.curEl={};
            this.curEl.el=task;
            var elT=task.find('a[data-bind=url]');
            var contT="";
            var pageName=elT.attr('href');
            var name=elT.parent().text();
            this.addinfo(pageName);
            var price=elT.parent().parent().find('span[data-bind=price]').text();
            thisEl.addinfo("Ждем "+price*10+" мин");

//            if(pageName.indexOf('facebook.com')!=-1){
//                task.find('[data-bind=hide]').click();
//                this.searthTask();
//                return;
//            }
            var perf=false;
            switch (name){
//                case 'Нажмитерассказать друзьям': contT="toldFrands";break;
                case 'Вступите всообщество': contT="goInGroup";break;
                case 'Поставьте лайк настранице': contT=(pageName.indexOf('facebook.com')!=-1)?"likeOnPageF":"likeOnPage";break;
                case "Поставьте 'Нравится'под видео":contT="likeOnPageYT";break;
                case 'Подпишитесь наканал':contT="signeYT";break;
                case 'Посмотритевидео': contT="lookvideo";break;
//                case 'Расскажите огруппе': contT="toldFrandsAG";break;
//                case 'Рассказать осайте': name+='_'+pageName; pageName=(task.find('[data-bind=item_network] i.fa').attr('class').indexOf('facebook'))?"facebook.com":"vk.com"; contT=(pageName.indexOf('facebook.com')!=-1)?"toldASF":"toldAS";   break;
                default : contT=name; this.addinfo('Не будем выполнять задание '+contT); perf=true; break;
            }
            if(perf){
                this.addinfo('Не будем выполнять задание '+contT);
                setTimeout(function(){thisEl.searthTask();},500);
                return;
            }
            // log(elT.innerHTML);

            var pN=(pageName!=null && pageName.indexOf('?http')!=-1)?pageName.substring(pageName.indexOf('?http')+1,pageName.length):pageName;
            pN=(pageName!=null && pageName.indexOf('://new.')!=-1)?pageName.replace("new.",""):pageName;

            this.addTask({"name":name,"executor":window.executor,"content":contT,"pageName":pN,"performed":perf,"additionalData":JSON.stringify({"price":price,"balance":$('.vkt-head span[data-bind=balance]').text()})},price,pageName);
        } else{
            if(this._stOk>0){
                this.timeReSearchTask=10*1000;
            }
            if(this._stOk<0){
                this.timeReSearchTask=this.timeReSearchTask*(this._stOk*(-1));
            }
            this.addinfo('Заданий больше нету - ждем нового поиска '+new Date());
            setTimeout(function(){location.reload(); setInterval(function(){location.reload();},thisEl.timeReSearchTask);},this.timeReSearchTask);
            thisEl.secOut=0;
            thisEl.addinfo('<div id="divSec"><div>');
            setInterval(function(){thisEl.secOut++;if((thisEl.secOut%100)==0){location.reload();} document.getElementById('divSec').innerHTML=(thisEl.timeReSearchTask/1000-thisEl.secOut);},1000);
        }
    },
    //добавляем задание для выполнения
    addTask:function(task,price,pageName){
        var thisEl=this;
        //обнуляем счетчики
        this.chRCount=0;
        this.datePrCount=0;
        this.countCheck=0;
        this.curEl.task=task;
        this.curEl.pageName=pageName;
        this.addinfo('Заводим новое задание');
        jQuery.ajax({
            type: "POST",
            dataType:"json",
            url: thisEl.servurl+"/changer/task/addOrChangeTask",
//            contentType: 'application/json; charset=utf-8',
//            beforeSend: function (xhr) {
//                xhr.setRequestHeader("Accept", "application/json");
//                xhr.setRequestHeader("Content-Type", "application/json");
//            },
//            xhrFields: {
//                withCredentials: false
//            },
            data: {"taskjson": JSON.stringify(task)} ,
//            data:task,
//            crossDomain: true,
            success: function(data){
                thisEl.addinfo('Завели задание task.content='+task.content+" "+window.execPermission[task.content]);
                if(data!=null && window.execPermission[task.content]){
                    thisEl.curEl.data=data;
                    window.open(pageName,'_blank');
                    var tdop=0;
                    thisEl.addinfo(task+" # "+task.content);
                    if(task.content=="lookvideo"){
                        tdop=parseFloat(price)*10*60000;
                        thisEl.addinfo("Увеличиваем время ожидания на "+(tdop/1000)+" сек");
                    }
                    setTimeout(function(){thisEl.checkComplete(data.id);},thisEl.timecheckComplete*2+tdop);
                }else{
                    thisEl.searthTask();
                }},
            error:function(data){
                thisEl.addinfo("Сервер не отвечает");
                setTimeout(function(){thisEl.searthTask();},thisEl.timeProv);
            }
        });
    },
    //проверяем выполнилось ли задание или нет
    checkComplete:function(id_task){
        var thisEl=this;
        thisEl.addinfo("Проверяем выполнено ли задание на серевере");
        if(this.countCheck<5 && this.curEl.task.content!="lookvideo"){
            this.countCheck++;
            jQuery.ajax({
                type: "GET",
                url: thisEl.servurl+"/changer/task/checkperform",
                data: {"id_task": id_task},
                success: function(data){
                    if(data){
                        thisEl.checkToDo(id_task);//проверка на выполненное задание
                    }else{
                        setTimeout(function(){thisEl.checkComplete(id_task);},thisEl.timecheckComplete);
                    }},
                error:function(data){thisEl.addinfo("Сервер не отвечает"); setTimeout(function(){thisEl.checkComplete(id_task);},thisEl.timecheckComplete)}
            });
        }else{
            this.countCheck++;
            this.addinfo("Пытаемся проверить а вдруг выполнили");
            this.checkToDo(id_task);
        }
    },
    _maxcountCP:5,
    _maxcountPR:3,
    //Пытаемся проверить  выполнение
    checkToDo:function(id_task){
        var thisEl=this;
        this.addinfo("Запускаем следующий цикл "+(this.chRCount+1)+"/"+this._maxcountCP);
        if(this.curEl.el.find('[data-bind=success]:visible').length>0){this.checkReady(id_task); return;}
        if(this.curEl.el.find('[data-bind=check]:visible').length>0){
            this.addinfo("Тискаем кнопку выполнено "+(this.chRCount+1)+"/"+this._maxcountCP);
            if(this.chRCount==3){
                //try do this task one tyme more
                window.open(this.curEl.pageName,'_blank');
                //push check button past half timeProv time
                setTimeout(function(){
                    if(thisEl.curEl.el.find('[data-bind=success]:visible').length<1){thisEl.curEl.el.find('[data-bind=check]:visible').click();}
                },thisEl.timeProv/2);
            }else{
                this.curEl.el.find('[data-bind=check]:visible').click();
            }
        }else{
            if(this.curEl.el.find('[data-bind=loader]:visible').length>0){
                this.addinfo("Просто ждем еще окончания загрузки "+(this.datePrCount+1)+"/"+this._maxcountPR);
                this.datePrCount++;
                if(this.datePrCount==this._maxcountPR){
                    this.chRCount++;
                    this.datePrCount=0;
                }
                if(this.chRCount>this._maxcountCP){
                    this.checkReady(id_task);
                    return;
                }
                if(this.chRCount==3 && this.curEl.task.pageName.indexOf('youtube.com')>=0){
                    this.addinfo("Тискаем авторизацию ютуба");
                    $('div[data-auth=yt]').click();
                }
                setTimeout(function(){thisEl.checkToDo(id_task);},this.timeLoadera);
                return;
            }
            this.datePrCount=0;
        }
        if(this.curEl.el.find('[data-bind=success]:visible').length>0){this.checkReady(id_task); return;}
        if(this.chRCount==3 && this.curEl.task.pageName.indexOf('youtube.com')>=0){
            this.addinfo("Тискаем авторизацию ютуба");
            $('div[data-auth=yt]').click();
        }
        this.addinfo("Ждем готовности");
        setTimeout(function(){thisEl.checkReady(id_task);},thisEl.timeProv);
    },
    checkReady:function(id_task){
        if(this.curEl.el.find('[data-bind=success]:visible').length>0 || this.chRCount>this._maxcountCP){
            this.sendPerformStatus(id_task,(this.chRCount<=this._maxcountCP)?2:3,0);
            this.searthTask();
        }else{
            this.chRCount++;
            this.checkToDo(id_task);
        }
    },
    _stOk:0,
    sendPerformStatus:function(id_task,status,ind){
        if(status==2){
            this._stOk++;
        }else{
            this._stOk--;
        }
        var thisEl=this;
        this.addinfo("Отправляем статус готовности "+status);
        jQuery.ajax({
            type: "GET",
            url: thisEl.servurl+"/changer/task/setPerformedStatus",
            data: {"id_task": id_task,"status": status},
            success: function(data){
                thisEl.addinfo("Status installed.");
            },
            error:function(){
                if(ind<5) {
                    setTimeout(function () {
                        thisEl.sendPerformStatus(id_task, status, ind);
                    }, this.timeProv);
                }else{
                    thisEl.addinfo("Diden`t succesfull send request about status complete.");
                }
            }
        });
    }
}

jQuery(function(){
    ted=true;
    taskCreator.init();
})