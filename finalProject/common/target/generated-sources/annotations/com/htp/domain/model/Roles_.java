package com.htp.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Roles.class)
public abstract class Roles_ {

	public static volatile SingularAttribute<Roles, Long> roleId;
	public static volatile SingularAttribute<Roles, String> name;
	public static volatile SingularAttribute<Roles, User> user;

	public static final String ROLE_ID = "roleId";
	public static final String NAME = "name";
	public static final String USER = "user";

}

