
create table if not exists mc_email(id bigint NOT null auto_increment, email varchar(80) not null, user_name varchar(100), is_primary boolean, primary key(id));

create table if not exists mc_phone(id bigint NOT null auto_increment, phone_number varchar(80) not null, user_name varchar(100), is_primary boolean, primary key(id));

insert into mc_email(id, email, user_name, is_primary) values(1, 'antony1@gmail.com', 'Antony 1', true);
insert into mc_email(id, email, user_name, is_primary) values(2, 'antony2@gmail.com', 'Antony 2', true);
insert into mc_email(id, email, user_name, is_primary) values(3, 'antony3@gmail.com', 'Antony 3', true);


insert into mc_phone(id, phone_number, user_name, is_primary) values(1, '948-856-9862', 'Antony 1', true);
insert into mc_phone(id, phone_number, user_name, is_primary)  values(2, '658-965-7891', 'Antony 2', true);
insert into mc_phone(id, phone_number, user_name, is_primary)  values(3, '657-986-6003', 'Antony 3', true);

commit;
