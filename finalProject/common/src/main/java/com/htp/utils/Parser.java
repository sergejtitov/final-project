package com.htp.utils;

import com.htp.domain.model.Application;
import com.htp.domain.model.CreditInfo;
import com.htp.domain.model.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parser {
    public static CreditInfo createCreditInfo (Application application, Product product){
        CreditInfo creditInfo = new CreditInfo();
        creditInfo.setLoanAmount(application.getFinalAmount());
        creditInfo.setInterestRate(product.getInterestRate());
        creditInfo.setBalanceAmount(application.getFinalAmount());
        creditInfo.setBalanceTerm(product.getLoanTerm());
        creditInfo.setPayment(application.getPayment());
        creditInfo.setApplicationId(application.getApplicationId());
        creditInfo.setPersonalNumber(Functions.findMainApplicantPersonalNumber(application.getApplicants()));
        return creditInfo;
    }
}
