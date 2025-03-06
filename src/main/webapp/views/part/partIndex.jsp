<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Parts List</title>
</head>
<body>
<h1>Parts List</h1>
<ul>
    <c:forEach var="part" items="${parts}">
        <li>
            <a href="${pageContext.request.contextPath}/parts?action=show&id=${part.id}">${part.name}</a>
        </li>
    </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}/parts?action=new">Add New Part</a>
</body>
</html>
