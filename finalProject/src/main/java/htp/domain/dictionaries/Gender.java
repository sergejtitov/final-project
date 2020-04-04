package htp.domain.dictionaries;

import htp.exceptions.NoSuchValueInDictionary;

import java.util.Collections;
import java.util.Set;

public enum Gender {
    M("Male"),
    F("Female"),
    NOT_SELECTED("Not selected");

    public static final Set<Gender> genders = Collections.unmodifiableSet(Set.of(Gender.values()));

    private String genderName;

    Gender(String genderName) {
        this.genderName = genderName;
    }

    public String getGenderName() {
        return genderName;
    }

    public static Gender findByName (String genderName){
        for (Gender gender : genders){
            if (gender.getGenderName().equalsIgnoreCase(genderName)){
                return gender;
            }
        }
        return NOT_SELECTED;
    }
}
