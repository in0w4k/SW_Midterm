CREATE TABLE t_orders (
    id BIGSERIAL PRIMARY KEY,
    created_at DATE,
    status VARCHAR(50),
    user_id BIGINT REFERENCES t_users(id)
);