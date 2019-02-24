package manager;

import db.DBConnectionProvider;
import model.Message;
import model.User;
import model.UserType;
import util.DateUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MessageManager {

    private Connection connection;
    public MessageManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public List<Message> getUsersMessage(long userId, int friendId) {
        String query = "SELECT * FROM `userMessage` WHERE userId IN("+userId+","+friendId+ ") AND `friendId` IN(" +userId+","+friendId+");";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Message> messages = new LinkedList<Message>();
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setUserId(resultSet.getInt(2));
                message.setUserName(resultSet.getString(3));
                message.setFriendId(resultSet.getInt(4));
                message.setFriendName(resultSet.getString(5));
                message.setSms(resultSet.getString(6));
                message.setSmsDate(DateUtil.convertStringToDate(resultSet.getString(7)));
                message.setFile(resultSet.getString(8));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addMessage(Message message) {
        try {
            String query = "INSERT INTO userMessage(`userId`,`userName`,`friendId`,`friendName`, `sms`,`smsDate`,`file`) " +
                    "VALUES(?,?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, message.getUserId());
            statement.setString(2, message.getUserName());
            statement.setLong(3, message.getFriendId());
            statement.setString(4, message.getFriendName());
            statement.setString(5, message.getSms());
            statement.setString(6, DateUtil.convertDateToString(message.getSmsDate()));
            statement.setString(7, message.getFile());

            System.out.println("executing the following statement ->" + query);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                message.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getFriendById(int fId) {
        String query = "SELECT * FROM user WHERE user.id = " + fId ;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setUserType(UserType.valueOf(resultSet.getString(6)));
                user.setPicUrl(resultSet.getString(7));
                return user;


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }




}
