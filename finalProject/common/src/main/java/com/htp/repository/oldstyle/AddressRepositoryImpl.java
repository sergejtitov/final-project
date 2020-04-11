//package com.htp.dao.oldstyle;
//
//import com.htp.dao.AddressRepository;
//import htp.entities.dbentities.Address;
//import com.htp.exceptions.NoSuchEntityException;
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
//public class AddressRepositoryImpl implements AddressRepository {
//    public static final String url = "jdbc:postgresql://localhost:5432/Final_ProjectDB";
//    public static final String username = "user_FP";
//    public static final String password = "5432";
//    public static final int initialSize = 10;
//    public static final String ADDRESS_ID = "address_id";
//    public static final String APPLICANT_ID = "applicant_id";
//    public static final String ADDRESS_TYPE = "address_type";
//    public static final String ADDRESS = "address";
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
//    private Address fillAddress(ResultSet set) throws SQLException {
//        Address address = new Address();
//        address.setAddressId(set.getLong(ADDRESS_ID));
//        address.setApplicantId(set.getLong(APPLICANT_ID));
//        address.setAddressType(set.getString(ADDRESS_TYPE));
//        address.setAddress(set.getString(ADDRESS));
//        return address;
//    }
//
//    @Override
//    public int save(Address address) {
//        return 0;
//    }
//
//    @Override
//    public List<Address> findSome(Long applicantId) {
//        Connection connection = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, username, password);
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from m_address where m_address.applicant_id = ?");
//            preparedStatement.setLong(1,applicantId);
//            ResultSet set = preparedStatement.executeQuery();
//
//            while (set.next()){
//                try {
//                    System.out.println(fillAddress(set));
//                }catch (NoSuchEntityException e){
//                    throw new NoSuchEntityException(String.format("Address with applicant_id %s not found", applicantId));
//                }
//
//            }
//        } catch (SQLException | ClassNotFoundException e){
//            throw new RuntimeException("Error from AddressRepositoryImpl");
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
//    public int update(Address address) {
//        return 0;
//    }
//
//    @Override
//    public void delete(Long applicantId) {
//
//    }
//}
