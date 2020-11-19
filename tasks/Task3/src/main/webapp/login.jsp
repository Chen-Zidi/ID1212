<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>

    <body>
        <form action="${pageContext.request.contextPath}/Login" method="post" onsubmit="">
            Username:
            <input type="text" name="username"><br>
            Password:
            <input type="password" name="password"><br>
            <input type="submit" value="Login">
            <input type="reset" value="Reset">
        </form>
        <a href="register.jsp">Are you new? Click to register!</a>
    </body>
</html>
