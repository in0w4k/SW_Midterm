CREATE TABLE t_orders_products (
    order_id BIGINT REFERENCES t_orders(id) ON DELETE CASCADE,
    product_id BIGINT REFERENCES t_products(id) ON DELETE CASCADE
);