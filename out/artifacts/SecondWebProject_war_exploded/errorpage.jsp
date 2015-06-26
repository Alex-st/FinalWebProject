<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/25/15
  Time: 10:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <title>error page</title>
</head>
<body>
<div id="wrapper">
  <center>
<h1> Error has occured  </h1>
  <p>

    <b>The status code is:</b> <%= request.getAttribute("javax.servlet.error.status_code") %><br>

    <b>The error message again is:</b> <%= request.getAttribute("javax.servlet.error.message") %><br>

  </p>
  <a href="index.jsp">Return to main</a>
    </center>
  </div>
</body>
</html>
