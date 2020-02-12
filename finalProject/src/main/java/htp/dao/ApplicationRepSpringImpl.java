package htp.dao;

import htp.dao.DAOinterfaces.ApplicationRepository;
import htp.entities.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Timestamp;

@Repository
@ComponentScan("htp")
public class ApplicationRepSpringImpl implements ApplicationRepository {
    public static final String APPLICATION_ID = "application_id";
    public static final String USER_ID = "user_id";
    public static final String CREATION_DATE = "creation_date";
    public static final String LOAN_TYPE = "loan_type";
    public static final String PRODUCT_CODE = "product_code";
    public static final String LOAN_AMOUNT = "loan_amount";
    public static final String CURRENCY = "currency";
    public static final String FINAL_AMOUNT = "final_amount";
    public static final String DECISION = "decision";
    public static final String STATUS = "status";
    public static final String PAYMENT = "payment";
    public static final String APPLICATION_OWNER = "application_owner";

    public static long appID;

    private JdbcTemplate jdbc;
    private NamedParameterJdbcTemplate namedParameter;

    @Autowired
    public ApplicationRepSpringImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        namedParameter = new NamedParameterJdbcTemplate(jdbc);
    }

    @PostConstruct
    private void setAppID(){
        final String maxId = "select max(to_number(substring(application_id from 3 for position('_'  in application_id)))) from m_application where to_number(substring(application_id from position('_'  in application_id) for 4) ) = :year";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("year", new Timestamp(System.currentTimeMillis()).getYear());
    }
    private Application fillApplication(ResultSet set, int i) throws SQLException {
        Application application = new Application();
        application.setApplicationId(set.getString(APPLICATION_ID));
        application.setUserId(set.getLong(USER_ID));
        application.setCreationDate(set.getTimestamp(CREATION_DATE));
        application.setLoanType(set.getInt(LOAN_TYPE));
        application.setProductCode(set.getLong(PRODUCT_CODE));
        application.setLoanAmount(set.getDouble(LOAN_AMOUNT));
        application.setCurrency(set.getString(CURRENCY));
        application.setFinalAmount(set.getDouble(FINAL_AMOUNT));
        application.setDecision(set.getString(DECISION));
        application.setStatus(set.getString(STATUS));
        application.setPayment(set.getDouble(PAYMENT));
        application.setOwner(set.getLong(APPLICATION_OWNER));
        return application;
    }

    @Override
    public int save(Application application) {
        return 0;
    }

    @Override
    public Application findOne(String applicationId) {
        try {
            final String getId = "select * from m_application where application_id = :applicationid";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("applicationid", applicationId);
            return namedParameter.queryForObject(getId, param, this::fillApplication);
        } catch (Exception e){
            System.out.println("Such Application doesn't exist");
            return null;
        }
    }

    @Override
    public List<Application> findAll() {
        final String getAll = "select * from m_application";
        return jdbc.query(getAll, this::fillApplication);
    }

    @Override
    public List<Application> findSome(Long userId) {
        try {
            final String getSome = "select * from m_application where user_id = :userid";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("userid", userId);
            return namedParameter.query(getSome, param, this::fillApplication);
        } catch (Exception e){
            System.out.println("Such Application doesn't exist");
            return null;
        }


    }

    @Override
    public int update(Application application) {
        return 0;
    }

    @Override
    public void delete(String applicationId) {

    }
}
