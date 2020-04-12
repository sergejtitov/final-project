package com.htp.processors.scorecards;


import com.htp.domain.wrappers.ApplicantWrapper;
import com.htp.processors.ScoreCard;
import lombok.extern.slf4j.Slf4j;

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



/*@Configuration
@ConfigurationProperties("creditcardsscoring")*/
@Slf4j
public class ScoreCardCreditCard implements ScoreCard{
    public Integer CREDIT_CARD_INITIAL_SCORE = 0;

    public Integer CREDIT_CARD_GENDER_VALUE_M = 8;
    public Integer CREDIT_CARD_GENDER_VALUE_F = 6;

    public Integer EXPERIENCE_CREDIT_CARD_VALUE_LESS_1Y = 1;
    public Integer EXPERIENCE_CREDIT_CARD_VALUE_MORE_1Y_LESS_3Y = 5;
    public Integer EXPERIENCE_CREDIT_CARD_VALUE_MORE_3Y_LESS_10Y = 8;
    public Integer EXPERIENCE_CREDIT_CARD_VALUE_MORE_10Y = 10;

    public Integer MARITAL_STATUS_CREDIT_CARD_VALUE_SINGLE = 6;
    public Integer MARITAL_STATUS_CREDIT_CARD_VALUE_MARRIED = 8;
    public Integer MARITAL_STATUS_CREDIT_CARD_VALUE_DIVORCED = 4;

    public Integer EDUCATION_CREDIT_CARD_VALUE_MAGISTRACY = 8;
    public Integer EDUCATION_CREDIT_CARD_VALUE_PHD = 10;
    public Integer EDUCATION_CREDIT_CARD_VALUE_SECONDARY = 4;
    public Integer EDUCATION_CREDIT_CARD_VALUE_VOCATIONAL = 7;
    public Integer EDUCATION_CREDIT_CARD_VALUE_WITHOUT = 1;

    public static Integer CUT_OFF_CREDIT_CARD = 18;

    @Override
    public synchronized Integer calculateScore(ApplicantWrapper applicantWrapper) {
        int score = CREDIT_CARD_INITIAL_SCORE;

        switch (applicantWrapper.getApplicant().getSex()){
            case M: score += CREDIT_CARD_GENDER_VALUE_M; break;
            case F: score += CREDIT_CARD_GENDER_VALUE_F; break;
        }

        switch (applicantWrapper.getApplicant().getExperience()){
            case EXPERIENCE_LESS_1M:
            case EXPERIENCE_MORE_1M_LESS_1Y:
                score += EXPERIENCE_CREDIT_CARD_VALUE_LESS_1Y; break;
            case EXPERIENCE_MORE_1Y_LESS_3Y: score += EXPERIENCE_CREDIT_CARD_VALUE_MORE_1Y_LESS_3Y; break;
            case EXPERIENCE_MORE_3Y_LESS_10Y: score += EXPERIENCE_CREDIT_CARD_VALUE_MORE_3Y_LESS_10Y; break;
            case EXPERIENCE_MORE_10Y: score += EXPERIENCE_CREDIT_CARD_VALUE_MORE_10Y; break;
        }

        switch (applicantWrapper.getApplicant().getMaritalStatus()){
            case MARITAL_STATUS_SINGLE: score += MARITAL_STATUS_CREDIT_CARD_VALUE_SINGLE; break;
            case MARITAL_STATUS_MARRIED: score += MARITAL_STATUS_CREDIT_CARD_VALUE_MARRIED; break;
            case MARITAL_STATUS_DIVORCED: score += MARITAL_STATUS_CREDIT_CARD_VALUE_DIVORCED; break;
        }

        switch (applicantWrapper.getApplicant().getEducation()){
            case EDUCATION_HIGHER:
            case EDUCATION_MAGISTRACY:
                score += EDUCATION_CREDIT_CARD_VALUE_MAGISTRACY; break;
            case EDUCATION_PHD: score += EDUCATION_CREDIT_CARD_VALUE_PHD; break;
            case EDUCATION_SECONDARY: score += EDUCATION_CREDIT_CARD_VALUE_SECONDARY; break;
            case EDUCATION_VOCATIONAL: score +=EDUCATION_CREDIT_CARD_VALUE_VOCATIONAL; break;
            case EDUCATION_WITHOUT: score +=EDUCATION_CREDIT_CARD_VALUE_WITHOUT; break;
        }

        return score;
    }

    @Override
    public Integer getDeclinedScore() {
        return CUT_OFF_CREDIT_CARD;
    }
}
