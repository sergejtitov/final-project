package htp.services;

import htp.dao.ProductRepository;
import htp.dao.hibernate_Impl.ProductHibernateImpl;
import htp.entities.db_entities.Product;
import htp.exceptions.EntityAleadyExists;
import htp.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService implements ProductRepository {

    private ProductRepository productDao;

    public ProductService(ProductHibernateImpl productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product findByProductCode(Long productCode) {
        Product product;
        try {
            product = productDao.findByProductCode(productCode);
        } catch (NoSuchEntityException e){
            throw new NoSuchEntityException(e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> findAll(int limit, int offset) {
        return productDao.findAll(limit, offset);
    }

    @Override
    public Product save(Product item) {
        Product product;
        try {
            product = productDao.findByProductCode(item.getProductCode());
            if (product!=null){
                throw new EntityAleadyExists("Such entity already exists!");
            }
        } catch (NoSuchEntityException e){
            return productDao.save(item);
        }
        return productDao.save(item);
    }

    @Override
    public Product update(Product item) throws EntityAleadyExists {
        if (item.getProductCode().equals(findByProductCode(item.getProductCode()).getProductCode())){
            throw new EntityAleadyExists("Such product already exists");
        }
        return productDao.update(item);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(productDao.findById(id).getProductId());
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }
}
