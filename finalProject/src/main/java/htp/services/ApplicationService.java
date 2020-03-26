package htp.services;

import htp.dao.ApplicationRepository;
import htp.dao.CreditInfoRepository;
import htp.dao.ProductRepository;
import htp.dao.hibernate_Impl.CreditInfoHibernateImpl;
import htp.dao.hibernate_Impl.ProductHibernateImpl;
import htp.dao.spring_impl.ApplicationRepSpringImpl;
import htp.entities.db_entities.Application;
import htp.processors.ApplicationProcessor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService implements ApplicationRepository {

    private ProductRepository productDao;
    private CreditInfoRepository creditInfoDao;
    private ApplicationRepository applicationDao;
//TODO: заменить реализацию репозитория для ApplicationRepository
    public ApplicationService(ProductHibernateImpl productDao, CreditInfoHibernateImpl creditInfoDao, ApplicationRepSpringImpl applicationDao) {
        this.productDao = productDao;
        this.creditInfoDao = creditInfoDao;
        this.applicationDao = applicationDao;
    }

    @Override
    public List<Application> findAll(int limit, int offset) {
        return null;
    }

    @Override
    public List<Application> findApplicationByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Application> findApplicationByUserId(Long userId, int limit, int offset) {
        return null;
    }
//TODO доделать!
    @Override
    public Application save(Application item) {
        Application application = new Application();
        ApplicationProcessor processor = new ApplicationProcessor(productDao, creditInfoDao);
        application = processor.start(item);
        return application;
    }

    @Override
    public Application update(Application item) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Application findById(Long id) {
        return null;
    }
}
