package htp.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class CreditInfo {
    private long infoId;
    private double loanAmount;
    private double interestRate;
    private double balanceAmount;
    private int balanceTerm;
    private double payment;
    private long applicantId;

    public CreditInfo() {
    }

    public CreditInfo(long infoId, double loanAmount, double interestRate, double balanceAmount, int balanceTerm, double payment, long applicantId) {
        this.infoId = infoId;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.balanceAmount = balanceAmount;
        this.balanceTerm = balanceTerm;
        this.payment = payment;
        this.applicantId = applicantId;
    }

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public int getBalanceTerm() {
        return balanceTerm;
    }

    public void setBalanceTerm(int balanceTerm) {
        this.balanceTerm = balanceTerm;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
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
