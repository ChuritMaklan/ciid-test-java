<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit Part</title>
</head>
<body>
<h1>Edit Part</h1>
<form action="parts" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${part.id}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${part.name}" required>
    <br/>
    <label for="supplierName">Supplier:</label>
    <select id="supplierName" name="supplierName" required>
        <c:forEach var="person" items="${persons}">
            <option value="${person.id}">${person.name}</option>
        </c:forEach>
    </select>
    <br/>
    <label for="price">Price:</label>
    <input type="number" id="price" name="price" value="${part.price}" required>
    <br/>
    <label for="quantity">Quantity in Stock:</label>
    <input type="number" id="quantity" name="quantity" value="${part.quantityInStock}" required>
    <br/>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" value="${part.description}">
    <br/>
    <input type="submit" value="Update">
</form>
<a href="${pageContext.request.contextPath}/parts">Back to List</a>
</body>
</html>
