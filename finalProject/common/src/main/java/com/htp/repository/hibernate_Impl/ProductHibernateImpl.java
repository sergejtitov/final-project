package com.htp.repository.hibernate_Impl;

import com.htp.domain.model.Product;
import com.htp.repository.ProductRepository;

import com.htp.exceptions.NoSuchEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Slf4j
@Repository
public class ProductHibernateImpl implements ProductRepository {
    public static final Integer PAGE = 1;

    EntityManager entityManager;

    public ProductHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAll(int limit, int offset) {
        return entityManager.createQuery("select mp from Product mp", Product.class)
                .setFirstResult(limit*offset)
                .setMaxResults(limit*(offset+PAGE))
                .getResultList();
    }

    @Override
    public Product save(Product item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
        return entityManager.find(Product.class, item.getProductId());
    }

    @Override
    public Product update(Product item) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(item);
        transaction.commit();
        return entityManager.find(Product.class, item.getProductId());
    }

    @Override
    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(findById(id));
        transaction.commit();
    }

    @Override
    public Product findById(Long id) throws NoSuchEntityException {
        Product product = entityManager.find(Product.class, id);
        if (product == null){
            throw new NoSuchEntityException("No such Product");
        }
        return product;
    }
}
