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
            <!-- Панель вкладок-->
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#panel1">Users table</a></li>
                <li><a data-toggle="tab" href="#panel2">New user</a></li>
            </ul>

            <div class="tab-content">
                <div id="panel1" class="tab-pane fade in active">
                    <%--                    <h3>All users</h3>--%>
                    <div class="panel-heading">
                        <h3 class="panel-title">Панель с h3 заголовком</h3>
                    </div>
                    <div class="table-responsive">
                        <div class="panel panel-primary">
                            <table class="table table-striped">
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
                                            <%--                                <td><a href="/admin/editUser?id=${user.id}" class="btn btn-info">Edit</a></td>--%>
                                        <td><a href="#${user.id}" class="btn btn-primary" data-toggle="modal">Edit</a>
                                        </td>
                                            <%--                            <td><a href="/admin/deleteUser?id=${user.id}">Remove</a></td>--%>
                                    </tr>

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
                                                            <label for="id">Email</label>
                                                            <input name="id" type="text" class="form-control" id="id"
                                                                   value="${user.id}">
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
                                                            <input name="password" type="password" class="form-control"
                                                                   id="password"
                                                                   value="${user.password}">
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default"
                                                                    data-dismiss="modal">
                                                                Cancel
                                                            </button>
                                                            <button type="submit" class="btn btn-primary">Save</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <div id="panel2" class="tab-pane fade">
                    <div class="panel-heading">
                        <h3 class="panel-title">Add new user</h3>
                    </div>
                    <%--                    <a href="/admin/addUser" class="link">Add user</a>--%>
                    <div class="form-group">
                        <label for="id">Email</label>
                        <input name="id" type="text" class="form-control" id="id"
                               value="${user.id}">
                    </div>
                    <div class="form-group">
                        <label for="login1">Login</label>
                        <input name="login1" type="text" class="form-control"
                               id="login1"
                               value="${user.login}">
                    </div>
                    <div class="form-group">
                        <label for="password1">Password</label>
                        <input name="password1" type="password" class="form-control"
                               id="password1"
                               value="${user.password}">
                    </div>
                    <div class="form-group">
                        <label for="role">Role</label>
                        <select id="role" class="form-control">
                            <option value="user">Пользователь</option>
                            <option value="admin">Администратор</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                    </form>
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
