package htp.processors;

import htp.domain.wrappers.ApplicantWrapper;
import htp.utils.Scorecards;

import static htp.domain.dictionaries.LoanType.*;

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
