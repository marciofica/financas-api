package com.serviconanuvem.financas.core.entity;

import com.serviconanuvem.financas.filters.FinancasResolver;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DefaultEntityListener {
    @PrePersist
    public void setAuditCreate(DefaultEntity e){
        if(e.getVersion() == null){
            e.setCriadoPor(FinancasResolver.getUsuario());
            e.setDhCriacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        }
    }

    @PreUpdate
    public void setAuditUpdate(DefaultEntity e){
        e.setAlteradoPor(FinancasResolver.getUsuario());
        e.setDhAlteracao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

    }
}
