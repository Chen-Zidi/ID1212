
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create a new Question</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/CreateQuestion" method="post" onsubmit="">
    Question: <input type="text" name="question"><br>
    Choice: <input type="text" name="choice0"><br>
    Choice: <input type="text" name="choice1"><br>
    Choice: <input type="text" name="choice2"><br>
    Choice: <input type="text" name="choice3"><br>
    Choice: <input type="text" name="choice4"><br>
<input type="submit" value="submit"><input type="reset" value="reset">
</form>
</body>
</html>
