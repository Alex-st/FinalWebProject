package myservlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by alex on 6/24/15.
 */
@WebFilter(filterName = "RegistrationFilter")
public class RegistrationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;

        //req.setAttribute("locale", "ru");

        if (((String)(req.getParameter("send"))).equals("register")) {

            if (req.getParameter("name").isEmpty()) {
                req.setAttribute("result", "nameisincorrect");
                RequestDispatcher rd = req.getRequestDispatcher("/registration.jsp");
                rd.forward(req, resp);
            }

            if (req.getParameter("email").isEmpty()) {
                req.setAttribute("result", "emailisincorrect");
                RequestDispatcher rd = req.getRequestDispatcher("/registration.jsp");
                rd.forward(req, resp);
            }

            if (req.getParameter("login").isEmpty()) {
                req.setAttribute("result", "loginisincorrect");
                RequestDispatcher rd = req.getRequestDispatcher("/registration.jsp");
                rd.forward(req, resp);
            }

            if (req.getParameter("password").isEmpty()) {
                req.setAttribute("result", "passisincorrect");
                RequestDispatcher rd = req.getRequestDispatcher("/registration.jsp");
                rd.forward(req, resp);
            }

        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
