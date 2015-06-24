package registration;

import dao.data.ResultsDao;
import dao.data.StudentsDao;
import dao.data.TopicDao;
import dao.models.Student;
import dao.models.Topic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 6/17/15.
 */
public class StudAuthCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Student student = new Student();
        student.setStudLogin(login);
        student.setStudPassword(password);

        StudentsDao studentsDao = new StudentsDao();
        ResultsDao resultsDao = new ResultsDao();
        TopicDao topicDao = new TopicDao();

//
//        System.out.println(login);
//        System.out.println(password);
//        System.out.println(studentsDao.findStudent(student));

            if (studentsDao.findStudent(student)) {

                HttpSession httpSession = request.getSession(true);
                //List<User> users = userDAO.getAll();
                httpSession.setAttribute("login", login);
                httpSession.setAttribute("user", studentsDao.findStudentByLogin(student));
                //httpSession.setAttribute("locale", "ua");

                Map<String, Integer> results = resultsDao.getStudentResultByLogin(login);
                request.setAttribute("results", results);

                List<Topic> topics = topicDao.getAllTopicsFromDB();
                request.setAttribute("topics", topics);

                //request.setAttribute("send", "exit");

                RequestDispatcher rd = request.getRequestDispatcher("/restricted/studlogin.jsp");
               // RequestDispatcher rd = request.getRequestDispatcher("/StudentResultsServlet");

                rd.forward(request, response);

            } else {
                request.setAttribute("result", "<center>Authorization is incorrect</center>");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");

                rd.forward(request, response);
            }

    }

    @Override
    public String toString() {
        return "StudAuthentication";
    }
}
