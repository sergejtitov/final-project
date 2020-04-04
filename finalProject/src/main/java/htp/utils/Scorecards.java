package htp.utils;

import htp.domain.wrappers.ApplicantWrapper;

import static htp.domain.dictionaries.Education.*;
import static htp.domain.dictionaries.Experience.*;
import static htp.domain.dictionaries.LoanType.*;
import static htp.domain.dictionaries.MaritalStatus.*;

public class Scorecards {
    public static final Integer INITIAL_SCORE = 0;

    public static final int AGE_LESS_25 = 1;
    public static final int AGE_MORE_25_LESS_40 = 2;
    public static final int AGE_MORE_40 = 3;

    public static final int CUT_OFF_MORTGAGE = 20;
    public static final int CUT_OFF_AUTO = 23;
    public static final int CUT_OFF_CONSUMER_LOAN =16;
    public static final int CUT_OFF_CREDIT_CARD = 18;

    public static final int ZERO = 0;

    public static Integer scorecardMortgageAndAuto (ApplicantWrapper applicantWrapper){
        final int ZERO_CHILD = 0;
        final int ONE_CHILD = 1;
        final int TWO_CHILDES = 2;
        final int THREE_CHILDES =3 ;

        int score = INITIAL_SCORE;

        switch (getAgeForMortgageAndAuto(applicantWrapper.getAgeMonths())){
            case AGE_LESS_25: score += 1; break;
            case AGE_MORE_25_LESS_40: score += 10; break;
            case AGE_MORE_40: score += 7; break;

        }

        switch (applicantWrapper.getApplicant().getSex()){
            case M: score += 7; break;
            case F: score += 5; break;
        }

        switch (applicantWrapper.getApplicant().getExperience()){
            case EXPERIENCE_LESS_1M: score += 1; break;
            case EXPERIENCE_MORE_1M_LESS_1Y: score += 2; break;
            case EXPERIENCE_MORE_1Y_LESS_3Y: score += 5; break;
            case EXPERIENCE_MORE_3Y_LESS_10Y: score += 7; break;
            case EXPERIENCE_MORE_10Y: score += 8; break;
        }

        switch (applicantWrapper.getApplicant().getMaritalStatus()){
            case MARITAL_STATUS_SINGLE: score += 3; break;
            case MARITAL_STATUS_MARRIED: score += 9; break;
            case MARITAL_STATUS_DIVORCED: score += 5; break;
        }

        switch (applicantWrapper.getApplicant().getEducation()){
            case EDUCATION_HIGHER: score += 8; break;
            case EDUCATION_MAGISTRACY: score += 9; break;
            case EDUCATION_PHD: score += 10; break;
            case EDUCATION_SECONDARY: score += 2; break;
            case EDUCATION_VOCATIONAL: score +=5; break;
            case EDUCATION_WITHOUT: score +=1; break;
        }

        switch (applicantWrapper.getApplicant().getChildrenQuantity()){
            case ZERO_CHILD: score += 5; break;
            case ONE_CHILD: score += 8; break;
            case TWO_CHILDES: score += 7; break;
            case THREE_CHILDES: score += 4; break;
            default: score += 1;  break;
        }

        return score;
    }

    public static Integer scorecardLoansAndCards (ApplicantWrapper applicantWrapper){
        int score = INITIAL_SCORE;

        switch (applicantWrapper.getApplicant().getSex()){
            case M: score += 8; break;
            case F: score += 6; break;
        }

        switch (applicantWrapper.getApplicant().getExperience()){
            case EXPERIENCE_LESS_1M:
            case EXPERIENCE_MORE_1M_LESS_1Y:
                score += 1; break;
            case EXPERIENCE_MORE_1Y_LESS_3Y: score += 5; break;
            case EXPERIENCE_MORE_3Y_LESS_10Y: score += 8; break;
            case EXPERIENCE_MORE_10Y: score += 10; break;
        }

        switch (applicantWrapper.getApplicant().getMaritalStatus()){
            case MARITAL_STATUS_SINGLE: score += 6; break;
            case MARITAL_STATUS_MARRIED: score += 8; break;
            case MARITAL_STATUS_DIVORCED: score += 4; break;
        }

        switch (applicantWrapper.getApplicant().getEducation()){
            case EDUCATION_HIGHER:
            case EDUCATION_MAGISTRACY:
                score += 8; break;
            case EDUCATION_PHD: score += 10; break;
            case EDUCATION_SECONDARY: score += 4; break;
            case EDUCATION_VOCATIONAL: score +=7; break;
            case EDUCATION_WITHOUT: score +=1; break;
        }

        return score;
    }

    private static Integer getAgeForMortgageAndAuto(Integer ageMonths){
        final int YEAR = 12;
        final int AGE_25 = 25;
        final int AGE_40 = 40;

        if (ageMonths < YEAR * AGE_25){
            return AGE_LESS_25;
        }
        if (ageMonths < YEAR * AGE_40){
            return AGE_MORE_25_LESS_40;
        }
        return AGE_MORE_40;
    }

    public static Integer declinedScore(Integer productCode) {
        switch (Functions.getTypeFromCode(productCode)){
            case MORTGAGE: return CUT_OFF_MORTGAGE;
            case AUTO: return CUT_OFF_AUTO;
            case CONSUMER_LOANS: return CUT_OFF_CONSUMER_LOAN;
            case CREDIT_CARD: return CUT_OFF_CREDIT_CARD;
            default: return ZERO;
        }
    }
}
