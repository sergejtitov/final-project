package com.htp.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Phone.class)
public abstract class Phone_ {

	public static volatile SingularAttribute<Phone, String> phoneType;
	public static volatile SingularAttribute<Phone, String> phoneNumber;
	public static volatile SingularAttribute<Phone, Long> phoneId;
	public static volatile SingularAttribute<Phone, Applicant> applicant;

	public static final String PHONE_TYPE = "phoneType";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String PHONE_ID = "phoneId";
	public static final String APPLICANT = "applicant";

}

