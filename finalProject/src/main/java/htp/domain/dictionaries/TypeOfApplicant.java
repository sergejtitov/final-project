package htp.domain.dictionaries;

import htp.exceptions.NoSuchValueInDictionary;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TypeOfApplicant {
    public static final int APPLICANT = 1;
    public static final int GUARANTOR = 2;

    public static String getNameTypeOfApplicant (Integer typeOfApplicant){
        switch (typeOfApplicant){
            case 1: return "Applicant";
            case 2: return "Guarantor";
            default: throw new NoSuchValueInDictionary("No such Type of Applicant");
        }
    }
}
