package htp.utils;

import htp.entities.wrappers.ApplicantWrapper;

public class Scorecards {
    public static final Integer INITIAL_SCORE = 0;
    public static final int EXPERIENCE_LESS_1M = 1;
    public static final int EXPERIENCE_MORE_1M_LESS_1Y = 2;
    public static final int EXPERIENCE_MORE_1Y_LESS_3Y = 3;
    public static final int EXPERIENCE_MORE_3Y_LESS_10Y = 4;
    public static final int EXPERIENCE_MORE_10Y = 5;

    public static final int MARITAL_STATUS_SINGLE = 1;
    public static final int MARITAL_STATUS_MARRIED = 2;
    public static final int MARITAL_STATUS_DIVORCED = 3;

    public static final int EDUCATION_HIGHER = 1;
    public static final int EDUCATION_MAGISTRACY = 2;
    public static final int EDUCATION_PHD = 3;
    public static final int EDUCATION_SECONDARY = 4;
    public static final int EDUCATION_VOCATIONAL = 5;
    public static final int EDUCATION_WITHOUT = 6;



    public static Integer scorecardMortgageAndAuto (ApplicantWrapper applicantWrapper){
        final int AGE_LESS_25 = 1;
        final int AGE_MORE_25_LESS_40 = 2;
        final int AGE_MORE_40 = 3;

        final int ZERO_CHILD = 0;
        final int ONE_CHILD = 1;
        final int TWO_CHILDS = 2;
        final int THREE_CHILDS =3 ;

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
            case TWO_CHILDS: score += 7; break;
            case THREE_CHILDS: score += 4; break;
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
        final int AGE_LESS_25 = 1;
        final int AGE_MORE_25_LESS_40 = 2;
        final int AGE_MORE_40 = 3;

        if (ageMonths < YEAR * AGE_25){
            return AGE_LESS_25;
        }
        if (ageMonths < YEAR * AGE_40){
            return AGE_MORE_25_LESS_40;
        }
        return AGE_MORE_40;
    }
}
