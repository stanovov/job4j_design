CREATE TABLE teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

INSERT INTO teens(name, gender) VALUES ('John', 'M'), ('Robert', 'M');
INSERT INTO teens(name, gender) VALUES ('Mary', 'W'), ('Kate', 'W');

SELECT
	t1.name,
	t1.gender,
	t2.name,
	t2.gender
FROM
	teens AS t1
		CROSS JOIN teens AS t2
WHERE 
	t1.id != t2.id