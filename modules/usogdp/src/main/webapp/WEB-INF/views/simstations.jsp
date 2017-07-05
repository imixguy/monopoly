<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdpgraph.js"></script>

<div>
<table id="simstations"></table>
</div>

<script>
    $(document).ready(function () {
        usogdp.ajaxSimStations(function(data){
            usogdpgraph.showStaMod("#simstations", data);
        })
    });
</script>