package com.htp.processors.scorecards;



import com.htp.domain.wrappers.ApplicantWrapper;
import com.htp.processors.ScoreCard;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static com.htp.domain.dictionaries.Education.EDUCATION_HIGHER;
import static com.htp.domain.dictionaries.Education.EDUCATION_MAGISTRACY;
import static com.htp.domain.dictionaries.Education.EDUCATION_PHD;
import static com.htp.domain.dictionaries.Education.EDUCATION_SECONDARY;
import static com.htp.domain.dictionaries.Education.EDUCATION_VOCATIONAL;
import static com.htp.domain.dictionaries.Education.EDUCATION_WITHOUT;
import static com.htp.domain.dictionaries.Experience.EXPERIENCE_LESS_1M;
import static com.htp.domain.dictionaries.Experience.EXPERIENCE_MORE_1M_LESS_1Y;
import static com.htp.domain.dictionaries.Experience.EXPERIENCE_MORE_1Y_LESS_3Y;
import static com.htp.domain.dictionaries.Experience.EXPERIENCE_MORE_3Y_LESS_10Y;
import static com.htp.domain.dictionaries.Experience.EXPERIENCE_MORE_10Y;
import static com.htp.domain.dictionaries.MaritalStatus.MARITAL_STATUS_SINGLE;
import static com.htp.domain.dictionaries.MaritalStatus.MARITAL_STATUS_MARRIED;
import static com.htp.domain.dictionaries.MaritalStatus.MARITAL_STATUS_DIVORCED;
import static org.hibernate.type.IntegerType.ZERO;


@Data
@Configuration
@ConfigurationProperties("mortgagescoring")
@Slf4j
public class ScoreCardMortgage implements ScoreCard {
    public static final int YEAR = 12;
    public static final int AGE_25 = 25;
    public static final int AGE_40 = 40;

    public Integer MORTGAGE_INITIAL_SCORE;

    public static final int AGE_LESS_25 = 1;
    public static final int AGE_MORE_25_LESS_40 = 2;
    public static final int AGE_MORE_40 = 3;
    public Integer AGE_MORTGAGE_LESS_25_VALUE;
    public Integer AGE_MORTGAGE_MORE_25_LESS_40_VALUE;
    public Integer AGE_MORTGAGE_MORE_40_VALUE;

    public Integer GENDER_MORTGAGE_M_VALUE;
    public Integer GENDER_MORTGAGE_F_VALUE;

    public Integer EXPERIENCE_MORTGAGE_LESS_1M_VALUE;
    public Integer EXPERIENCE_MORTGAGE_MORE_1M_LESS_1Y_VALUE;
    public Integer EXPERIENCE_MORTGAGE_MORE_1Y_LESS_3Y_VALUE;
    public Integer EXPERIENCE_MORTGAGE_MORE_3Y_LESS_10Y_VALUE;
    public Integer EXPERIENCE_MORTGAGE_MORE_10Y_VALUE;

    public Integer MARITAL_STATUS_MORTGAGE_SINGLE_VALUE;
    public Integer MARITAL_STATUS_MORTGAGE_MARRIED_VALUE;
    public Integer MARITAL_STATUS_MORTGAGE_DIVORCED_VALUE;

    public Integer EDUCATION_MORTGAGE_HIGHER_VALUE;
    public Integer EDUCATION_MORTGAGE_MAGISTRACY_VALUE;
    public Integer EDUCATION_MORTGAGE_PHD_VALUE;
    public Integer EDUCATION_MORTGAGE_SECONDARY_VALUE;
    public Integer EDUCATION_MORTGAGE_VOCATIONAL_VALUE;
    public Integer EDUCATION_MORTGAGE_WITHOUT_VALUE;

    public static final int NO_CHILD_MORTGAGE = 0;
    public static final int ONE_CHILD_MORTGAGE = 1;
    public static final int TWO_CHILDES_MORTGAGE = 2;
    public static final int THREE_CHILDES_MORTGAGE = 3;
    public Integer NO_CHILD_MORTGAGE_VALUE;
    public Integer ONE_CHILD_MORTGAGE_VALUE;
    public Integer TWO_CHILDES_MORTGAGE_VALUE;
    public Integer THREE_CHILDES_MORTGAGE_VALUE;
    public Integer DEFAULT_CHILDS_AMOUNT_MORTGAGE_VALUE;

    public  Integer CUT_OFF_MORTGAGE;

    @Override
    public Integer calculateScore(ApplicantWrapper applicantWrapper) {
        int score = MORTGAGE_INITIAL_SCORE;

        score += getScoreByAge(applicantWrapper);
        score += getScoreByGender(applicantWrapper);
        score += getScoreByExperience(applicantWrapper);
        score += getScoreByMaritalStatus(applicantWrapper);
        score += getScoreByEducation(applicantWrapper);
        score += getScoreByChildrenQuantity(applicantWrapper);

        return score;
    }

    @Override
    public Integer getDeclinedScore() {
        return CUT_OFF_MORTGAGE;
    }

    private int getScoreByChildrenQuantity(ApplicantWrapper applicantWrapper) {
        int score = ZERO;
        switch (applicantWrapper.getApplicant().getChildrenQuantity()){
            case NO_CHILD_MORTGAGE: score += NO_CHILD_MORTGAGE_VALUE; break;
            case ONE_CHILD_MORTGAGE: score += ONE_CHILD_MORTGAGE_VALUE; break;
            case TWO_CHILDES_MORTGAGE: score += TWO_CHILDES_MORTGAGE_VALUE; break;
            case THREE_CHILDES_MORTGAGE: score += THREE_CHILDES_MORTGAGE_VALUE; break;
            default: score += DEFAULT_CHILDS_AMOUNT_MORTGAGE_VALUE;  break;
        }
        return score;
    }

    private int getScoreByEducation(ApplicantWrapper applicantWrapper) {
        int score = ZERO;
        switch (applicantWrapper.getApplicant().getEducation()){
            case EDUCATION_HIGHER: score += EDUCATION_MORTGAGE_HIGHER_VALUE; break;
            case EDUCATION_MAGISTRACY: score += EDUCATION_MORTGAGE_MAGISTRACY_VALUE; break;
            case EDUCATION_PHD: score += EDUCATION_MORTGAGE_PHD_VALUE; break;
            case EDUCATION_SECONDARY: score += EDUCATION_MORTGAGE_SECONDARY_VALUE; break;
            case EDUCATION_VOCATIONAL: score +=EDUCATION_MORTGAGE_VOCATIONAL_VALUE; break;
            case EDUCATION_WITHOUT: score +=EDUCATION_MORTGAGE_WITHOUT_VALUE; break;
        }
        return score;
    }

    private int getScoreByMaritalStatus(ApplicantWrapper applicantWrapper) {
        int score = ZERO;
        switch (applicantWrapper.getApplicant().getMaritalStatus()){
            case MARITAL_STATUS_SINGLE: score += MARITAL_STATUS_MORTGAGE_SINGLE_VALUE; break;
            case MARITAL_STATUS_MARRIED: score += MARITAL_STATUS_MORTGAGE_MARRIED_VALUE; break;
            case MARITAL_STATUS_DIVORCED: score += MARITAL_STATUS_MORTGAGE_DIVORCED_VALUE; break;
        }
        return score;
    }

    private int getScoreByExperience(ApplicantWrapper applicantWrapper) {
        int score = ZERO;
        switch (applicantWrapper.getApplicant().getExperience()){
            case EXPERIENCE_LESS_1M: score += EXPERIENCE_MORTGAGE_LESS_1M_VALUE; break;
            case EXPERIENCE_MORE_1M_LESS_1Y: score += EXPERIENCE_MORTGAGE_MORE_1M_LESS_1Y_VALUE; break;
            case EXPERIENCE_MORE_1Y_LESS_3Y: score += EXPERIENCE_MORTGAGE_MORE_1Y_LESS_3Y_VALUE; break;
            case EXPERIENCE_MORE_3Y_LESS_10Y: score += EXPERIENCE_MORTGAGE_MORE_3Y_LESS_10Y_VALUE; break;
            case EXPERIENCE_MORE_10Y: score += EXPERIENCE_MORTGAGE_MORE_10Y_VALUE; break;
        }
        return score;
    }

    private int getScoreByGender(ApplicantWrapper applicantWrapper) {
        int score = ZERO;
        switch (applicantWrapper.getApplicant().getSex()){
            case M: score += GENDER_MORTGAGE_M_VALUE; break;
            case F: score += GENDER_MORTGAGE_F_VALUE; break;
        }
        return score;
    }

    private int getScoreByAge(ApplicantWrapper applicantWrapper) {
        int score = ZERO;
        switch (getAgeForMortgageAndAuto(applicantWrapper.getAgeMonths())){
            case AGE_LESS_25: score += AGE_MORTGAGE_LESS_25_VALUE; break;
            case AGE_MORE_25_LESS_40: score += AGE_MORTGAGE_MORE_25_LESS_40_VALUE; break;
            case AGE_MORE_40: score += AGE_MORTGAGE_MORE_40_VALUE; break;

        }
        return score;
    }

    private static Integer getAgeForMortgageAndAuto(Integer ageMonths){
        if (ageMonths < YEAR * AGE_25){
            return AGE_LESS_25;
        }
        if (ageMonths < YEAR * AGE_40){
            return AGE_MORE_25_LESS_40;
        }
        return AGE_MORE_40;
    }
}
