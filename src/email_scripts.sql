create table mc_email(id bigint NOT null auto_increment, email varchar(80) not null, user_name varchar(100), is_primary boolean, primary key(id));
create table mc_phone(id bigint NOT null auto_increment, phone_number varchar(80) not null, user_name varchar(100), is_primary boolean, primary key(id));

insert into mc_email values(1, 'antony1@gmail.com', 'Antony 1', true);
insert into mc_email values(2, 'antony2@gmail.com', 'Antony 2', true);
insert into mc_email values(3, 'antony3@gmail.com', 'Antony 3', true);


insert into mc_phone values(1, '948-856-9862', 'Antony 1', true);
insert into mc_phone values(2, '658-965-7891', 'Antony 2', true);
insert into mc_phone values(3, '657-986-6003', 'Antony 3', true);