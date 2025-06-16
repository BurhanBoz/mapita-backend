
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS company;

CREATE TABLE company (
    company_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role_type VARCHAR(50) NOT NULL,
    company_id BIGINT,
    FOREIGN KEY (company_id) REFERENCES company(company_id)
);

CREATE TABLE product (
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
    product_count INT NOT NULL,
    product_weight DECIMAL(10,2) NOT NULL,
    company_id BIGINT,
    FOREIGN KEY (company_id) REFERENCES company(company_id)
);

-- Insert sample company
INSERT INTO company (company_name) VALUES ('Mapita Technologies');

-- Insert sample user (assuming company_id = 1)
INSERT INTO users (user_name, password, email, role_type, company_id)
VALUES ('john_doe', 'password123', 'john@example.com', 'ADMIN', 1);

-- Insert sample product (assuming company_id = 1)
INSERT INTO product (product_name, product_count, product_weight, company_id)
VALUES ('Sample Product', 10, 1.25, 1);