SELECT
	*
FROM product AS p
	JOIN type AS t ON p.type_id = t.id
WHERE 
	t.name = 'СЫР' OR t.name = 'МОЛОКО'