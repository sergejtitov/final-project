package htp.services;

import htp.dao.ApplicationRepository;
import htp.dao.CreditInfoRepository;
import htp.dao.ProductRepository;
import htp.dao.hibernate_Impl.ApplicationHibernateImpl;
import htp.dao.hibernate_Impl.CreditInfoHibernateImpl;
import htp.dao.hibernate_Impl.ProductHibernateImpl;
import htp.dao.spring_data.ApplicationDataRepository;
import htp.domain.dictionaries.Decision;
import htp.domain.dictionaries.Status;
import htp.domain.model.Application;
import htp.domain.model.CreditInfo;
import htp.domain.model.Product;
import htp.processors.ApplicationProcessor;
import htp.utils.CustomValidation;
import htp.utils.Parsers;
import jdk.jshell.Snippet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationService {

    private ProductRepository productDao;
    private CreditInfoRepository creditInfoDao;
    private ApplicationRepository applicationDao;
    ApplicationProcessor processor;

    public ApplicationService(ProductHibernateImpl productDao, CreditInfoHibernateImpl creditInfoDao, ApplicationHibernateImpl applicationDao) {
        this.productDao = productDao;
        this.creditInfoDao = creditInfoDao;
        this.applicationDao = applicationDao;
        processor = new ApplicationProcessor(productDao, creditInfoDao);
    }


    public List<Application> findAll(int limit, int offset) {
        return null;
    }


    public List<Application> findApplicationByUserId(Long userId) {
        return null;
    }


    public List<Application> findApplicationByUserId(Long userId, int limit, int offset) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public Application save(Application item) {
        CustomValidation.validate(item);
        Application application = processor.start(item);
        Application savedApplication = applicationDao.save(application);
        if (application.getDecision().equals(Decision.ACCEPT)){
            Product product = productDao.findByProductCode(application.getProductCode());
            CreditInfo creditInfo = Parsers.createCreditInfo(application, product);
            creditInfoDao.save(creditInfo);
        }
       return savedApplication;
    }

    public Application update(Long applicationId, Double finalAmount) {
        Application updatedApplication = applicationDao.findById(applicationId);
        if (updatedApplication.getDecision().equals(Decision.ACCEPT) && updatedApplication.getStatus().equals(Status.ACCEPT)) {
            updatedApplication = processor.confirm(updatedApplication, finalAmount);
            if (updatedApplication.getStatus().equals(Status.ISSUED)){
                updatedApplication = applicationDao.update(updatedApplication);
            }
            if (updatedApplication.getStatus().equals(Status.CUSTOMER_FAILURE)){
                updatedApplication = applicationDao.update(updatedApplication);
                creditInfoDao.delete(creditInfoDao.findCreditInfoByApplicationId(updatedApplication.getApplicationId()).getInfoId());
            }
        }
        return updatedApplication;
    }

    public void delete(Long id) {
        applicationDao.delete(applicationDao.findById(id).getApplicationId());
    }

    public Application findById(Long id) {
        return applicationDao.findById(id);
    }
}
