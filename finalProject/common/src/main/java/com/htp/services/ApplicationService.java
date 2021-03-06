package com.htp.services;


import com.htp.domain.dictionaries.Decision;
import com.htp.domain.dictionaries.Status;
import com.htp.domain.model.Application;
import com.htp.domain.model.CreditInfo;
import com.htp.domain.model.Product;
import com.htp.repository.spring_data.ApplicationDataRepository;
import com.htp.repository.spring_data.CreditInfoDataRepository;
import com.htp.repository.spring_data.ProductDataRepository;
import com.htp.exceptions.NoSuchEntityException;
import com.htp.processors.ApplicationProcessor;
import com.htp.utils.CustomValidation;

import com.htp.utils.Parser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ApplicationService {

    private final ProductDataRepository productDao;
    private final CreditInfoDataRepository creditInfoDataRepository;
    private final ApplicationDataRepository applicationDataRepository;
    private final CustomValidation customValidation;
    private final ApplicationProcessor processor;

    public ApplicationService(ProductDataRepository productDao, CreditInfoDataRepository creditInfoDataRepository, ApplicationDataRepository applicationDataRepository, CustomValidation customValidation, ApplicationProcessor processor) {
        this.productDao = productDao;
        this.creditInfoDataRepository = creditInfoDataRepository;
        this.applicationDataRepository = applicationDataRepository;
        this.customValidation = customValidation;
        this.processor = processor;
    }

    public Page<Application> findAll(int limit, int offset) {
        return applicationDataRepository.findAll(PageRequest.of(offset, limit));
    }

    public List<Application> findAll(Specification<Application> specification){
        return applicationDataRepository.findAll(specification);
    }

    public List<Application> findApplicationByUserId(Long userId) {
        return applicationDataRepository.findApplicationByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Application save(Application item) {
        customValidation.validate(item);
        Application application = processor.start(item);
        Application savedApplication = applicationDataRepository.save(application);
        if (application.getDecision().equals(Decision.ACCEPT)){
            Optional<Product> product = productDao.findByProductCode(application.getProductCode());
            if (product.isPresent()){
                CreditInfo creditInfo = Parser.createCreditInfo(application, product.get());
                creditInfoDataRepository.save(creditInfo);
            }
        }
       return savedApplication;
    }

    public Application update(Long applicationId, Double finalAmount, Long userId) {
        Optional<Application> optionalUpdatedApplication = applicationDataRepository.findById(applicationId);
        if (optionalUpdatedApplication.isPresent()) {
            Application updatedApplication = optionalUpdatedApplication.get();
            if (updatedApplication.getUserId().equals(userId)) {
                updatedApplication = methodToSaveApplication(finalAmount, updatedApplication);
                return updatedApplication;
            } else {
                throw new AuthenticationServiceException("Access is denied");
            }
        } else {
            throw new NoSuchEntityException("No such Application");
        }
    }

    private Application methodToSaveApplication(Double finalAmount, Application updatedApplication) {
        if (updatedApplication.getDecision().equals(Decision.ACCEPT) && updatedApplication.getStatus().equals(Status.ACCEPT)) {
            updatedApplication = processor.confirm(updatedApplication, finalAmount);
            if (updatedApplication.getStatus().equals(Status.ISSUED)) {
                updatedApplication = applicationDataRepository.saveAndFlush(updatedApplication);
            }
            if (updatedApplication.getStatus().equals(Status.CUSTOMER_FAILURE)) {
                updatedApplication = applicationDataRepository.saveAndFlush(updatedApplication);
                creditInfoDataRepository.delete(creditInfoDataRepository.findCreditInfoByApplicationId(updatedApplication.getApplicationId()));
            }
        }
        return updatedApplication;
    }

    public void delete(Long id) {
        Optional<Application> optionalApplicationToDelete = applicationDataRepository.findById(id);
        if (optionalApplicationToDelete.isPresent()) {
            Application applicationToDelete = optionalApplicationToDelete.get();
            applicationDataRepository.delete(applicationToDelete);
        }
    }

    public Application findById(Long applicationId){
        Optional<Application> application = applicationDataRepository.findById(applicationId);
        return application.orElseThrow(()->  new NoSuchEntityException("No such Application"));
    }

    public Application findByIdAndUserId(Long applicationId, Long userId) {
        Optional<Application> application = applicationDataRepository.findById(applicationId);
        if (application.isPresent()){
            if (application.get().getUserId().equals(userId)){
                return  application.get();
            } else {
                throw new AuthenticationServiceException("Access is denied");
            }
        } else {
            throw new NoSuchEntityException("No such Application");
        }
    }
}
