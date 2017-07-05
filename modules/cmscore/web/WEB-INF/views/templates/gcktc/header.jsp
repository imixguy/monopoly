<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<div style="height:20px;"></div><div style="height:120px; background-color:#003988;"></div><div style="height:5px;"></div>
<div style="height:20px;background-color:#003988;"></div>
<div style="height:20px; z-index:2;position:absolute;left:0;top:143px; width:100%;" id="menu"><jsp:include page="../../template/menu.jsp"/>
  <span style="float: right;" id="localP">
      <div>
          <style>#localP{color:white;} #localP a:link {color: #e7f4f9;} #localP a:visited {color: white;} #localP a:active {color: #999999;} #localP a:hover{color:#e7f4f9}</style>
          <a href="?lang=ru">ру</a> | <a href="?lang=by">бел</a>&nbsp;&nbsp;&nbsp;&nbsp;
      </div>
  </span>
</div>
<div style="height:10px;" ></div>
<div style="z-index:1;position:absolute;left:0;top:10px; width:100%;"><div align="right"><img src="/resources/images/vagon.png" height="165px" alt="<spring:message code="programm.ksupr"/>"/></div></div>
<div style="z-index:1;position:absolute;left:0;top:30px; width:100%;"><div style="padding-left:50px;"><img src="/resources/images/tytlesys_<spring:message code="local"/>.png" height="100px" alt="programm.ksupr"/></div></div>


