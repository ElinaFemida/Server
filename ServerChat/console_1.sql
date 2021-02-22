create table if not exists chatclients
(
	ID int auto_increment
		primary key,
	NAME text not null,
	PASSWORD text not null);
insert into chatclients (NAME, PASSWORD) values ('a', 'a');
insert into chatclients (NAME, PASSWORD) values ('s', 's');
insert into chatclients (NAME, PASSWORD) values ('d', 'd');






