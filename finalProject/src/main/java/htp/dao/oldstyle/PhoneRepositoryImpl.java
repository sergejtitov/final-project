//package htp.dao.oldstyle;
//
//import htp.dao.PhoneRepository;
//import htp.entities.dbentities.Phone;
//import htp.exceptions.NoSuchEntityException;
//import org.apache.commons.dbcp2.BasicDataSource;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//public class PhoneRepositoryImpl implements PhoneRepository {
//    private DataSource dataSource;
//
//    public static final String url = "jdbc:postgresql://localhost:5432/Final_ProjectDB";
//    public static final String username = "phone_FP";
//    public static final String password = "5432";
//    public static final int initialSize = 10;
//    public static final String PHONE_ID = "phone_id";
//    public static final String APPLICANT_ID = "applicant_id";
//    public static final String PHONE_TYPE = "phone_type";
//    public static final String PHONE_NUMBER = "phone_number";
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
//    private Phone fillPhone(ResultSet set) throws SQLException {
//        Phone phone = new Phone();
//        phone.setPhoneId(set.getLong(PHONE_ID));
//        phone.setApplicantId(set.getLong(APPLICANT_ID));
//        phone.setPhoneType(set.getString(PHONE_TYPE));
//        phone.setPhoneNumber(set.getString(PHONE_NUMBER));
//        return phone;
//    }
//
//    @Override
//    public List<Phone> findSome(Long applicantId) {
//        Connection connection = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, username, password);
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from m_phone where applicant_id = ?");
//            preparedStatement.setLong(1,applicantId);
//            ResultSet set = preparedStatement.executeQuery();
//
//            while (set.next()){
//                try {
//                    System.out.println(fillPhone(set));
//                }catch (NoSuchEntityException e){
//                    throw new NoSuchEntityException(String.format("Phone with applicant_id %s not found", applicantId));
//                }
//
//            }
//        } catch (SQLException | ClassNotFoundException e){
//            throw new RuntimeException("Error from PhoneRepositoryImpl");
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
//    public int save(Phone phone) {
//        return 0;
//    }
//
//    @Override
//    public int update(Phone phone) {
//        return 0;
//    }
//
//    @Override
//    public void delete(Long applicantId) {
//
//    }
//}
