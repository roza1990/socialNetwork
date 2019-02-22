package manager;

import db.DBConnectionProvider;
import model.Message;
import model.Request;
import model.User;
import model.UserType;
import util.DateUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserManager {

    private Connection connection;

    public UserManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addUser(User user) {
        try {
            String query = "INSERT INTO user(`name`,`surname`,`email`,`password`, `userType`,`user_img`) " +
                    "VALUES(?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getUserType().name());
            statement.setString(6, user.getPicUrl());
            System.out.println("executing the following statement ->" + query);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserByIds(long userId,long friendId) {
        try {
            String query = "INSERT INTO user_request(`user_id`,`friend_id`,`request`) " +
                    "VALUES(?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, userId);
            statement.setLong(2, friendId);
            statement.setString(3, String.valueOf(Request.SEND));

            System.out.println("executing the following statement ->" + query);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void userMessage(long userId,long friendId) {
        try {
            String query = "INSERT INTO user_request(`user_id`,`friend_id`,`request`) " +
                    "VALUES(?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, userId);
            statement.setLong(2, friendId);
            statement.setString(3, String.valueOf(Request.SEND));

            System.out.println("executing the following statement ->" + query);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM user WHERE id = " + id;
        return getUserFromDB(query);
    }

    public List<User> getAllUsersBesideCurrent(long id) {
        String query = "SELECT * FROM user WHERE id!="+id;
        //String query = "SELECT * FROM `user` WHERE id NOT IN(SELECT user_id FROM `user_request`) ";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<User> users = new LinkedList<User>();
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUserByRequest(long id) {
        String query = "SELECT * FROM `user`,`user_request` WHERE user.`id`=`user_request`.`friend_id` AND `user_request`.`request`='SEND' AND `user_request`.`user_id`="+id;
 try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<User> users = new LinkedList<User>();
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }






    public List<User> getUserFriends(long id) {
        String query = "SELECT * FROM `user`,`user_request` WHERE user.`id`=`user_request`.`friend_id` AND `user_request`.`request`='ACCEPT' AND `user_request`.`user_id`="+id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<User> users = new LinkedList<User>();
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsersBesidesFriends() {
        String query = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<User> users = new LinkedList<User>();
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'";
        return getUserFromDB(query);
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<User> users = new LinkedList<User>();
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void removeUserById(int id) {
        String query = "DELETE  FROM user WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRequest(long current_id,int fId) {
        String query = "DELETE  FROM user_request WHERE user_id = " + current_id+ " AND friend_id="+fId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(long uId,int fId) {

        try {
            String query = "UPDATE user_request set user_id=?,friend_id=?,request=?  where user_id="+ uId +" AND "+ "friend_id=" + fId;
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, uId);
            preparedStatement.setInt(2, fId);
            preparedStatement.setString(3, String.valueOf(Request.ACCEPT));
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private User getUserFromDB(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return createUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
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



}
