-- Create the "mydatabase" database
CREATE DATABASE mydatabase;

-- Connect to the "mydatabase" database
\c mydatabase;

-- Create the "investor" table
CREATE TABLE investor (
   id SERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   surname VARCHAR(255) NOT NULL,
   date_of_birth VARCHAR(255) NOT NULL,
   address VARCHAR(255) NOT NULL,
   mobile_number VARCHAR(255) NOT NULL,
   email_address VARCHAR(255) NOT NULL
);

-- Create the "products" table
CREATE TABLE products (
   id SERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   amount DECIMAL(10, 2) NOT NULL
);


-- Insert data into the "investor" table
INSERT INTO investor (name, surname, date_of_birth, address, mobile_number, email_address)
VALUES
   ('John', 'Doe', '','', '0812345678', 'john.doe@example.com');

-- Insert data into the "product" table
INSERT INTO product (name, price)
VALUES
   ('RETIREMENT', 500000.00),
   ('SAVINGS', 36000.00);

