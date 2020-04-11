package com.htp.controller.converters;

import com.htp.controller.request.ProductFront;
import com.htp.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductFrontConverter extends GenericConverter<ProductFront, Product> {

    @Override
    public Product convert(ProductFront productFront) {
        Product product = new Product();
        product.setProductCode(productFront.getProductCode());
        product.setProductName(productFront.getProductName());
        product.setInterestRate(productFront.getInterestRate());
        product.setLoanTerm(productFront.getLoanTerm());
        product.setMinAmount(productFront.getMinAmount());
        product.setMaxAmount(productFront.getMaxAmount());
        product.setCoefficient(productFront.getCoefficient());
        return product;
    }
}
