package htp.domain.wrappers;

import htp.domain.model.Applicant;
import htp.domain.model.CreditInfo;
import htp.domain.dictionaries.Decision;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class ApplicantWrapper {
    private Applicant applicant;
    private List<CreditInfo> creditInfoList;

    private Integer ageMonths;
    private Integer maxTerm;
    private Integer loanTerm;

    private Double externalPayments;
    private Double maxAmount;

    private Integer score;

    private Decision decision;

}
