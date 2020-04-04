package htp.processors;

import htp.dao.CreditInfoRepository;
import htp.dao.ProductRepository;
import htp.domain.model.Applicant;
import htp.domain.model.Application;
import htp.domain.model.CreditInfo;
import htp.domain.wrappers.ApplicantWrapper;
import htp.domain.wrappers.ApplicationWrapper;

import java.util.ArrayList;
import java.util.List;

public class CreatorWrappersImpl implements CreatorWrappers {
    @Override
    public ApplicantWrapper createApplicantWrapper(Applicant applicant, List<CreditInfo> creditInfos) {
            ApplicantWrapper applicantWrapper = new ApplicantWrapper();
            applicantWrapper.setApplicant(applicant);
            applicantWrapper.setCreditInfoList(creditInfos);
            return applicantWrapper;
    }

    @Override
    public List<ApplicantWrapper> createListApplicantWrapper(Application application, CreditInfoRepository creditInfoService) {
        List<ApplicantWrapper> applicantWrappers = new ArrayList<>();
        for (Applicant applicant: application.getApplicants()){
            ApplicantWrapper applicantWrapper = createApplicantWrapper(applicant, creditInfoService.findCreditInfosByPersonalNumber(applicant.getPersonalNumber()));
            applicantWrappers.add(applicantWrapper);
        }
        return applicantWrappers;
    }

    @Override
    public ApplicationWrapper createApplicationWrapper(Application application, CreditInfoRepository creditInfoService, ProductRepository productService) {
        ApplicationWrapper applicationWrapper = new ApplicationWrapper();
        applicationWrapper.setApplicantsWrapper(createListApplicantWrapper(application, creditInfoService));
        applicationWrapper.setProduct(productService.findByProductCode(application.getProductCode()));
        applicationWrapper.setUserId(application.getUserId());
        applicationWrapper.setCreationDate(application.getCreationDate());
        applicationWrapper.setLoanType(application.getLoanType());
        applicationWrapper.setProductCode(application.getProductCode());
        applicationWrapper.setLoanAmount(application.getLoanAmount());
        return applicationWrapper;
    }
}
