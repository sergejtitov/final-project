package com.htp.domain.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Timestamp> created;
	public static volatile SetAttribute<User, Roles> roles;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, Boolean> isdeleted;
	public static volatile SingularAttribute<User, Timestamp> changed;

	public static final String PASSWORD = "password";
	public static final String CREATED = "created";
	public static final String ROLES = "roles";
	public static final String LOGIN = "login";
	public static final String USER_ID = "userId";
	public static final String ISDELETED = "isdeleted";
	public static final String CHANGED = "changed";

}

