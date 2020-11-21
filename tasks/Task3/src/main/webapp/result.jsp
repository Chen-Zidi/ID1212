<%@ page import="bean.User" %><%--
  Created by IntelliJ IDEA.
  User: amao
  Date: 2020-11-19
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<%
    //get information
    String info = (String) request.getAttribute("info");
    if (info != null) {
        out.print(info);
    }

   //get user session information
    User user = (User) session.getAttribute("user");
    if (user != null) {
%>
<table align="center" width="600" border="1" height="550" bordercolor="#E8F4CC">
    <tr>
        <td align="center" colspan="2">
            <span style="font-weight: bold;font-size: 18px;"><%=user.getUsername()%></span>Login Successful！
        </td>
    </tr>
    <tr>
        <td align="center" colspan="2">Username：</td>
        <td align="center" colspan="2"><%=user.getUsername()%></td>
    </tr>
    <tr>
        <td align="center" colspan="2">Email</td>
        <td align="center" colspan="2"><%=user.getEmail()%></td>
    </tr>
</table>
<%
    }else {
        out.print("<br>对不起您没有登录！");
    }
%>
</body>
</html>
