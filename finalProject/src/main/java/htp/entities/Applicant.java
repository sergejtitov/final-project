package htp.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.Objects;

public class Applicant {
    private long applicantId;
    private String firstName;
    private String secondName;
    private String patronymic;
    private int typeOfApplicant;
    private Date birthdayDate;
    private long income;
    private String incomeCurrency;
    private String sex;
    private int experience;
    private int maritalStatus;
    private int education;
    private int childrenQuantity;
    private String personalNumber;
    private long applicationId;

    public Applicant(long applicantId, String firstName, String secondName, String patronymic, int typeOfApplicant, Date birthdayDate, long income, String incomeCurrency, String sex, int experience, int maritalStatus, int education, int childrenQuantity, String personalNumber, long applicationId) {
        this.applicantId = applicantId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.typeOfApplicant = typeOfApplicant;
        this.birthdayDate = birthdayDate;
        this.income = income;
        this.incomeCurrency = incomeCurrency;
        this.sex = sex;
        this.experience = experience;
        this.maritalStatus = maritalStatus;
        this.education = education;
        this.childrenQuantity = childrenQuantity;
        this.personalNumber = personalNumber;
        this.applicationId = applicationId;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Applicant() {
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getTypeOfApplicant() {
        return typeOfApplicant;
    }

    public void setTypeOfApplicant(int typeOfApplicant) {
        this.typeOfApplicant = typeOfApplicant;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public String getIncomeCurrency() {
        return incomeCurrency;
    }

    public void setIncomeCurrency(String incomeCurrency) {
        this.incomeCurrency = incomeCurrency;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getChildrenQuantity() {
        return childrenQuantity;
    }

    public void setChildrenQuantity(int childrenQuantity) {
        this.childrenQuantity = childrenQuantity;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicantId, firstName, secondName, patronymic, typeOfApplicant,birthdayDate, income, incomeCurrency, sex, experience, maritalStatus, education, childrenQuantity, personalNumber, applicationId);
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
                Objects.equals(personalNumber, applicant.personalNumber) &&
                Objects.equals(applicationId, applicant.applicationId);
    }
}
