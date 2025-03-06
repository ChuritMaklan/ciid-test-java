<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Category Details</title>
</head>
<body>
<h1>Category Details</h1>
<p>Name: ${category.name}</p>
<p>Parts in category</p>
<ul>
  <c:forEach var="part" items="${parts}">
    <li>
      <a href="${pageContext.request.contextPath}/parts?action=show&id=${part.id}">${part.name}</a>
    </li>
  </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}/categories?action=edit&id=${category.id}">Edit</a>
<a href="${pageContext.request.contextPath}/categories?action=delete&id=${category.id}">Delete</a>
<a href="${pageContext.request.contextPath}/categories">Back to List</a>
</body>
</html>
