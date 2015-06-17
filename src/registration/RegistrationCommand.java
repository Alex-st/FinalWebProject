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

/**
 * Created by alex on 6/17/15.
 */
public class RegistrationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String whoIs = request.getParameter("select");

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
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "registration";
    }
}
