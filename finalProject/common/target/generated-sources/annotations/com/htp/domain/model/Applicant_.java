package com.htp.domain.model;

import com.htp.domain.dictionaries.Gender;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Applicant.class)
public abstract class Applicant_ {

	public static volatile SingularAttribute<Applicant, Double> income;
	public static volatile SingularAttribute<Applicant, Integer> childrenQuantity;
	public static volatile SetAttribute<Applicant, Address> addresses;
	public static volatile SingularAttribute<Applicant, Integer> education;
	public static volatile SingularAttribute<Applicant, Gender> sex;
	public static volatile SetAttribute<Applicant, Phone> phones;
	public static volatile SingularAttribute<Applicant, String> personalNumber;
	public static volatile SingularAttribute<Applicant, Integer> experience;
	public static volatile SingularAttribute<Applicant, Integer> typeOfApplicant;
	public static volatile SingularAttribute<Applicant, String> firstName;
	public static volatile SingularAttribute<Applicant, String> patronymic;
	public static volatile SingularAttribute<Applicant, Application> application;
	public static volatile SingularAttribute<Applicant, Timestamp> birthdayDate;
	public static volatile SingularAttribute<Applicant, Long> applicantId;
	public static volatile SingularAttribute<Applicant, Integer> maritalStatus;
	public static volatile SingularAttribute<Applicant, String> secondName;

	public static final String INCOME = "income";
	public static final String CHILDREN_QUANTITY = "childrenQuantity";
	public static final String ADDRESSES = "addresses";
	public static final String EDUCATION = "education";
	public static final String SEX = "sex";
	public static final String PHONES = "phones";
	public static final String PERSONAL_NUMBER = "personalNumber";
	public static final String EXPERIENCE = "experience";
	public static final String TYPE_OF_APPLICANT = "typeOfApplicant";
	public static final String FIRST_NAME = "firstName";
	public static final String PATRONYMIC = "patronymic";
	public static final String APPLICATION = "application";
	public static final String BIRTHDAY_DATE = "birthdayDate";
	public static final String APPLICANT_ID = "applicantId";
	public static final String MARITAL_STATUS = "maritalStatus";
	public static final String SECOND_NAME = "secondName";

}

