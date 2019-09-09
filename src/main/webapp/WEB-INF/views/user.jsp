<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<h3>Hello, ${user.login} </h3>
<a href="<c:url value="/perform_logout" />">Logout</a>
</body>
</html>
