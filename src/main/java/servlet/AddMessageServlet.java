package servlet;

import manager.MessageManager;
import manager.UserManager;
import model.Message;
import model.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/user/message")
public class AddMessageServlet extends HttpServlet {
    private MessageManager messageManager = new MessageManager();
    private UserManager userManager = new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         User user = (User) req.getSession().getAttribute("user");
         //String friendId = req.getParameter("id");

//        String sms = req.getParameter("sms");


//        message.setUserId(user.getId());
//        message.setUserName(user.getName());
//        message.setFriendId(Integer.parseInt(friendId));
//        message.setFriendName(u.getName());
//        message.setSms(sms);
//        message.setSmsDate(new Date());
//
//        messageManager.addMessage(message);


        if (ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 1024);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(1024 * 1024 * 50);
            upload.setSizeMax(1024 * 1024 * 5 * 50);
            String uploadPath = "/home/roza/Desktop/socialNetwork/uploadImages";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                Message message = new Message();
                List<FileItem> formItems = upload.parseRequest(req);
                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            String fileName = System.currentTimeMillis() + "_" + new File(item.getName()).getName();
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                            message.setFile(fileName);
                        } else {
                            if (item.getFieldName().equals("sms")) {
                                message.setSms(item.getString());
                            }
                            else if(item.getFieldName().equals("friendId")){
                                message.setFriendId(Integer.parseInt(item.getString()));


                            }else if(item.getFieldName().equals("friendName")){
                                message.setFriendName(item.getString());

                            }


                        }
                    }

                    message.setUserId(user.getId());
                    message.setUserName(user.getName());
                    message.setSmsDate(new Date());
                    messageManager.addMessage(message);
                    resp.sendRedirect("/user/userHome");

                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }
}
