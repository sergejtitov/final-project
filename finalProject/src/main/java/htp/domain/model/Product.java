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
@Table(name = "m_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_code")
    private Integer productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "loan_term")
    private Integer loanTerm;

    @Column(name = "min_amount")
    private Long minAmount;

    @Column(name = "max_amount")
    private Long maxAmount;

    @Column
    private Double coefficient;

    public Product() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productCode, productName, interestRate, loanTerm, minAmount, maxAmount, coefficient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(productCode, product.productCode) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(interestRate, product.interestRate) &&
                Objects.equals(loanTerm, product.loanTerm) &&
                Objects.equals(minAmount, product.minAmount) &&
                Objects.equals(maxAmount, product.maxAmount) &&
                Objects.equals(coefficient, product.coefficient);
    }
}
