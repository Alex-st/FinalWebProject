<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/15/15
  Time: 8:44 PM
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
  <title><fmt:message key="title"/></title>
</head>
<body>
${requestScope.result}
<br>
<center>
  <form method="post" action="authorization.jsp" >
    <select name="locale">
      <option value="en" name="opt">English</option>
      <option value="ru" name="opt">Russian</option>
    </select><br>

    <button type="submit" name="button" value="Tutor" >
      <fmt:message key="signInAsTutor"/>
    </button><br>
    <button type="submit" name="button" value="Student">
      <fmt:message key="signInAsStudent"/>
    </button><br>
  </form>

  <form method="post" action="registration.jsp">
    <button type="submit" name = "button" value="Registration">
      <fmt:message key="registrationButton"/>
    </button>
  </form>


</center>
</body>
</html>
