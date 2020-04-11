package com.htp.domain.wrappers;


import com.htp.domain.dictionaries.Decision;
import com.htp.domain.model.Applicant;
import com.htp.domain.model.CreditInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class ApplicantWrapper {
    private Applicant applicant;
    private List<CreditInfo> creditInfoList;

    private Integer ageMonths;
    private Integer maxTerm;
    private Integer loanTerm;

    private Double externalPayments;
    private Double maxAmount;

    private Integer score;

    private Decision decision;

}
