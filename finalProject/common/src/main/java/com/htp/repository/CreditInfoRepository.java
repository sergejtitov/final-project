package com.htp.repository;

import com.htp.domain.model.CreditInfo;

import java.util.List;

public interface CreditInfoRepository extends GenericDao<CreditInfo, Long> {
    List<CreditInfo> findCreditInfosByPersonalNumber(String personalNumber);
    CreditInfo findCreditInfoByApplicationId(Long applicationId);
}