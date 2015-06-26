<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/18/15
  Time: 1:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="/WEB-INF/mytaglib.tld" prefix ="myjsp" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="resources.text" />

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <title><fmt:message key="studentTitle"/></title>
</head>
<body>
  <center>
    <div id="wrapper">
    <fmt:message key="studentTitle"/><br/>

  <%--  <fmt:message key="hello"/> ${user}<br> --%>

    <h1> hidden text </h1><br>


    ${requestScope.result}<br>
      <div id="register">
        <myjsp:hello uname=" ${user}"/>
    <c:if test="${not empty results}" >
      <table>
        <tr>
          <th><fmt:message key="subject"/></th>
          <th><fmt:message key="mark"/></th>
        </tr>
        <c:forEach var="entry" items="${results}">
        <tr>
          <td>${entry.key}</td>
          <td>${entry.value}</td>
        </tr>
        </c:forEach>
      </table>
    </c:if>
    <br><br>
    <p><fmt:message key="passNewTest"/></p><br>
    <form method="post" action="/tutorpage?send=exam" >
      <c:forEach var="topic" items="${topics}">
        <button type="submit" name="test" value="${topic.topicName}" >${topic.topicName}
        </button><br>
      </c:forEach>
    </form>
        <c:import url="/menues/studentmenu.jsp"></c:import>
        </div>


      </div>
    </center>
</body>
</html>
