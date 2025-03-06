<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Person Details</title>
</head>
<body>
<h1>Person Details</h1>
<p>Name: ${person.name}</p>
<p>Email: ${person.email}</p>
<p>Phone: ${person.phone}</p>
<p>Type: ${personType.typeName}</p>
<a href="${pageContext.request.contextPath}/persons?action=edit&id=${person.id}">Edit</a>
<a href="${pageContext.request.contextPath}/persons?action=delete&id=${person.id}">Delete</a>
<a href="${pageContext.request.contextPath}/persons">Back to List</a>
</body>
</html>
