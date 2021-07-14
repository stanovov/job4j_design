CREATE TABLE body(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE engine(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE transmission(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE cars(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

INSERT INTO body(name) VALUES ('Седан'), ('Хэтчбэк'), ('Универсал'), ('Лифтбек');
INSERT INTO engine(name) VALUES ('Бензин'), ('Дизель'), ('Гибрид'), ('Электро');
INSERT INTO transmission(name) VALUES ('Механическая'), ('Автоматическая'), ('Робот'), ('Вариатор');

INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Mazda 3', 1, 1, 1);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Mazda 3', 2, 1, 1);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Mazda 3', 2, 1, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Wolksvagen passat', 1, 1, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Wolksvagen passat', 3, 2, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Tesla Model 3', 1, 4, 2);

-- список всех машин
SELECT
	c.id,
	c.name,
	b.name AS body,
	e.name AS engine,
	t.name AS transmission
FROM
	cars AS c
		JOIN body AS b
			ON c.body_id = b.id
		JOIN engine AS e
			ON c.engine_id = e.id
		JOIN transmission AS t
			ON c.transmission_id = t.id;
			
-- не использованные кузова
SELECT
	b.name
FROM 
	body AS b
		LEFT JOIN cars AS c
			ON b.id = c.body_id
WHERE
	c.id IS NULL;
	
-- не использованные двигатели
SELECT
	e.name
FROM
	engine AS e
		LEFT JOIN cars AS c
			ON e.id = c.engine_id
WHERE
	c.id IS NULL;
	
-- не использованные коробки передач
SELECT
	t.name
FROM
	transmission AS t
		LEFT JOIN cars AS c
			ON t.id = c.transmission_id
WHERE
	c.id IS NULL;