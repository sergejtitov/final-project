//package com.htp.dao.oldstyle;
//
//
//import com.htp.dao.ApplicationRepository;
//import htp.entities.dbentities.Application;
//import com.htp.exceptions.NoSuchEntityException;
//import org.apache.commons.dbcp2.BasicDataSource;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ApplicationRepositoryImpl implements ApplicationRepository {
//    public static final String url = "jdbc:postgresql://localhost:5432/Final_ProjectDB";
//    public static final String username = "User_FP";
//    public static final String password = "5432";
//    public static final int initialSize = 10;
//    public static final String APPLICATION_ID = "application_id";
//    public static final String USER_ID = "user_id";
//    public static final String CREATION_DATE = "creation_date";
//    public static final String LOAN_TYPE = "loan_type";
//    public static final String PRODUCT_CODE = "product_code";
//    public static final String LOAN_AMOUNT = "loan_amount";
//    public static final String CURRENCY = "currency";
//    public static final String FINAL_AMOUNT = "final_amount";
//    public static final String DECISION = "decision";
//    public static final String STATUS = "status";
//    public static final String PAYMENT = "payment";
//    public static final String APPLICATION_OWNER = "application_owner";
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
//    private Application fillApplication(ResultSet set) throws SQLException {
//        Application application = new Application();
//        application.setApplicationId(set.getString(APPLICATION_ID));
//        application.setUserId(set.getLong(USER_ID));
//        application.setCreationDate(set.getTimestamp(CREATION_DATE));
//        application.setLoanType(set.getInt(LOAN_TYPE));
//        application.setProductCode(set.getLong(PRODUCT_CODE));
//        application.setLoanAmount(set.getDouble(LOAN_AMOUNT));
//        application.setCurrency(set.getString(CURRENCY));
//        application.setFinalAmount(set.getDouble(FINAL_AMOUNT));
//        application.setDecision(set.getString(DECISION));
//        application.setStatus(set.getString(STATUS));
//        application.setPayment(set.getDouble(PAYMENT));
//        application.setOwner(set.getLong(APPLICATION_OWNER));
//
//        return application;
//    }
//
//    @Override
//    public int save(Application application) {
//        return 0;
//    }
//
//    @Override
//    public Application findOne(String applicationId) {
//        Connection connection = null;
//        try  {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, username, password);
//            //
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from m_application where m_application.application_id = ?");
//            preparedStatement.setString(1, applicationId);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                System.out.println(fillApplication(resultSet));
//            } else {
//                throw new NoSuchEntityException(String.format("Application with id %s not found", applicationId));
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
//    @Override
//    public List<Application> findAll() {
//        Connection connection = null;
//        try  {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, username, password);
//            //
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from m_application ");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Application> applications = new ArrayList<>();
//            while (resultSet.next()) {
//                applications.add(fillApplication(resultSet));
//            }
//            for (Application application : applications){
//                System.out.println(application);
//            }
//
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException("Error!!");
//        } finally {
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
//    @Override
//    public List<Application> findSome(Long userId) {
//        Connection connection = null;
//        try  {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, username, password);
//            //
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from m_application where m_application.user_id = ?");
//            preparedStatement.setLong(1, userId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Application> applications = new ArrayList<>();
//            try {
//                while (resultSet.next()) {
//                    applications.add(fillApplication(resultSet));
//                }
//                for (Application application : applications){
//                    System.out.println(application);
//                }
//            } catch (NoSuchEntityException e){
//                throw new NoSuchEntityException(String.format("Application with user_id %s not found", userId));
//            }
//
//
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException("Error!!");
//        } finally {
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
//    @Override
//    public int update(Application application) {
//        return 0;
//    }
//
//    @Override
//    public void delete(String applicationId) {
//
//    }
//}
