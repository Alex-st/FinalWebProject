<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/18/15
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Создание нового вопроса</title>
</head>
<body>
<center>
  <br>
  Dear ${user}<br>
  Please write your question<br>
<form action='newquestion' method="post">
  <select name="qtopic">
  <c:forEach var="topic" items="${topics}">
    <option value="${topic.topicName}" name="opt">${topic.topicName}</option>
  </c:forEach>
  </select><br>
  Введите текст вопроса
  <input type='text' name='qtext' value=''/><br>
  Введите корректный ответ
  <input type='text' name='correctanswer' value=''/><br>
  Введите второй вариант ответа
  <input type="text" name='email' value=''/><br>
  Введите третий вариант ответа
  <input type='text' name='login' value=''/><br>
  Введите четвертый вариант ответа
  <input type='text' name='login' value=''/><br>
  Введите пятый вариант ответа
  <input type='text' name='login' value=''/><br>

  <input type = "reset" name="Reset" value="Reset">
  <input type="submit" name="send" value="createquestion"/>
</form>
</center>
</body>
</html>
