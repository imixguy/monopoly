<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="container_block cbname_centerPage">

    <c:choose>
        <c:when test="${centerPage.type == 'text'}">
            ${centerPage.content}
        </c:when>
        <c:when test="${centerPage.type == 'jsp'}">
            <jsp:include page="../../${centerPage.content}.jsp"/>
        </c:when>
        <c:when test="${centerPage.type == 'tiles'}">
            <c:catch var ="catchException">
                <tiles:insertDefinition name="${centerPage.content}" />
            </c:catch>
            <c:if test = "${catchException != null}">
                <jsp:include page="../../${centerPage.content}.jsp"/>
            </c:if>
        </c:when>
        <c:when test="${centerPage.type == 'page'}">
            <jsp:include page="${centerPage.content}"/>
        </c:when>
        <c:otherwise>
            Not past body !!!
            ${centerPage}
        </c:otherwise>
    </c:choose>

</div>