package com.htp.domain.model;

import com.htp.domain.dictionaries.Decision;
import com.htp.domain.dictionaries.Status;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Application.class)
public abstract class Application_ {

	public static volatile SingularAttribute<Application, Integer> productCode;
	public static volatile SingularAttribute<Application, Integer> loanType;
	public static volatile SingularAttribute<Application, Decision> decision;
	public static volatile SingularAttribute<Application, Double> finalAmount;
	public static volatile SetAttribute<Application, Applicant> applicants;
	public static volatile SingularAttribute<Application, Double> payment;
	public static volatile SingularAttribute<Application, Long> applicationId;
	public static volatile SingularAttribute<Application, Timestamp> creationDate;
	public static volatile SingularAttribute<Application, Long> userId;
	public static volatile SingularAttribute<Application, Double> loanAmount;
	public static volatile SingularAttribute<Application, Status> status;

	public static final String PRODUCT_CODE = "productCode";
	public static final String LOAN_TYPE = "loanType";
	public static final String DECISION = "decision";
	public static final String FINAL_AMOUNT = "finalAmount";
	public static final String APPLICANTS = "applicants";
	public static final String PAYMENT = "payment";
	public static final String APPLICATION_ID = "applicationId";
	public static final String CREATION_DATE = "creationDate";
	public static final String USER_ID = "userId";
	public static final String LOAN_AMOUNT = "loanAmount";
	public static final String STATUS = "status";

}

