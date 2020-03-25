package htp.entities.wrappers;


import htp.entities.db_entities.Product;
import htp.entities.dictionaries.Decision;
import htp.entities.dictionaries.Status;
import lombok.Data;


import java.sql.Timestamp;
import java.util.List;

@Data
public class ApplicationWrapper {
    List<ApplicantWrapper> applicantsWrapper;
    Product product;

    private Long applicationId;
    private Long userId;
    private Timestamp creationDate;
    private Integer loanType;
    private Integer productCode;
    private Double loanAmount;
    private Double finalAmount;
    private Decision decision;
    private Status status;
    private Double payment;

    private Integer loanTerm;

    private Double maxApplicantAmount;
    private Double maxGuarantorAmount;
    private Double maxApplicationAmount;

    public void addMaxGuarantorAmount(Double value){
        maxGuarantorAmount +=value;
    }

}
