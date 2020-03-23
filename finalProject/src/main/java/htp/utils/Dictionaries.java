package htp.utils;

import htp.entities.dictionaries.Gender;
import htp.exceptions.NoSuchValueInDictionary;

public class Dictionaries {

    public static Gender setGender(String sex){
        switch (sex){
            case "M":return Gender.M;
            case "F":return Gender.F;
            default: throw new NoSuchValueInDictionary("No such Gender");
        }

    }

    public static String convertTypeOfApplicant (Integer typeOfApplicant){
        switch (typeOfApplicant){
            case 1: return "Applicant";
            case 2: return "Guarantor";
            default: throw new NoSuchValueInDictionary("No such Type of Applicant");
        }
    }

    public static String convertLoanType (Integer loanType){
        switch (loanType){
            case 1: return "Mortgage";
            case 2: return "Auto";
            case 3: return "Consumer Loan";
            case 4: return "Credit card";
            default: throw new NoSuchValueInDictionary("No such Loan Type");
        }
    }

    public static String convertExperience(Integer experience){
        switch (experience){
            case 1: return "Less than 1 month";
            case 2: return "From 1 month to 1 year";
            case 3: return "From 1 year to 3 years";
            case 4: return "From 3 years to 10 years";
            case 5: return "More than 10 years";
            default: throw new NoSuchValueInDictionary("No such Experience");
        }
    }
    public static String convertMaritalStatus(Integer maritalStatus){
        switch (maritalStatus){
            case 1: return  "Single";
            case 2: return "Married";
            case 3: return "Divorced";
            default: throw  new NoSuchValueInDictionary("No such Marital status");
        }
    }

    public static String convertEducation (Integer education){
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
