package com.htp.repository;

import com.htp.domain.model.Address;

import java.util.List;

public interface AddressRepository extends GenericDao<Address, Long> {
    List<Address> findAddressByApplicantId (Long applicantId);
}