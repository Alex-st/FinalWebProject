<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/18/15
  Time: 1:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="resources.text" />

<html>
<head>
    <title><fmt:message key="tutorTitle"/></title>
</head>
<body>
<center>
  <fmt:message key="hello"/> "${user}"

  ${requestScope.result}

<c:if test="${not empty questions}" >
  <p><fmt:message key="listOfQuestion"/></p>
  <table>
    <tr>
      <th><fmt:message key="subject"/></th>
      <th><fmt:message key="question"/></th>
    </tr>
    <c:forEach var="entry" items="${questions}">
      <tr>
        <td>${entry.key}</td>
        <td>${entry.value}</td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<br><br>

    <c:import url="/menues/tutormenu.jsp"></c:import>

</center>
<%--<select name="qtopic">
  <c:forEach var="topic" items="${topics}">
    <option value="${topic.topicName}" name="opt">${topic.topicName}</option>
  </c:forEach>
</select><br>
<%= request.getAttribute("rtopics") %>
<%= request.getParameter("rtopics") %>--%>
</body>
</html>
