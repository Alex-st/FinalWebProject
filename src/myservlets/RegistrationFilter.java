package myservlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by alex on 6/24/15.
 */
@WebFilter(filterName = "RegistrationFilter")
public class RegistrationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

//        System.out.println("registraion filter start");
//        System.out.println(req.getParameter("send"));
//        System.out.println(req.getParameter("name"));

        if (((String)(req.getParameter("send"))).equals("register")) {
//            System.out.println(req.getParameter("name").isEmpty());
//            System.out.println("it was name");

            if (req.getParameter("name").isEmpty()) {
                req.setAttribute("result", "Name is incorrect");
                RequestDispatcher rd = req.getRequestDispatcher("/registration.jsp");
                rd.forward(req, resp);
            }

            if (req.getParameter("email").isEmpty()) {
                req.setAttribute("result", "Email is incorrect");
                RequestDispatcher rd = req.getRequestDispatcher("/registration.jsp");
                rd.forward(req, resp);
            }

            if (req.getParameter("login").isEmpty()) {
                req.setAttribute("result", "Login is incorrect");
                RequestDispatcher rd = req.getRequestDispatcher("/registration.jsp");
                rd.forward(req, resp);
            }

            if (req.getParameter("password").isEmpty()) {
                req.setAttribute("result", "Password is incorrect");
                RequestDispatcher rd = req.getRequestDispatcher("/registration.jsp");
                rd.forward(req, resp);
            }

        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
