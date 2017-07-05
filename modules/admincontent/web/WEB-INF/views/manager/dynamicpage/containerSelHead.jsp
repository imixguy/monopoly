<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:if test="${dynContent.getContainerByName(nameContainer)!=null}">
        <c:set var="oldContainer" scope="page" value="${container}"/>
        <c:set var="container" scope="request" value="${dynContent.getContainerByName(nameContainer)}"/>

        <c:choose>
            <c:when test="${container.type == 'text'}">
                ${container.content}
            </c:when>
            <c:when test="${container.type == 'jsp'}">
                <jsp:include page="${container.content}"/>
            </c:when>
            <c:when test="${container.type == 'container'}">
                <c:set var="oldDynContent" scope="page" value="${dynContent}"/>
                <c:set var="dynContent" scope="request" value="${dynContent.getDynamicContent(container.content)}"/>
                <jsp:include page="/WEB-INF/views/templates/dynamictemplates/template_id_${dynContent.shablonName}.jsp"/>
                <c:set var="dynContent" scope="request" value="${oldDynContent}"/>
            </c:when>
            <c:when test="${container.type == 'tiles'}">
                <tiles:insertDefinition name="${container.content}" />
            </c:when>
            <c:when test="${container.type == 'page'}">
                <jsp:include page="${container.content}"/>
            </c:when>
            <c:otherwise>
                Not past body !!!
                ${container}
            </c:otherwise>
        </c:choose>
        <c:set var="container" scope="request" value="${oldContainer}"/>
</c:if>