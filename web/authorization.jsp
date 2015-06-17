<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/15/15
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <c:if test="${params['button']=='Student'}" ><title>Страница авторизации для студента</title></c:if>
  <c:if test="${params['button']=='Tutor'}" ><title>Страница авторизации для администратора</title></c:if>

</head>
<body>
<c:out value="${param.button}" />
<%= request.getParameter("button") %>

<c:if test="${params['button']=='Tutor'}" >Страница авторизации для администратора</c:if>
<c:if test="${params['button']=='Student'}" >Страница авторизации для студента</c:if>

<form action='authorization' method="post">
  <input type='text' name='login' /><br>
  <input type='text' name='password' /><br>
  <input type='submit' name='send' value='signAs<%= request.getParameter("button") %>' /><br>

</form>
</body>
</html>
