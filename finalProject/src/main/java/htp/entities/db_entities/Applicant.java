package htp.entities.db_entities;

import htp.entities.dictionaries.Gender;
import htp.entities.dictionaries.MyCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
public class Applicant {
    private Long applicantId;
    private String firstName;
    private String secondName;
    private String patronymic;
    private Integer typeOfApplicant;
    private Timestamp birthdayDate;
    private Long income;
    private MyCurrency incomeCurrency;
    private Gender sex;
    private Integer experience;
    private Integer maritalStatus;
    private Integer education;
    private Integer childrenQuantity;
    private String personalNumber;
    private Set<Phone> phones;
    private Set<Address> addresses;
    private Application application;



    public Applicant() {
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicantId, firstName, secondName, patronymic, typeOfApplicant,birthdayDate, income, incomeCurrency, sex, experience, maritalStatus, education, childrenQuantity, personalNumber);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(applicantId, applicant.applicantId) &&
                Objects.equals(firstName, applicant.firstName) &&
                Objects.equals(secondName, applicant.secondName) &&
                Objects.equals(patronymic, applicant.patronymic) &&
                Objects.equals(typeOfApplicant, applicant.typeOfApplicant) &&
                Objects.equals(birthdayDate, applicant.birthdayDate) &&
                Objects.equals(income, applicant.income) &&
                Objects.equals(incomeCurrency, applicant.incomeCurrency) &&
                Objects.equals(sex, applicant.sex) &&
                Objects.equals(experience, applicant.experience) &&
                Objects.equals(maritalStatus, applicant.maritalStatus) &&
                Objects.equals(education, applicant.education) &&
                Objects.equals(childrenQuantity, applicant.childrenQuantity) &&
                Objects.equals(personalNumber, applicant.personalNumber) ;
    }
}
