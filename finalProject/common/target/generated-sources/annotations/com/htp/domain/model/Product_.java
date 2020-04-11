package com.htp.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Double> interestRate;
	public static volatile SingularAttribute<Product, Long> minAmount;
	public static volatile SingularAttribute<Product, Integer> loanTerm;
	public static volatile SingularAttribute<Product, Integer> productCode;
	public static volatile SingularAttribute<Product, Long> productId;
	public static volatile SingularAttribute<Product, Double> coefficient;
	public static volatile SingularAttribute<Product, Long> maxAmount;
	public static volatile SingularAttribute<Product, String> productName;

	public static final String INTEREST_RATE = "interestRate";
	public static final String MIN_AMOUNT = "minAmount";
	public static final String LOAN_TERM = "loanTerm";
	public static final String PRODUCT_CODE = "productCode";
	public static final String PRODUCT_ID = "productId";
	public static final String COEFFICIENT = "coefficient";
	public static final String MAX_AMOUNT = "maxAmount";
	public static final String PRODUCT_NAME = "productName";

}

