package htp.entities.dictionaries;

import htp.exceptions.NoSuchValueInDictionary;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;
import java.util.Map;

public class ProductCode {
    private static Map<Long, String> productCodeMap;
    static {
        productCodeMap = new HashMap<>();
        productCodeMap.put(1001L, "Mortgage - 10 years");
        productCodeMap.put(1002L, "Mortgage - 15 years");
        productCodeMap.put(1003L, "Mortgage - 20 years");
        productCodeMap.put(2001L, "Auto - 5 years");
        productCodeMap.put(2002L, "Auto - 7 years");
        productCodeMap.put(2003L, "Auto - 10 years");
        productCodeMap.put(3001L, "Consumer Loan - 1 year");
        productCodeMap.put(3002L, "Consumer Loan - 5 years");
        productCodeMap.put(3003L, "Consumer Loan - 7 years");
        productCodeMap.put(4001L, "Credit card with cash-back");
        productCodeMap.put(4002L, "Credit card with grace");
        productCodeMap.put(4003L, "Credit card with cash-back and grace");
    }
    private Long index;

    public String getProductCode (){
        return productCodeMap.get(index);
    }

    public ProductCode(Long index) {
        if (productCodeMap.containsKey(index)) {
            this.index = index;
        } else {
            throw new NoSuchValueInDictionary("No such value in Dictionary Product Code");
        }
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return index.toString();
    }
}
