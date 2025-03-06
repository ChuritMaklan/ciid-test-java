<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category</title>
</head>
<body>
<h1>Edit Category</h1>
<form action="categories" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${category.id}">
    <label for="name">Category Name:</label>
    <input type="text" id="name" name="name" value="${category.name}" required>
    <br/>
    <input type="submit" value="Update">
</form>
<a href="${pageContext.request.contextPath}/categories">Back to List</a>
</body>
</html>
