--liquibase formatted sql

--changeset marcio:1
CREATE TABLE plano_usuarios(
	id BIGINT NOT NULL AUTO_INCREMENT,
	usuario_id BIGINT NOT NULL,
	plano_id BIGINT NOT NULL,
	owner varchar(3) not null,
	PRIMARY KEY (id),
	CONSTRAINT fk_plano_user FOREIGN KEY (usuario_id) REFERENCES users(id),
	CONSTRAINT fk_plano_plano FOREIGN KEY (plano_id) REFERENCES plano_contas(id),
	INDEX(owner)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

--changeset marcio:2
ALTER TABLE plano_usuarios ADD  UNIQUE INDEX uq_user_plano (usuario_id, plano_id);