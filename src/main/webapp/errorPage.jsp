<%--
  Created by IntelliJ IDEA.
  User: ReshetkoKV
  Date: 16.08.2019
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h3><%= request.getAttribute("error") %></h3>
</body>
</html>
