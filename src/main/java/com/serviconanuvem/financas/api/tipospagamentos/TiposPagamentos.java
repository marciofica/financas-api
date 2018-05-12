package com.serviconanuvem.financas.api.tipospagamentos;

import com.serviconanuvem.financas.api.planodecontas.PlanoDeContas;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tipos_pagamentos", schema = "financas")
@Data @Builder
public class TiposPagamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id", referencedColumnName = "id")
    private PlanoDeContas planoDeContas;

    @NotNull
    @NotEmpty
    @Column(name = "descricao", length = 150, nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "ativo", length = 1, nullable = false)
    private Integer ativo;

    @Tolerate
    public TiposPagamentos() {
    }
}
