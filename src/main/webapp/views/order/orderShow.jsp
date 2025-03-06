<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order Details</title>
</head>
<body>
<h1>Order Details</h1>
<p>Customer: ${customer.name}</p>
<p>Order Date: ${order.orderDate}</p>
<p>Ordered parts</p>
<table border="1">
    <tr>
        <th>Part name</th>
        <th>Quantity</th>
        <th>Price</th>
    </tr>
    <!-- Loop through the list of objects -->
    <c:forEach var="orderItem" items="${orderItems}">
        <tr>
            <td>${partsMap[orderItem.partId].name}</td>
            <td>${orderItem.price}</td>
            <td>${orderItem.quantity}</td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/orders?action=edit&id=${order.id}">Edit</a>
<a href="${pageContext.request.contextPath}/orders?action=delete&id=${order.id}">Delete</a>
<a href="${pageContext.request.contextPath}/orders">Back to List</a>
</body>
</html>
