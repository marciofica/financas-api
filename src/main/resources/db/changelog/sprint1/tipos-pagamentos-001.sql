--liquibase formatted sql

--changeset marcio:1
CREATE TABLE tipos_pagamentos(
	id BIGINT NOT NULL AUTO_INCREMENT,
	plano_id BIGINT NOT NULL,
	descricao VARCHAR(150) NOT NULL,
	ativo tinyint(1) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_tipospgto_plano FOREIGN KEY (plano_id) REFERENCES plano_contas(id),
	INDEX(ativo)
) ENGINE=INNODB DEFAULT CHARSET=utf8;