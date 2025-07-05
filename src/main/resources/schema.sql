
DROP TABLE IF EXISTS orders;
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
    product_name VARCHAR(255) NOT NULL
);

CREATE TABLE ORDERS (
    order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_count INT NOT NULL,
    order_weight DECIMAL(10,2),
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,
    product_id BIGINT,
    company_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id) ON DELETE SET NULL,
    FOREIGN KEY (company_id) REFERENCES COMPANY(company_id),
    FOREIGN KEY (user_id) REFERENCES USERS(user_id)
);

-- Insert sample company
INSERT INTO company (company_name) VALUES ('Mapita Technologies');

-- Insert sample user (assuming company_id = 1)
INSERT INTO users (user_name, password, email, role_type, company_id)
VALUES ('john_doe', '$2a$10$G9GjT/UBJPM2L3jTy7ydlO7uegwzhjWulsQcrLwhYT4OGDUu2AW2y', 'john@example.com', 'ADMIN', 1);

-- Insert sample product (assuming company_id = 1)
INSERT INTO product (product_name)
VALUES ('Baton Döner');
INSERT INTO product (product_name)
VALUES ('Kanat');
INSERT INTO product (product_name)
VALUES ('İncik');

INSERT INTO ORDERS (order_count, order_weight, start_date, end_date, product_id, company_id, user_id)
VALUES (10, 5.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 1);