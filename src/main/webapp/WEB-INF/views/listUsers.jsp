<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin panel</title>
    <style>
        <%@include file="/resources/css/signin.css"%>
    </style>
    <style>
        <%@include file="/resources/css/bootstrap.min.css"%>
    </style>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/perform_logout" />">Logout</a></li>
            </ul>
        </div>
    </div>
</div>
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
<a href="<c:url value="/perform_logout" />">Logout</a>
</body>
</html>
