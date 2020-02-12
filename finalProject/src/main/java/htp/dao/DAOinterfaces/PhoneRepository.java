package htp.dao.DAOinterfaces;

import htp.entities.Phone;

import java.util.List;

public interface PhoneRepository extends GenericDao<Phone, Long> {
    List<Phone> findSome(Long applicantId);
}


