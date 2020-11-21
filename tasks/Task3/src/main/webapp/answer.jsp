<%@ page import="bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thanks you for answer!</title>
</head>
<body>
<%
    User u = (User) request.getSession().getAttribute("user");
    if(u == null){
        System.out.println("user not login");
        //not working??
        //response.sendRedirect("login.jsp");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    ArrayList<String> answerList = (ArrayList<String>) request.getAttribute("answerList");
    ArrayList<Question> questionList = (ArrayList<Question>) request.getAttribute("questionList");
%>
<div>
    <p>Here are your answers:</p>
    <%
        for(int i = 0; i < questionList.size();i++){
            Question q = questionList.get(i);
            String a =  answerList.get(i);
    %>
    <p><%=q.getId()%>: <%=q.getQuestion()%></p>
    <p><%=a%></p>
    <%
        }
    %>
</div>
</body>
</html>
