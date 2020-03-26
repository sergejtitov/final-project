package htp.processors;

import htp.dao.CreditInfoRepository;
import htp.dao.ProductRepository;
import htp.entities.db_entities.Applicant;
import htp.entities.db_entities.Application;
import htp.entities.dictionaries.Decision;
import htp.entities.dictionaries.Status;
import htp.entities.wrappers.ApplicantWrapper;
import htp.entities.wrappers.ApplicationWrapper;
import htp.utils.Functions;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static htp.entities.dictionaries.TypeOfApplicant.APPLICANT;
import static htp.entities.dictionaries.TypeOfApplicant.GUARANTOR;

@Data
public class ApplicationProcessor {

    private ProductRepository productService;
    private CreditInfoRepository creditInfoService;
    private ApplicationWrapper applicationWrapper;
    private ApplicantProcessor applicantProcessor;
    private CreatorWrappers creatorWrappers;


    public ApplicationProcessor(ProductRepository productService, CreditInfoRepository creditInfoService) {
        this.productService = productService;
        this.creditInfoService = creditInfoService;
        this.applicantProcessor = new ApplicantProcessor();
        creatorWrappers = new CreatorWrappersImpl();
    }

    public Application start(Application application){
        this.applicationWrapper = creatorWrappers.createApplicationWrapper(application,creditInfoService, productService);
        this.applicationWrapper.setLoanTerm(calculateLoanTerm(applicationWrapper.getApplicantsWrapper()));
        setMaxAmounts(applicationWrapper.getApplicantsWrapper());
        this.applicationWrapper.setFinalAmount(setFinalAmount());
        this.applicationWrapper.setPayment(Functions.calculatePayment(this.applicationWrapper.getFinalAmount(), applicationWrapper.getProduct().getLoanTerm(), applicationWrapper.getProduct().getInterestRate()));
        applicationWrapper.setApplicantsWrapper(applicantProcessor.setApplicantsScore(applicationWrapper.getApplicantsWrapper(), applicationWrapper.getProductCode()));
        applicationWrapper.setDecision(makeDecision(applicationWrapper));
        if (applicationWrapper.getDecision().equals(Decision.DECLINE)){
            applicationWrapper.setStatus(Status.FAILURE);
        } else {applicationWrapper.setStatus(Status.ACCEPT);}
        return returnApplication(this.applicationWrapper);
    }

    private Integer calculateLoanTerm(List<ApplicantWrapper> applicants){
        List<Integer> applicantTerms = getListTerms(applicants);
        Integer minTerm = applicantTerms.stream().reduce(Integer::min).get();
        return Functions.positiveOrZeroInt(minTerm);
    }

    private List<Integer> getListTerms(List<ApplicantWrapper> applicants) {
        List<Integer> applicantTerms = new ArrayList<>();
        for (ApplicantWrapper applicantWrapper : applicants){
            applicantWrapper = applicantProcessor.definiteTerm(applicantWrapper, applicationWrapper.getProduct());
            applicantTerms.add(applicantWrapper.getLoanTerm());
        }
        return applicantTerms;
    }

    private void setMaxAmounts(List<ApplicantWrapper> applicants){
        boolean hasGuarantors = false;
        for (ApplicantWrapper applicantWrapper : applicants){
            applicantWrapper = applicantProcessor.definiteMaxAmount(applicantWrapper, applicationWrapper.getProduct());
            if (applicantWrapper.getApplicant().getTypeOfApplicant().equals(APPLICANT)){
                applicationWrapper.setMaxApplicantAmount(applicantWrapper.getMaxAmount());
            }
            if (applicantWrapper.getApplicant().getTypeOfApplicant().equals(GUARANTOR)){
                applicationWrapper.addMaxGuarantorAmount(applicantWrapper.getMaxAmount());
                hasGuarantors =true;
            }
        }
        if (hasGuarantors) {
            applicationWrapper.setMaxApplicationAmount(Math.min(applicationWrapper.getMaxApplicantAmount(), applicationWrapper.getMaxGuarantorAmount()));
        } else {
            applicationWrapper.setMaxApplicationAmount(applicationWrapper.getMaxApplicantAmount());
        }
    }

    private Double setFinalAmount(){
        double finalAmount = Math.min(applicationWrapper.getLoanAmount(), applicationWrapper.getProduct().getMaxAmount());
        finalAmount = Math.min(finalAmount, applicationWrapper.getMaxApplicantAmount());
        if (finalAmount < applicationWrapper.getProduct().getMinAmount()){
            finalAmount = 0;
        }
        return finalAmount;
    }

    private Decision makeDecision (ApplicationWrapper applicationWrapper){
        for (ApplicantWrapper applicantWrapper : applicationWrapper.getApplicantsWrapper()){
            applicantWrapper.setDecision(applicantProcessor.makeDecisionForApplicant(applicantWrapper, applicationWrapper.getProduct()));
            if (applicantWrapper.getDecision().equals(Decision.DECLINE)){
                return Decision.DECLINE;
            }
        }
        if (!applicationWrapper.getLoanTerm().equals(applicationWrapper.getProduct().getLoanTerm())){
            return Decision.DECLINE;
        }
        if (applicationWrapper.getFinalAmount()<applicationWrapper.getProduct().getMinAmount()){
            return Decision.DECLINE;
        }
        return Decision.ACCEPT;
    }

    private Application returnApplication (ApplicationWrapper applicationWrapper){
        Application application = new Application();
        application.setUserId(applicationWrapper.getUserId());
        application.setCreationDate(applicationWrapper.getCreationDate());
        application.setLoanType(applicationWrapper.getLoanType());
        application.setProductCode(applicationWrapper.getProductCode());
        application.setLoanAmount(applicationWrapper.getLoanAmount());
        application.setFinalAmount(applicationWrapper.getFinalAmount());
        application.setDecision(applicationWrapper.getDecision());
        application.setStatus(applicationWrapper.getStatus());
        application.setPayment(applicationWrapper.getPayment());
        application.setApplicants(returnListApplicants(applicationWrapper.getApplicantsWrapper()));
        return application;
    }

    private Set<Applicant> returnListApplicants(List<ApplicantWrapper> applicantWrapperList){
        Set<Applicant> applicants = new HashSet<>();
        for (ApplicantWrapper applicantWrapper : applicantWrapperList){
            applicants.add(applicantWrapper.getApplicant());
        }
        return applicants;
    }
}
