drop table INVESTOR if exists;

create table INVESTOR
(
ID integer identity primary key,
NAME varchar(100) NOT NULL,
SURNAME varchar(100) NOT NULL,
DATE_OF_BIRTH date NOT NULL,
ADDRESS varchar(100) NOT NULL,
MOBILE_NUMBER varchar(10) NOT NULL,
EMAIL_ADDRESS varchar(100) DEFAULT NULL,
PRODUCT_ID integer DEFAULT NULL,
primary KEY (ID)
);