<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>All users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css"/>
</head>
<body>
<a href="/"><-- Home</a>
<h3>Users...</h3>
<table class="form-style-9">
    <tr><th><a href="/admin/addUser" calss="link">Add user</a></th></tr>
    <tr>
        <th>User login</th>
        <th>User password</th>
        <th>User email</th>
        <th>User role</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>
                <c:forEach var="role" items="${user.roles}">
                    ${role.name}
                </c:forEach>
            </td>
            <td><a href="/admin/editUser?id=${user.id}">Edit</a></td>
            <td><a href="/admin/deleteUser?id=${user.id}">Remove</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
