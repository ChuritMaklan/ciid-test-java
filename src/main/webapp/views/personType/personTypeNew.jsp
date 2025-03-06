<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add New Person</title>
</head>
<body>
<h1>Add New Person Type</h1>
<form action="person-types" method="post">
    <input type="hidden" name="action" value="create">

    <br/>
    <label for="typeName">Type name:</label>
    <input type="text" id="typeName" name="typeName" required>
    <br/>
    <input type="submit" value="Create">
</form>
<a href="${pageContext.request.contextPath}/person-types">Back to List</a>
</body>
</html>
