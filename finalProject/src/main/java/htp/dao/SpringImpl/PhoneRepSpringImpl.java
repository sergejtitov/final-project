package htp.dao.SpringImpl;

import htp.dao.DAOinterfaces.PhoneRepository;
import htp.entities.Phone;
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
import java.util.Objects;

@Repository
@ComponentScan("htp")
public class PhoneRepSpringImpl implements PhoneRepository {
    public static final String PHONE_ID = "phone_id";
    public static final String APPLICANT_ID = "applicant_id";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String PHONE_TYPE = "phone_type";
    public static final String M_VALUE = "m_value";

    public static long phoneID;

    private JdbcTemplate jdbc;
    private NamedParameterJdbcTemplate namedParameter;

    public PhoneRepSpringImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        namedParameter = new NamedParameterJdbcTemplate(jdbc);
        final String getMaxId = "select max(phone_id) m_value from m_phone";
        phoneID = jdbc.queryForObject(getMaxId, this::getLongValue);
    }

    private long getId() {
        phoneID++;
        return phoneID;
    }

    @Override
    public List<Phone> findSome(Long applicantId) {
        try {
            final String getAddress = "select * from m_phone where applicant_id = :applicant_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("applicant_id", applicantId);
            return namedParameter.query(getAddress, param, this::fillPhone);
        } catch (Exception e){
            System.out.println("Such Applicant doesn't exist");
            return null;
        }
    }

    @Override
    @Transactional  (rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Long save(Phone item) {
        final String createQuery = "INSERT INTO m_phone (phone_id, applicant_id, phone_number, phone_type) " +
                "VALUES (:phone_id, :applicant_id, :phone_number, :phone_type);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("phone_id", getId());
        params.addValue("applicant_id", item.getApplicantId());
        params.addValue("phone_number", item.getPhoneNumber());
        params.addValue("phone_type", item.getPhoneType());

        namedParameter.update(createQuery, params, keyHolder, new String[]{"phone_id"});

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    @Transactional  (rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Phone update(Phone item) {
        final String updatePhone = "update m_phone set phone_number = :phone_number, phone_type = :phone_type where phone_id = :phone_id";
        if (idExists(item.getPhoneId())){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("phone_number", item.getPhoneNumber());
            params.addValue("phone_type", item.getPhoneNumber());
            namedParameter.update(updatePhone,params);
            return findById(item.getPhoneId());
        } else {
            System.out.println("Such Phone doesn't exists");
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        final String deletePhone = "delete from m_phone where phone_id = :phone_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("phone_id", id);
        if (!idExists(id)) {
            System.out.println("Such ID doesn't exists");
        } else {
            namedParameter.update(deletePhone, params);
        }
    }

    @Override
    public Phone findById(Long id) {
        try {
            final String getAddress = "select * from m_phone where phone_id = :phone_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("phone_id", id);
            return namedParameter.queryForObject(getAddress, param, this::fillPhone);
        } catch (Exception e){
            System.out.println("Such Phone doesn't exist");
            return null;
        }
    }

    private long getLongValue(ResultSet set, int i) throws SQLException {
        return set.getLong(M_VALUE);
    }

    private boolean idExists (Long id){
        final String getId = "select count(*) m_value from m_phone where phone_id = :phone_id";
        try {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("phone_id", id);
            long numberId = namedParameter.queryForObject(getId, param,this::getLongValue);
            return numberId >0;
        } catch (NullPointerException e){
            return true;
        }
    }

    private Phone fillPhone(ResultSet set, int i) throws SQLException {
        Phone phone = new Phone();
        phone.setPhoneId(set.getLong(PHONE_ID));
        phone.setApplicantId(set.getLong(APPLICANT_ID));
        phone.setPhoneType(set.getString(PHONE_TYPE));
        phone.setPhoneNumber(set.getString(PHONE_NUMBER));
        return phone;
    }
}
