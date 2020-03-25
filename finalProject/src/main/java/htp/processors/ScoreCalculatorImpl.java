package htp.processors;

import htp.entities.wrappers.ApplicantWrapper;
import htp.utils.Scorecards;

import static htp.entities.dictionaries.LoanType.*;

public class ScoreCalculatorImpl implements ScoreCalculator {
    @Override
    public Integer getScore(ApplicantWrapper applicantWrapper, Integer productCode) {
            if (productCode == MORTGAGE){
                return Scorecards.scorecardMortgageAndAuto(applicantWrapper);
            }
            if (productCode == AUTO){
                return Scorecards.scorecardMortgageAndAuto(applicantWrapper);
            }
        return Scorecards.scorecardLoansAndCards(applicantWrapper);
    }
}
