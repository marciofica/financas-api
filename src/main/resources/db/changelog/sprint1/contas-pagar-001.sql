--liquibase formatted sql

--changeset marcio:1
CREATE TABLE contas_pagar(
	id BIGINT NOT NULL AUTO_INCREMENT,
	plano_id BIGINT NOT NULL,
	evento_financeiro_id BIGINT NOT NULL,
	tipos_pagamentos_id BIGINT NOT NULL,
  valor_parcela decimal(15,2) not null,
  valor_total decimal(15,2) not null,
  parcela tinyint(1) default null,
  data_vencimento date not null,
  data_pagamento date default null,
	descricao varchar(255) not null,
	data_hora_lancamento datetime not null,
  PRIMARY KEY (id),
  CONSTRAINT fk_contaspagar_plano FOREIGN KEY (plano_id) REFERENCES plano_contas(id),
  CONSTRAINT fk_contaspagar_eventos FOREIGN KEY (evento_financeiro_id) REFERENCES eventos_financeiros(id),
  CONSTRAINT fk_contaspagar_tipospgtos FOREIGN KEY (tipos_pagamentos_id) REFERENCES tipos_pagamentos(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;