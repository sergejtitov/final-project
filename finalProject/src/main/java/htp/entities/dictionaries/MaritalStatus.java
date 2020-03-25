package htp.entities.dictionaries;

import htp.exceptions.NoSuchValueInDictionary;

public class MaritalStatus {
    public static final int MARITAL_STATUS_SINGLE = 1;
    public static final int MARITAL_STATUS_MARRIED = 2;
    public static final int MARITAL_STATUS_DIVORCED = 3;

    public static String getNameMaritalStatus(Integer maritalStatus){
        switch (maritalStatus){
            case 1: return  "Single";
            case 2: return "Married";
            case 3: return "Divorced";
            default: throw  new NoSuchValueInDictionary("No such Marital status");
        }
    }
}
