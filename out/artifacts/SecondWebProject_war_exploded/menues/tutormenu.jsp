<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/18/15
  Time: 9:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="resources.text" />

<ul class="makeMenu">
  <li><a href="restricted/newquestion.jsp"><fmt:message key="createQuestion"/></a>
  </li>
 <%-- <li><a href="restricted/personaldata.jsp"><fmt:message key="modifyPersonalData"/></a>
  </li>--%>
  <li><a href="/tutorpage?send=exit"><fmt:message key="exitbutton"/></a>
  </li>
 </ul>

