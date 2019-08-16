<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>All users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" type="text/css"/>
</head>
<body>
<h3>Users...</h3>
<a href="index.html"><-- Home</a>
<table class="form-style-9">
    <tr>
        <th>User login</th>
        <th>User password</th>
        <th>User email</th>
        <th>User birthday</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>${user.birthday}</td>
            <td><a href="updateUser?id=${user.id}">Edit</a></td>
            <td><a href="deleteUser?id=${user.id}">Remove</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
