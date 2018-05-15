--liquibase formatted sql

--changeset marcio:1
CREATE TABLE contas_receber(
	id BIGINT NOT NULL AUTO_INCREMENT,
	plano_id BIGINT NOT NULL,
	evento_financeiro_id BIGINT NOT NULL,
	descricao varchar(255) not null,
	valor decimal(15,2) not null,
	data_credito date not null,
	data_hora_lancamento datetime not null,
  PRIMARY KEY (id),
  CONSTRAINT fk_contasreceber_plano FOREIGN KEY (plano_id) REFERENCES plano_contas(id),
  CONSTRAINT fk_contasreceber_eventos FOREIGN KEY (evento_financeiro_id) REFERENCES eventos_financeiros(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;