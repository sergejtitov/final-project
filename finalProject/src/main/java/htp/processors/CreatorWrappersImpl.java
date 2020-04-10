package htp.processors;

import htp.dao.spring_data.CreditInfoDataRepository;
import htp.dao.spring_data.ProductDataRepository;
import htp.domain.model.Applicant;
import htp.domain.model.Application;
import htp.domain.model.CreditInfo;
import htp.domain.model.Product;
import htp.domain.wrappers.ApplicantWrapper;
import htp.domain.wrappers.ApplicationWrapper;
import htp.exceptions.NoSuchEntityException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CreatorWrappersImpl implements CreatorWrappers {
    @Override
    public ApplicantWrapper createApplicantWrapper(Applicant applicant, List<CreditInfo> creditInfos) {
            ApplicantWrapper applicantWrapper = new ApplicantWrapper();
            applicantWrapper.setApplicant(applicant);
            applicantWrapper.setCreditInfoList(creditInfos);
            return applicantWrapper;
    }

    @Override
    public List<ApplicantWrapper> createListApplicantWrapper(Application application, CreditInfoDataRepository creditInfoService) {
        List<ApplicantWrapper> applicantWrappers = new ArrayList<>();
        for (Applicant applicant: application.getApplicants()){
            ApplicantWrapper applicantWrapper = createApplicantWrapper(applicant, creditInfoService.findCreditInfoByPersonalNumber(applicant.getPersonalNumber()));
            applicantWrappers.add(applicantWrapper);
        }
        return applicantWrappers;
    }

    @Override
    public ApplicationWrapper createApplicationWrapper(Application application, CreditInfoDataRepository creditInfoService, ProductDataRepository productService) {
        ApplicationWrapper applicationWrapper = new ApplicationWrapper();
        applicationWrapper.setApplicantsWrapper(createListApplicantWrapper(application, creditInfoService));
        Optional<Product> product = productService.findByProductCode(application.getProductCode());
        if (product.isPresent()) {
            applicationWrapper.setProduct(product.get());
        } else {
            throw new NoSuchEntityException("No such product");
        }
        applicationWrapper.setUserId(application.getUserId());
        applicationWrapper.setCreationDate(application.getCreationDate());
        applicationWrapper.setLoanType(application.getLoanType());
        applicationWrapper.setProductCode(application.getProductCode());
        applicationWrapper.setLoanAmount(application.getLoanAmount());
        return applicationWrapper;
    }
}
