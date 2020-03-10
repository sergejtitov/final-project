package htp.dao;

import htp.entities.db_entities.Address;

import java.util.List;

public interface AddressRepository extends GenericDao<Address, Long> {
    List<Address> findAddressByApplicantId (Long applicantId);
}