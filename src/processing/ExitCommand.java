package processing;

import registration.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by alex on 6/18/15.
 */
public class ExitCommand implements ProcessingCommand {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.getSession().invalidate();
        System.out.println(request.getSession().getAttribute("login"));

//        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//
//        rd.forward(request, response);
        response.sendRedirect("index.jsp");
    }
    public String toString() {
        return "exit";
    }
}
