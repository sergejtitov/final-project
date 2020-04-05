package htp.processors.scorecards;

import htp.domain.wrappers.ApplicantWrapper;
import htp.processors.ScoreCard;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static htp.domain.dictionaries.Education.*;
import static htp.domain.dictionaries.Education.EDUCATION_WITHOUT;
import static htp.domain.dictionaries.Experience.*;
import static htp.domain.dictionaries.MaritalStatus.*;

@Configuration
@ConfigurationProperties("consloanscoring")
public class ScoreCardConsumerLoans implements ScoreCard {
    public static Integer CONS_LOAN_INITIAL_SCORE;

    public static int CONS_LOAN_GENDER_VALUE_M;
    public static int CONS_LOAN_GENDER_VALUE_F;

    public static int EXPERIENCE_CONS_LOAN_VALUE_LESS_1Y;
    public static int EXPERIENCE_CONS_LOAN_VALUE_MORE_1Y_LESS_3Y;
    public static int EXPERIENCE_CONS_LOAN_VALUE_MORE_3Y_LESS_10Y;
    public static int EXPERIENCE_CONS_LOAN_VALUE_MORE_10Y;

    public static int MARITAL_STATUS_CONS_LOAN_VALUE_SINGLE;
    public static int MARITAL_STATUS_CONS_LOAN_VALUE_MARRIED;
    public static int MARITAL_STATUS_CONS_LOAN_VALUE_DIVORCED;

    public static int EDUCATION_CONS_LOAN_VALUE_MAGISTRACY;
    public static int EDUCATION_CONS_LOAN_VALUE_PHD;
    public static int EDUCATION_CONS_LOAN_VALUE_SECONDARY;
    public static int EDUCATION_CONS_LOAN_VALUE_VOCATIONAL;
    public static int EDUCATION_CONS_LOAN_VALUE_WITHOUT;

    public static int CUT_OFF_CONSUMER_LOAN;

    @Override
    public Integer calculateScore(ApplicantWrapper applicantWrapper) {
        int score = CONS_LOAN_INITIAL_SCORE;

        switch (applicantWrapper.getApplicant().getSex()){
            case M: score += CONS_LOAN_GENDER_VALUE_M; break;
            case F: score += CONS_LOAN_GENDER_VALUE_F; break;
        }

        switch (applicantWrapper.getApplicant().getExperience()){
            case EXPERIENCE_LESS_1M:
            case EXPERIENCE_MORE_1M_LESS_1Y:
                score += EXPERIENCE_CONS_LOAN_VALUE_LESS_1Y; break;
            case EXPERIENCE_MORE_1Y_LESS_3Y: score += EXPERIENCE_CONS_LOAN_VALUE_MORE_1Y_LESS_3Y; break;
            case EXPERIENCE_MORE_3Y_LESS_10Y: score += EXPERIENCE_CONS_LOAN_VALUE_MORE_3Y_LESS_10Y; break;
            case EXPERIENCE_MORE_10Y: score += EXPERIENCE_CONS_LOAN_VALUE_MORE_10Y; break;
        }

        switch (applicantWrapper.getApplicant().getMaritalStatus()){
            case MARITAL_STATUS_SINGLE: score += MARITAL_STATUS_CONS_LOAN_VALUE_SINGLE; break;
            case MARITAL_STATUS_MARRIED: score += MARITAL_STATUS_CONS_LOAN_VALUE_MARRIED; break;
            case MARITAL_STATUS_DIVORCED: score += MARITAL_STATUS_CONS_LOAN_VALUE_DIVORCED; break;
        }

        switch (applicantWrapper.getApplicant().getEducation()){
            case EDUCATION_HIGHER:
            case EDUCATION_MAGISTRACY:
                score += EDUCATION_CONS_LOAN_VALUE_MAGISTRACY; break;
            case EDUCATION_PHD: score += EDUCATION_CONS_LOAN_VALUE_PHD; break;
            case EDUCATION_SECONDARY: score += EDUCATION_CONS_LOAN_VALUE_SECONDARY; break;
            case EDUCATION_VOCATIONAL: score +=EDUCATION_CONS_LOAN_VALUE_VOCATIONAL; break;
            case EDUCATION_WITHOUT: score +=EDUCATION_CONS_LOAN_VALUE_WITHOUT; break;
        }

        return score;
    }

    @Override
    public Integer getDeclinedScore(Integer productCode) {
        return CUT_OFF_CONSUMER_LOAN;
    }
}
