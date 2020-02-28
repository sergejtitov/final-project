package htp.dao;

import htp.entities.Phone;

import java.util.List;

public interface PhoneRepository extends GenericDao<Phone, Long> {
    List<Phone> findPhonesByApplicantId(Long applicantId);
}

