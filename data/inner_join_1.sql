select
	m.id,
	m.date_time,
	m.txt,
	s.name as sender_name,
	s.phone_number as sender_phone,
	r.name as recipient_name,
	r.phone_number as recipient_phone
from messages as m
	join contacts as s on m.sender_id = s.id
	join contacts as r on m.recipient_id = r.id
order by m.date_time desc
	