package htp.dao.SpringImpl;

import htp.dao.DAOinterfaces.ApplicationRepository;
import htp.entities.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Timestamp;
import java.util.Objects;

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
    public static final String M_VALUE = "m_value";

    public static long appID;

    private JdbcTemplate jdbc;
    private NamedParameterJdbcTemplate namedParameter;

    public ApplicationRepSpringImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        namedParameter = new NamedParameterJdbcTemplate(jdbc);
        final String getMaxId = "select max(application_id) m_value from m_application";
        appID = jdbc.queryForObject(getMaxId, this::getLongValue);
    }

    private long getId() {
        appID++;
        return appID;
    }


    private Application fillApplication(ResultSet set, int i) throws SQLException {
        Application application = new Application();
        application.setApplicationId(set.getLong(APPLICATION_ID));
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
        return application;
    }

    @Override
    @Transactional  (rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Long save(Application application) {
            final String createQuery = "INSERT INTO m_application (application_id, user_id, creation_date, loan_type, " +
                    "product_code, loan_amount, currency, status) " +
                    "VALUES (:application_id, :user_id, :creation_date, :loan_type, :product_code, :loan_amount, :currency, :status);";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("application_id", getId());
            params.addValue("user_id", application.getUserId());
            params.addValue("creation_date", application.getCreationDate());
            params.addValue("loan_type", application.getLoanType());
            params.addValue("product_code", application.getProductCode());
            params.addValue("loan_amount", application.getLoanAmount());
            params.addValue("currency", application.getCurrency());
            params.addValue("status", application.getStatus());

            namedParameter.update(createQuery, params, keyHolder, new String[]{"application_id"});

            return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Application findById(Long applicationId) {
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
    public List<Application> findAll(int limit, int offset) {
        final String getAll = "select * from m_application order by application_id limit :limit offset :offset";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("limit", limit);
        params.addValue("offset", offset);
        return namedParameter.query(getAll, params, this::fillApplication);
    }

    @Override
    public List<Application> findSome(Long userId) {
        try {
            final String getSome = "select * from m_application where user_id = :userid";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("userid", userId);
            return namedParameter.query(getSome, param, this::fillApplication);
        } catch (Exception e){
            System.out.println("Such User doesn't exist");
            return null;
        }
    }

    @Override
    public List<Application> findSome(Long userId, int limit, int offset) {
        try {
            final String getSome = "select * from m_application where user_id = :user_id order by application_id limit :limit offset :offset";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("user_id", userId);
            param.addValue("limit", limit);
            param.addValue("offset", offset);
            return namedParameter.query(getSome, param, this::fillApplication);
        } catch (Exception e){
            System.out.println("Such User doesn't exist");
            return null;
        }
    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public Application update(Application application) {
        final String updateAppl = "update m_application set final_amount = :final_amount, decision = :decision, status = :status, payment = :payment where application_id = :application_id";
        if (idExists(application.getApplicationId())){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("final_amount", application.getFinalAmount());
            params.addValue("decision", application.getDecision());
            params.addValue("status", application.getStatus());
            params.addValue("payment", application.getPayment());
            params.addValue("application_id", application.getApplicationId());
            namedParameter.update(updateAppl,params);
            return findById(application.getApplicationId());
        } else {
            System.out.println("Such Application doesn't exists");
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long applicationId) {
        final String deleteAppl = "delete from m_application where application_id = :application_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("application_id", applicationId);
        if (!idExists(applicationId)) {
            System.out.println("Such ID doesn't exists");
        } else {
            namedParameter.update(deleteAppl, params);
        }
    }



    private long getLongValue(ResultSet set, int i) throws SQLException {
        return set.getLong(M_VALUE);
    }

    private boolean idExists (Long id){
        final String getId = "select count(*) m_value from m_application where application_id = :application_id";
        try {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("application_id", id);
            long numberId = namedParameter.queryForObject(getId, param,this::getLongValue);
            return numberId >0;
        } catch (NullPointerException e){
            return true;
        }
    }
}
