<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/18/15
  Time: 1:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student quiz page</title>
</head>
<body>
  <center>
    Hello Dear ${user}<br>
    <c:if test="${not empty results}" >
      <table>
        <tr>
          <th>Предмет</th>
          <th>Оценка</th>
        </tr>
        <c:forEach var="entry" items="${results}">
        <tr>
          <td>${entry.key}</td>
          <td>${entry.value}</td>
        </tr>
        </c:forEach>
      </table>
    </c:if>
    <br><br>
    <p>Сдать новый тест</p><br>
    <form method="post" action="/exam" >
      <c:forEach var="topic" items="${topics}">
        <button type="submit" name="button" value="${topic.topicName}" >${topic.topicName}
        </button><br>
      </c:forEach>
    </form>
    </center>
</body>
</html>
