package htp.entities.db_entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import htp.entities.dictionaries.Decision;
import htp.entities.dictionaries.MyCurrency;
import htp.entities.dictionaries.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = {"applicants"})
@Entity
@Table(name = "m_application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "loan_type")
    private Integer loanType;

    @Column(name = "product_code")
    private Integer productCode;

    @Column(name = "loan_amount")
    private Double loanAmount;

    @Enumerated(STRING)
    @Column
    private MyCurrency currency;

    @Column(name = "final_amount")
    private Double finalAmount;

    @Enumerated(STRING)
    @Column
    private Decision decision;

    @Enumerated(STRING)
    @Column
    private Status status;

    @Column
    private Double payment;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "application")
    private Set<Applicant> applicants;

    public Application() {
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

