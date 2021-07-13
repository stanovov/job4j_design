create table drivers_licenses(
	id serial primary key,
	seria int,
	number int
);

create table drivers(
	id serial primary key,
	name varchar(255),
	drivers_license_id int references drivers_licenses(id) unique
);

insert into drivers_licenses(seria, number) values (6999, 101292);
insert into drivers(name, drivers_license_id) values ('Semyon', 1);