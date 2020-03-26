package htp.dao;

import htp.entities.db_entities.CreditInfo;

import java.util.List;

public interface CreditInfoRepository extends GenericDao<CreditInfo, Long> {
    List<CreditInfo> findCreditInfosByPersonalNumber(String personalNumber);
    CreditInfo findCreditInfoByApplicationId(Long applicationId);
}