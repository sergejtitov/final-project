package htp.entities.wrappers;

import htp.entities.db_entities.Applicant;
import htp.entities.db_entities.CreditInfo;
import lombok.Data;

import java.util.List;

@Data
public class ApplicantWrapper {
    private Applicant applicant;
    private List<CreditInfo> creditInfoList;

    private Integer ageMonths;
    private Double externalPayments;
    private Integer maxTerm;
    private Integer loanTerm;
    private Double maxAmount;

}
