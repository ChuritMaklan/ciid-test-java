<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit Person</title>
</head>
<body>
<h1>Edit Person</h1>
<form action="persons" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${person.id}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${person.name}" required>
    <br/>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${person.email}" required>
    <br/>
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" value="${person.phone}">
    <br/>
    <label for="typeName">Type:</label>
    <select id="typeName" name="typeName" required>
        <c:forEach var="personType" items="${personTypes}">
            <option value="${personType.id}">${personType.typeName}</option>
        </c:forEach>
    </select>
    <br/>
    <input type="submit" value="Update">
</form>
<a href="${pageContext.request.contextPath}/persons">Back to List</a>
</body>
</html>
