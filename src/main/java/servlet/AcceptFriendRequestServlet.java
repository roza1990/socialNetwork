package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/sendAcceptRequest")
public class AcceptFriendRequestServlet extends HttpServlet {
    UserManager userManager=new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accept_id = req.getParameter("id");
        User user = (User) req.getSession().getAttribute("user");
        userManager.updateUser(user.getId(),Integer.parseInt(accept_id));
        resp.sendRedirect("/user/userHome");
    }
}
