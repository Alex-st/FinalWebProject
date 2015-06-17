package registration;

import dao.data.StudentsDao;
import dao.models.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

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

            if (studentsDao.findStudent(student)) {

                HttpSession httpSession = request.getSession(true);
                //List<User> users = userDAO.getAll();
                httpSession.setAttribute("user", studentsDao.findStudentByLogin(student));

                RequestDispatcher rd = request.getRequestDispatcher("/studlogin.jsp");

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
