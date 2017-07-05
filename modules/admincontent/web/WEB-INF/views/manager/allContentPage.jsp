<%@ page import="rw.gcktc.cms.nodedata.Node" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 04.09.2014
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <%
        List<Node> listNode= (List<Node>) request.getAttribute("listNode");
        for (Node node:listNode){
    %>
        <div><%=node.getId()%> | <%=node.getClass().getName()%></div>
    <%}%>
</div>
