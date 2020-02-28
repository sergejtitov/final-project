package htp.dao.spring_impl;

import htp.dao.ApplicationRepository;
import htp.entities.Application;
import htp.entities.dictionaries.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";
    public static final Long UNIQUE_APPLICATION = 1L;

    public static Long appID;

    private NamedParameterJdbcTemplate namedParameter;

    public ApplicationRepSpringImpl(JdbcTemplate jdbc, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        namedParameter = namedParameterJdbcTemplate;
        final String getMaxId = "select max(application_id) m_value from m_application";
        appID = jdbc.queryForObject(getMaxId, this::getLongValue);
    }

    private Long getId() {
        appID++;
        return appID;
    }


    private Application fillApplication(ResultSet set, int i) throws SQLException {
        Application application = new Application();
        application.setApplicationId(set.getLong(APPLICATION_ID));
        application.setUserId(set.getLong(USER_ID));
        application.setCreationDate(set.getTimestamp(CREATION_DATE));
        application.setLoanType(new LoanType(set.getInt(LOAN_TYPE)));
        application.setProductCode(new ProductCode(set.getLong(PRODUCT_CODE)));
        application.setLoanAmount(set.getDouble(LOAN_AMOUNT));
        application.setCurrency(MyCurrency.valueOf(set.getString(CURRENCY)));
        application.setFinalAmount(set.getDouble(FINAL_AMOUNT));
        application.setDecision(Decision.valueOf(set.getString(DECISION).toUpperCase()));
        application.setStatus(Status.valueOf(set.getString(STATUS).toUpperCase()));
        application.setPayment(set.getDouble(PAYMENT));
        return application;
    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public Application save(Application application) {
            final String createQuery = "INSERT INTO m_application (application_id, user_id, creation_date, loan_type, " +
                    "product_code, loan_amount, currency, decision, status) " +
                    "VALUES (:application_id, :user_id, :creation_date, :loan_type, :product_code, :loan_amount, :currency, :decision, :status);";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(APPLICATION_ID, getId());
            params.addValue(USER_ID, application.getUserId());
            params.addValue(CREATION_DATE, application.getCreationDate());
            params.addValue(LOAN_TYPE, application.getLoanType().getIndex());
            params.addValue(PRODUCT_CODE, application.getProductCode().getIndex());
            params.addValue(LOAN_AMOUNT, application.getLoanAmount());
            params.addValue(CURRENCY, application.getCurrency().toString());
            params.addValue(DECISION, application.getDecision().toString());
            params.addValue(STATUS, application.getStatus().toString());

            namedParameter.update(createQuery, params, keyHolder, new String[]{APPLICATION_ID});

            return findById(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    public Application findById(Long applicationId) {
        final String getId = "select * from m_application where application_id = :application_id";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue(APPLICATION_ID, applicationId);
        return namedParameter.queryForObject(getId, param, this::fillApplication);
    }

    @Override
    public List<Application> findAll(int limit, int offset) {
        final String getAll = "select * from m_application order by application_id limit :limit offset :offset";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(LIMIT, limit);
        params.addValue(OFFSET, offset);
        return namedParameter.query(getAll, params, this::fillApplication);
    }

    @Override
    public List<Application> findApplicationByUserId(Long userId) {
        final String getSome = "select * from m_application where user_id = :user_id";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue(USER_ID, userId);
        return namedParameter.query(getSome, param, this::fillApplication);
    }

    @Override
    public List<Application> findApplicationByUserId(Long userId, int limit, int offset) {
        final String getSome = "select * from m_application where user_id = :user_id order by application_id limit :limit offset :offset";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue(USER_ID, userId);
        param.addValue(LIMIT, limit);
        param.addValue(OFFSET, offset);
        return namedParameter.query(getSome, param, this::fillApplication);

    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public Application update(Application application) {
        final String updateAppl = "update m_application set final_amount = :final_amount, decision = :decision, status = :status, payment = :payment where application_id = :application_id";
        if (idExists(application.getApplicationId())){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(FINAL_AMOUNT, application.getFinalAmount());
            params.addValue(DECISION, application.getDecision().toString());
            params.addValue(STATUS, application.getStatus().toString());
            params.addValue(PAYMENT, application.getPayment());
            params.addValue(APPLICATION_ID, application.getApplicationId());
            namedParameter.update(updateAppl,params);
            return findById(application.getApplicationId());
        } else {
            System.out.println("Such Application doesn't exists");
            return new Application();
        }
    }

    @Override
    @Transactional
    public void delete(Long applicationId) {
        final String deleteAppl = "delete from m_application where application_id = :application_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(APPLICATION_ID, applicationId);
        if (idNotExists(applicationId)) {
            System.out.println("Such ID doesn't exists");
        } else {
            namedParameter.update(deleteAppl, params);
        }
    }



    private Long getLongValue(ResultSet set, int i) throws SQLException {
        return set.getLong(M_VALUE);
    }

    private boolean idExists (Long id){
        try {
            final String getId = "select count(*) m_value from m_application where application_id = :application_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue(APPLICATION_ID, id);
            Long numberId = namedParameter.queryForObject(getId, param,this::getLongValue);
            return Objects.equals(numberId, UNIQUE_APPLICATION);
        } catch (NullPointerException e){
            return true;
        }
    }

    private boolean idNotExists(Long id){
        return !idExists(id);
    }
}
