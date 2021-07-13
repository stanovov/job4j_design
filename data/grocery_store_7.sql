SELECT
	t.name
FROM product AS p
	JOIN type AS t ON p.type_id = t.id
GROUP BY t.name
HAVING SUM(1) < 10