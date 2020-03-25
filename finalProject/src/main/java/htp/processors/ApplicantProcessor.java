package htp.processors;


import htp.entities.db_entities.Applicant;
import htp.entities.db_entities.CreditInfo;
import htp.entities.db_entities.Product;
import htp.entities.wrappers.ApplicantWrapper;
import htp.utils.Functions;
import htp.utils.Scorecards;

import java.sql.Timestamp;



public class ApplicantProcessor {
    public static final Integer MAX_AGE = 840;
    public static final Double ZERO = 0D;
    public static final int MORTGAGE = 1;
    public static final int AUTO = 1;
    public static final int CONSUMER_LOANS = 1;
    public static final int CREDIT_CARD = 1;

    public ApplicantProcessor() {

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

    public Integer calculateScoreCard(ApplicantWrapper applicantWrapper, Integer productCode){
        if (productCode == MORTGAGE){
            return Scorecards.scorecardMortgageAndAuto(applicantWrapper);
        }
        if (productCode == AUTO){
            return Scorecards.scorecardMortgageAndAuto(applicantWrapper);
        }
        if (productCode == CONSUMER_LOANS){
            return Scorecards.scorecardLoansAndCards(applicantWrapper);
        } else {
            return Scorecards.scorecardLoansAndCards(applicantWrapper);
        }
    }
}
