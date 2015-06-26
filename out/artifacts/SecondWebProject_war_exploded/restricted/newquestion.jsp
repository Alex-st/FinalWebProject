<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/18/15
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="resources.text" />

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <title><fmt:message key="questionTitle"/></title>
</head>
<body>
  <div id="wrapper">

  <br>
  ${locale}<br>
  <fmt:message key="hello"/> ${user}<br>
  <fmt:message key="writeQuestion"/><br>

<form action='tutorpage' method="post">

  <select name="qtopic">
  <c:forEach var="topic" items="${topics}">
    <option value="${topic.topicName}" name="opt">${topic.topicName}</option>
  </c:forEach>
  </select><br>

  <fmt:message key="inputQuestion"/>
  <input type='text' name='qtext' value=''/><br>
  <fmt:message key="inputCorrectAnswer"/>
  <input type='text' name='correctanswer' value=''/><br>
  <fmt:message key="inputSecondAnswer"/>
  <input type="text" name='q2' value=''/><br>
  <fmt:message key="inputThirdAnswer"/>
  <input type='text' name='q3' value=''/><br>
  <fmt:message key="inputFourthAnswer"/>
  <input type='text' name='q4' value=''/><br>
  <fmt:message key="inputFifthAnswer"/>
  <input type='text' name='q5' value=''/><br>

  <button type = "reset" name="Reset" value="reset"><fmt:message key="reset"/></button>
  <button type="submit" name="send" value="newquestion"><fmt:message key="addQuestion"/></button>
</form>
      <c:import url="/menues/tutormenu.jsp"></c:import>

    </div>

</body>
</html>
