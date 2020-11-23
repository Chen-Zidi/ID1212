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

//        System.out.println("question list length"+questionList.size());

        List<String> answerList = new ArrayList<String>();

        for(int i = 0; i < questionList.size();i++) {

            //get answers
            String answer = "";
            String identifier = "question" + Integer.toString(questionList.get(i).getId());
            String[] result = request.getParameterValues(identifier);
            String answerResult = "";
 //           System.out.println("identifier" + identifier);

  //          System.out.println(result);

            //reformat the answers
            for(String s : result){
                if(answer != ""){
                    answer = answer + "," + s;
                }else{
                    answer = answer + s;
                }


            }
            System.out.println(answer.trim());
            System.out.println(questionList.get(i).getCorrectAnswer().trim());
            String[] a = answer.trim().split(",");
            String[] ca = questionList.get(i).getCorrectAnswer().trim().split(",");
            System.out.println(a);
            System.out.println(ca);
            System.out.println(a.length);
            System.out.println(ca.length);

            List<Boolean> exist = new ArrayList<Boolean>();

            boolean correct = true;

            if(a.length == ca.length){
                for(int j = 0; j< a.length;j++){
                    for(int k = 0; k< ca.length ; k++){
                        if(a[j].equals(ca[k])){
                            exist.add(true);
                        }
                    }
                }

                if(exist.size() == a.length){
                    correct = true;
                }else{
                    correct = false;

                }
            }else{
                correct = false;
            }


            if(correct){
                answerResult = "correct";
            }else{
                answerResult = "false, the correct answer are: " + questionList.get(i).getCorrectAnswer();
            }


            answerList.add(answerResult);
        }


        request.setAttribute("questionList", questionList);
        request.setAttribute("answerList", answerList);
        request.getRequestDispatcher("answer.jsp").forward(request, response);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
