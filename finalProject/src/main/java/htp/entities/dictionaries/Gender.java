package htp.entities.dictionaries;

import htp.exceptions.NoSuchValueInDictionary;

public enum Gender {
    M("Male"),
    F("Female");
    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static Gender getGenderFromString(String sex){
        switch (sex){
            case "M":return Gender.M;
            case "F":return Gender.F;
            default: throw new NoSuchValueInDictionary("No such Gender");
        }

    }

    @Override
    public String toString() {
        return gender;
    }
}
