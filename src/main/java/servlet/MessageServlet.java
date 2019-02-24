package servlet;

import manager.MessageManager;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/sendSms")
public class MessageServlet extends HttpServlet {
    MessageManager messageManager = new MessageManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String smsId = req.getParameter("id");
        req.setAttribute("usersMessage", messageManager.getUsersMessage(user.getId(), Integer.parseInt(smsId)));
        req.setAttribute("userSms", messageManager.getFriendById(Integer.parseInt(smsId)));
        req.getRequestDispatcher("/WEB-INF/user/message.jsp").forward(req, resp);

    }
}
