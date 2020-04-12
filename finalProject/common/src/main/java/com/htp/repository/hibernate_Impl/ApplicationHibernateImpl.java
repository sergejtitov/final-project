package com.htp.repository.hibernate_Impl;


import com.htp.domain.model.Application;
import com.htp.repository.ApplicationRepository;

import com.htp.exceptions.NoSuchEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Slf4j
@Repository
public class ApplicationHibernateImpl implements ApplicationRepository {
    public static final String USER_ID = "userId";
    public static final int NEXT_PAGE = 1;
    EntityManager entityManager;

    public ApplicationHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Application> findAll(int limit, int offset) {
        return entityManager.createQuery("select man from Application man")
                .setFirstResult(limit*offset)
                .setMaxResults(limit*(offset+NEXT_PAGE))
                .getResultList();
    }

    @Override
    public List<Application> findApplicationByUserId(Long userId) {
        return entityManager.createQuery("select man from Application man where man.userId = :userId")
                .setParameter(USER_ID, userId)
                .getResultList();
    }

    @Override
    public List<Application> findApplicationByUserId(Long userId, int limit, int offset) {
        return entityManager.createQuery("select man from Application man where man.userId = :userId")
                .setParameter(USER_ID, userId)
                .setFirstResult(limit*offset)
                .setMaxResults(limit*(offset+NEXT_PAGE))
                .getResultList();
    }

    @Override
    public Application save(Application item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
        return entityManager.find(Application.class, item.getApplicationId());
    }

    @Override
    public Application update(Application item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(item);
        transaction.commit();
        return entityManager.find(Application.class, item.getApplicationId());
    }

    @Override
    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(findById(id));
        transaction.commit();
    }

    @Override
    public Application findById(Long id) {
        Application application = entityManager.find(Application.class, id);
        if (application == null){
            throw new NoSuchEntityException("No such Loan in Bureau");
        }
        return application;
    }
}
