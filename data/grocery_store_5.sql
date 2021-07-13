SELECT
	t.name AS name,
	SUM(1) AS count
FROM product AS p
	JOIN type AS t ON p.type_id = t.id
GROUP BY t.name