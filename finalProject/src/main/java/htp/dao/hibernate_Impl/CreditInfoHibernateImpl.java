package htp.dao.hibernate_Impl;

import htp.dao.CreditInfoRepository;
import htp.domain.model.CreditInfo;
import htp.exceptions.NoSuchEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Slf4j
@Repository
public class CreditInfoHibernateImpl implements CreditInfoRepository {
    public static final String PERSONAL_NUMBER = "personalNumber";

    EntityManager entityManager;

    public CreditInfoHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CreditInfo> findCreditInfosByPersonalNumber(String personalNumber) {
       return entityManager.createQuery("select mci from CreditInfo mci " +
                "where mci.personalNumber = :personalNumber")
                .setParameter(PERSONAL_NUMBER, personalNumber)
                .getResultList();
    }

    @Override
    public CreditInfo findCreditInfoByApplicationId(Long applicationId) {
        return null;
    }

    @Override
    public CreditInfo save(CreditInfo item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
        return entityManager.find(CreditInfo.class, item.getInfoId());
    }

    @Override
    public CreditInfo update(CreditInfo item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(item);
        transaction.commit();
        return entityManager.find(CreditInfo.class, item.getInfoId());
    }

    @Override
    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(findById(id));
        transaction.commit();
    }

    @Override
    public CreditInfo findById(Long id) {
        CreditInfo creditInfo = entityManager.find(CreditInfo.class, id);
        if (creditInfo == null){
            throw new NoSuchEntityException("No such Loan in Bureau");
        }
        return creditInfo;
    }
}
