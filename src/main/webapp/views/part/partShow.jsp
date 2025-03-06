<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Part Details</title>
</head>
<body>
<h1>Part Details</h1>
<p>Name: ${part.name}</p>
<p>Supplier: ${supplier.name}</p>
<p>Price: ${part.price}</p>
<p>Quantity in Stock: ${part.quantityInStock}</p>
<p>Description: ${part.description}</p>
<a href="${pageContext.request.contextPath}/parts?action=edit&id=${part.id}">Edit</a>
<a href="${pageContext.request.contextPath}/parts?action=delete&id=${part.id}">Delete</a>
<a href="${pageContext.request.contextPath}/parts">Back to List</a>
</body>
</html>
