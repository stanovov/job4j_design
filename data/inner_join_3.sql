select
	m.id,
	m.date_time,
	m.txt,
	s.id as sender_id,
	s.name as sender_name,
	s.phone_number as sender_phone
from messages as m
	join contacts as s on m.sender_id = s.id AND s.name = 'Semyon'