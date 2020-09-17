BEGIN;

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE IF NOT EXISTS customers (id bigserial, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO customers (name) VALUES ('Bob'), ('Mike'), ('Ann'), ('Lucy'), ('Alex');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), price int, PRIMARY KEY (id));
INSERT INTO products (title, price) VALUES ('milk', 10), ('cheese', 20),('bread', 30), ('tea', 40), ('meat', 50);

DROP TABLE IF EXISTS purchases CASCADE;
CREATE TABLE IF NOT EXISTS purchases (id bigserial, customer_id bigint, FOREIGN KEY (customer_id) REFERENCES customers(id), product_id bigint, FOREIGN KEY (product_id) REFERENCES products(id), money_spent int, PRIMARY KEY (id));
INSERT INTO purchases (customer_id, product_id, money_spent) VALUES (1, 3, 30), (2, 4, 40), (3, 5, 50), (4, 1, 100), (5, 2, 20), (1, 2, 20);

COMMIT;