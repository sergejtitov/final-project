package htp.processors;

import htp.dao.CreditInfoRepository;
import htp.dao.ProductRepository;
import htp.dao.hibernate_Impl.CreditInfoHibernateImpl;
import htp.dao.hibernate_Impl.ProductHibernateImpl;
import htp.entities.db_entities.Application;
import htp.entities.db_entities.Product;
import htp.entities.wrappers.ApplicantWrapper;
import htp.entities.wrappers.ApplicationWrapper;
import htp.utils.Functions;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static htp.entities.dictionaries.TypeOfApplicant.APPLICANT;
import static htp.entities.dictionaries.TypeOfApplicant.GUARANTOR;

@Data
public class ApplicationProcessor {

    private ProductRepository productService;
    private CreditInfoRepository creditInfoService;
    private ApplicationWrapper applicationWrapper;
    private ApplicantProcessor applicantProcessor;
    private Product product;
    private CreatorWrappers creatorWrappers;


    public ApplicationProcessor(ProductHibernateImpl productService, CreditInfoHibernateImpl creditInfoService) {
        this.productService = productService;
        this.creditInfoService = creditInfoService;
        this.applicantProcessor = new ApplicantProcessor();
        creatorWrappers = new CreatorWrappersImpl();
    }

    public void start(Application application){
        this.applicationWrapper = creatorWrappers.createApplicationWrapper(application,creditInfoService, productService);
        this.applicationWrapper.setLoanTerm(calculateLoanTerm(applicationWrapper.getApplicantsWrapper()));
        setMaxAmounts(applicationWrapper.getApplicantsWrapper());
        this.applicationWrapper.setFinalAmount(setFinalAmount());
        applicationWrapper.setApplicantsWrapper(applicantProcessor.setApplicantsScore(applicationWrapper.getApplicantsWrapper(), applicationWrapper.getProductCode()));
    }

    private Integer calculateLoanTerm(List<ApplicantWrapper> applicants){
        List<Integer> applicantTerms = getListTerms(applicants);
        Integer minTerm = applicantTerms.stream().reduce(Integer::min).get();
        return Functions.positiveOrZeroInt(minTerm-product.getLoanTerm());
    }

    private List<Integer> getListTerms(List<ApplicantWrapper> applicants) {
        List<Integer> applicantTerms = new ArrayList<>();
        for (ApplicantWrapper applicantWrapper : applicants){
            applicantWrapper = applicantProcessor.definiteTerm(applicantWrapper, product);
            applicantTerms.add(applicantWrapper.getLoanTerm());
        }
        return applicantTerms;
    }

    private void setMaxAmounts(List<ApplicantWrapper> applicants){
        for (ApplicantWrapper applicantWrapper : applicants){
            applicantWrapper = applicantProcessor.definiteMaxAmount(applicantWrapper, product);
            if (applicantWrapper.getApplicant().getTypeOfApplicant().equals(APPLICANT)){
                applicationWrapper.setMaxApplicantAmount(applicantWrapper.getMaxAmount());
            }
            if (applicantWrapper.getApplicant().getTypeOfApplicant().equals(GUARANTOR)){
                applicationWrapper.addMaxGuarantorAmount(applicantWrapper.getMaxAmount());
            }
        }
        applicationWrapper.setMaxApplicationAmount(Math.min(applicationWrapper.getMaxApplicantAmount(), applicationWrapper.getMaxGuarantorAmount()));
    }

    private Double setFinalAmount(){
        double finalAmount = Math.min(applicationWrapper.getLoanAmount(), applicationWrapper.getProduct().getMaxAmount());
        finalAmount = Math.min(finalAmount, applicationWrapper.getMaxApplicantAmount());
        if (finalAmount < applicationWrapper.getProduct().getMinAmount()){
            finalAmount = 0;
        }
        return finalAmount;
    }
}
