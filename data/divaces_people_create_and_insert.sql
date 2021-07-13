create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values 
('Умный браслет Xiaomi Mi Band 4', 2079), ('Наушники Marshall Mode EQ, black & gold', 2750), 
('Ноутбук Acer Extensa 15 EX215-22-R0VC', 34810), ('Ноутбук Apple MacBook Pro 13 Mid 2020', 111290),
('Клавиатура Logitech Keyboard K120', 542), ('Мышь A4Tech X-710BK, черный', 990);

insert into people(name) values ('John'), ('Ivan'), ('Saul Goodman'), ('Kate');

insert into devices_people(device_id, people_id) values (2, 1), (5, 1), (6, 1);
insert into devices_people(device_id, people_id) values (3, 2), (6, 2);
insert into devices_people(device_id, people_id) values (4, 3);
insert into devices_people(device_id, people_id) values (1, 4), (2, 4), (5, 4), (6, 4);