package com.htp.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CreditInfo.class)
public abstract class CreditInfo_ {

	public static volatile SingularAttribute<CreditInfo, Double> interestRate;
	public static volatile SingularAttribute<CreditInfo, Long> infoId;
	public static volatile SingularAttribute<CreditInfo, Double> payment;
	public static volatile SingularAttribute<CreditInfo, String> personalNumber;
	public static volatile SingularAttribute<CreditInfo, Double> balanceAmount;
	public static volatile SingularAttribute<CreditInfo, Long> applicationId;
	public static volatile SingularAttribute<CreditInfo, Integer> balanceTerm;
	public static volatile SingularAttribute<CreditInfo, Double> loanAmount;

	public static final String INTEREST_RATE = "interestRate";
	public static final String INFO_ID = "infoId";
	public static final String PAYMENT = "payment";
	public static final String PERSONAL_NUMBER = "personalNumber";
	public static final String BALANCE_AMOUNT = "balanceAmount";
	public static final String APPLICATION_ID = "applicationId";
	public static final String BALANCE_TERM = "balanceTerm";
	public static final String LOAN_AMOUNT = "loanAmount";

}

