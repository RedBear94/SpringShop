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
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');

insert into users_roles (user_id, role_id) values (1, 1), (1, 2);

create table customers (
    id  bigserial,
    name    varchar(255) not null,
    primary key (id)
);

create table products (
    id  bigserial primary key,
    title   varchar(255),
    price   int
);

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

insert into products (title, price)
values
('Bread1', 21),
('Bread2', 22),
('Bread3', 23),
('Bread4', 24),
('Bread5', 25),
('Bread6', 26),
('Bread7', 27),
('Bread8', 28),
('Bread9', 29),
('Bread10', 31),
('Bread11', 32),
('Bread12', 33),
('Bread13', 34),
('Bread14', 35),
('Bread15', 36),
('Bread16', 37),
('Bread17', 38),
('Bread18', 39),
('Bread19', 40),
('Milk1', 21),
('Milk2', 22),
('Milk3', 23),
('Milk4', 24),
('Milk5', 25),
('Milk6', 26),
('Milk7', 27),
('Milk8', 28),
('Milk9', 29),
('Milk10', 31),
('Milk11', 32),
('Milk12', 33),
('Milk13', 34),
('Milk14', 35),
('Milk15', 36),
('Milk16', 37),
('Milk17', 38),
('Milk18', 39),
('Milk19', 40);