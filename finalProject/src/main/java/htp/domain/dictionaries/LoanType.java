package htp.domain.dictionaries;

import htp.exceptions.NoSuchValueInDictionary;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoanType {
    public static final int MORTGAGE = 1;
    public static final int AUTO = 2;
    public static final int CONSUMER_LOANS = 3;
    public static final int CREDIT_CARD = 4;

    public static String getNameLoanType (Integer loanType){
        switch (loanType){
            case 1: return "Mortgage";
            case 2: return "Auto";
            case 3: return "Consumer Loan";
            case 4: return "Credit card";
            default: throw new NoSuchValueInDictionary("No such Loan Type");
        }
    }
}
