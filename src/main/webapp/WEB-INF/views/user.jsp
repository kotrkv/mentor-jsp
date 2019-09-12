<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User page</title>
    <style>
        <%@include file="/resources/css/signin.css"%>
    </style>
    <style>
        <%@include file="/resources/css/bootstrap.min.css"%>
    </style>
    <style>
        <%@include file="/resources/css/dashboard.css"%>
    </style>
    <style>
        <%@include file="/resources/css/modal.css"%>
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

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="/admin">Admin</a></li>
                <li class="active"><a href="#">User</a></li>
                <%--                <li><a href="/user">User</a></li>--%>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">User page</h1>
            <h3>Hello, ${user} </h3>
            <a href="#error" class="btn btn-block">Dialog</a>
        </div>
        <div id="error" class="modalbackground">
            <div class="modalwindow">
                <h3>Ошибка!</h3>
                <p>Проверьте введенные данные</p>
                <a href=" ">Закрыть</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
