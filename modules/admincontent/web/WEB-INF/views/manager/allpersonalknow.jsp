<%@ page import="rw.gcktc.cms.nodedata.Node" %>
<%@ page import="java.util.List" %>
<%@ page import="rw.gcktc.cms.nodedata.NodeProperty" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  Date: 09.09.2014
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/WEB-INF/views/manager/cssTable.jsp"/>
<div style="overflow: auto">
    <table class="rttable">
        <%
            List<Node> lNode=(List<Node>)request.getAttribute("listNode");
            String[] arrl={"firstName","lastName","java","hib","spring","libjava","maven","ant","othersborsh","csh","cpl","vc","delphi","assembler","php",
                    "javascript","jquery","ajax","libjs","otherLangPr","html","xml","tomcat","jboss","mysql","mssql","db2sql","wordPress","joomla","drupal","photoshop","msOffice"};
            String[] arr2={"Фамилия","Имя","java","hibernate","spring","Др.java lib","maven","ant","Др. сборщики java","C#","C++","VC","delphi","assembler","php",
                    "javascript","jquery","ajax","Др.lib js","Др.яз.прогр.","html","xml","tomcat","jboss","MySQL","MS SQL","db2sql","WordPress","joomla","drupal","photoshop","MS Office"};
        %>
        <tbody>
        <tr>
            <%
                for(String sd:arr2){
            %>
            <th>
                <%=sd%>
            </th>
            <%}%>
        </tr>
        </tbody>
        <%
            for(Node node:lNode){
        %>
        <tr>
            <%
                int il=0;
                for(String sd:arrl){
                    boolean keyV=false;
                    for(NodeProperty nodePr:node.getNodeProperties()){
                        if(nodePr.getKeyt().equals(sd)){
                            keyV =true;
                            if(il<2){
            %>
            <td>
                <a href="/user/content/<%=node.getId()%>/viewpersonalknow.html"> <%=nodePr.getValue()%></a>
            </td>
            <%}else{ %>
            <td class="cntr">
                <%=nodePr.getValue()%>
            </td>
            <%}
            }}
                il+=1;
                if(!keyV){
            %>
            <td></td>
            <%}}%>
        </tr>
        <%}%>
    </table>
</div>
<br/><br/>
<div style="overflow: auto">
    <table class="rttable">
        <%
            String[] arr3={"firstName","lastName","javaCh","hibCh","springCh","libjavaCh","mavenCh","antCh","othersborshCh","cshCh","cplCh","vcCh","delphiCh","assemblerCh","phpCh",
                    "javascriptCh","jqueryCh","ajaxCh","libjsCh","otherLangPrCh","htmlCh","xmlCh","tomcatCh","jbossCh","mysqlCh","mssqlCh","db2sqlCh","wordPressCh","joomlaCh","drupalCh","photoshopCh","msOfficeCh"};
            String[] arr4={"Фамилия","Имя","java","hibernate","spring","Др.java lib","maven","ant","Др. сборщики java","C#","C++","VC","delphi","assembler","php",
                    "javascript","jquery","ajax","Др.lib js","Др.яз.прогр.","html","xml","tomcat","jboss","MySQL","MS SQL","db2sql","WordPress","joomla","drupal","photoshop","MS Office"};
        %>
        <tbody>
        <tr>
            <%
                for(String sd:arr4){
            %>
            <th>
                <%=sd%>
            </th>
            <%}%>
        </tr>
        </tbody>
        <%
            for(Node node:lNode){
        %>
        <tr>
            <%
                int il=0;
                for(String sd:arr3){
                    boolean keyV=false;
                    for(NodeProperty nodePr:node.getNodeProperties()){
                        if(nodePr.getKeyt().equals(sd)){
                            keyV =true;
                            if(il<2){
            %>
            <td>
                <a href="/user/content/<%=node.getId()%>/viewpersonalknow.html"> <%=nodePr.getValue()%></a>
            </td>
            <%}else{ %>
            <td  class="cntr">
                <%if(nodePr.getValue().equals("true")){%>
                √
                <%}else{%>
                -
                <%}%>
            </td>
            <%}
            }}
                il+=1;
                if(!keyV){
            %>
            <td></td>
            <%}}%>
        </tr>
        <%}%>
    </table>
</div>