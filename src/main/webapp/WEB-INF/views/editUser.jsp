<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit user</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css"/>
</head>
<body>
<a href="../../index.jsp"><-- Home</a>
<h3>Edit user...</h3>
<form action="/admin/editUser" method="post" class="form-style-9">
    <ul>

        <input type="hidden" name="id" value="${user.id}">
        <li>
            <input type = "text" name = "login" value = "${user.login}" class="field-style field-full align-none"/>
        </li>
        <li>
            <input type = "password" name = "password" value = "${user.password}" class="field-style field-full align-none"/>
        </li>
        <li>
            <input type = "text" name = "email" value = "${user.email}" class="field-style field-full align-none"/>
        </li>
        <li>
            <input type = "text" name = "email" value = "${user.role}" class="field-style field-full align-none"/>
        </li>
        <li>
            <input type = "submit"/>
        </li>
    </ul>
</form>

</body>
</html>