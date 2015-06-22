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
<%--<form method="post" action="/tutorpage" >
  <button type="submit" name="button" value="newquestion" >
    Создать новый вопрос
  </button><br>
  <button type="submit" name="button" value="modifypersonal">
    Редактировать учетную запись
  </button><br>
  <button type="submit" name="button" value="exit">
    Выход
  </button><br>
</form>--%>

<ul class="makeMenu">
  <li><a href="newquestion.jsp"><fmt:message key="createQuestion"/></a>
  </li>
  <li><a href="personaldata.jsp"><fmt:message key="modifyPersonalData"/></a>
  </li>
  <li><a href="/tutorpage?send=exit"><fmt:message key="exitbutton"/></a>
  </li>
 </ul>

