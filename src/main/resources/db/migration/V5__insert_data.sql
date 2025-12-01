
INSERT INTO t_users (name, email, password, role) VALUES
                                                      ('ilya', 'ilya@test.test', '$2a$10$/nmuuUGqeE5QLxIHnSWMt.Q5pagr93DknFgDNcqSAnOlJGSjBZAEW', 'ADMIN'),
                                                      ('nurken', 'nurken@test2.test2', '$2a$10$ZXbCkXCwgNOHO.fgUYx5decnQjG5TePFOmdlYRR6utzzjsqT2RJBu', 'CUSTOMER'),
                                                      ('rafi', 'rafi@test3.test3', '$2a$10$frLqq3C5ThzLnAwA0HIe8e6tBLKhouD4/ugzFgd8IZgn/V/E35dIK', 'CUSTOMER');

INSERT INTO t_orders (created_at, status, user_id) VALUES
                                                       ('2025-11-20', 'SHIPPED', 1),
                                                       ('2018-8-12', 'CANCELLED', 3),
                                                       ('2021-03-29', 'PAID', 2);

INSERT INTO t_products (name, description, category, price, stock) VALUES
                                                                       ('laptop', 'desc', 'electr', 120.00, 50),
                                                                       ('apple-watch', 'desc', 'smart', 80.00, 72),
                                                                       ('sofa', 'desc', 'furn', 20.00, 100),
                                                                       ('eggs', 'desc', 'food', 1000.50, 20);

INSERT INTO t_orders_products (order_id, product_id) VALUES
                                                         (2, 4),
                                                         (3, 1),
                                                         (1, 2);
