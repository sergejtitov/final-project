//package com.htp.dao.oldstyle;
//
//import com.htp.dao.UserRepository;
//import htp.entities.dbentities.User;
//import com.htp.exceptions.NoSuchEntityException;
//import org.apache.commons.dbcp2.BasicDataSource;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserRepositoryImpl{
//
//    public static final String url = "jdbc:postgresql://localhost:5432/Final_ProjectDB";
//    public static final String username = "User_FP";
//    public static final String password = "5432";
//    public static final int initialSize = 10;
//    public static final String USER_ID = "user_id";
//    public static final String LOGIN = "login";
//    public static final String PASSWORD = "password";
//    public static final String CREATED = "created";
//    public static final String CHANGED = "changed";
//    public static final String ROLE = "role";
//    public static final String IS_DELETED ="isDeleted";
//
//    private DataSource init() {
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setDriverClassName(url);
//        basicDataSource.setUsername(username);
//        basicDataSource.setPassword(password);
//        basicDataSource.setInitialSize(initialSize);
//        return basicDataSource;
//    }
//
//    private User fillUser(ResultSet set) throws SQLException {
//        User user = new User();
//        user.setUserId(set.getLong(USER_ID));
//        user.setLogin(set.getString(LOGIN));
//        user.setPassword(set.getString(PASSWORD));
//        user.setCreated(set.getTimestamp(CREATED));
//        user.setChanged(set.getTimestamp(CHANGED));
//        user.setRole(set.getString(ROLE));
//        user.setDeleted(set.getBoolean(IS_DELETED));
//        return user;
//    }
//
//
//    public User findOne(Long userId) {
//        Connection connection = null;
//        try  {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, username, password);
//            //
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from m_user where user_id = ?");
//            preparedStatement.setLong(1, userId);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                System.out.println(fillUser(resultSet));
//            } else {
//                throw new NoSuchEntityException(String.format("User with id %s not found", userId));
//            }
//
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException("Error!!");
//        }
//        finally {
//            try {
//                if (!connection.isClosed()) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//
//    public int save(User user) {
//        return 0;
//    }
//
//
//    public int update(User user) {
//        return 0;
//    }
//
//
//    public void delete(Long userId) {
//
//    }
//}
