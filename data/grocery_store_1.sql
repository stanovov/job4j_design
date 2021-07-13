SELECT 
	*
FROM product AS p
	JOIN TYPE AS t ON p.type_id = t.id
WHERE 
	t.name = 'СЫР'