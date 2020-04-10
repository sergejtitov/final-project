package htp.domain.model;


import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Objects;

@Slf4j
@Data
@ToString
@Entity
@Table(name = "m_credit_info")
public class CreditInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Long infoId;

    @Column(name = "loan_amount")
    private Double loanAmount;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "balance_amount")
    private Double balanceAmount;

    @Column(name = "balance_term")
    private Integer balanceTerm;

    @Column
    private Double payment;

    @Column(name = "personal_number")
    private String personalNumber;

    @Column(name = "application_id")
    private Long applicationId;

    public CreditInfo() {
    }


    @Override
    public int hashCode() {
        return Objects.hash(infoId, loanAmount, interestRate, balanceAmount, balanceTerm, payment, personalNumber, applicationId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditInfo creditInfo = (CreditInfo) o;
        return Objects.equals(infoId, creditInfo.infoId) &&
                Objects.equals(loanAmount, creditInfo.loanAmount) &&
                Objects.equals(interestRate, creditInfo.interestRate) &&
                Objects.equals(balanceAmount, creditInfo.balanceAmount) &&
                Objects.equals(balanceTerm, creditInfo.balanceTerm) &&
                Objects.equals(payment, creditInfo.payment) &&
                Objects.equals(personalNumber, creditInfo.personalNumber)&&
                Objects.equals(applicationId, creditInfo.applicationId);
    }
}
