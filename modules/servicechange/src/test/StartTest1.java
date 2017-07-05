package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by miha on 03.10.2014.
 */
public class StartTest1 {
    public static void main(String[] str){
        String strs="skdljfsa;kj html sadfasdfj;kjdiv;lsjkdf;k";
        String sdf="<%@ page import=\"org.springframework.context.i18n.LocaleContextHolder\" %>\n" +
                "<%@ page import=\"rw.gcktc.webcms.form.NodeView\" %>\n" +
                "<%@ page import=\"java.util.Locale\" %>\n" +
                "<%--\n" +
                "  Created by IntelliJ IDEA.\n" +
                "  User: miha\n" +
                "  Date: 28.01.13\n" +
                "  Time: 11:48\n" +
                "  To change this template use File | Settings | File Templates.\n" +
                "--%>\n" +
                "<%@ page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>\n" +
                "<%--<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>--%>\n" +
                "<%@ taglib prefix=\"tiles\" uri=\"http://tiles.apache.org/tags-tiles\" %>\n" +
                "<%@ taglib prefix=\"spring\" uri=\"http://www.springframework.org/tags\" %>\n" +
                "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n" +
                "<%--<%@ taglib uri=\"http://struts.apache.org/tags-titles\" prefix=\"titles\"%>--%>\n" +
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n" +
                "\"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                "\n" +
                "    <title>\n" +
                "        <%--Если есть атрибут titlePage - то вставляется он иначе вставляется атрибут titleKey --%>\n" +
                "        <c:choose>\n" +
                "            <c:when test=\"${titlePage!=null}\">\n" +
                "                ${titlePage}\n" +
                "            </c:when>\n" +
                "            <c:otherwise>\n" +
                "                <c:set var=\"titleKey\"><tiles:getAsString name=\"titleKey\"/></c:set>\n" +
                "                <spring:message code=\"${titleKey}\" />\n" +
                "            </c:otherwise>\n" +
                "        </c:choose>\n" +
                "    </title>\n" +
                "\t<!--# javascript #-->\n" +
                "</head>\n" +
                "<body marginwidth=\"0\" marginheight=\"0\"  leftmargin=\"0\" topmargin=\"0\" class=\"bodyCl\">\n" +
                "<div>\n" +
                "\t<!--# javascript2 #-->\n" +
                "    <tiles:insertAttribute name=\"header\" />\n" +
                "</div>\n" +
                "<div>\n" +
                "    <%--Блоки входа пользователя и другие--%>\n" +
                "    <c:set var=\"nameBlock\"><spring:message code=\"programm.userauth\" /></c:set>\n" +
                "    <%\n" +
                "        Locale l=LocaleContextHolder.getLocale();\n" +
                "        NodeView n = new NodeView();\n" +
                "        n.setTitles(\"authblock.jsp\");\n" +
                "        n.setName((String) pageContext.getAttribute(\"nameBlock\"));\n" +
                "        request.setAttribute(\"CURENT_NODEA\", n);\n" +
                "    %>\n" +
                "    <jsp:include page=\"../../template/blockview.jsp\"/>\n" +
                "</div>\n" +
                "\tsafasfasf<!--# javascript3 #-->sdfsdf sdf \n" +
                "<div>\n" +
                "    <%--Ошибки системы--%>\n" +
                "    <jsp:include page=\"../../template/error.jsp\"/>\n" +
                "    <%--Центральная страница--%>\n" +
                "    <tiles:insertAttribute name=\"content\" />\n" +
                "</div>\n" +
                "<div>\n" +
                "    <tiles:insertAttribute name=\"footer\" />\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        Pattern p = Pattern.compile("<\\!--#.+#-->",Pattern.MULTILINE);
        Matcher m =  p.matcher(sdf);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
