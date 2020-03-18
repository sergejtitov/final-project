package htp.dao.hibernate_Impl;

import htp.dao.UserRepository;
import htp.entities.db_entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Qualifier("hibernateUserDao")
public class UserHibernateImpl implements UserRepository {
    public static final int NEXT_PAGE = 1;
    public static final String LOGIN = "login";
    public static final int ZERO = 0;

    EntityManager entityManager;

    public UserHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        return entityManager.createQuery("select mu from User mu")
                .setFirstResult(limit*offset)
                .setMaxResults(limit*(offset+NEXT_PAGE))
                .getResultList();
    }

    @Override
    public void fakeDelete(Long id) {
        User updatedUser = entityManager.find(User.class, id);
        updatedUser.setIsdeleted(true);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(updatedUser);
        transaction.commit();
    }

    @Override
    public User findByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery("select mu from User mu where mu.login = :login", User.class);
        query.setParameter(LOGIN, login);
        List<User> user = query.getResultList();
        if (user.size()==0){
            return new User();
        }
        return user.get(ZERO);
    }

    @Override
    public User save(User item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
        return entityManager.find(User.class, item.getUserId());
    }

    @Override
    public User update(User item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(item);
        transaction.commit();
        return entityManager.find(User.class, item.getUserId());
    }

    @Override
    public void delete(Long id) {
    entityManager.remove(findById(id));
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
}
