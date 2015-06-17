<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/17/15
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
<center>
<form action='authorization' method="post">
  <select name="select">
    <option value="student" name="opt">Зарегистрироваться как студент</option>
    <option value="tutor" name="opt">Зарегистрироваться как администратор</option>
  </select><br>
  Имя
  <input type='text' name='name' value=''/><br>
  Фамилия
  <input type='text' name='surname' value=''/><br>
  Емейл
  <input type="text" name='email' value=''/><br>
  Логин
  <input type='text' name='login' value=''/><br>
  Пароль
  <input type="password" name='password' value=''/><br>

  <input type = "reset" name="Reset" value="Reset">
  <input type="submit" name="send" value="register"/>
</form>
</center>
</body>
</html>
