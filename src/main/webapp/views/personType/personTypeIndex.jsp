<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>People List</title>
</head>
<body>
<h1>People List</h1>
<ul>
    <c:forEach var="personType" items="${personTypes}">
        <li>
            <a href="${pageContext.request.contextPath}/person-types?action=show&id=${personType.id}">${personType.typeName}</a>
        </li>
    </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}/person-types?action=new">Add New Person</a>
</body>
</html>
