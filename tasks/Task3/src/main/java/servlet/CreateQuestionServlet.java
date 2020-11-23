package servlet;

import DAO.QuestionDAO;
import bean.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CreateQuestionServlet")
public class CreateQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String question = request.getParameter("question");
        String choice0 = request.getParameter("choice0");
        String choice1 = request.getParameter("choice1");
        String choice2 = request.getParameter("choice2");
        String choice3 = request.getParameter("choice3");
        String choice4 = request.getParameter("choice4");
        String ca = request.getParameter("correctAnswer");

        //List<Question> qList = new ArrayList<Question>();
        Question q = new Question(question,choice0,choice1,choice2,choice3,choice4,ca.trim());
        //qList.add(q);

        QuestionDAO qd = new QuestionDAO();
        qd.createQuestion(q);


        List<Question> questionList = qd.getAllQuestions();
        request.setAttribute("questionList", questionList);
        request.getRequestDispatcher("question.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
