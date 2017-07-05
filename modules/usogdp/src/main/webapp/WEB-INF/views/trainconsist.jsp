<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<script src="${ctx}/resources/js/error.js"></script>
<%--<script src="${ctx}/resources/js/trainR.js"></script>--%>
<script src="${ctx}/resources/js/trainConsist.js"></script>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdp.js"></script>


    <style>
        .tbFormat{
            width: 100%;
            overflow: auto;
            text-align: center;
        }
        .tbFormat, header, footer{
            box-sizing: border-box;
            -moz-box-sizing: border-box;
        }
        .center{
            display: inline-block;
        }
    </style>


    <div class="tbFormat">
        <%--вывод информации о прогнозе и о составе--%>
        <div  class="">
            <Label id="info" class="color" style="display: inline-block; margin-top: 5px; margin-left: 20px"></Label><br>
        </div>
        <div class="center">
            <table id="tableTrain">

            </table>
        </div>
    </div>
<script>
            $(function() {
               train.init();
            });
</script>