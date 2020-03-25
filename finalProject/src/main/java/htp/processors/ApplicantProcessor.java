package htp.processors;


import htp.entities.db_entities.Applicant;
import htp.entities.db_entities.CreditInfo;
import htp.entities.db_entities.Product;
import htp.entities.wrappers.ApplicantWrapper;
import htp.utils.Functions;
import htp.utils.Scorecards;

import java.sql.Timestamp;
import java.util.List;

import static htp.entities.dictionaries.LoanType.*;


public class ApplicantProcessor {
    public static final Integer MAX_AGE = 840;
    public static final Double ZERO = 0D;
    private ScoreCalculator scoreCalculator;

    public ApplicantProcessor() {
    scoreCalculator = new ScoreCalculatorImpl();
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
}
