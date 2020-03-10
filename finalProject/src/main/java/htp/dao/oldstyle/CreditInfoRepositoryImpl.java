//package htp.dao.oldstyle;
//
//import htp.dao.CreditInfoRepository;
//import htp.entities.dbentities.Address;
//import htp.entities.dbentities.CreditInfo;
//import htp.exceptions.NoSuchEntityException;
//import org.apache.commons.dbcp2.BasicDataSource;
//
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.List;
//
//public class CreditInfoRepositoryImpl implements CreditInfoRepository {
//    public static final String url = "jdbc:postgresql://localhost:5432/Final_ProjectDB";
//    public static final String username = "phone_FP";
//    public static final String password = "5432";
//    public static final int initialSize = 10;
//    public static final String INFO_ID = "info_id";
//    public static final String LOAN_AMOUNT = "loan_amount";
//    public static final String INTEREST_RATE = "interest_rate";
//    public static final String BALANCE_AMOUNT = "balance_amount";
//    public static final String BALANCE_TERM = "balance_term";
//    public static final String PAYMENT = "payment";
//    public static final String APPLICANT_ID = "applicant_id";
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
//    private CreditInfo fillCreditInfo(ResultSet set) throws SQLException {
//        CreditInfo creditInfo = new CreditInfo();
//        creditInfo.setInfoId(set.getLong(INFO_ID));
//        creditInfo.setLoanAmount(set.getDouble(LOAN_AMOUNT));
//        creditInfo.setInterestRate(set.getDouble(INTEREST_RATE));
//        creditInfo.setBalanceAmount(set.getDouble(BALANCE_AMOUNT));
//        creditInfo.setBalanceTerm(set.getInt(BALANCE_TERM));
//        creditInfo.setPayment(set.getDouble(PAYMENT));
//        creditInfo.setApplicantId(set.getLong(APPLICANT_ID));
//        return creditInfo;
//    }
//
//    @Override
//    public int save(CreditInfo creditInfo) {
//        return 0;
//    }
//
//    @Override
//    public List<CreditInfo> findSome(Long applicantId) {
//        Connection connection = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, username, password);
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from m_credit_info where m_credit_info.applicant_id = ?");
//            preparedStatement.setLong(1,applicantId);
//            ResultSet set = preparedStatement.executeQuery();
//
//            while (set.next()){
//                try {
//                    System.out.println(fillCreditInfo(set));
//                }catch (NoSuchEntityException e){
//                    throw new NoSuchEntityException(String.format("CreditInfo with applicant_id %s not found", applicantId));
//                }
//
//            }
//        } catch (SQLException | ClassNotFoundException e){
//            throw new RuntimeException("Error from CreditInfoRepositoryImpl");
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
//    public int update(CreditInfo creditInfo) {
//        return 0;
//    }
//
//    @Override
//    public void delete(String applicationId) {
//
//    }
//}
