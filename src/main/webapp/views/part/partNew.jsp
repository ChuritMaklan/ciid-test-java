<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add New Part</title>
</head>
<body>
<h1>Add New Part</h1>
<form action="parts" method="post">
    <input type="hidden" name="action" value="create">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>
    <br/>
    <label for="supplierName">Supplier:</label>
    <select id="supplierName" name="supplierName" required>
        <c:forEach var="person" items="${persons}">
            <option value="${person.id}">${person.name}</option>
        </c:forEach>
    </select>
    <br/>
    <label for="price">Price:</label>
    <input type="number" id="price" name="price" required>
    <br/>
    <label for="quantity">Quantity</label>
    <input type="number" id="quantity" name="quantity" required>
    <br/>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description">
    <br/>
    <p>Select categories</p>>
    <table border="1">
        <tr>
            <th>Select</th>
            <th>Name</th>
        </tr>
        <!-- Loop through the list of objects -->
        <c:forEach var="category" items="${categories}">
            <tr>
                <td><input type="checkbox" name="selectedItems" value="${category.id}"></td>
                <td>${category.name}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Create">
</form>
<a href="${pageContext.request.contextPath}/parts">Back to List</a>
</body>
</html>
