package htp.entities.dictionaries;

import htp.exceptions.NoSuchValueInDictionary;

public class Experience {

    public static final int EXPERIENCE_LESS_1M = 1;
    public static final int EXPERIENCE_MORE_1M_LESS_1Y = 2;
    public static final int EXPERIENCE_MORE_1Y_LESS_3Y = 3;
    public static final int EXPERIENCE_MORE_3Y_LESS_10Y = 4;
    public static final int EXPERIENCE_MORE_10Y = 5;

    public static String getNameExperience(Integer experience){
        switch (experience){
            case 1: return "Less than 1 month";
            case 2: return "From 1 month to 1 year";
            case 3: return "From 1 year to 3 years";
            case 4: return "From 3 years to 10 years";
            case 5: return "More than 10 years";
            default: throw new NoSuchValueInDictionary("No such Experience");
        }
    }
}
