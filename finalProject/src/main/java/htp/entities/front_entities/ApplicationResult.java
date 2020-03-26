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
    private Integer productCode;
    private Double loanAmount;
    private String decision;
    private Double finalAmount;
    private Double payment;
    private String status;
}
