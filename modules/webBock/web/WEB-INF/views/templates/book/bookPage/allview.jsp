<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: miha
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/WEB-INF/views/manager/cssTable.jsp"/>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <div>
        <table id="tableRole" class="rttable">
            <thead>
            <tr>
                <th>№</th>
                <th>Имя</th>
                <th>Описание</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${listRecrds}"  var="record">
               <tr>
                   <td><c:out value="${record.id}"/></td>
                   <td><c:out value="${record.title}"/></td>
                   <td><c:out value="${record.content}"/></td>
               </tr>
            </c:forEach >
            </tbody>
        </table>
    </div>
    <security:authorize access="hasRole('ROLE_CRUD_BOOK')">
        <div>
            <button>Удалить</button>
            <button>Добавить</button>
        </div>
    </security:authorize>
    <%--<div id="panSett">--%>
        <%--<input type='button' id='butAddNew' value='Добавить новый'/>--%>
        <%--<input type='button' id='butRemSel' value='Удалить отмеченные'/>--%>
    <%--</div>--%>

</div>
