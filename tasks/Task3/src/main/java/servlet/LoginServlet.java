package servlet;

import DAO.QuestionDAO;
import DAO.UserDAO;
import bean.Question;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(username,password);

        if (user != null) {
            //put user into session
            request.getSession().setAttribute("user", user);
            //redirect to the result page
            //request.getRequestDispatcher("result.jsp").forward(request, response);

            QuestionDAO qd = new QuestionDAO();
            List<Question> questionList = qd.getAllQuestions();
            request.setAttribute("questionList", questionList);
            request.getRequestDispatcher("question.jsp").forward(request, response);

            /**
             response.sendRedirect(url)跳转到指定的URL地址，产生一个新的request，所以要传递参数只有在url后加参
             数，如：
             url?id=1.
             request.getRequestDispatcher(url).forward(request,response)是直接将请求转发到指定URL，所以该请求
             能够直接获得上一个请求的数据，也就是说采用请求转发，request对象始终存在，不会重新创建。而
             sendRedirect()会新建request对象，所以上一个request中的数据会丢失。
             */
        }else {
            //login error
            request.setAttribute("info","Error");
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
