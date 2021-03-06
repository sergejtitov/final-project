package com.htp.processors;


import com.htp.domain.dictionaries.Decision;
import com.htp.domain.model.CreditInfo;
import com.htp.domain.model.Product;
import com.htp.domain.wrappers.ApplicantWrapper;
import com.htp.processors.scorecards.ScoreCalculatorImpl;
import com.htp.utils.Functions;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;


@Slf4j
@Data
@Service
public class ApplicantProcessor {
    public static final Integer MAX_AGE = 840;
    public static final int AGE_18 = 216;
    public static final int AGE_70 = 840;
    public static final Double ZERO = 0D;

    private final ScoreCalculatorImpl scoreCalculator;

    public ApplicantProcessor(ScoreCalculatorImpl scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
    }

    public ApplicantWrapper definiteTerm(ApplicantWrapper applicantWrapper, Product product){
        applicantWrapper.setAgeMonths(Functions.calculateAge(applicantWrapper.getApplicant().getBirthdayDate()));
        applicantWrapper.setMaxTerm(Functions.positiveOrZeroInt(Functions.calculateMonths(applicantWrapper.getAgeMonths(), MAX_AGE)));
        applicantWrapper.setLoanTerm(Functions.positiveOrZeroInt(Math.min(product.getLoanTerm(),applicantWrapper.getMaxTerm())));
        return applicantWrapper;
    }

    public ApplicantWrapper definiteMaxAmount (ApplicantWrapper applicantWrapper, Product product){
        applicantWrapper.setExternalPayments(setPayments(applicantWrapper));
        applicantWrapper.setMaxAmount(Functions.calculateMaxAmount(applicantWrapper.getApplicant().getIncome(), applicantWrapper.getExternalPayments(), product.getLoanTerm(), product.getInterestRate(), product.getCoefficient()));
        return applicantWrapper;
    }

    private Double setPayments (ApplicantWrapper applicantWrapper){
        Double allPayments = ZERO;
        for (CreditInfo creditInfo : applicantWrapper.getCreditInfoList()){
            allPayments += creditInfo.getPayment();
        }
        return allPayments;
    }

    public List<ApplicantWrapper> setApplicantsScore(List<ApplicantWrapper> applicantsWrapper, Integer productCode) {
        for (ApplicantWrapper applicantWrapper : applicantsWrapper){
            applicantWrapper.setScore(scoreCalculator.getScore(applicantWrapper, productCode));
        }
        return applicantsWrapper;
    }

    public Decision makeDecisionForApplicant (ApplicantWrapper applicantWrapper, Product product){
        if (applicantWrapper.getMaxAmount().equals(ZERO)
                || applicantWrapper.getAgeMonths() < AGE_18
                || applicantWrapper.getAgeMonths() > AGE_70
                || !applicantWrapper.getLoanTerm().equals(product.getLoanTerm())
                || applicantWrapper.getScore() < scoreCalculator.getDeclinedScore(product.getProductCode())){
            return Decision.DECLINE;
        }
        return Decision.ACCEPT;
    }
}
