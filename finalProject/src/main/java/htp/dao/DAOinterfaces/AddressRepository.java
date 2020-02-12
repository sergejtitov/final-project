package htp.dao.DAOinterfaces;

import htp.entities.Address;

import java.util.List;

public interface AddressRepository extends GenericDao<Address, Long> {
    List<Address> findSome (Long applicantId);
}