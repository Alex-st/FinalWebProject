package registration;

import dao.data.StudentsDao;
import dao.data.TutorsDao;
import dao.models.Student;
import dao.models.Tutor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by alex on 6/18/15.
 */
public class TutorAuthCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Tutor tutor = new Tutor();
        tutor.settLogin(login);
        tutor.settPassword(password);

        TutorsDao tutorsDao = new TutorsDao();

        if (tutorsDao.findTutor(tutor)) {

            HttpSession httpSession = request.getSession(true);
            //List<User> users = userDAO.getAll();
            httpSession.setAttribute("user", tutorsDao.findTutorByLogin(tutor));

            RequestDispatcher rd = request.getRequestDispatcher("/tutorlogin.jsp");

            rd.forward(request, response);

        } else {
            request.setAttribute("result", "<center>Authorization is incorrect</center>");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");

            rd.forward(request, response);
        }

    }

    @Override
    public String toString() {
        return "TutorAuthentication";
    }
}
