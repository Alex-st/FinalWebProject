package myservlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alex on 6/23/15.
 */
@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        //System.out.println(((HttpServletRequest)request).getSession().getAttribute("login"));
        if (((HttpServletRequest)request).getSession().getAttribute("login") == null) {
            request.setAttribute("result", "<center>You haven't permissions for this resource.<br> Sign in please.</center>");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request,response);

        }

        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("filterInitialization");
    }

}
