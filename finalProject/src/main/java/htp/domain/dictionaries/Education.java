package htp.domain.dictionaries;

import htp.exceptions.NoSuchValueInDictionary;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Education {
    public static final int EDUCATION_HIGHER = 1;
    public static final int EDUCATION_MAGISTRACY = 2;
    public static final int EDUCATION_PHD = 3;
    public static final int EDUCATION_SECONDARY = 4;
    public static final int EDUCATION_VOCATIONAL = 5;
    public static final int EDUCATION_WITHOUT = 6;

    public static String getNameEducation (Integer education){
        switch (education){
            case 1: return "Higher education";
            case 2: return "Magistracy";
            case 3: return "PhD";
            case 4: return "secondary education";
            case 5: return "vocational education";
            case 6: return "Without education";
            default: throw new NoSuchValueInDictionary("No such education");
        }
    }
}
