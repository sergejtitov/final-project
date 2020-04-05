package htp.processors.scorecards;

import htp.domain.wrappers.ApplicantWrapper;
import htp.processors.ScoreCalculator;
import htp.processors.ScoreCard;

import static htp.domain.dictionaries.LoanType.*;

public class ScoreCalculatorImpl implements ScoreCalculator {

    @Override
    public Integer getScore(ApplicantWrapper applicantWrapper, Integer productCode) {
        ScoreCard scoreCard;
        if (productCode == MORTGAGE){
            scoreCard = new ScoreCardMortgage();
            return scoreCard.calculateScore(applicantWrapper);
            }
        if (productCode == AUTO){
            scoreCard = new ScoreCardAuto();
            return scoreCard.calculateScore(applicantWrapper);
            }
        if (productCode == CONSUMER_LOANS){
            scoreCard = new ScoreCardConsumerLoans();
            return scoreCard.calculateScore(applicantWrapper);
        }
        scoreCard = new ScoreCardCreditCard();
        return scoreCard.calculateScore(applicantWrapper);
    }

    @Override
    public Integer getDeclinedScore(Integer productCode) {
        ScoreCard scoreCard;
        if (productCode == MORTGAGE){
            scoreCard = new ScoreCardMortgage();
            return scoreCard.getDeclinedScore(productCode);
        }
        if (productCode == AUTO){
            scoreCard = new ScoreCardAuto();
            return scoreCard.getDeclinedScore(productCode);
        }
        if (productCode == CONSUMER_LOANS){
            scoreCard = new ScoreCardConsumerLoans();
            return scoreCard.getDeclinedScore(productCode);
        }
        scoreCard = new ScoreCardCreditCard();
        return scoreCard.getDeclinedScore(productCode);
    }
}
