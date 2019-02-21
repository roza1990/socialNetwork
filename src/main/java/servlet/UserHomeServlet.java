package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/userHome")
public class UserHomeServlet extends HttpServlet {
UserManager userManager=new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("users", userManager.getAllUsersBesideCurrent(user.getId()));
        req.setAttribute("usersByRequest", userManager.getUserByRequest(user.getId()));
        req.setAttribute("usersFriends", userManager.getUserFriends(user.getId()));
        req.getRequestDispatcher("/WEB-INF/user/userHome.jsp").forward(req, resp);

    }
}
