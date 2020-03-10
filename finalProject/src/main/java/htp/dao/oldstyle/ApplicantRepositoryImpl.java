//package htp.dao.oldstyle;
//
//
//
//import htp.dao.ApplicantRepository;
//import htp.entities.dbentities.Applicant;
//import htp.exceptions.NoSuchEntityException;
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
//public class ApplicantRepositoryImpl implements ApplicantRepository {
//    public static final String url = "jdbc:postgresql://localhost:5432/Final_ProjectDB";
//    public static final String username = "User_FP";
//    public static final String password = "5432";
//    public static final int initialSize = 10;
//    public static final String APPLICANT_ID = "applicant_id";
//    public static final String FIRST_NAME = "first_name";
//    public static final String SECOND_NAME = "second_name";
//    public static final String PATRONYMIC = "patronymic";
//    public static final String TYPE_OF_APPLICANT = "type_of_applicant";
//    public static final String DATE_OF_BIRTHDAY = "date_of_birthday";
//    public static final String INCOME = "income";
//    public static final String INCOME_CURRENCY = "income_currency";
//    public static final String SEX = "sex";
//    public static final String EXPERIENCE = "experience";
//    public static final String MARITAL_STATUS = "marital_status";
//    public static final String EDUCATION = "education";
//    public static final String NUMBER_OF_CHILDREN = "number_of_children";
//    public static final String PERSONAL_NUMBER = "personal_number";
//    public static final String USER_ID = "user_id";
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
//    private Applicant fillApplicant(ResultSet set) throws SQLException {
//        Applicant applicant = new Applicant();
//        applicant.setApplicantId(set.getLong(APPLICANT_ID));
//        applicant.setFirstName(set.getString(FIRST_NAME));
//        applicant.setSecondName(set.getString(SECOND_NAME));
//        applicant.setPatronymic(set.getString(PATRONYMIC));
//        applicant.setTypeOfApplicant(set.getInt(TYPE_OF_APPLICANT));
//        applicant.setBirthdayDate(set.getTimestamp(DATE_OF_BIRTHDAY));
//        applicant.setIncome(set.getLong(INCOME));
//        applicant.setIncomeCurrency(set.getString(INCOME_CURRENCY));
//        applicant.setSex(set.getString(SEX));
//        applicant.setExperience(set.getInt(EXPERIENCE));
//        applicant.setEducation(set.getInt(EDUCATION));
//        applicant.setNumberOfChildren(set.getInt(NUMBER_OF_CHILDREN));
//        applicant.setPersonalNumber(set.getString(PERSONAL_NUMBER));
//        applicant.setUserId(set.getLong(USER_ID));
//        return applicant;
//    }
//
//    @Override
//    public int save(Applicant applicant) {
//        return 0;
//    }
//
//    @Override
//    public Applicant findByUserId(Long userId) {
//        Connection connection = null;
//        try  {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, username, password);
//            //
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from m_applicant where m_applicant.user_id = ?");
//            preparedStatement.setLong(1, userId);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                System.out.println(fillApplicant(resultSet));
//            } else {
//                throw new NoSuchEntityException(String.format("Applicant with user id %s not found", userId));
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
//    public List<Applicant> findAll() {
//        Connection connection = null;
//        try  {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, username, password);
//            //
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from m_applicant ");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Applicant> applicants = new ArrayList<>();
//            while (resultSet.next()) {
//                applicants.add(fillApplicant(resultSet));
//            }
//            for (Applicant applicant : applicants){
//                System.out.println(applicant);
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
//    public int update(Applicant applicant) {
//        return 0;
//    }
//
//    @Override
//    public void delete(Long ApplicantId) {
//
//    }
//}
