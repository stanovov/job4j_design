create table products(
	id serial primary key,
	name varchar(255)
);

create table types_of_prices(
	id serial primary key,
	name varchar(255)
);

create table product_prices(
	id serial primary key,
	product_id int references products(id),
	type_of_price_id int references types_of_prices(id),
	price decimal
);

insert into products(name) values ('Тетрадь');
insert into products(name) values ('Ручка');
insert into types_of_prices(name) values ('Розница');
insert into types_of_prices(name) values ('Опт');
insert into product_prices(product_id, type_of_price_id, price) values (1, 1, 111.11);
insert into product_prices(product_id, type_of_price_id, price) values (1, 2, 99.99);
insert into product_prices(product_id, type_of_price_id, price) values (2, 1, 42.42);
insert into product_prices(product_id, type_of_price_id, price) values (2, 2, 33.33);