package htp.dao.DAOinterfaces;

import htp.entities.CreditInfo;

import java.util.List;

public interface CreditInfoRepository extends GenericDao<CreditInfo, Long> {
    List<CreditInfo> findSome(Long applicantId);
}