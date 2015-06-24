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
  <link rel="stylesheet" type="text/css" href="css/style.css" />

  <c:if test="${param.button=='Student'}" ><title><fmt:message key="authStudentPage"/></title></c:if>
  <c:if test="${param.button=='Tutor'}" ><title><fmt:message key="authTutorPage"/></title></c:if>

</head>
<body>
<br>
<br>
<div id="wrapper">
  <div id="login">
    <p>
    <h1>
    <fmt:message key="authButton"/> ${param.button}
    <c:if test="${param.button=='Student'}" ><title><fmt:message key="authStudentPage"/></title></c:if>
    <c:if test="${param.button=='Tutor'}" ><title><fmt:message key="authTutorPage"/></title></c:if>
    </h1>
    </p>
    <form action='authorization' method="post">
      <p>
        <label for="blogin" class="uname" data-icon="u" ><fmt:message key="loginButton"/></label>
        <input id="blogin" type='text' name='login' /><br>
      </p>
      <p>
        <label for="password" class="youpasswd" data-icon="p"><fmt:message key="passButton"/></label>
        <input id="password" type='password' name='password' /><br>
      </p>
      <p class="login button">
        <button type='submit' name='send' value='signAs<%= request.getParameter("button") %>'><fmt:message key="authButton"/></button><br>
      </p>
  </form>
  </div>
</div>

</body>
</html>
