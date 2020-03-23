package htp.processors;


import htp.entities.db_entities.Applicant;
import htp.entities.db_entities.CreditInfo;
import htp.entities.db_entities.Product;
import htp.entities.wrappers.ApplicantWrapper;
import htp.utils.Functions;

import java.sql.Timestamp;



public class ApplicantProcessor {
    public static final Integer MAX_AGE = 840;
    public static final Integer ZERO = 0;

    public ApplicantProcessor() {

    }

    /*public void start(){
        applicant.setExternalPayments(Functions.calculateExternalPayments(applicant.getCreditInfoList()));
        if (applicant.getLoanTerm().equals(ZERO)){
            applicant.setMaxAmount(ZERO.doubleValue());
        } else {
            applicant.setMaxAmount(Functions.calculateMaxAmount(applicant.getApplicant().getIncome(),applicant.getExternalPayments(),applicant.getLoanTerm(), product.getInterestRate(), product.getCoefficient()));
        }
    }*/
    
    public ApplicantWrapper definiteTerm(ApplicantWrapper applicantWrapper, Product product){
        applicantWrapper.setAgeMonths(Functions.calculateAge(applicantWrapper.getApplicant().getBirthdayDate()));
        applicantWrapper.setMaxTerm(Functions.positiveOrZeroInt(Functions.calculateMonths(applicantWrapper.getAgeMonths(), MAX_AGE)));
        applicantWrapper.setLoanTerm(Functions.positiveOrZeroInt(Math.min(product.getLoanTerm(),applicantWrapper.getMaxTerm())));
        return applicantWrapper;
    }

}
