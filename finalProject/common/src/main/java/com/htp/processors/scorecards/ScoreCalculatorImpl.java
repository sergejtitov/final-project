package com.htp.processors.scorecards;



import com.htp.domain.wrappers.ApplicantWrapper;
import com.htp.processors.ScoreCard;
import com.htp.utils.Functions;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.htp.domain.dictionaries.LoanType.MORTGAGE;
import static com.htp.domain.dictionaries.LoanType.AUTO;
import static com.htp.domain.dictionaries.LoanType.CONSUMER_LOANS;


@Slf4j
@Data
@Service
public class ScoreCalculatorImpl {

    private final ScoreCardAuto scoreCardAuto;
    private final ScoreCardConsumerLoans scoreCardConsumerLoans;
    private final ScoreCardCreditCard scoreCardCreditCard;
    private final ScoreCardMortgage scoreCardMortgage;

    public ScoreCalculatorImpl(ScoreCardAuto scoreCardAuto, ScoreCardConsumerLoans scoreCardConsumerLoans, ScoreCardCreditCard scoreCardCreditCard, ScoreCardMortgage scoreCardMortgage) {
        this.scoreCardAuto = scoreCardAuto;
        this.scoreCardConsumerLoans = scoreCardConsumerLoans;
        this.scoreCardCreditCard = scoreCardCreditCard;
        this.scoreCardMortgage = scoreCardMortgage;
    }

    public Integer getScore(ApplicantWrapper applicantWrapper, Integer productCode) {
        ScoreCard scoreCard = getScoreCardImpl(productCode);
        return scoreCard.calculateScore(applicantWrapper);
    }


    public Integer getDeclinedScore(Integer productCode) {
        ScoreCard scoreCard = getScoreCardImpl(productCode);
        return scoreCard.getDeclinedScore();
    }

    private ScoreCard getScoreCardImpl(Integer productCode){
        ScoreCard  scoreCard;
        switch (Functions.getTypeFromCode(productCode)){
            case MORTGAGE: scoreCard = scoreCardMortgage; break;
            case AUTO: scoreCard = scoreCardAuto; break;
            case CONSUMER_LOANS : scoreCard = scoreCardConsumerLoans; break;
            default: scoreCard = scoreCardCreditCard; break;
        }
        return scoreCard;
    }
}
