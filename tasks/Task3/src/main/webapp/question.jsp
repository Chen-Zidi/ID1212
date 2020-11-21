<%@ page import="bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Answer the questions</title>
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

    ArrayList<Question> questionList = (ArrayList<Question>) request.getAttribute("questionList");

    //if user is admin, then he/she can create a new question
    if(u.getUsername().trim().equals("admin")){
%>
<a href="createQuestion.jsp">Create a new question</a>
<%
    }
%>
<form action="${pageContext.request.contextPath}/AnswerQuestion" method="post" onsubmit="">
    <%
        for(int i = 0; i < questionList.size();i++){
            Question q = (Question) questionList.get(i);

    %>
    <p><%=q.getId()%>: <%=q.getQuestion()%></p>
    <input type="checkbox" name="question<%=Integer.toString(q.getId())%>" value= <%=q.getChoice0()%>> <%=q.getChoice0()%><br />
    <input type="checkbox" name="question<%=Integer.toString(q.getId())%>" value= <%=q.getChoice1()%>> <%=q.getChoice1()%><br />
    <input type="checkbox" name="question<%=Integer.toString(q.getId())%>" value= <%=q.getChoice2()%>> <%=q.getChoice2()%><br />
    <input type="checkbox" name="question<%=Integer.toString(q.getId())%>" value= <%=q.getChoice3()%>> <%=q.getChoice3()%><br />
    <input type="checkbox" name="question<%=Integer.toString(q.getId())%>" value= <%=q.getChoice4()%>> <%=q.getChoice4()%><br />
    <%
        }
    %>

    <input type="submit" value="submit">
</form>




</body>
</html>
