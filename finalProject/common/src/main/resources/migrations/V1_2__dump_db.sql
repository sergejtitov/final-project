INSERT INTO m_user (user_id, login, password, created, changed, isdeleted) VALUES (1, 'superuser', '12345678', '2020-03-18 22:39:53.864000', '2020-03-18 22:39:35.413000', false);
INSERT INTO m_user (user_id, login, password, created, changed, isdeleted) VALUES (8, 'mufasa@lion.afr', '$2a$10$lLProAHpEGfHJcGIOVtzQeAUFN4qQKjRRhO2zGHSE/qs65gWdYHx.', '2020-03-19 11:39:18.299000', '2020-03-19 11:39:18.299000', false);
INSERT INTO m_user (user_id, login, password, created, changed, isdeleted) VALUES (11, 'new_admin', '$2a$10$QtodXrfLTeFD8mXpNrGKKOArtNqca7iDk64Eo8UJSB76Xb9KO1Yfu', '2020-03-19 12:00:08.653000', '2020-03-19 12:00:08.653000', false);
INSERT INTO m_user (user_id, login, password, created, changed, isdeleted) VALUES (12, 'olduser', '$2a$10$3pwovuS9l76ydGXRrxgZIOyRVZqZUm/h3YXKFydrSuxazPtm16FWK', '2020-03-19 13:00:04.936000', '2020-03-19 13:41:37.474000', false);
INSERT INTO m_user (user_id, login, password, created, changed, isdeleted) VALUES (14, 'John@snow.we', '$2a$10$HjF9jAb/2m8tHTPpUZnr/e5b2NrDv2/PzalPKaaCx2zWPHkepaa8S', '2020-04-01 20:00:56.746000', '2020-04-01 20:00:56.746000', false);
INSERT INTO m_user (user_id, login, password, created, changed, isdeleted) VALUES (15, 'tirion@lanister.wes', '$2a$10$2uZ6l7pa/5TDWOGIwsvRr.zkkktg2S85ik58Ox5DCj9.oFwYagElK', '2020-04-05 20:42:45.572000', '2020-04-05 20:42:45.572000', false);
INSERT INTO m_user (user_id, login, password, created, changed, isdeleted) VALUES (16, 'winnie@puh.com', '$2a$10$N8bJiPLPMCtnXC1EY9oqje640x6D.iA7DtWcZHrjnLioGwvrX9wFi', '2020-04-08 11:56:33.829000', '2020-04-08 11:56:33.829000', false);
INSERT INTO m_user (user_id, login, password, created, changed, isdeleted) VALUES (17, 'optimus@prime.com', '$2a$10$o8QE9nbQrJdNs6oD811uHeatqqVHfBtgyU91t7XoblcoxYVxeSgjS', '2020-04-09 10:57:54.282000', '2020-04-09 10:57:54.282000', false);
INSERT INTO m_user (user_id, login, password, created, changed, isdeleted) VALUES (19, 'string', 'string', '2020-04-10 07:07:39.532000', '2020-04-10 07:13:51.261000', false);
INSERT INTO m_user (user_id, login, password, created, changed, isdeleted) VALUES (21, 'admin@user.by', '$2a$10$OVaKyT0A76SmuQix8fy6pepdgioK8jo4zuJv6VJF7o25obau6.0W.', '2020-04-10 07:39:20.708000', '2020-04-10 07:39:20.708000', false);
INSERT INTO m_roles (role_id, name, user_id) VALUES (3, 'ROLE_ADMIN', 1);
INSERT INTO m_roles (role_id, name, user_id) VALUES (6, 'ROLE_USER', 8);
INSERT INTO m_roles (role_id, name, user_id) VALUES (9, 'ROLE_USER', 11);
INSERT INTO m_roles (role_id, name, user_id) VALUES (10, 'ROLE_ADMIN', 11);
INSERT INTO m_roles (role_id, name, user_id) VALUES (18, 'ROLE_USER', 12);
INSERT INTO m_roles (role_id, name, user_id) VALUES (19, 'ROLE_ADMIN', 12);
INSERT INTO m_roles (role_id, name, user_id) VALUES (22, 'ROLE_USER', 14);
INSERT INTO m_roles (role_id, name, user_id) VALUES (23, 'ROLE_USER', 15);
INSERT INTO m_roles (role_id, name, user_id) VALUES (24, 'ROLE_USER', 16);
INSERT INTO m_roles (role_id, name, user_id) VALUES (25, 'ROLE_USER', 17);
INSERT INTO m_roles (role_id, name, user_id) VALUES (29, 'ROLE_ADMIN', 19);
INSERT INTO m_roles (role_id, name, user_id) VALUES (30, 'ROLE_ADMIN', 21);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (2, 1002, 'Mortgage - 15 years', 12, 180, 10000, 999999, 0.7);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (1, 1001, 'Mortgage - 10 years', 12, 120, 10000, 999999, 0.7);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (11, 4002, 'Credit card with grace', 15, 12, 100, 100000, 0.4);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (10, 4001, 'Credit card with cash-back', 15, 12, 100, 100000, 0.4);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (6, 2003, 'Auto - 10 years', 12.5, 120, 5000, 500000, 0.7);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (5, 2002, 'Auto - 7 years', 12.5, 84, 5000, 500000, 0.7);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (4, 2001, 'Auto - 5 years', 12.5, 60, 5000, 500000, 0.7);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (12, 4003, 'Credit card with cash-back and grace', 15.5, 12, 100, 100000, 0.4);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (8, 3002, 'Consumer Loan - 5 years', 13.5, 60, 100, 100000, 0.5);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (9, 3003, 'Consumer Loan - 7 years', 13, 84, 100, 100000, 0.5);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (7, 3001, 'Consumer Loan - 1 year', 14, 12, 100, 100000, 0.5);
INSERT INTO m_product (product_id, product_code, product_name, interest_rate, loan_term, min_amount, max_amount, coefficient) VALUES (3, 1003, 'Mortgage - 20 years', 12, 240, 10000, 999999, 0.7);
INSERT INTO m_application (user_id, creation_date, loan_type, product_code, loan_amount, final_amount, decision, status, payment, application_id) VALUES (1, '2020-03-30 17:24:26.702000', 1, 1001, 20000, 20000, 'ACCEPT', 'ACCEPT', 366.67, 6);
INSERT INTO m_application (user_id, creation_date, loan_type, product_code, loan_amount, final_amount, decision, status, payment, application_id) VALUES (1, '2020-03-30 17:26:20.076000', 1, 1001, 20000, 0, 'DECLINE', 'FAILURE', 0, 7);
INSERT INTO m_application (user_id, creation_date, loan_type, product_code, loan_amount, final_amount, decision, status, payment, application_id) VALUES (16, '2020-04-08 19:32:40.815000', 3, 3001, 3000, 3000, 'ACCEPT', 'ISSUED', 285, 12);
INSERT INTO m_application (user_id, creation_date, loan_type, product_code, loan_amount, final_amount, decision, status, payment, application_id) VALUES (17, '2020-04-09 11:02:52.422000', 1, 1002, 100000, 0, 'DECLINE', 'FAILURE', 0, 13);
INSERT INTO m_application (user_id, creation_date, loan_type, product_code, loan_amount, final_amount, decision, status, payment, application_id) VALUES (17, '2020-04-09 11:05:20.410000', 1, 1002, 100000, 0, 'ACCEPT', 'CUSTOMER_FAILURE', 0, 14);
INSERT INTO m_application (user_id, creation_date, loan_type, product_code, loan_amount, final_amount, decision, status, payment, application_id) VALUES (17, '2020-04-09 11:06:41.366000', 1, 1002, 100000, 45000, 'ACCEPT', 'ISSUED', 700, 15);
INSERT INTO m_applicant (applicant_id, first_name, second_name, patronymic, type_of_applicant, date_of_birthday, income, sex, experience, marital_status, education, children_quantity, personal_number, application_id) VALUES (6, 'Harry', 'Potter', '', 1, '1984-02-01 03:52:35.566000', 2000, 'M', 2, 1, 5, 0, '354050343H455KJ4', 6);
INSERT INTO m_applicant (applicant_id, first_name, second_name, patronymic, type_of_applicant, date_of_birthday, income, sex, experience, marital_status, education, children_quantity, personal_number, application_id) VALUES (7, 'Harry', 'Potter', '', 1, '1984-02-01 03:52:35.566000', 400, 'M', 2, 1, 5, 0, '354050343H455KJ4', 7);
INSERT INTO m_applicant (applicant_id, first_name, second_name, patronymic, type_of_applicant, date_of_birthday, income, sex, experience, marital_status, education, children_quantity, personal_number, application_id) VALUES (9, 'winnie', 'pooh', '', 1, '1970-01-08 09:39:36.086000', 1000, 'M', 4, 1, 5, 0, '5456577G5656GF4', 12);
INSERT INTO m_applicant (applicant_id, first_name, second_name, patronymic, type_of_applicant, date_of_birthday, income, sex, experience, marital_status, education, children_quantity, personal_number, application_id) VALUES (10, 'Optimus', 'Prime', '', 1, '1970-01-15 08:14:34.778000', 100, 'M', 2, 1, 6, 10, '45345F454fs44', 13);
INSERT INTO m_applicant (applicant_id, first_name, second_name, patronymic, type_of_applicant, date_of_birthday, income, sex, experience, marital_status, education, children_quantity, personal_number, application_id) VALUES (11, 'Optimus', 'Prime', '', 1, '1970-01-15 08:14:34.778000', 3000, 'M', 5, 1, 3, 1, '45345F454fs44', 14);
INSERT INTO m_applicant (applicant_id, first_name, second_name, patronymic, type_of_applicant, date_of_birthday, income, sex, experience, marital_status, education, children_quantity, personal_number, application_id) VALUES (12, 'Optimus', 'Prime', '', 1, '1970-01-15 08:14:34.778000', 1000, 'M', 5, 1, 3, 1, '45345F454fs44', 15);
INSERT INTO m_credit_info (info_id, loan_amount, interest_rate, balance_amount, balance_term, payment, personal_number, application_id) VALUES (4, 20000, 12, 20000, 120, 366.67, '354050343H455KJ4', 6);
INSERT INTO m_credit_info (info_id, loan_amount, interest_rate, balance_amount, balance_term, payment, personal_number, application_id) VALUES (5, 3000, 14, 3000, 12, 285, '5456577G5656GF4', 12);
INSERT INTO m_credit_info (info_id, loan_amount, interest_rate, balance_amount, balance_term, payment, personal_number, application_id) VALUES (7, 45000, 12, 45000, 180, 700, '45345F454fs44', 15);
INSERT INTO m_address (address_id, address_type, address_description, applicant_id) VALUES (4, 'Work', 'Hogwats', 6);
INSERT INTO m_address (address_id, address_type, address_description, applicant_id) VALUES (5, 'Work', 'Hogwats', 7);
INSERT INTO m_address (address_id, address_type, address_description, applicant_id) VALUES (7, 'home', 'whole', 9);
INSERT INTO m_address (address_id, address_type, address_description, applicant_id) VALUES (8, 'work', 'LA, Holiwood', 10);
INSERT INTO m_address (address_id, address_type, address_description, applicant_id) VALUES (9, 'work', 'LA, Holiwood', 11);
INSERT INTO m_address (address_id, address_type, address_description, applicant_id) VALUES (10, 'work', 'LA, Holiwood', 12);
INSERT INTO m_phone (phone_id, applicant_id, phone_type, phone_number) VALUES (6, 6, 'Mobile', '+2357438539');
INSERT INTO m_phone (phone_id, applicant_id, phone_type, phone_number) VALUES (7, 6, 'Dursl''s home', '+2456456439');
INSERT INTO m_phone (phone_id, applicant_id, phone_type, phone_number) VALUES (8, 7, 'Mobile', '+2357438539');
INSERT INTO m_phone (phone_id, applicant_id, phone_type, phone_number) VALUES (9, 7, 'Dursl''s home', '+2456456439');
INSERT INTO m_phone (phone_id, applicant_id, phone_type, phone_number) VALUES (11, 9, 'mobile', '+5654645654456');
INSERT INTO m_phone (phone_id, applicant_id, phone_type, phone_number) VALUES (12, 10, 'home', '+5353535');
INSERT INTO m_phone (phone_id, applicant_id, phone_type, phone_number) VALUES (13, 10, 'work', '+5675396539345');
INSERT INTO m_phone (phone_id, applicant_id, phone_type, phone_number) VALUES (14, 11, 'home', '+5353535');
INSERT INTO m_phone (phone_id, applicant_id, phone_type, phone_number) VALUES (15, 11, 'work', '+5675396539345');
INSERT INTO m_phone (phone_id, applicant_id, phone_type, phone_number) VALUES (16, 12, 'home', '+5353535');
INSERT INTO m_phone (phone_id, applicant_id, phone_type, phone_number) VALUES (17, 12, 'work', '+5675396539345');
