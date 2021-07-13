create table brand(
	id serial primary key,
	name varchar(255)
);

create table car_models(
	id serial primary key,
	name varchar(255),
	brand_id int references brand(id)
);

insert into brand(name) values ('audi');
insert into car_models(name, brand_id) values ('a6', 1);
insert into car_models(name, brand_id) values ('a5', 1);
insert into car_models(name, brand_id) values ('q5', 1);