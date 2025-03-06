<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Person type Details</h1>
<p>Type name: ${personType.typeName}</p>
<a href="${pageContext.request.contextPath}/person-types?action=edit&id=${personType.id}">Edit</a>
<a href="${pageContext.request.contextPath}/person-types?action=delete&id=${personType.id}">Delete</a>
<a href="${pageContext.request.contextPath}/person-types">Back to List</a>
</body>
</html>
