<%--
  Created by IntelliJ IDEA.
  User: sedler
  Date: 27.01.15
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/trainprocessing.js"></script>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/error.js"></script>
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
        width: 100%;
        text-align: center;
        padding-bottom: 10px;
    }
    .float{
        display:inline-block;
    }
    .brigateFormat{
        margin-left: 10px;
    }

</style>

<div class="center color">
    <label id="info"></label>
</div>
<div class="tbFormat">

</div>

<%--скрипт--%>
<script>
    $(function() {
        trainprocessing.init();
    });
</script>