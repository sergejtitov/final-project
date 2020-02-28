package htp.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class CreditInfo {
    private Long infoId;
    private Double loanAmount;
    private Double interestRate;
    private Double balanceAmount;
    private Integer balanceTerm;
    private Double payment;
    private Long applicantId;

    public CreditInfo() {
    }

    public CreditInfo(Long infoId, Double loanAmount, Double interestRate, Double balanceAmount, Integer balanceTerm, Double payment, Long applicantId) {
        this.infoId = infoId;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.balanceAmount = balanceAmount;
        this.balanceTerm = balanceTerm;
        this.payment = payment;
        this.applicantId = applicantId;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Integer getBalanceTerm() {
        return balanceTerm;
    }

    public void setBalanceTerm(Integer balanceTerm) {
        this.balanceTerm = balanceTerm;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(infoId, loanAmount, interestRate, balanceAmount, balanceTerm, payment, applicantId);
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
                Objects.equals(applicantId, creditInfo.applicantId);
    }
}
