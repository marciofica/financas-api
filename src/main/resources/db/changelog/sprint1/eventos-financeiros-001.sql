--liquibase formatted sql

--changeset marcio:1
CREATE TABLE eventos_financeiros(
	id BIGINT NOT NULL AUTO_INCREMENT,
	plano_id BIGINT NOT NULL,
	descricao VARCHAR(150) NOT NULL,
	tipo varchar(7) not null,
	ativo tinyint(1) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_eventosfinanceiros_plano FOREIGN KEY (plano_id) REFERENCES plano_contas(id),
	INDEX(ativo),
	INDEX(tipo)
) ENGINE=INNODB DEFAULT CHARSET=utf8;