CREATE TABLE t_products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    category VARCHAR(255),
    price DOUBLE PRECISION,
    stock INT
);