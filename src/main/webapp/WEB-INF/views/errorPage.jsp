<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<a href="/"><-- Home</a>
<h3><%= request.getAttribute("error") %>
</h3>
</body>
</html>
