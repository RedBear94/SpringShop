set search_path to sc;
create table products (
    id      bigserial primary key,
    title   varchar(255),
    price   int
);

create table orders (
    id      bigserial primary key,
    price   int,
    items   bigint array
);

insert into orders 	(price, items)
values
('60', '{ 1, 2, 3}'),
('70', '{ 3, 4 }'),
('40', '{ 3, 4, 5}');

insert into products (title, price)
values
('Bread1', 10),
('Bread2', 20),
('Milk1', 30),
('Milk2', 40),
('Bread1', 50);
