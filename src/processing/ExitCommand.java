package processing;

import registration.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * <h1>ExitCommand</h1>
 * Kills session and redirect to index.jsp page
 * Created by alex on 6/18/15.
 */
public class ExitCommand implements ProcessingCommand {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.getSession().invalidate();

//        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//
//        rd.forward(request, response);
        response.sendRedirect("index.jsp");
    }
    public String toString() {
        return "exit";
    }
}
