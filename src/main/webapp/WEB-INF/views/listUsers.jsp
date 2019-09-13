<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin panel</title>

    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/dashboard.css"/>" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/resources/js/myscript.js"></script>

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
                <div class="panel panel-primary">
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
                </div>
                <p><a href="#myModal1" class="btn btn-primary" data-toggle="modal">Открыть модальное окно 1</a></p>
                <div id="myModal1" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h4 class="modal-title">Заголовок модального окна 1</h4>
                            </div>
                            <div class="modal-body">
                                Содержимое модального окна 1...
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                                <button type="button" class="btn btn-primary">Сохранить изменения</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Кнопка, открывающее модальное окно -->
                <input value="Нажми меня" onclick="clickme()" type="button">

            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>

</body>
</html>
