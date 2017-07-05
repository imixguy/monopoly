<%--
  Created by IntelliJ IDEA.
  User: sedler
  Date: 20.12.14
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%--<meta http-equiv="X-UA-Compatible" content="IE=9,10,11">--%>
    <tiles:insertAttribute name="inHeader" />
    <title><tiles:getAsString name="titleKey"/></title>
    <%--<meta http-equiv="X-UA-Compatible" content="IE=Edge" />--%>
</head>
<body>
    <div id="context">
        <%--Импорт хидера--%>
        <tiles:insertAttribute name="header" />
        <%----------------------------%>
        <%--Импорт контекста--%>
        <tiles:insertAttribute name="content" />
        <%----------------------------%>
        <%--Импорт футтера--%>
        <%--<tiles:insertAttribute name="footer" />--%>
        <%----------------------------%>
    </div>
    <%----------------------------%>
</body>
</html>
