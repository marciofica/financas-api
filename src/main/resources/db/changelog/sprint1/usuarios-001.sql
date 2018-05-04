--liquibase formatted sql

--changeset marcio:1
CREATE TABLE usuarios (
    id BIGINT auto_increment,
    nome varchar(80) not null,
    email varchar(255) not null unique,
    username VARCHAR(80) UNIQUE,
    password VARCHAR(255),
    enabled BOOL not null,
    PRIMARY KEY (id),
	  INDEX(email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;