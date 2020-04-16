package com.htp.processors;


import com.htp.domain.dictionaries.Decision;
import com.htp.domain.dictionaries.Status;
import com.htp.domain.model.Applicant;
import com.htp.domain.model.Application;
import com.htp.domain.wrappers.ApplicantWrapper;
import com.htp.domain.wrappers.ApplicationWrapper;
import com.htp.repository.spring_data.CreditInfoDataRepository;
import com.htp.repository.spring_data.ProductDataRepository;

import com.htp.utils.Functions;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.htp.domain.dictionaries.TypeOfApplicant.APPLICANT;
import static com.htp.domain.dictionaries.TypeOfApplicant.GUARANTOR;


@Slf4j
@Data
@Service
public class ApplicationProcessor {
    public static final Double DECLINE_AMOUNT = 0D;

    private final ProductDataRepository productService;
    private final CreditInfoDataRepository creditInfoService;
    private final ApplicantProcessor applicantProcessor;
    private final CreatorWrappers creatorWrappers;

    public ApplicationProcessor(ProductDataRepository productService, CreditInfoDataRepository creditInfoService, ApplicantProcessor applicantProcessor, CreatorWrappers creatorWrappers) {
        this.productService = productService;
        this.creditInfoService = creditInfoService;
        this.applicantProcessor = applicantProcessor;
        this.creatorWrappers = creatorWrappers;
    }

    public Application start(Application application){
        ApplicationWrapper applicationWrapper = creatorWrappers.createApplicationWrapper(application,creditInfoService, productService);
        applicationWrapper.setLoanTerm(calculateLoanTerm(applicationWrapper));
        setMaxAmounts(applicationWrapper);
        applicationWrapper.setFinalAmount(setFinalAmount(applicationWrapper));
        applicationWrapper.setPayment(Functions.calculatePayment(applicationWrapper.getFinalAmount(), applicationWrapper.getProduct().getLoanTerm(), applicationWrapper.getProduct().getInterestRate()));
        applicationWrapper.setApplicantsWrapper(applicantProcessor.setApplicantsScore(applicationWrapper.getApplicantsWrapper(), applicationWrapper.getProductCode()));
        applicationWrapper.setDecision(makeDecision(applicationWrapper));
        if (applicationWrapper.getDecision().equals(Decision.DECLINE)){
            applicationWrapper.setStatus(Status.FAILURE);
        } else {applicationWrapper.setStatus(Status.ACCEPT);}
        return returnApplication(applicationWrapper);
    }

    private Integer calculateLoanTerm(ApplicationWrapper applicationWrapper){
        List<Integer> applicantTerms = getListTerms(applicationWrapper);
        Integer minTerm = applicantTerms.stream().reduce(Integer::min).get();
        return Functions.positiveOrZeroInt(minTerm);
    }

    private List<Integer> getListTerms(ApplicationWrapper applicationWrapper) {
        List<Integer> applicantTerms = new ArrayList<>();
        for (ApplicantWrapper applicantWrapper : applicationWrapper.getApplicantsWrapper()){
            applicantWrapper = applicantProcessor.definiteTerm(applicantWrapper, applicationWrapper.getProduct());
            applicantTerms.add(applicantWrapper.getLoanTerm());
        }
        return applicantTerms;
    }

    private void setMaxAmounts(ApplicationWrapper applicationWrapper){
        boolean hasGuarantors = false;
        for (ApplicantWrapper applicantWrapper : applicationWrapper.getApplicantsWrapper()){
            applicantWrapper = applicantProcessor.definiteMaxAmount(applicantWrapper, applicationWrapper.getProduct());
            if (applicantWrapper.getApplicant().getTypeOfApplicant().equals(APPLICANT)){
                applicationWrapper.setMaxApplicantAmount(applicantWrapper.getMaxAmount());
            }
            if (applicantWrapper.getApplicant().getTypeOfApplicant().equals(GUARANTOR)){
                applicationWrapper.addMaxGuarantorAmount(applicantWrapper.getMaxAmount());
                hasGuarantors =true;
            }
        }
        Double maxApplAmount;
        if (hasGuarantors) {
            maxApplAmount = Math.min(applicationWrapper.getMaxApplicantAmount(), applicationWrapper.getMaxGuarantorAmount());
        } else {
            maxApplAmount = applicationWrapper.getMaxApplicantAmount();
        }
        applicationWrapper.setMaxApplicationAmount(maxApplAmount);
    }

    private Double setFinalAmount(ApplicationWrapper applicationWrapper){
        double finalAmount = Math.min(applicationWrapper.getLoanAmount(), applicationWrapper.getProduct().getMaxAmount());
        finalAmount = Math.min(finalAmount, applicationWrapper.getMaxApplicationAmount());
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
        application.setApplicants(returnListApplicants(applicationWrapper.getApplicantsWrapper(), application));
        return application;
    }

    private Set<Applicant> returnListApplicants(List<ApplicantWrapper> applicantWrapperList, Application application){
        Set<Applicant> applicants = new HashSet<>();
        for (ApplicantWrapper applicantWrapper : applicantWrapperList){
            Applicant applicant = applicantWrapper.getApplicant();
            applicant.setApplication(application);
            applicants.add(applicantWrapper.getApplicant());
        }
        return applicants;
    }

    public Application confirm(Application item, Double finalAmount) {
        if (finalAmount < item.getFinalAmount()){
            item.setFinalAmount(DECLINE_AMOUNT);
            item.setStatus(Status.CUSTOMER_FAILURE);
            item.setPayment(DECLINE_AMOUNT);
        } else {
            item.setStatus(Status.ISSUED);
        }
        return item;
    }
}
