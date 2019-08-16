<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add new user</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" type="text/css"/>
</head>
<body>
<h3>Add new user...</h3>
<a href="../../index.html"><-- Home</a>
<form action="/updateUser" method="post" class="form-style-9">
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
            <input type = "birthday" name = "birthday" value = "${user.birthday}" class="field-style field-full align-none"/>
        </li>
        <li>
            <input type = "submit"/>
        </li>
    </ul>
</form>

</body>
</html>