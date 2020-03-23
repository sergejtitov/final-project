package htp.dao.hibernate_Impl;

import htp.dao.ProductRepository;
import htp.entities.db_entities.Product;
import htp.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


@Repository
public class ProductHibernateImpl implements ProductRepository {
    public static final Integer PAGE = 1;
    public static final String PRODUCT_CODE = "productCode";
    public static final Integer FIRST_ITEM = 0;

    EntityManager entityManager;

    public ProductHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAll(int limit, int offset) {
        return entityManager.createQuery("select mp from Product mp")
                .setFirstResult(limit*offset)
                .setMaxResults(limit*(offset+PAGE))
                .getResultList();
    }

    @Override
    public Product findByProductCode(Integer productCode) throws NoSuchEntityException {
        List<Product> products = (List<Product>) entityManager.createQuery("select mp from Product mp " +
                "where mp.productCode = :productCode")
                .setParameter(PRODUCT_CODE, productCode)
                .getResultList();
        if (products.size() == 0){
            throw new NoSuchEntityException("No such Entity");
        }
        return products.get(FIRST_ITEM);
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
