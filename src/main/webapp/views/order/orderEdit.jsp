<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
        <title>Edit Order</title>
</head>
<body>
<h1>Edit Order</h1>
<form action="orders" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${order.id}">
        <label for="customerName">Supplier:</label>
        <select id="customerName" name="customerName" required>
                <c:forEach var="person" items="${persons}">
                        <option value="${person.id}">${person.name}</option>
                </c:forEach>
        </select>
        <br/>
        <label for="orderDate">Order Date:</label>
        <input type="date" id="orderDate" name="orderDate" value="${order.orderDate}" required>
        <br/>
        <input type="submit" value="Update">
</form>
<a href="${pageContext.request.contextPath}/orders">Back to List</a>
</body>
</html>
