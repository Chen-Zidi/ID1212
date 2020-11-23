package servlet;

import DAO.QuestionDAO;
import bean.Question;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AnswerQuestionServlet")
public class AnswerQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        QuestionDAO qd = new QuestionDAO();
        List<Question> questionList = qd.getAllQuestions();

        System.out.println("question list length"+questionList.size());

        List<String> answerList = new ArrayList<String>();

        for(int i = 0; i < questionList.size();i++) {

            //get answers
            String answer = "";
            String identifier = "question" + Integer.toString(questionList.get(i).getId());
            String[] result = request.getParameterValues(identifier);

            System.out.println("identifier" + identifier);

            System.out.println(result);

            //reformat the answers
            for(String s : result){
                if(answer != ""){
                    answer = answer + ", " + s;
                }else{
                    answer = answer + s;
                }

               //System.out.println(s);
            }
            System.out.println(answer);
            answerList.add(answer);
        }
        request.setAttribute("questionList", questionList);
        request.setAttribute("answerList", answerList);
        request.getRequestDispatcher("answer.jsp").forward(request, response);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
