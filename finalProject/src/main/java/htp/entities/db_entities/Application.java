package htp.entities.db_entities;

import htp.entities.dictionaries.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Objects;

public class Application {
    private Long applicationId;
    private Long userId;
    private Timestamp creationDate;
    private LoanType loanType;
    private ProductCode productCode;
    private Double loanAmount;
    private MyCurrency currency;
    private Double finalAmount;
    private Decision decision;
    private Status status;
    private Double payment;

    public Application() {
    }

    public Application(Long applicationId, Long userId, Timestamp creationDate, LoanType loanType, ProductCode productCode, Double loanAmount, MyCurrency currency, Double finalAmount, Decision decision, Status status, Double payment) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public ProductCode getProductCode() {
        return productCode;
    }

    public void setProductCode(ProductCode productCode) {
        this.productCode = productCode;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public MyCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(MyCurrency currency) {
        this.currency = currency;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
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

