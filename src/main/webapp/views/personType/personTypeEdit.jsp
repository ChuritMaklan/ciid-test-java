<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit Person</title>
</head>
<body>
<h1>Edit Person</h1>
<form action="person-types" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${personType.id}">
    <label for="typeName">Name:</label>
    <input type="text" id="typeName" name="typeName" value="${personType.typeName}" required>
    <br/>
    <input type="submit" value="Update">
</form>
<a href="${pageContext.request.contextPath}/person-types">Back to List</a>
</body>
</html>
