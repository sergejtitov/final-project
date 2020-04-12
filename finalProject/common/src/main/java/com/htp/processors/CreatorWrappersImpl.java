package com.htp.processors;

import com.htp.domain.model.Applicant;
import com.htp.domain.model.Application;
import com.htp.domain.model.CreditInfo;
import com.htp.domain.model.Product;
import com.htp.domain.wrappers.ApplicantWrapper;
import com.htp.domain.wrappers.ApplicationWrapper;
import com.htp.repository.spring_data.CreditInfoDataRepository;
import com.htp.repository.spring_data.ProductDataRepository;

import com.htp.exceptions.NoSuchEntityException;
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
