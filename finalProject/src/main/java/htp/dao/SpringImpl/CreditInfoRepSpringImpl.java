package htp.dao.SpringImpl;

import htp.dao.DAOinterfaces.CreditInfoRepository;
import htp.entities.CreditInfo;
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
    public static final String APPLICANT_ID = "applicant_id";
    public static final String M_VALUE = "m_value";
    public static long infoId;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CreditInfoRepSpringImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        final String getMaxId = "select max(info_id) m_value from m_credit_info";
        infoId = jdbcTemplate.queryForObject(getMaxId, this::getLongValue);
    }

    private long getId() {
        infoId++;
        return infoId;
    }

    @Override
    public List<CreditInfo> findSome(Long applicantId) {
        try {
            final String getInfo = "select * from m_credit_info where info_id = :info_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("info_id", applicantId);
            return namedParameterJdbcTemplate.query(getInfo, param, this::fillCreditInfo);
        } catch (Exception e){
            System.out.println("Such Applicant doesn't exist");
            return null;
        }
    }

    @Override
    public Long save(CreditInfo item) {
        final String createQuery = "INSERT INTO m_credit_info (info_id, loan_amount, interest_rate, balance_amount, balance_term, payment, applicant_id) " +
                "VALUES (:info_id, :loan_amount, :interest_rate, :balance_amount, :balance_term, :payment, :applicant_id);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("info_id", getId());
        params.addValue("loan_amount", item.getLoanAmount());
        params.addValue("interest_rate", item.getInterestRate());
        params.addValue("balance_amount", item.getBalanceAmount());
        params.addValue("balance_term", item.getBalanceTerm());
        params.addValue("payment", item.getPayment());
        params.addValue("applicant_id", item.getApplicantId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"info_id"});

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public CreditInfo update(CreditInfo item) {
        final String updateInfo = "update m_credit_info set loan_amount = :loan_amount, interest_rate = :interest_rate, balance_amount = :balance_amount," +
                "balance_term = :balance_term, payment = :payment where info_id = :info_id";
        if (idExists(item.getInfoId())){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("loan_amount", item.getLoanAmount());
            params.addValue("interest_rate", item.getInterestRate());
            params.addValue("balance_amount", item.getBalanceAmount());
            params.addValue("balance_term", item.getBalanceTerm());
            params.addValue("payment", item.getPayment());
            params.addValue("info_id", item.getInfoId());
            namedParameterJdbcTemplate.update(updateInfo,params);
            return findById(item.getInfoId());
        } else {
            System.out.println("Such Loan doesn't exists");
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        final String deleteInfo = "delete from m_credit_info where info_id = :info_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("info_id", id);
        if (!idExists(id)) {
            System.out.println("Such ID doesn't exists");
        } else {
            namedParameterJdbcTemplate.update(deleteInfo, params);
        }
    }

    @Override
    public CreditInfo findById(Long id) {
        final String findById = "select * from m_credit_info where info_id = :info_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("info_id", id);
        if (!idExists(id)){
            System.out.println("Such ID doesn't exists");
            return null;
        } else {
            return namedParameterJdbcTemplate.queryForObject(findById, params, this::fillCreditInfo);
        }
    }

    private long getLongValue(ResultSet set, int i) throws SQLException {
        return set.getLong(M_VALUE);
    }
    private boolean idExists (Long id){
        final String getId = "select count(*) m_value from m_credit_info where info_id = :info_id";
        try {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("info_id", id);
            long numberId = namedParameterJdbcTemplate.queryForObject(getId, param,this::getLongValue);
            return numberId >0;
        } catch (NullPointerException e){
            return true;
        }
    }

    private CreditInfo fillCreditInfo(ResultSet set, int i) throws SQLException {
        CreditInfo creditInfo = new CreditInfo();
        creditInfo.setInfoId(set.getLong(INFO_ID));
        creditInfo.setLoanAmount(set.getDouble(LOAN_AMOUNT));
        creditInfo.setInterestRate(set.getDouble(INTEREST_RATE));
        creditInfo.setBalanceAmount(set.getDouble(BALANCE_AMOUNT));
        creditInfo.setBalanceTerm(set.getInt(BALANCE_TERM));
        creditInfo.setPayment(set.getDouble(PAYMENT));
        creditInfo.setApplicantId(set.getLong(APPLICANT_ID));
        return creditInfo;
    }
}
