<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/stationsR.js"></script>

<script type="text/javascript" charset="utf8" src="${ctx}/resources/js/usogdpgraph.js"></script>

<svg width="800" height="200">
    <rect x="50" y="20" width="700" height="150"></rect>
</svg>
<br/>
<div>
    <table id="listTrain"></table>
</div>
<script>
    $(function() {
        station.init();
    });
</script>