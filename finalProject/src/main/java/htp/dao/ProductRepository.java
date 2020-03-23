package htp.dao;

import htp.entities.db_entities.Product;

import java.util.List;

public interface ProductRepository extends GenericDao<Product, Long> {
     Product findByProductCode(Integer productCode);

     List<Product> findAll(int limit, int offset);
}
