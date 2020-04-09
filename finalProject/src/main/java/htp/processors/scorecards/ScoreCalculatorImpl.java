package htp.processors.scorecards;

import htp.domain.wrappers.ApplicantWrapper;

import htp.processors.ScoreCard;
import htp.utils.Functions;
import lombok.Data;
import org.springframework.stereotype.Component;


import static htp.domain.dictionaries.LoanType.MORTGAGE;
import static htp.domain.dictionaries.LoanType.AUTO;
import static htp.domain.dictionaries.LoanType.CONSUMER_LOANS;


@Data
@Component
public class ScoreCalculatorImpl {


    public static Integer getScore(ApplicantWrapper applicantWrapper, Integer productCode) {
        ScoreCard scoreCard;
        if (Functions.getTypeFromCode(productCode) == MORTGAGE){
            scoreCard = new ScoreCardMortgage();
            return scoreCard.calculateScore(applicantWrapper);
            }
        if (Functions.getTypeFromCode(productCode) == AUTO){
            scoreCard = new ScoreCardAuto();
            return scoreCard.calculateScore(applicantWrapper);
            }
        if (Functions.getTypeFromCode(productCode) == CONSUMER_LOANS){
            scoreCard = new ScoreCardConsumerLoans();
            return scoreCard.calculateScore(applicantWrapper);
        }
        scoreCard = new ScoreCardCreditCard();
        return scoreCard.calculateScore(applicantWrapper);
    }


    public static Integer getDeclinedScore(Integer productCode) {
        ScoreCard  scoreCard;
        if (productCode == MORTGAGE){
            scoreCard =new ScoreCardMortgage();
            return scoreCard.getDeclinedScore();
        }
        if (productCode == AUTO){
            scoreCard = new ScoreCardAuto();
            return scoreCard.getDeclinedScore();
        }
        if (productCode == CONSUMER_LOANS){
            scoreCard =new ScoreCardConsumerLoans();
            return scoreCard.getDeclinedScore();
        }
        scoreCard =new ScoreCardCreditCard();
        return scoreCard.getDeclinedScore();
    }
}
