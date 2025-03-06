<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Categories List</title>
</head>
<body>
<h1>Categories List</h1>
<ul>
    <c:forEach var="category" items="${categories}">
        <li>
            <a href="${pageContext.request.contextPath}/categories?action=show&id=${category.id}">${category.name}</a>
        </li>
    </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}/categories?action=new">Add New Category</a>
</body>
</html>
