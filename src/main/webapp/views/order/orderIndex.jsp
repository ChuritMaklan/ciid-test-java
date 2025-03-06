<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders List</title>
</head>
<body>
<h1>Orders List</h1>
<ul>
    <c:forEach var="order" items="${orders}">
        <li>
            <a href="${pageContext.request.contextPath}/orders?action=show&id=${order.id}">${order.orderDate} Order: ${order.id}</a>
        </li>
    </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}/orders?action=new">Add New Order</a>
</body>
</html>
