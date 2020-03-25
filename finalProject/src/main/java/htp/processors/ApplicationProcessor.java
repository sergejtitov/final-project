package htp.processors;

import htp.dao.CreditInfoRepository;
import htp.dao.ProductRepository;
import htp.dao.hibernate_Impl.CreditInfoHibernateImpl;
import htp.dao.hibernate_Impl.ProductHibernateImpl;
import htp.entities.db_entities.Applicant;
import htp.entities.db_entities.Application;
import htp.entities.db_entities.Product;
import htp.entities.wrappers.ApplicantWrapper;
import htp.entities.wrappers.ApplicationWrapper;
import htp.utils.Functions;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApplicationProcessor {
    public static final Integer MAIN_APPLICANT = 1;
    public static final Integer GUARANTOR = 2;

    private ProductRepository productService;
    private CreditInfoRepository creditInfoService;
    private ApplicationWrapper applicationWrapper;
    private ApplicantProcessor applicantProcessor;
    private Product product;


    public ApplicationProcessor(ProductHibernateImpl productService, CreditInfoHibernateImpl creditInfoService) {
        this.productService = productService;
        this.creditInfoService = creditInfoService;
        this.applicantProcessor = new ApplicantProcessor();
    }

    public void start(Application application){
        this.applicationWrapper = createApplicationWrapper(application);
        setLoanTerm(applicationWrapper.getApplicantsWrapper());
        setFinalAmount(applicationWrapper.getApplicantsWrapper());
    }

    private void setLoanTerm(List<ApplicantWrapper> applicants){
        List<Integer> applicantTerms = getListTerms(applicants);
        Integer minTerm = applicantTerms.stream().reduce(Integer::min).get();
        applicationWrapper.setLoanTerm(Functions.positiveOrZeroInt(minTerm-product.getLoanTerm()));
    }

    private List<Integer> getListTerms(List<ApplicantWrapper> applicants) {
        List<Integer> applicantTerms = new ArrayList<>();
        for (ApplicantWrapper applicantWrapper : applicants){
            applicantWrapper = applicantProcessor.definiteTerm(applicantWrapper, product);
            applicantTerms.add(applicantWrapper.getLoanTerm());
        }
        return applicantTerms;
    }

    private List<ApplicantWrapper> createListApplicantWrapper(Application application){
        List<ApplicantWrapper> applicantWrappers = new ArrayList<>();
        for (Applicant applicant: application.getApplicants()){
            ApplicantWrapper applicantWrapper = createApplicantWrapper(applicant);
            applicantWrappers.add(applicantWrapper);
        }
        return applicantWrappers;
    }

    private ApplicantWrapper createApplicantWrapper(Applicant applicant){
        ApplicantWrapper applicantWrapper = new ApplicantWrapper();
        applicantWrapper.setApplicant(applicant);
        applicantWrapper.setCreditInfoList(creditInfoService.findCreditInfosByPersonalNumber(applicant.getPersonalNumber()));
        return applicantWrapper;
    }

    private ApplicationWrapper createApplicationWrapper (Application application){
        ApplicationWrapper applicationWrapper = new ApplicationWrapper();
        applicationWrapper.setApplicantsWrapper(createListApplicantWrapper(application));
        applicationWrapper.setProduct(productService.findByProductCode(application.getProductCode()));
        applicationWrapper.setUserId(application.getUserId());
        applicationWrapper.setCreationDate(application.getCreationDate());
        applicationWrapper.setLoanType(application.getLoanType());
        applicationWrapper.setProductCode(application.getProductCode());
        applicationWrapper.setLoanAmount(application.getLoanAmount());
        return applicationWrapper;
    }

    private void setFinalAmount(List<ApplicantWrapper> applicants){
        for (ApplicantWrapper applicantWrapper : applicants){
            applicantWrapper = applicantProcessor.definiteMaxAmount(applicantWrapper, product);
            if (applicantWrapper.getApplicant().getTypeOfApplicant().equals(MAIN_APPLICANT)){
                applicationWrapper.setMaxApplicantAmount(applicantWrapper.getMaxAmount());
            }
            if (applicantWrapper.getApplicant().getTypeOfApplicant().equals(GUARANTOR)){
                applicationWrapper.addMaxGuarantorAmount(applicantWrapper.getMaxAmount());
            }
        }
        applicationWrapper.setMaxApplicationAmount(Math.min(applicationWrapper.getMaxApplicantAmount(), applicationWrapper.getMaxGuarantorAmount()));
    }
}
