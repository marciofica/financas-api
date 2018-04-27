--liquibase formatted sql

--changeset marcio:1
CREATE TABLE plano_contas(
	id BIGINT NOT NULL AUTO_INCREMENT,
	descricao varchar(10) not null,
  ativo tinyint(1) NOT NULL,
	criado_por varchar(20) DEFAULT NULL,
  dh_criacao datetime DEFAULT NULL,
  alterado_por varchar(20) DEFAULT NULL,
  dh_alteracao datetime DEFAULT NULL,
  version int(11) DEFAULT NULL,
	PRIMARY KEY (id),
	INDEX(ativo)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
