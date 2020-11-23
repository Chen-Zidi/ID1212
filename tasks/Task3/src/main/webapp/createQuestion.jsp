<%@ page import="bean.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create a new Question</title>
</head>
<body>
<%

    User u = (User) request.getSession().getAttribute("user");
    if(u == null){

        System.out.println("not login");
        //not working??
        //response.sendRedirect("login.jsp");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    if(!u.getUsername().equals("admin")){

        System.out.println("not admin");
        //not working??
        //response.sendRedirect("login.jsp");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
%>

<form action="${pageContext.request.contextPath}/CreateQuestion" method="post" onsubmit="">
    Question: <input type="text" name="question"><br>
    Choice: <input type="text" name="choice0"><br>
    Choice: <input type="text" name="choice1"><br>
    Choice: <input type="text" name="choice2"><br>
    Choice: <input type="text" name="choice3"><br>
    Choice: <input type="text" name="choice4"><br>
    Correct answer: <input type="text" name="correctAnswer"><br>
<input type="submit" value="submit"><input type="reset" value="reset">
</form>
</body>
</html>
