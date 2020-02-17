package htp.dao.SpringImpl;

import htp.dao.DAOinterfaces.AddressRepository;
import htp.entities.Address;
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
public class AddressRepSpringImpl implements AddressRepository {
    public static final String ADDRESS_ID = "address_id";
    public static final String APPLICANT_ID = "applicant_id";
    public static final String ADDRESS = "address";
    public static final String ADDRESS_TYPE = "address_type";
    public static final String M_VALUE = "m_value";

    public static long addressID;

    private JdbcTemplate jdbc;
    private NamedParameterJdbcTemplate namedParameter;

    public AddressRepSpringImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        namedParameter = new NamedParameterJdbcTemplate(jdbc);
        final String getMaxId = "select max(address_id) m_value from m_address";
        addressID = jdbc.queryForObject(getMaxId, this::getLongValue);
    }

    private long getId() {
        addressID++;
        return addressID;
    }

    @Override
    public List<Address> findSome(Long applicantId) {
        try {
            final String getAddress = "select * from m_address where applicant_id = :applicant_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("applicant_id", applicantId);
            return namedParameter.query(getAddress, param, this::fillAddress);
        } catch (Exception e){
            System.out.println("Such Applicant doesn't exist");
            return null;
        }
    }

    @Override
    @Transactional  (rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Long save(Address item) {
        final String createQuery = "INSERT INTO m_address (address_id, applicant_id, address, address_type) " +
                "VALUES (:address_id, :applicant_id, :address, :address_type);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("address_id", getId());
        params.addValue("applicant_id", item.getApplicantId());
        params.addValue("address", item.getAddress());
        params.addValue("address_type", item.getAddressType());

        namedParameter.update(createQuery, params, keyHolder, new String[]{"address_id"});

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    @Transactional  (rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Address update(Address item) {
        final String updateAddress = "update m_address set address = :address, address_type = :address_type where address_id = :address_id";
        if (idExists(item.getAddressId())){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("address", item.getAddress());
            params.addValue("address_type", item.getAddressType());
            namedParameter.update(updateAddress,params);
            return findById(item.getAddressId());
        } else {
            System.out.println("Such Address doesn't exists");
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        final String deleteAddress = "delete from m_address where address_id = :address_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("address_id", id);
        if (!idExists(id)) {
            System.out.println("Such ID doesn't exists");
        } else {
            namedParameter.update(deleteAddress, params);
        }
    }

    @Override
    public Address findById(Long id) {
        try {
            final String getAddress = "select * from m_address where address_id = :address_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("address_id", id);
            return namedParameter.queryForObject(getAddress, param, this::fillAddress);
        } catch (Exception e){
            System.out.println("Such Address doesn't exist");
            return null;
        }
    }

    private long getLongValue(ResultSet set, int i) throws SQLException {
        return set.getLong(M_VALUE);
    }

    private boolean idExists (Long id){
        final String getId = "select count(*) m_value from m_address where address_id = :address_id";
        try {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("address_id", id);
            long numberId = namedParameter.queryForObject(getId, param,this::getLongValue);
            return numberId >0;
        } catch (NullPointerException e){
            return true;
        }
    }

    private Address fillAddress(ResultSet set, int i) throws SQLException {
        Address address = new Address();
        address.setAddressId(set.getLong(ADDRESS_ID));
        address.setApplicantId(set.getLong(APPLICANT_ID));
        address.setAddressType(set.getString(ADDRESS_TYPE));
        address.setAddress(set.getString(ADDRESS));
        return address;
    }
}
