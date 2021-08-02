CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company(id, name) VALUES (1, 'Mac Donald''s');
INSERT INTO company(id, name) VALUES (2, 'Donuts');
INSERT INTO company(id, name) VALUES (3, 'Lenta');
INSERT INTO company(id, name) VALUES (4, 'Tinkoff');
INSERT INTO company(id, name) VALUES (5, 'Sber');
INSERT INTO company(id, name) VALUES (6, 'Yandex');

INSERT INTO person(id, name, company_id) VALUES (1, 'Semyon', 1);
INSERT INTO person(id, name, company_id) VALUES (2, 'Dmitry', 1);
INSERT INTO person(id, name, company_id) VALUES (3, 'Ivan', 2);
INSERT INTO person(id, name, company_id) VALUES (4, 'Ekaterina', 3);
INSERT INTO person(id, name, company_id) VALUES (5, 'Natalia', 3);
INSERT INTO person(id, name, company_id) VALUES (6, 'Konstantin', 3);
INSERT INTO person(id, name, company_id) VALUES (7, 'Kirill', 4);
INSERT INTO person(id, name, company_id) VALUES (8, 'Boris', 5);
INSERT INTO person(id, name, company_id) VALUES (9, 'Viktor', 5);
INSERT INTO person(id, name, company_id) VALUES (10, 'Viktoria', 6);
INSERT INTO person(id, name) VALUES (11, 'Stas');

-- 1
SELECT
	p.name AS name,
	CASE 
		WHEN c.name IS NULL 
			THEN 'no company'
		ELSE c.name 
	END AS company
FROM
	person AS p
		LEFT JOIN company c
		ON p.company_id = c.id
WHERE
	(p.company_id <> 5 
	 	OR p.company_id IS NULL);
	
-- 2
SELECT
	c.name AS company,
	COUNT(p.id) AS number_of_people
FROM
	company AS c
		INNER JOIN person AS p
		ON c.id = p.company_id
GROUP BY
	c.name
ORDER BY
	COUNT(p.id) DESC
LIMIT 1;
		