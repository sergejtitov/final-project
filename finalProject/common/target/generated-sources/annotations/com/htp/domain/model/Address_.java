package com.htp.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> addressDescription;
	public static volatile SingularAttribute<Address, String> addressType;
	public static volatile SingularAttribute<Address, Long> addressId;
	public static volatile SingularAttribute<Address, Applicant> applicant;

	public static final String ADDRESS_DESCRIPTION = "addressDescription";
	public static final String ADDRESS_TYPE = "addressType";
	public static final String ADDRESS_ID = "addressId";
	public static final String APPLICANT = "applicant";

}

