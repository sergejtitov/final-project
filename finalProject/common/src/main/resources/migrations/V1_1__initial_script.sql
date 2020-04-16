create table m_credit_info
(
	info_id bigserial not null
		constraint m_credit_info_pkey
			primary key,
	loan_amount double precision not null,
	interest_rate double precision not null,
	balance_amount double precision,
	balance_term integer,
	payment double precision,
	personal_number varchar(20) not null,
	application_id bigint not null
);

alter table m_credit_info owner to "User_FP";

create table m_user
(
	user_id bigserial not null
		constraint m_user_pkey
			primary key,
	login varchar(100) not null,
	password varchar(100) not null,
	created timestamp(6) with time zone not null,
	changed timestamp(6) with time zone not null,
	isdeleted boolean not null
);

alter table m_user owner to "User_FP";

create unique index m_user_login_uindex
	on m_user (login);

create table m_application
(
	user_id bigint not null
		constraint m_application_m_user__fk
			references m_user,
	creation_date timestamp(6) with time zone not null,
	loan_type integer not null,
	product_code integer not null,
	loan_amount double precision not null,
	final_amount double precision,
	decision varchar(10),
	status varchar,
	payment double precision,
	application_id bigserial not null
		constraint m_application_pkey
			primary key
);

alter table m_application owner to "User_FP";

create index m_application_creation_date_index
	on m_application (creation_date);

create index m_application_decision_index
	on m_application (decision);

create index m_application_loan_type_index
	on m_application (loan_type);

create index m_application_status_index
	on m_application (status);

create index m_application_product_code_index
	on m_application (product_code);

create table m_product
(
	product_id bigserial not null
		constraint m_product_pkey
			primary key,
	product_code integer not null,
	product_name varchar(200) not null,
	interest_rate double precision not null,
	loan_term integer not null,
	min_amount bigint not null,
	max_amount bigint not null,
	coefficient double precision
);

alter table m_product owner to "User_FP";

create index m_product_product_code_index
	on m_product (product_code);

create table m_applicant
(
	applicant_id bigserial not null
		constraint m_applicant_pkey
			primary key,
	first_name varchar(100) not null,
	second_name varchar(100) not null,
	patronymic varchar(100),
	type_of_applicant integer not null,
	date_of_birthday timestamp(6) with time zone not null,
	income double precision not null,
	sex varchar(1) not null,
	experience integer,
	marital_status integer,
	education integer,
	children_quantity integer,
	personal_number varchar(20) not null,
	application_id bigint not null
		constraint m_applicant_m_user__fk
			references m_application
);

alter table m_applicant owner to "User_FP";

create index m_applicant_first_name_index
	on m_applicant (first_name);

create index m_applicant_second_name_index
	on m_applicant (second_name);

create index m_applicant_personal_number_index
	on m_applicant (personal_number);

create table m_phone
(
	phone_id bigserial not null
		constraint m_phone_pkey
			primary key,
	applicant_id bigint not null
		constraint m_phone_m_applicant__fk
			references m_applicant
				on update cascade on delete cascade
				deferrable,
	phone_type varchar(100),
	phone_number varchar(100)
);

alter table m_phone owner to "User_FP";

create table m_roles
(
	role_id bigserial not null
		constraint m_roles_pkey
			primary key,
	name varchar(100) not null,
	user_id bigint not null
		constraint m_roles_m_user_user_id_fk
			references m_user
				on update cascade on delete cascade
				deferrable
);

alter table m_roles owner to "User_FP";

create table m_address
(
	address_id bigserial not null
		constraint m_address_pkey
			primary key,
	address_type varchar(100),
	address_description varchar(200) not null,
	applicant_id bigint not null
		constraint m_address_m_applicant_applicant_id_fk
			references m_applicant
				on update cascade on delete cascade
				deferrable
);

alter table m_address owner to "User_FP";

