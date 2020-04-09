package htp.services;


import htp.dao.spring_data.ProductDataRepository;
import htp.domain.model.Product;
import htp.exceptions.EntityAlreadyExists;
import htp.exceptions.NoSuchEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ProductService {

    private ProductDataRepository productDao;

    public ProductService(ProductDataRepository productDao) {
        this.productDao = productDao;
    }


    public Product findByProductCode(Integer productCode) throws NoSuchEntityException {
        Optional<Product> searchedProduct = productDao.findByProductCode(productCode);
        if (searchedProduct.isPresent()) {
            return searchedProduct.get();
        } else {
            throw new NoSuchEntityException("No such product");
        }
    }


    public Page<Product> findAll(int limit, int offset) {
        return productDao.findAll(PageRequest.of(offset, limit));
    }


    @Transactional(rollbackFor = Exception.class)
    public Product save(Product item) {
        try {
            Optional<Product> product= productDao.findByProductCode(item.getProductCode());
            if (product.isPresent()){
                throw new EntityAlreadyExists("Such entity already exists!");
            }
        } catch (NoSuchEntityException e){
            return productDao.save(item);
        }
        return productDao.save(item);
    }


    @Transactional(rollbackFor = Exception.class)
    public Product update(Product item) {
        Product productToUpdate = findByProductCode(item.getProductCode());
        if (!item.getProductCode().equals(productToUpdate.getProductCode())) {
                throw new EntityAlreadyExists("You try to update wrong product");
            }
        return productDao.saveAndFlush(item);
    }


    public void delete(Long id) {
        Optional<Product> productToDelete = productDao.findById(id);
        if (productToDelete.isPresent()){
            productDao.delete(productToDelete.get());
        } else {
            throw new NoSuchEntityException("No such Product");
        }
    }


    public Product findById(Long id) {
        Optional<Product> searchedProduct = productDao.findById(id);
        if (searchedProduct.isPresent()){
            return searchedProduct.get();
        } else {
            throw new NoSuchEntityException("No such Product");
        }
    }
}
