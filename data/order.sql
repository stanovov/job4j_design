CREATE TABLE orders(
	id serial primary key,
	name varchar(255),
	delivery bool,
	create_date "timestamp",
	price money
);
INSERT INTO orders(
	name, delivery, create_date, price
) VALUES (
	'New order', true, CURRENT_TIMESTAMP, 111.55 
);
UPDATE orders SET price = 112.42;
DELETE FROM orders;