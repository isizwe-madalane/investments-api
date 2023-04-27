DROP DATABASE IF EXISTS "investorsDB";
CREATE DATABASE "investorsDB";

DROP TABLE investor IF EXISTS;
DROP TABLE product IF EXISTS;
DROP TABLE withdrawals IF EXISTS;

CREATE TABLE investor (
    id INTEGER IDENTITY PRIMARY KEY,
    investor_id INTEGER,
    investor_name VARCHAR(50),
    investor_surname VARCHAR(50),
    investor_date_of_birth VARCHAR(10),
    investor_address VARCHAR(100),
    investor_mobile_number VARCHAR(10),
    investor_email_address VARCHAR(50),
    UNIQUE(investor_id)
);

CREATE TABLE product (
    id INTEGER IDENTITY PRIMARY KEY,
    product_id INTEGER,
    product_type VARCHAR(20),
    product_name VARCHAR(100),
    product_current_balance DECIMAL(19,4),
    UNIQUE(product_id),
    FOREIGN KEY (investor_id) REFERENCES investor (investor_id)
);

CREATE TABLE withdrawals (
    id INTEGER IDENTITY PRIMARY KEY,
    withdrawal_id INTEGER,
    withdrawal_status VARCHAR(20),
    UNIQUE(withdrawal_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

ALTER TABLE product ADD CONSTRAINT fk_products FOREIGN KEY (investor_id) references investor(investor_id) ON DELETE CASCADE;
ALTER TABLE withdrawals ADD CONSTRAINT fk_withdrawals FOREIGN KEY (product_id) references product(product_id) ON DELETE CASCADE;