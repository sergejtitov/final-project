package htp.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Address {
    private Long addressId;
    private Long applicantId;
    private String address;
    private String addressType;

    public Address(Long addressId, Long applicantId, String address, String addressType) {
        this.addressId = addressId;
        this.applicantId = applicantId;
        this.address = address;
        this.addressType = addressType;
    }

    public Address() {
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, applicantId, address, addressType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(addressId, address1.addressId)&&
                Objects.equals(applicantId, address1.applicantId)&&
                Objects.equals(address, address1.address)&&
                Objects.equals(addressType, address1.addressType);
    }
}
