package htp.dao;

import htp.domain.model.Address;

import java.util.List;

public interface AddressRepository extends GenericDao<Address, Long> {
    List<Address> findAddressByApplicantId (Long applicantId);
}