package htp.dao;

import htp.domain.model.Product;

import java.util.List;

public interface ProductRepository extends GenericDao<Product, Long> {
     List<Product> findAll(int limit, int offset);
}
