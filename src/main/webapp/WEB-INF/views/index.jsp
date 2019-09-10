<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Test jsp project</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css"/>
</head>
<body>
<h3>
    Welcome
</h3>
<c:if test="${param.error != null}">
    <div class="alert alert-danger" role="alert">
        Invalid username and password.
    </div>
</c:if>
<div class="form-style-2">
    <form action="/login" method="post" class="form-style-9">
        <ul>
            <li>
                <input type="text" name="login" placeholder="Input login..." class="field-style field-full align-none"/>
            </li>
            <li>
                <input type="password" name="password" placeholder="Input password..."
                       class="field-style field-full align-none"/>
            </li>
            <li>
                <input type="submit" value="Login"/>
            </li>
        </ul>
    </form>
</div>
</body>
</html>