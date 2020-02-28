package htp.dao.spring_impl;

import htp.dao.AddressRepository;
import htp.entities.Address;
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
public class AddressRepSpringImpl implements AddressRepository {
    public static final String ADDRESS_ID = "address_id";
    public static final String APPLICANT_ID = "applicant_id";
    public static final String ADDRESS = "address";
    public static final String ADDRESS_TYPE = "address_type";
    public static final String M_VALUE = "m_value";
    public static final Long UNIQUE_ID = 1L;
    public static Long addressId;

    private NamedParameterJdbcTemplate namedParameter;

    public AddressRepSpringImpl(JdbcTemplate jdbc, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameter = namedParameterJdbcTemplate;
        final String getMaxId = "select max(address_id) m_value from m_address";
        addressId = jdbc.queryForObject(getMaxId, this::getLongValue);
    }
    private Long getId() {
        addressId++;
        return addressId;
    }

    @Override
    public List<Address> findAddressByApplicantId(Long applicantId) {
            final String getAddress = "select * from m_address where applicant_id = :applicant_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue(APPLICANT_ID, applicantId);
            return namedParameter.query(getAddress, param, this::fillAddress);
    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public Address save(Address item) {
        final String createQuery = "INSERT INTO m_address (address_id, applicant_id, address, address_type) " +
                "VALUES (:address_id, :applicant_id, :address, :address_type);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ADDRESS_ID, getId());
        params.addValue(APPLICANT_ID, item.getApplicantId());
        params.addValue(ADDRESS, item.getAddress());
        params.addValue(ADDRESS_TYPE, item.getAddressType());

        namedParameter.update(createQuery, params, keyHolder, new String[]{ADDRESS_ID});
        return findById(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    @Transactional  (rollbackFor = Exception.class)
    public Address update(Address item) {
        final String updateAddress = "update m_address set address = :address, address_type = :address_type where address_id = :address_id";
        if (idExists(item.getAddressId())){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(ADDRESS, item.getAddress());
            params.addValue(ADDRESS_TYPE, item.getAddressType());
            namedParameter.update(updateAddress,params);
            return findById(item.getAddressId());
        } else {
            System.out.println("Such Address doesn't exists");
            return new Address();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        final String deleteAddress = "delete from m_address where address_id = :address_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ADDRESS_ID, id);
        if (idNotExists(id)) {
            System.out.println("Such ID doesn't exists");
        } else {
            namedParameter.update(deleteAddress, params);
        }
    }

    @Override
    public Address findById(Long id) {
            final String getAddress = "select * from m_address where address_id = :address_id";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue(ADDRESS_ID, id);
            return namedParameter.queryForObject(getAddress, param, this::fillAddress);
    }

    private Long getLongValue(ResultSet set, int i) throws SQLException {
        return set.getLong(M_VALUE);
    }

    private boolean idExists (Long id){
        final String getId = "select count(*) m_value from m_address where address_id = :address_id";
        try {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue(ADDRESS_ID, id);
            Long numberId = namedParameter.queryForObject(getId, param,this::getLongValue);
            return Objects.equals(numberId, UNIQUE_ID);
        } catch (NullPointerException e){
            return true;
        }
    }

    private boolean idNotExists (Long id){
        return !idExists(id);
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
