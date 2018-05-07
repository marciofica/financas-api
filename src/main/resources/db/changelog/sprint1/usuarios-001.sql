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

--changeset marcio:2
RENAME TABLE usuarios TO users;

--changeset marcio:3
CREATE TABLE authorities (
  id BIGINT auto_increment,
  usuario_id BIGINT,
  username VARCHAR(80) NOT NULL,
  authority VARCHAR(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user_authorities FOREIGN KEY (usuario_id) REFERENCES users(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;