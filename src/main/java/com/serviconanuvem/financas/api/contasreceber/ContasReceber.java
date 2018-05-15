package com.serviconanuvem.financas.api.contasreceber;

import com.serviconanuvem.financas.api.eventosfinanceiros.EventosFinanceiros;
import com.serviconanuvem.financas.api.planodecontas.PlanoDeContas;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contas_receber", schema = "financas")
@Data @Builder
public class ContasReceber {

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
    private BigDecimal valor;

    @NotNull
    @Column(name = "data_credito")
    private LocalDate dataCredito;

    @NotNull
    @Column(name = "data_hora_lancamento")
    private LocalDateTime dataHoraLancamento;

    private String descricao;

    @Tolerate
    public ContasReceber() {
    }
}
