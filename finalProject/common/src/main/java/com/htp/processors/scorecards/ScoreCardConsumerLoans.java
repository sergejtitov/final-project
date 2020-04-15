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

@Slf4j
@Data
@Configuration
@ConfigurationProperties("consloanscoring")
public class ScoreCardConsumerLoans implements ScoreCard {
    public int CONS_LOAN_INITIAL_SCORE;

    public int CONS_LOAN_GENDER_VALUE_M;
    public int CONS_LOAN_GENDER_VALUE_F;

    public int EXPERIENCE_CONS_LOAN_VALUE_LESS_1Y;
    public int EXPERIENCE_CONS_LOAN_VALUE_MORE_1Y_LESS_3Y;
    public int EXPERIENCE_CONS_LOAN_VALUE_MORE_3Y_LESS_10Y;
    public int EXPERIENCE_CONS_LOAN_VALUE_MORE_10Y;

    public int MARITAL_STATUS_CONS_LOAN_VALUE_SINGLE;
    public int MARITAL_STATUS_CONS_LOAN_VALUE_MARRIED;
    public int MARITAL_STATUS_CONS_LOAN_VALUE_DIVORCED;

    public int EDUCATION_CONS_LOAN_VALUE_MAGISTRACY;
    public int EDUCATION_CONS_LOAN_VALUE_PHD;
    public int EDUCATION_CONS_LOAN_VALUE_SECONDARY;
    public int EDUCATION_CONS_LOAN_VALUE_VOCATIONAL;
    public int EDUCATION_CONS_LOAN_VALUE_WITHOUT;

    public int CUT_OFF_CONSUMER_LOAN;

    @Override
    public  Integer calculateScore(ApplicantWrapper applicantWrapper) {
        int score = CONS_LOAN_INITIAL_SCORE;

        score += getScoreByGender(applicantWrapper);
        score += getScoreByExperience(applicantWrapper);
        score += getScoreByMaritalStatus(applicantWrapper);
        score += getScoreByEducation(applicantWrapper);

        return score;
    }

    @Override
    public Integer getDeclinedScore() {
        return CUT_OFF_CONSUMER_LOAN;
    }

    private int getScoreByEducation(ApplicantWrapper applicantWrapper) {
        int score = ZERO;
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

    private int getScoreByMaritalStatus(ApplicantWrapper applicantWrapper) {
        int score = ZERO;
        switch (applicantWrapper.getApplicant().getMaritalStatus()){
            case MARITAL_STATUS_SINGLE: score += MARITAL_STATUS_CONS_LOAN_VALUE_SINGLE; break;
            case MARITAL_STATUS_MARRIED: score += MARITAL_STATUS_CONS_LOAN_VALUE_MARRIED; break;
            case MARITAL_STATUS_DIVORCED: score += MARITAL_STATUS_CONS_LOAN_VALUE_DIVORCED; break;
        }
        return score;
    }

    private int getScoreByExperience(ApplicantWrapper applicantWrapper) {
        int score = ZERO;
        switch (applicantWrapper.getApplicant().getExperience()){
            case EXPERIENCE_LESS_1M:
            case EXPERIENCE_MORE_1M_LESS_1Y:
                score += EXPERIENCE_CONS_LOAN_VALUE_LESS_1Y; break;
            case EXPERIENCE_MORE_1Y_LESS_3Y: score += EXPERIENCE_CONS_LOAN_VALUE_MORE_1Y_LESS_3Y; break;
            case EXPERIENCE_MORE_3Y_LESS_10Y: score += EXPERIENCE_CONS_LOAN_VALUE_MORE_3Y_LESS_10Y; break;
            case EXPERIENCE_MORE_10Y: score += EXPERIENCE_CONS_LOAN_VALUE_MORE_10Y; break;
        }
        return score;
    }

    private int getScoreByGender(ApplicantWrapper applicantWrapper) {
        int score = ZERO;
        switch (applicantWrapper.getApplicant().getSex()){
            case M: score += CONS_LOAN_GENDER_VALUE_M; break;
            case F: score += CONS_LOAN_GENDER_VALUE_F; break;
        }
        return score;
    }
}
