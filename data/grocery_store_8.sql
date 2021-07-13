SELECT
	p.id,
	p.name,
	t.name AS type,
	p.expired_date,
	p.price
FROM product AS p
	JOIN type AS t ON p.type_id = t.id