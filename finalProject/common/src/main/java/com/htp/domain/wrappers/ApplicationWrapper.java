package com.htp.domain.wrappers;



import com.htp.domain.dictionaries.Decision;
import com.htp.domain.dictionaries.Status;
import com.htp.domain.model.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Data
public class ApplicationWrapper {
    public static final double INITIAL_VALUE = 0D;

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
    private Double maxGuarantorAmount = INITIAL_VALUE;
    private Double maxApplicationAmount;

    public void addMaxGuarantorAmount(Double value){
        maxGuarantorAmount +=value;
    }

}
