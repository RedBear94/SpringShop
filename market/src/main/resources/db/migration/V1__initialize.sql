create table users (
    id                    bigserial,
    username              varchar(30) not null,
    password              varchar(80) not null,
    email                 varchar(50) unique,
    primary key (id)
);

create table roles (
    id                    serial,
    name                  varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_roles (
    user_id               bigint not null,
    role_id               int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('SOMETHING');

insert into users (username, password, email)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'); -- pass 100

insert into users_roles (user_id, role_id) values (1, 1), (1, 2);

create table customers (
    id  bigserial,
    name    varchar(255) not null,
    primary key (id)
);

create table products (
    id  bigserial primary key,
    title   varchar(255),
    price   int,
    category_id bigint,
    foreign key (category_id) references products (id)
);

create table categories (
    id bigserial primary key,
    title varchar(255)
);

insert into categories (title)
values
('bakery products'),
('milk products'),
('vegetables'),
('juices'),
('meat products'),
('juices');

create table orders (
    id    bigserial primary key,
    user_id bigint references users(id),
    price int,
    address varchar(1000)
);

create table order_items (
    id                  bigserial primary key,
    product_id          bigint references products(id),
    order_id            bigint references orders(id),
    price               int,
    price_per_product   int,
    quantity            int
);

insert into products (title, price, category_id)
values
('Bread1', 21, 1),
('Bread2', 22, 1),
('Bread3', 23, 1),
('Bread4', 24, 1),
('Bread5', 25, 1),
('Bread6', 26, 1),
('Bread7', 27, 1),
('Bread8', 28, 1),
('Bread9', 29, 1),
('Bread10', 31, 1),
('Bread11', 32, 1),
('Bread12', 33, 1),
('Bread13', 34, 1),
('Bread14', 35, 1),
('Bread15', 36, 1),
('Bread16', 37, 1),
('Bread17', 38, 1),
('Bread18', 39, 1),
('Bread19', 40, 1),
('Milk1', 21, 2),
('Milk2', 22, 2),
('Milk3', 23, 2),
('Milk4', 24, 2),
('Milk5', 25, 2),
('Milk6', 26, 2),
('Milk7', 27, 2),
('Milk8', 28, 2),
('Milk9', 29, 2),
('Milk10', 31, 2),
('Milk11', 32, 2),
('Milk12', 33, 2),
('Milk13', 34, 2),
('Milk14', 35, 2),
('Milk15', 36, 2),
('Milk16', 37, 2),
('Milk17', 38, 2),
('Milk18', 39, 2),
('Milk19', 40, 2);