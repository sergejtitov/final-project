package htp.utils;

import htp.domain.model.Applicant;
import htp.domain.model.Application;
import htp.exceptions.CustomValidationException;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CustomValidation {
    public static final int INITIALIZE_INT = 0;
    public static final int MIN_LOAN_TYPE = 1;
    public static final int MAX_LOAN_TYPE = 4;
    public static final int MIN_TYPE_OF_APPLICANT = 1;
    public static final int MAX_TYPE_OF_APPLICANT = 2;
    public static final int MAIN_APPLICANT = 1;
    public static final int MAX_AMOUNT_APPLICANTS = 5;
    public static final int MIN_EXPERIENCE = 1;
    public static final int MAX_EXPERIENCE = 5;
    public static final int MIN_MARITAL_STATUS = 1;
    public static final int MAX_MARITAL_STATUS = 3;
    public static final int MIN_EDUCATION = 1;
    public static final int MAX_EDUCATION = 6;

    public static Application validate(Application application){
        validateLoanType(application.getLoanType());
        validateAmountApplicants(application.getApplicants());
        for (Applicant applicant : application.getApplicants()){
            validateExperience(applicant.getExperience());
            validateMaritalStatus(applicant.getMaritalStatus());
            validateEducation(applicant.getEducation());
        }
        return application;
    }

    private static void validateLoanType(Integer loanType){
        if (loanType < MIN_LOAN_TYPE || loanType > MAX_LOAN_TYPE){
            throw new CustomValidationException("Incorrect Loan Type. It should be from 1 to 4");
        }
    }

    private static void validateAmountApplicants(Set<Applicant> applicants){
        int numberMainApplicants = INITIALIZE_INT;
        int numberApplicants = INITIALIZE_INT;
        for (Applicant applicant : applicants){
            if (applicant.getTypeOfApplicant()<MIN_TYPE_OF_APPLICANT || applicant.getTypeOfApplicant() >MAX_TYPE_OF_APPLICANT){
                throw new CustomValidationException("Incorrect type of applicant. It should be from 1 to 2");
            }
            if (applicant.getTypeOfApplicant() == MAIN_APPLICANT){
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

    private static void validateExperience (Integer experience){
        if (experience < MIN_EXPERIENCE || experience > MAX_EXPERIENCE){
            throw new CustomValidationException("Incorrect experience. It should be from 1 to 5");
        }
    }

   private  static void validateMaritalStatus (Integer maritalStatus){
        if (maritalStatus < MIN_MARITAL_STATUS || maritalStatus > MAX_MARITAL_STATUS){
            throw new CustomValidationException("Incorrect marital status. It should be from 1 to 3");
        }
    }

    private static void validateEducation (Integer education){
        if (education < MIN_EDUCATION || education > MAX_EDUCATION){
            throw new CustomValidationException("Incorrect education. It should be from 1 to 6");
        }
    }

}
