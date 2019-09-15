<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" class="mdl-js">
<head>
    <meta charset="UTF-8">
    <title>Test jsp project</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/signin.css" />" />

</head>
<body>
<c:if test="${param.error != null}">
    <div class="alert alert-danger" role="alert">
        Invalid username and password.
    </div>
</c:if>
<div class="container">
    <form action="/login" method="post" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" name="login" placeholder="Email address" class="form-control"/>
        <input type="password" name="password" placeholder="Password" class="form-control"/>
        <button type="submit" class="btn btn-lg btn-primary btn-block">Sign in</button>
    </form>
</div>
</body>
</html>