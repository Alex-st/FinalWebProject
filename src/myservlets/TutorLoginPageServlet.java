package myservlets;

import processing.ProcessingCommand;
import processing.ProcessingFactory;
import registration.Command;
import registration.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created by alex on 6/18/15.
 */
@WebServlet(name = "TutorLoginPageServlet")
public class TutorLoginPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String value = request.getParameter("send");

        ProcessingCommand c = ProcessingFactory.getCommand(request);

        //System.out.println(c.toString());
        try {
            c.execute(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TutorLoginPageServlet.class.getName()).log(Level.ERROR, null, ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("send");

        ProcessingCommand c = ProcessingFactory.getCommand(request);

        //System.out.println(c.toString());
        try {
            c.execute(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TutorLoginPageServlet.class.getName()).log(Level.ERROR, null, ex);
        }
    }
}
