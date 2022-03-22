--liquibase formatted sql
--changeset k_nizamiev:2022-03-17-initial
--comment Создание таблицы person


create table users (
	id bigint primary key,
	name varchar(30) not null,
	family varchar(30) not null,
	patronymic varchar(30) not null,
	birthday timestamp not null
);

create sequence users_seq;
