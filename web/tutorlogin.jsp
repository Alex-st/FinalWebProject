<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/18/15
  Time: 1:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tutor's admin page</title>
</head>
<body>
Hello Dear "${user}"

<c:if test="${not empty questions}" >
  <p>Список Ваших вопросов</p>>
  <table>
    <tr>
      <th>Предмет</th>
      <th>Вопрос</th>
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

<form method="post" action="newquestion.jsp" >
    <button type="submit" name="button" value="newquestion" >Создать новый вопрос
    </button><br>
</form>
</center>
</body>
</html>
