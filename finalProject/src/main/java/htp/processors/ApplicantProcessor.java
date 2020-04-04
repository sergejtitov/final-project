package htp.processors;



import htp.domain.model.CreditInfo;
import htp.domain.model.Product;
import htp.domain.dictionaries.Decision;
import htp.domain.wrappers.ApplicantWrapper;
import htp.utils.Functions;
import htp.utils.Scorecards;

import java.util.List;




public class ApplicantProcessor {
    public static final Integer MAX_AGE = 840;
    public static final int AGE_18 = 216;
    public static final int AGE_70 = 840;
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

    public Decision makeDecisionForApplicant (ApplicantWrapper applicantWrapper, Product product){
        if (applicantWrapper.getMaxAmount().equals(ZERO)){
            return Decision.DECLINE;
        }
        if (applicantWrapper.getAgeMonths() < AGE_18){
            return Decision.DECLINE;
        }
        if (applicantWrapper.getAgeMonths() > AGE_70){
            return Decision.DECLINE;
        }
        if (!applicantWrapper.getLoanTerm().equals(product.getLoanTerm())){
            return Decision.DECLINE;
        }
        if (applicantWrapper.getScore() < Scorecards.declinedScore(product.getProductCode())){
            return Decision.DECLINE;
        }
        return Decision.ACCEPT;
    }
}
