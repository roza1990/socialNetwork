package filter;

import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = "/user/*")
public class AdminAuthFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("run");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            ((HttpServletResponse) servletResponse).sendRedirect("/login.jsp");

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("user");
        }
    }

    public void destroy() {
        System.out.println("in destroy method");
    }
}
