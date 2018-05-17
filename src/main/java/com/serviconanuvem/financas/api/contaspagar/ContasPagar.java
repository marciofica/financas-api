package com.serviconanuvem.financas.api.contaspagar;

import com.serviconanuvem.financas.api.eventosfinanceiros.EventosFinanceiros;
import com.serviconanuvem.financas.api.planodecontas.PlanoDeContas;
import com.serviconanuvem.financas.api.tipospagamentos.TiposPagamentos;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contas_pagar", schema = "financas")
@Data @Builder
public class ContasPagar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id", referencedColumnName = "id")
    private PlanoDeContas planoDeContas;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_financeiro_id", referencedColumnName = "id")
    private EventosFinanceiros eventoFinanceiro;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipos_pagamentos_id", referencedColumnName = "id")
    private TiposPagamentos tiposPagamentos;

    @NotNull
    @Column(name = "valor_parcela")
    private BigDecimal valorParcela;

    @NotNull
    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    private Integer parcela;

    @NotNull
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @NotNull
    @Column(name = "data_hora_lancamento")
    private LocalDateTime dataHoraLancamento;

    private String descricao;

    @Tolerate
    public ContasPagar() {
    }
}
