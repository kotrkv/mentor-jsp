<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>--%>
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
    <style>
        <%@include file="/resources/css/dashboard.css"%>
    </style>

    <%--    <jsp:include page="/resources/js/bootstrap.js"/>--%>
    <%--    <jsp:include page="/resources/js/jquery-3.4.1.js"/>--%>
    <%--    <jsp:include  page="/resources/js/myscript.js"/>--%>

    <%--    <script src="<c:url value="/resources/js/myscript.js"/>"></script>--%>
    <!--Крах-->
    <%--    <script src="/resources/js/myscript.js"/>--%>
    <%--    <script type="text/javascript" src="/resources/js/myscript.js"/>--%>

    <%--    <script type="text/javascript">--%>
    <%--        <%@include file="/resources/js/bootstrap.js" %>--%>
    <%--    </script>--%>
    <%--    <script type="text/javascript">--%>
    <%--        <%@include file="/resources/js/myscript.js" %>--%>
    <%--    </script>--%>
    <%--<script>--%>
    <%--    function clickme() {--%>
    <%--        alert('Клик!!');--%>
    <%--    }--%>
    <%--</script>--%>
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
                <li class="active"><a href="#">Admin</a></li>
                <li><a href="/user">User</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Admin panel</h1>
            <h2 class="sub-header">Section title</h2>
<%--            <div class="table-responsive table-bordered">--%>
            <a href="/admin/addUser" class="link">Add user</a>
            <div class="table-responsive">
                <table class="table table-striped">
                    <tr>All users</tr>
                    <tr>
                        <th>ID</th>
                        <th>Role</th>
                        <th>Login</th>
                        <th>Password</th>
                        <th>Email</th>
                        <th>Edit</th>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>
                                <c:forEach var="role" items="${user.roles}">
                                    ${role.name}
                                </c:forEach>
                            </td>
                            <td>${user.login}</td>
                            <td>${user.password}</td>
                            <td>${user.email}</td>
<%--                            <td><a href="/admin/editUser?id=${user.id}">Edit</a></td>--%>
                            <td><a href="/admin/editUser?id=${user.id}" class="btn btn-info">Edit</a></td>
<%--                            <td><a href="/admin/deleteUser?id=${user.id}">Remove</a></td>--%>
                        </tr>
                    </c:forEach>
                </table>
                <!-- Кнопка, открывающее модальное окно -->
                <button id="btn" type="button" class="btn btn-success" data-toggle="modal" data-target="#modal-1">
                    Открыть модальное окно
                </button>
                <%--                <input value="Нажми меня" onclick="alert('Клик!')" type="button">--%>
                <input value="Нажми меня" onclick="clickme()" type="button">
            </div>
        </div>
    </div>
</div>
<!-- Модальное окно, основное содержимое которого организовано с использованием системы сеток Bootstrap 3 или 4 -->
<%--                <div id="gridSystemModal" class="modal fade" tabindex="-1" role="dialog"--%>
<div id="modal-1" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Modal window</h3>
            </div>
            <div class="modal-body">
                <p>This is modal window</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#btn").click(function () {
            $("#modal-1").modal('show');
        });
    });
</script>
</body>
</html>
