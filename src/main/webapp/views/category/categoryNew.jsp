<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Add New Category</h1>
<form action="categories" method="post">
    <input type="hidden" name="action" value="create">
    <label for="name">Category Name:</label>
    <input type="text" id="name" name="name" required>
    <br/>
    <input type="submit" value="Create">
</form>
<a href="${pageContext.request.contextPath}/categories">Back to List</a>
</body>
</html>
