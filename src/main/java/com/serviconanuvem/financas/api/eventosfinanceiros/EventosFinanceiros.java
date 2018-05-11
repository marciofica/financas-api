package com.serviconanuvem.financas.api.eventosfinanceiros;

import com.serviconanuvem.financas.api.enums.EnumEventoFinanceiro;
import com.serviconanuvem.financas.api.planodecontas.PlanoDeContas;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "eventos_financeiros", schema = "financas")
@Data @Builder
public class EventosFinanceiros {
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
    @Enumerated(EnumType.STRING)
    @Column(length = 7)
    private EnumEventoFinanceiro tipo;

    @NotNull
    @Column(name = "ativo", length = 1, nullable = false)
    private Integer ativo;

    @Tolerate
    public EventosFinanceiros() {
    }
}
