<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/15/15
  Time: 8:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Wellcome to Alex's quize site</title>
</head>
<body>
${requestScope.result}
<br>
<center>
  <form method="post" action="authorization.jsp" >
    <button type="submit" name="button" value="Tutor" >
      Войти как администратор
    </button><br>
    <button type="submit" name="button" value="Student">
      Войти как студент
    </button><br>
  </form>
  <form method="post" action="registration.jsp">
    <button type="submit" name = "button" value="Registration">
      Зарегистрироваться
    </button>
  </form>
</center>
</body>
</html>
