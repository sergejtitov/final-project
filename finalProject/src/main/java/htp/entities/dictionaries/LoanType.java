package htp.entities.dictionaries;

import htp.exceptions.NoSuchValueInDictionary;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoanType {
    private static List<String> loanType = new ArrayList<>(Arrays.asList("Mortgage", "Auto", "Consumer Loan","Credit card"));
    private Integer index;

    public LoanType(Integer index) {
        if (index > 4 || index < 1) {
            throw new NoSuchValueInDictionary("No such value in Dictionary Loan Type");
        } else {
            this.index = index;
        }
    }
    public String getLoanType(){
        return loanType.get(index-1);
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return loanType.get(index-1);
    }
}
