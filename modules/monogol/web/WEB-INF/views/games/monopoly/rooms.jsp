<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="margin: 20px">
    <c:if test="${userRoom!=null}">
        <button value="создать игру" id="butCreateGame">создать игру</button>
        <div id="silImg" style="margin:5px 15px;"></div>
    </c:if>
</div>

<div id="listGames" style="min-height: 500px;">

</div>

<script type="application/javascript">
    var objRoom={
        countUs:2,
        init:function(){
            var thisEl=this;
            var imgSel=$('<img src="/resources/images/siluetsel.png" width="20"/>').attr("ind","2");
            jQuery('#silImg').append(imgSel).append(imgSel.clone().attr("ind","2")).append(imgSel.clone().attr("ind","3")).append(imgSel.clone().attr("ind","4"));
            this.unsel(2);
            $('#silImg img').click(function(el){thisEl.unsel(parseInt($(el.target).attr('ind')))});
            this.loadAllGame();
            this.loadUserData();
            $('#butCreateGame').click(function(){
                thisEl.createGame();
            });
        },
        unselSiluet:function(el,ind){
            if(ind==0){
                unsel(ind);
            }
        },
        unsel:function(ind){
            this.countUs=ind;
            var mi=$("#silImg img");
            mi.attr('src','/resources/images/siluetsel.png');
            for(var i=ind;i<mi.length;i++){
                $(mi[i]).attr('src','/resources/images/siluet.png');
            }
        },
        loadUserData:function(){
            return;
            var thisEl=this;
            jQuery.ajax({
                url:"/games/room/userdata",
                dataType:"json",
                type:"GET"
            }).done(function (data) {
                if(data!=null){
//                    if(data.activeRooms.length>0){
//                         $('#butCreateGame').attr("disabled","disabled");
//                    }
                }
            }).always(function(){
                setTimeout(function(){thisEl.loadUserData()},3000);
            });

        },
        loadAllGame:function(){
            var thisEl=this;
            jQuery.ajax({
                url:"/games/room/getAllRoom",
                dataType:"json",
                type:"GET"
            }).done(function (data) {
                if(data!=null){
                    for(var i=0;i<data.length;i++){
                        var buttonD=$('<button value="Присоединится">Присоединится</button>');
                        var elD=$('<div><input type="hidden" value="'+data[i].numberRoom+'"><span>№'+(parseInt(data[i].numberRoom)+1)+' ('+data[i].maxCountUser+') '+'</span></div>');
                        elD.append(buttonD);
                        $('#listGames').empty().append(elD);
                        thisEl.createCl(buttonD,data[i].numberRoom);
                    }
                }else{
                    jQuery('#listGames').empty();
                }
            }).always(function(){
                setTimeout(function(){thisEl.loadAllGame()},3000);

            });
        },
        createCl:function(buttonD,numberRoom){
            var thisEl=this;
            buttonD.click(function(){thisEl.joinRoom(numberRoom);});
        },
        createGame:function(){
            document.location.href="/games/monopoly/createroom.html?countgameuser="+this.countUs;
        },
        joinRoom:function(numberRoom){
            document.location.href="/games/monopoly/"+numberRoom+"/joinRoom.html";
        }
    }
    $(function(){
        objRoom.init();
    })
</script>
