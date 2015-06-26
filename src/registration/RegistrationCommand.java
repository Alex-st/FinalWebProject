package registration;

import dao.data.StudentsDao;
import dao.data.TutorsDao;
import dao.models.Student;
import dao.models.Tutor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * <h1>RegistrationCommand</h1>
 * Class responsible for creation of new user in system
 * Created by alex on 6/17/15.
 */
public class RegistrationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.setCharacterEncoding("UTF-8");

        String name = new String (request.getParameter("name").getBytes ("iso-8859-1"), "UTF-8");
        String surname = new String (request.getParameter("surname").getBytes ("iso-8859-1"), "UTF-8");
        String login = new String (request.getParameter("login").getBytes ("iso-8859-1"), "UTF-8");
        String password = new String (request.getParameter("password").getBytes ("iso-8859-1"), "UTF-8");
        String email = new String (request.getParameter("email").getBytes ("iso-8859-1"), "UTF-8");
        String whoIs = new String (request.getParameter("select").getBytes ("iso-8859-1"), "UTF-8");

        //String name = request.getParameter("name");
//        System.out.println(name);
//
//        String surname = request.getParameter("surname");
//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        String whoIs = request.getParameter("select");

        switch (whoIs) {
            case "student" :
                Student student = new Student();
                student.setStudName(name);
                student.setStudSurname(surname);
                student.setStudLogin(login);
                student.setStudPassword(password);
                student.setEmail(email);

                StudentsDao studentsDao = new StudentsDao();
                studentsDao.insertStudentsToDB(student);
                break;

            case "tutor":
                Tutor tutor = new Tutor();
                tutor.settName(name);
                tutor.settSurname(surname);
                tutor.settLogin(login);
                tutor.settPassword(password);
                tutor.setEmail(email);

                TutorsDao tutorsDao= new TutorsDao();
                tutorsDao.insertTutorsToDB(tutor);
                break;
        }

        request.setAttribute("result", "<center>Registration successful</center>");
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            Logger.getLogger(RegistrationCommand.class.getName()).log(Level.ERROR, null, e);
        } catch (IOException e) {
            Logger.getLogger(RegistrationCommand.class.getName()).log(Level.ERROR, null, e);
        }
    }

    @Override
    public String toString() {
        return "registration";
    }
}
