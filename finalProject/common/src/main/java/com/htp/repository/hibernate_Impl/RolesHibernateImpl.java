package com.htp.repository.hibernate_Impl;

import com.htp.domain.model.Roles;
import com.htp.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Repository
@Qualifier("hibernateRoleDao")
public class RolesHibernateImpl implements RolesRepository {
    public static final String USER_ID = "user_id";

    private EntityManager entityManager;

    public RolesHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Roles save(Roles item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
        return entityManager.find(Roles.class, item.getRoleId());
    }

    @Override
    public Roles update(Roles item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(item);
        transaction.commit();
        return entityManager.find(Roles.class, item.getRoleId());
    }

    @Override
    public void delete(Long id) {
    entityManager.remove(findById(id));
    }

    @Override
    public Roles findById(Long id) {
        return entityManager.find(Roles.class, id);
    }

    @Override
    public List<Roles> findRolesByUserId(Long id) {
        Query nativeQuery = entityManager.createNativeQuery("select * from m_roles  where user_id = :user_id", Roles.class);
        nativeQuery.setParameter(USER_ID, id);
        return nativeQuery.getResultList();
    }
}
