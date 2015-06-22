<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/15/15
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="locale" value="${param.locale}" scope="session"  />

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="resources.text" />

<html>
<head>
  <c:if test="${param.button=='Student'}" ><title><fmt:message key="authStudentPage"/></title></c:if>
  <c:if test="${param.button=='Tutor'}" ><title><fmt:message key="authTutorPage"/></title></c:if>

</head>
<body>
<br>
<br>
<center>
  <c:if test="${param.button=='Student'}" ><title><fmt:message key="authStudentPage"/></title></c:if>
  <c:if test="${param.button=='Tutor'}" ><title><fmt:message key="authTutorPage"/></title></c:if>

<form action='authorization' method="post">
  <fmt:message key="loginButton"/><input type='text' name='login' /><br>
  <fmt:message key="passButton"/><input type='password' name='password' /><br>
  <button type='submit' name='send' value='signAs<%= request.getParameter("button") %>'><fmt:message key="authButton"/></button><br>

</form>
</center>
</body>
</html>
