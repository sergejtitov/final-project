package htp.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Objects;

public class Application {
    private Long applicationId;
    private long userId;
    private Timestamp creationDate;
    private int loanType;
    private long productCode;
    private double loanAmount;
    private String currency;
    private double finalAmount;
    private String decision;
    private String status;
    private double payment;

    public Application() {
    }

    public Application(Long applicationId, long userId, Timestamp creationDate, int loanType, long productCode, double loanAmount, String currency, double finalAmount, String decision, String status, double payment) {
        this.applicationId = applicationId;
        this.userId = userId;
        this.creationDate = creationDate;
        this.loanType = loanType;
        this.productCode = productCode;
        this.loanAmount = loanAmount;
        this.currency = currency;
        this.finalAmount = finalAmount;
        this.decision = decision;
        this.status = status;
        this.payment = payment;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public int getLoanType() {
        return loanType;
    }

    public void setLoanType(int loanType) {
        this.loanType = loanType;
    }

    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, userId, creationDate, loanType, productCode, loanAmount, currency, finalAmount, decision, status, payment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application application = (Application) o;
        return Objects.equals(applicationId, application.applicationId) &&
                Objects.equals(userId, application.userId) &&
                Objects.equals(creationDate, application.creationDate) &&
                Objects.equals(loanType, application.loanType) &&
                Objects.equals(productCode, application.productCode) &&
                Objects.equals(loanAmount, application.loanAmount) &&
                Objects.equals(currency, application.currency) &&
                Objects.equals(finalAmount, application.finalAmount) &&
                Objects.equals(decision, application.decision) &&
                Objects.equals(status, application.status) &&
                Objects.equals(payment, application.payment);
    }
}

