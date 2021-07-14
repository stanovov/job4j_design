CREATE TABLE departments(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE employees(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

INSERT INTO departments(name) VALUES ('Marketing'), ('Purchasing'), ('Production'), ('Accounting and Finance'), ('Human Resource Management');

INSERT INTO employees(name, department_id) VALUES ('Ivan', 1), ('Natalia', 1), ('Kate', 1);
INSERT INTO employees(name, department_id) VALUES ('Dmitry', 2), ('Konstantin', 2), ('Evgeniy', 2), ('Boris', 2);
INSERT INTO employees(name, department_id) VALUES ('Semyon', 3), ('Roman', 3);
INSERT INTO employees(name, department_id) VALUES ('Alina', 4);
INSERT INTO employees(name) VALUES ('Kirill');

-- left join
SELECT 
	e.id,
	e.name,
	d.name AS department
FROM 
	employees AS e
		LEFT JOIN departments AS d
		ON e.department_id = d.id;
		
-- right join
SELECT 
	e.id,
	e.name,
	d.name AS department
FROM 
	employees AS e
		RIGHT JOIN departments AS d
		ON e.department_id = d.id;
		
-- full join
SELECT 
	e.id,
	e.name,
	d.name AS department
FROM 
	employees AS e
		FULL JOIN departments AS d
		ON e.department_id = d.id;
		
-- cross join
SELECT 
	e.id,
	e.name,
	d.name AS department
FROM 
	employees AS e
		CROSS JOIN departments AS d;
		
-- Подразделения без сотрудников
SELECT 
	d.id,
	d.name
FROM 
	departments AS d
		LEFT JOIN employees AS e
		ON d.id = e.department_id
WHERE 
	e.id IS NULL;
	
-- Запросы с одинаковым результатом RIGHT и LEFT соединения
SELECT 
	e.id,
	e.name,
	d.name AS department
FROM 
	employees AS e
		LEFT JOIN departments AS d
		ON e.department_id = d.id;
		
SELECT 
	e.id,
	e.name,
	d.name AS department
FROM 
	departments AS d
		RIGHT JOIN employees AS e
		ON d.id = e.department_id;