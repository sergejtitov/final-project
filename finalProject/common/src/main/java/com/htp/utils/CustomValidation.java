package com.htp.utils;


import com.htp.domain.model.Applicant;
import com.htp.domain.model.Application;
import com.htp.exceptions.CustomValidationException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Slf4j
@Data
@Configuration
@ConfigurationProperties("validation")
public class CustomValidation {
    public static final int INITIALIZE_INT = 0;
    public Integer MIN_LOAN_TYPE;
    public Integer MAX_LOAN_TYPE;
    public Integer MIN_TYPE_OF_APPLICANT;
    public Integer MAX_TYPE_OF_APPLICANT;
    public Integer MAIN_APPLICANT;
    public Integer MAX_AMOUNT_APPLICANTS;
    public Integer MIN_EXPERIENCE;
    public Integer MAX_EXPERIENCE;
    public Integer MIN_MARITAL_STATUS;
    public Integer MAX_MARITAL_STATUS;
    public Integer MIN_EDUCATION;
    public Integer MAX_EDUCATION;

    public void validate(Application application){
        validateLoanType(application.getLoanType());
        validateAmountApplicants(application.getApplicants());
        for (Applicant applicant : application.getApplicants()){
            validateExperience(applicant.getExperience());
            validateMaritalStatus(applicant.getMaritalStatus());
            validateEducation(applicant.getEducation());
        }
    }

    private void validateLoanType(Integer loanType){
        if (loanType < MIN_LOAN_TYPE || loanType > MAX_LOAN_TYPE){
            throw new CustomValidationException("Incorrect Loan Type. It should be from 1 to 4");
        }
    }

    private void validateAmountApplicants(Set<Applicant> applicants){
        int numberMainApplicants = INITIALIZE_INT;
        int numberApplicants = INITIALIZE_INT;
        for (Applicant applicant : applicants){
            if (applicant.getTypeOfApplicant()<MIN_TYPE_OF_APPLICANT || applicant.getTypeOfApplicant() >MAX_TYPE_OF_APPLICANT){
                throw new CustomValidationException("Incorrect type of applicant. It should be from 1 to 2");
            }
            if (applicant.getTypeOfApplicant().equals(MAIN_APPLICANT)){
                numberMainApplicants++;
            }
            numberApplicants++;
        }
        if (numberMainApplicants > MAIN_APPLICANT){
            throw new CustomValidationException("Main applicant shouldbe only one.");
        }
        if (numberApplicants > MAX_AMOUNT_APPLICANTS){
            throw new CustomValidationException("Total amount of applicants should be less than 6");
        }
    }

    private void validateExperience (Integer experience){
        if (experience < MIN_EXPERIENCE || experience > MAX_EXPERIENCE){
            throw new CustomValidationException("Incorrect experience. It should be from 1 to 5");
        }
    }

   private void validateMaritalStatus (Integer maritalStatus){
        if (maritalStatus < MIN_MARITAL_STATUS || maritalStatus > MAX_MARITAL_STATUS){
            throw new CustomValidationException("Incorrect marital status. It should be from 1 to 3");
        }
    }

    private void validateEducation (Integer education){
        if (education < MIN_EDUCATION || education > MAX_EDUCATION){
            throw new CustomValidationException("Incorrect education. It should be from 1 to 6");
        }
    }

}
