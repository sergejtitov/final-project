package com.htp.repository.spring_data;


import com.htp.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductDataRepository extends CrudRepository<Product, Long>, JpaRepository<Product, Long> {
    Optional<Product> findByProductCode (Integer productCode);
}
