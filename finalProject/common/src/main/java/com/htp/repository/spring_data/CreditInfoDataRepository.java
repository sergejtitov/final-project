package com.htp.repository.spring_data;


import com.htp.domain.model.CreditInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditInfoDataRepository extends CrudRepository<CreditInfo, Long>, JpaRepository<CreditInfo, Long> {

    CreditInfo findCreditInfoByApplicationId(Long applicationId);

    List<CreditInfo> findCreditInfoByPersonalNumber (String personalNumber);

}