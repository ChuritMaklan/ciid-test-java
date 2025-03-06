<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add New Order</title>
</head>
<body>
<h1>Add New Order</h1>
<form action="orders" method="post">
    <input type="hidden" name="action" value="create">
    <label for="customerName">Person:</label>
    <select id="customerName" name="customerName" required>
        <c:forEach var="person" items="${persons}">
            <option value="${person.id}">${person.name}</option>
        </c:forEach>
    </select>
    <br/>
    <label for="orderDate">Order Date:</label>
    <input type="date" id="orderDate" name="orderDate" required>
    <br/>
    <table border="1">
        <tr>
            <th>Select</th>
            <th>Name</th>
            <th>Quantity</th>
        </tr>
        <!-- Loop through the list of objects -->
        <c:forEach var="part" items="${parts}">
            <tr>
                <td><input type="checkbox" name="selectedItems" value="${part.id}"></td>
                <td>${part.name}</td>
                <td>
                    <input type="number" name="quantities" min="0" value="0">
                </td>
            </tr>
        </c:forEach>
        </table>
        <br>
        <input type="submit" value="Create order">
</form>
<a href="${pageContext.request.contextPath}/orders">Back to List</a>
</body>
</html>
