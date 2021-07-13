create table contacts(
	id serial primary key,
	name varchar(255),
	phone_number text
);

create table messages(
	id serial primary key,
	date_time TIMESTAMP, 
	txt text,
	sender_id int references contacts(id),
	recipient_id int references contacts(id)
);

insert into contacts(name, phone_number) values ('Semyon', '+79995554422');
insert into contacts(name, phone_number) values ('Ivan', '+8(922)222-11-44');

insert into messages(date_time, txt, sender_id, recipient_id) values ('2021-07-13 11:11:11', 'Hello', 1, 2);
insert into messages(date_time, txt, sender_id, recipient_id) values ('2021-07-13 12:12:12', 'Hi', 2, 1);
insert into messages(date_time, txt, sender_id, recipient_id) values ('2021-07-13 13:13:13', 'How are you?', 1, 2);
insert into messages(date_time, txt, sender_id, recipient_id) values ('2021-07-13 14:14:14', 'I''m fine, thanks', 2, 1);
insert into messages(date_time, txt, sender_id, recipient_id) values ('2021-07-13 15:15:15', 'Bye', 1, 2);