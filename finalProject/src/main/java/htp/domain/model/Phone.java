package htp.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.util.Objects;

@Data
@ToString(exclude = {"applicant"})
@Entity
@Table(name = "m_phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Long phoneId;

    @Column(name = "phone_type")
    private String phoneType;

    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;



    public Phone() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneId, phoneNumber, phoneType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(phoneId, phone.phoneId) &&
                Objects.equals(phoneNumber, phone.phoneNumber) &&
                Objects.equals(phoneType, phone.phoneType) ;
    }
}
