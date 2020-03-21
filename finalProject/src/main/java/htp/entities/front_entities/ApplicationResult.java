package htp.entities.front_entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResult {
    private Long applicationId;
    private Timestamp creationDate;
    private String loanType;
    private Long productCode;
    private String currency;
    private Long loanAmount;
    private String decision;
    private Double finalAmount;
    private Double payment;
}
