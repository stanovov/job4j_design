insert into role(name) values ('regular user');
insert into role(name) values ('advanced user');
insert into role(name) values ('admin');

insert into users(name, role_id) values ('Semyon', 1);
insert into users(name, role_id) values ('Ivan', 2);
insert into users(name, role_id) values ('admin', 3);

insert into rules(name) values ('reading items');
insert into rules(name) values ('editing items');
insert into rules(name) values ('creating items');
insert into rules(name) values ('administration');

insert into role_rules(role_id, rule_id) values (1, 1);
insert into role_rules(role_id, rule_id) values (1, 2);
insert into role_rules(role_id, rule_id) values (2, 1);
insert into role_rules(role_id, rule_id) values (2, 2);
insert into role_rules(role_id, rule_id) values (2, 3);
insert into role_rules(role_id, rule_id) values (3, 4);

insert into category(name) values ('Важная');
insert into category(name) values ('Обычная');

insert into state(name) values ('Новая');
insert into state(name) values ('В работе');
insert into state(name) values ('Выполнена');
insert into state(name) values ('Не выполнена');

insert into item(name, user_id, category_id, state_id) values ('Необходимо пофиксить баг!', 1, 1, 1);
insert into item(name, user_id, category_id, state_id) values ('Переговоры с заказчиком', 2, 2, 2);

insert into comments(name, item_id) values ('система упала', 1);
insert into comments(name, item_id) values ('СРОЧНО!11', 1);
insert into comments(name, item_id) values ('В среду на 17:00', 2);

insert into attachs(name, item_id) values ('scrinshot_1.png', 1);
