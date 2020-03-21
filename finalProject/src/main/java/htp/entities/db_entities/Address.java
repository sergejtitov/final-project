package htp.entities.db_entities;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

@Data
public class Address {
    private Long addressId;
    private String address;
    private String addressType;
    private Applicant applicant;

    public Address() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, applicant, address, addressType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(addressId, address1.addressId)&&
                Objects.equals(applicant, address1.applicant)&&
                Objects.equals(address, address1.address)&&
                Objects.equals(addressType, address1.addressType);
    }
}
