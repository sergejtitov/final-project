package htp.dao.hibernate_Impl;

import htp.dao.RolesRepository;
import htp.entities.db_entities.Roles;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Repository
@Qualifier("hibernateRoleDao")
public class RolesHibernateImpl implements RolesRepository {

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
}
