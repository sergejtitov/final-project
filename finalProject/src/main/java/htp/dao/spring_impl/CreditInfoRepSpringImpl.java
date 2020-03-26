package htp.dao.spring_impl;

import htp.dao.CreditInfoRepository;
import htp.entities.db_entities.CreditInfo;
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
public class CreditInfoRepSpringImpl implements CreditInfoRepository {
    public static final String INFO_ID = "info_id";
    public static final String LOAN_AMOUNT = "loan_amount";
    public static final String INTEREST_RATE = "interest_rate";
    public static final String BALANCE_AMOUNT = "balance_amount";
    public static final String BALANCE_TERM = "balance_term";
    public static final String PAYMENT = "payment";
    public static final String PERSONAL_NUMBER = "personal_number";
    public static final String APPLICANT_ID = "applicant_id";
    public static final String M_VALUE = "m_value";
    public static final Long UNIQUE_CREDIT_INFO = 1L;
    public static Long infoId;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CreditInfoRepSpringImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        final String getMaxId = "select max(info_id) m_value from m_credit_info";
        infoId = jdbcTemplate.queryForObject(getMaxId, this::getLongValue);
    }

    private Long getId() {
        infoId++;
        return infoId;
    }

    @Override
    public List<CreditInfo> findCreditInfosByPersonalNumber(String personalNumber) {
            final String getInfo = "select * from m_credit_info where personal_number = :personalNumber";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue(PERSONAL_NUMBER, personalNumber);
            return namedParameterJdbcTemplate.query(getInfo, param, this::fillCreditInfo);
    }

    @Override
    @Transactional
    public CreditInfo save(CreditInfo item) {
        final String createQuery = "INSERT INTO m_credit_info (info_id, loan_amount, interest_rate, balance_amount, balance_term, payment, applicant_id) " +
                "VALUES (:info_id, :loan_amount, :interest_rate, :balance_amount, :balance_term, :payment, :applicant_id);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(INFO_ID, getId());
        params.addValue(LOAN_AMOUNT, item.getLoanAmount());
        params.addValue(INTEREST_RATE, item.getInterestRate());
        params.addValue(BALANCE_AMOUNT, item.getBalanceAmount());
        params.addValue(BALANCE_TERM, item.getBalanceTerm());
        params.addValue(PAYMENT, item.getPayment());
        params.addValue(APPLICANT_ID, item.getPersonalNumber());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{INFO_ID});

        return findById(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    @Transactional
    public CreditInfo update(CreditInfo item) {
        final String updateInfo = "update m_credit_info set loan_amount = :loan_amount, interest_rate = :interest_rate, balance_amount = :balance_amount," +
                "balance_term = :balance_term, payment = :payment where info_id = :info_id";
        if (idExists(item.getInfoId())){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(LOAN_AMOUNT, item.getLoanAmount());
            params.addValue(INTEREST_RATE, item.getInterestRate());
            params.addValue(BALANCE_AMOUNT, item.getBalanceAmount());
            params.addValue(BALANCE_TERM, item.getBalanceTerm());
            params.addValue(PAYMENT, item.getPayment());
            params.addValue(INFO_ID, item.getInfoId());
            namedParameterJdbcTemplate.update(updateInfo,params);
            return findById(item.getInfoId());
        } else {
            System.out.println("Such Loan doesn't exists");
            return new CreditInfo();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        final String deleteInfo = "delete from m_credit_info where info_id = :info_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(INFO_ID, id);
        if (idNotExists(id)) {
            System.out.println("Such ID doesn't exists");
        } else {
            namedParameterJdbcTemplate.update(deleteInfo, params);
        }
    }

    @Override
    public CreditInfo findById(Long id) {
        final String findById = "select * from m_credit_info where info_id = :info_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(INFO_ID, id);
        if (idNotExists(id)){
            System.out.println("Such ID doesn't exists");
            return new CreditInfo();
        } else {
            return namedParameterJdbcTemplate.queryForObject(findById, params, this::fillCreditInfo);
        }
    }

    private Long getLongValue(ResultSet set, int i) throws SQLException {
        return set.getLong(M_VALUE);
    }

    private boolean idExists (Long id){
        try {
            final String getId = "select count(*) m_value from m_credit_info where info_id = :info_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue(INFO_ID, id);
            Long numberId = namedParameterJdbcTemplate.queryForObject(getId, param,this::getLongValue);
            return Objects.equals(numberId, UNIQUE_CREDIT_INFO);
        } catch (NullPointerException e){
            return true;
        }
    }

    private boolean idNotExists(Long id){
        return !idExists(id);
    }

    private CreditInfo fillCreditInfo(ResultSet set, int i) throws SQLException {
        CreditInfo creditInfo = new CreditInfo();
        creditInfo.setInfoId(set.getLong(INFO_ID));
        creditInfo.setLoanAmount(set.getDouble(LOAN_AMOUNT));
        creditInfo.setInterestRate(set.getDouble(INTEREST_RATE));
        creditInfo.setBalanceAmount(set.getDouble(BALANCE_AMOUNT));
        creditInfo.setBalanceTerm(set.getInt(BALANCE_TERM));
        creditInfo.setPayment(set.getDouble(PAYMENT));
        creditInfo.setPersonalNumber(set.getString(PERSONAL_NUMBER));
        return creditInfo;
    }
}
