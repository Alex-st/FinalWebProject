package myservlets;

import registration.Command;
import registration.CommandFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alex on 6/15/15.
 */
@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {


    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        String path = servletConfig.getServletContext().getRealPath("/WEB-INF");
        System.out.println(path);

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("relative path is:"+s);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("send");

        Command c = CommandFactory.getCommand(request);

        //System.out.println(c.toString());
        try {
            c.execute(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

       // System.out.println("doPost"+value);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("button");
        System.out.println("doPost"+value);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String value = request.getParameter("button");
        System.out.println("doPost"+value);
    }
}
