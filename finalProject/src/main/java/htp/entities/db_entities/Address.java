package htp.entities.db_entities;

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
@Table(name = "m_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column
    private String address;

    @Column(name = "address_type")
    private String addressType;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    public Address() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId,  address, addressType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(addressId, address1.addressId)&&
                Objects.equals(address, address1.address)&&
                Objects.equals(addressType, address1.addressType);
    }
}
