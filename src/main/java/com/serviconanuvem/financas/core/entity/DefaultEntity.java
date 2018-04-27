package com.serviconanuvem.financas.core.entity;

import lombok.Data;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(DefaultEntityListener.class)
@Data
public class DefaultEntity {

    private String criadoPor;
    private String alteradoPor;
    private LocalDateTime dhCriacao;
    private LocalDateTime dhAlteracao;

    @Version
    private Integer version;

}
