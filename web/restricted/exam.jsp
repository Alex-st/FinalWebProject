<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/19/15
  Time: 2:01 PM
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
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <title>exam</title>
</head>
<body>


  <div id="wrapper">

    <div id="register">
  <form name="questionForm" action="tutorpage">
    <p><b><center>${curQText}</center></b></p>
    <center>
    <c:forEach var="ans" items="${answers}">
      <p class="keeplogin"><input type="radio" name="answer" value="${ans}" id="${ans}"><label for="${ans}">${ans}</label></p>
    </c:forEach>

    <button type="submit" name="send" value="exam"><fmt:message key="answerQuestion"/></button>
        </center>
  </form>
      <c:import url="/menues/studentmenu.jsp"></c:import>
      </div>


    </div>

</body>
</html>
