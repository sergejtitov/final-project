package htp.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Phone {
    private long phoneId;
    private long applicantId;
    private String phoneType;
    private String phoneNumber;

    public Phone(long phoneId, long applicantId, String phoneType, String phoneNumber) {
        this.phoneId = phoneId;
        this.applicantId = applicantId;
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
    }

    public Phone() {
    }

    public long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(long phoneId) {
        this.phoneId = phoneId;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneId, applicantId, phoneNumber, phoneType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(phoneId, phone.phoneId) &&
                Objects.equals(applicantId, phone.applicantId) &&
                Objects.equals(phoneNumber, phone.phoneNumber) &&
                Objects.equals(phoneType, phone.phoneType) ;
    }
}
