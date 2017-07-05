<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 10.09.2014
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    table.rttable {
        overflow:hidden;
        border:1px solid #d3d3d3;
        width:100%;
        -moz-border-radius:5px; /* FF1+ */
        -webkit-border-radius:5px; /* Saf3-4 */
        border-radius:5px;
        -moz-box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
        -webkit-box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
    }

    .rttable th, .rttable td {
        padding:4px;
        /*text-align:center;*/
    }

    .rttable th {
        padding-top:6px;
        text-shadow: 1px 1px 1px #fff;
        background:#e8eaeb;
    }

    .rttable td {
        border-top:1px solid #e0e0e0;
        border-right:1px solid #e0e0e0;
    }

    .rttable tr.odd-row td {
        background:#f6f6f6;
    }

    .rttable td.first, th.first {
        text-align:left
    }

    .rttable td.last {
        border-right:none;
    }

    .rttable td {
        background: -moz-linear-gradient(100% 25% 90deg, #fefefe, #f9f9f9);
        background: -webkit-gradient(linear, 0% 0%, 0% 25%, from(#f9f9f9), to(#fefefe));
    }

    .rttable tr.odd-row td {
        background: -moz-linear-gradient(100% 25% 90deg, #f6f6f6, #f1f1f1);
        background: -webkit-gradient(linear, 0% 0%, 0% 25%, from(#f1f1f1), to(#f6f6f6));
    }

    .rttable th {
        background: -moz-linear-gradient(100% 20% 90deg, #e8eaeb, #ededed);
        background: -webkit-gradient(linear, 0% 0%, 0% 20%, from(#ededed), to(#e8eaeb));
    }

    .rttable tr:first-child th.first {
        -moz-border-radius-topleft:5px;
        -webkit-border-top-left-radius:5px; /* Saf3-4 */
    }

    .rttable tr:first-child th.last {
        -moz-border-radius-topright:5px;
        -webkit-border-top-right-radius:5px; /* Saf3-4 */
    }

    .rttable tr:last-child td.first {
        -moz-border-radius-bottomleft:5px;
        -webkit-border-bottom-left-radius:5px; /* Saf3-4 */
    }

    .rttable tr:last-child td.last {
        -moz-border-radius-bottomright:5px;
        -webkit-border-bottom-right-radius:5px; /* Saf3-4 */
    }
    .rttable .cntr{
        text-align: center;
    }
</style>
