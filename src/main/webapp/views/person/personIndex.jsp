<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>People List</title>
</head>
<body>
<h1>People List</h1>
<ul>
    <c:forEach var="person" items="${persons}">
        <li>
            <a href="${pageContext.request.contextPath}/persons?action=show&id=${person.id}">${person.name}</a>
        </li>
    </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}/persons?action=new">Add New Person</a>
</body>
</html>
