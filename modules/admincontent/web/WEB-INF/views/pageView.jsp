<%@ page import="rw.gcktc.cms.material.Page" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Page pageD= (Page) request.getAttribute("page");
%>
<div class="pagecontent">
   <%=pageD.getContent()%>
</div>
</br>