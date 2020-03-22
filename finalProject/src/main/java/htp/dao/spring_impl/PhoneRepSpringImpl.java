/*package htp.dao.spring_impl;

import htp.dao.PhoneRepository;
import htp.entities.db_entities.Phone;
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
public class PhoneRepSpringImpl implements PhoneRepository {
    public static final String PHONE_ID = "phone_id";
    public static final String APPLICANT_ID = "applicant_id";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String PHONE_TYPE = "phone_type";
    public static final String M_VALUE = "m_value";
    public static final Long UNIQUE_ID = 1L;

    public static Long phoneID;

    private NamedParameterJdbcTemplate namedParameter;

    public PhoneRepSpringImpl(JdbcTemplate jdbc, NamedParameterJdbcTemplate namedParameter) {
        this.namedParameter = namedParameter;
        final String getMaxId = "select max(phone_id) m_value from m_phone";
        phoneID = jdbc.queryForObject(getMaxId, this::getLongValue);
    }

    private Long getId() {
        phoneID++;
        return phoneID;
    }

    @Override
    public List<Phone> findPhonesByApplicantId(Long applicantId) {
        final String getAddress = "select * from m_phone where applicant_id = :applicant_id";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue(APPLICANT_ID, applicantId);
        return namedParameter.query(getAddress, param, this::fillPhone);
    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public Phone save(Phone item) {
        final String createQuery = "INSERT INTO m_phone (phone_id, applicant_id, phone_number, phone_type) " +
                "VALUES (:phone_id, :applicant_id, :phone_number, :phone_type);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(PHONE_ID, getId());
        params.addValue(APPLICANT_ID, item.getApplicantId());
        params.addValue(PHONE_NUMBER, item.getPhoneNumber());
        params.addValue(PHONE_TYPE, item.getPhoneType());

        namedParameter.update(createQuery, params, keyHolder, new String[]{PHONE_ID});

        return findById(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public Phone update(Phone item) {
        final String updatePhone = "update m_phone set phone_number = :phone_number, phone_type = :phone_type where phone_id = :phone_id";
        if (idExists(item.getPhoneId())){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(PHONE_NUMBER, item.getPhoneNumber());
            params.addValue(PHONE_TYPE, item.getPhoneNumber());
            namedParameter.update(updatePhone,params);
            return findById(item.getPhoneId());
        } else {
            System.out.println("Such Phone doesn't exists");
            return new Phone();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        final String deletePhone = "delete from m_phone where phone_id = :phone_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(PHONE_ID, id);
        if (idNotExists(id)) {
            System.out.println("Such ID doesn't exists");
        } else {
            namedParameter.update(deletePhone, params);
        }
    }

    @Override
    public Phone findById(Long id) {
        final String getAddress = "select * from m_phone where phone_id = :phone_id";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue(PHONE_ID, id);
        return namedParameter.queryForObject(getAddress, param, this::fillPhone);
    }

    private Long getLongValue(ResultSet set, int i) throws SQLException {
        return set.getLong(M_VALUE);
    }

    private boolean idExists (Long id){
        final String getId = "select count(*) m_value from m_phone where phone_id = :phone_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue(PHONE_ID, id);
            Long numberId = namedParameter.queryForObject(getId, param,this::getLongValue);
            return Objects.equals(numberId, UNIQUE_ID);
    }
    private boolean idNotExists (Long id){
        return !idExists(id);
    }

    private Phone fillPhone(ResultSet set, int i) throws SQLException {
        Phone phone = new Phone();
        phone.setPhoneId(set.getLong(PHONE_ID));
        phone.setApplicantId(set.getLong(APPLICANT_ID));
        phone.setPhoneType(set.getString(PHONE_TYPE));
        phone.setPhoneNumber(set.getString(PHONE_NUMBER));
        return phone;
    }
}*/
