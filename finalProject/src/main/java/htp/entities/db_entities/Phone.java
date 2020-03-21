package htp.entities.db_entities;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

@Data
public class Phone {
    private Long phoneId;

    private String phoneType;
    private String phoneNumber;

    private Applicant applicant;



    public Phone() {
    }

     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneId, applicant, phoneNumber, phoneType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(phoneId, phone.phoneId) &&
                Objects.equals(applicant, phone.applicant) &&
                Objects.equals(phoneNumber, phone.phoneNumber) &&
                Objects.equals(phoneType, phone.phoneType) ;
    }
}
