<!--#ece8e8-->
<!--#f5f5f5-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin panel</title>

    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/dashboard.css"/>" rel="stylesheet">

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
            <!-- Панель вкладок-->
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#panel1">Users table</a></li>
                <li><a data-toggle="tab" href="#panel2">New user</a></li>
            </ul>
            <!-- All users -->
            <div class="tab-content">
                <div id="panel1" class="tab-pane fade in active">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">All users</h3>
                        </div>
                        <div class="panel-body">
<%--                            <div class="table-responsive">--%>
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Role</th>
                                        <th>Login</th>
                                        <th>Password</th>
                                        <th>Email</th>
                                        <th>Edit</th>
                                    </tr>
                                    </thead>
                                    <c:forEach var="user" items="${users}">
                                        <tbody>
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
                                            <td><a href="#${user.id}" class="btn btn-primary"
                                                   data-toggle="modal">Edit</a>
                                            </td>
                                        </tr>
                                        </tbody>
                                        <div id="${user.id}" class="modal fade">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-hidden="true">
                                                            ×
                                                        </button>
                                                        <h4 class="modal-title">Edit user ...</h4>
                                                    </div>
                                                    <form action="/admin/editUser" method="post">
                                                        <div class="modal-body">
                                                            <div class="form-group">
                                                                <label for="id">ID</label>
                                                                <input name="id" type="text" class="form-control"
                                                                       id="id"
                                                                       value="${user.id}" readonly>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="email">Email</label>
                                                                <input name="email" type="text" class="form-control"
                                                                       id="email"
                                                                       value="${user.email}">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="login">Login</label>
                                                                <input name="login" type="text" class="form-control"
                                                                       id="login"
                                                                       value="${user.login}">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="password">Password</label>
                                                                <input name="password" type="password"
                                                                       class="form-control"
                                                                       id="password"
                                                                       value="${user.password}">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="role">Role</label>
                                                                <select id="role" name="roleName"
                                                                        class="form-control">
                                                                    <c:forEach var="roles" items="${roles}">
                                                                        <option value="${roles.name}">${roles.name}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-"
                                                                        data-dismiss="modal">
                                                                    Cancel
                                                                </button>
                                                                <button type="submit" class="btn btn-primary">Save
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </table>
<%--                            </div>--%>
                        </div>
                    </div>
                </div>
                <!-- Add new user-->
                <div id="panel2" class="tab-pane fade">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Add new user</h3>
                        </div>
                        <div class="panel-body">
                            <form action="/admin/addUser" method="post" class="form-style-9" autocomplete="off">
                                <div class="form-group">
                                    <label for="Email">Email</label>
                                    <input name="Email" type="text"
                                           class="form-control"
                                           id="email"
                                           placeholder="Enter email">
                                </div>
                                <div class="form-group">
                                    <label for="login">Login</label>
                                    <input name="login" type="text" class="form-control"
                                           id="login"
                                           placeholder="Login">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input name="password" type="password" class="form-control"
                                           id="password"
                                           placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <label for="role">Role</label>
                                    <select id="role" name="roleName" class="form-control">
                                        <c:forEach var="roles" items="${roles}">
                                            <option value="${roles.name}" class="center-block">${roles.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-success center-block">Add new user</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>

</body>
</html>
