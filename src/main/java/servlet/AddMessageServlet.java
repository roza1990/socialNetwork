package servlet;

import manager.MessageManager;
import manager.UserManager;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/user/message")
public class AddMessageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageManager messageManager =new MessageManager();
        UserManager userManager = new UserManager();
        User user = (User) req.getSession().getAttribute("user");
        String friendId=req.getParameter("friendId");
        String sms=req.getParameter("sms");

        User u= (User) userManager.getUserById(Integer.parseInt(friendId));
        Message message=new Message();
        message.setUserId(user.getId());
        message.setUserName(user.getName());
        message.setFriendId(Integer.parseInt(friendId));
        message.setFriendName(u.getName());
        message.setSms(sms);
        message.setSmsDate(new Date());

        messageManager.addMessage(message);
        resp.sendRedirect("/user/userHome");



    }
}
