<%--
  Created by IntelliJ IDEA.
  User: amao
  Date: 2020-11-19
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/Register" method="post" onsubmit="">
        Username:
        <input type="text" name="username"><br>
        Password:
        <input type="password" name="password"><br>
        Email:
        <input type="Email" name="email"><br>
        <input type="submit" value="Register">
        <input type="reset" value="Reset">
    </form>
</body>
</html>
