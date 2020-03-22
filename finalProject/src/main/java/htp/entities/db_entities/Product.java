package htp.entities.db_entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Objects;


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
    private Long productCode;

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

    public Product() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productCode, productName, interestRate, loanTerm, minAmount, maxAmount);
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
                Objects.equals(maxAmount, product.maxAmount);
    }
}
