package com.serviconanuvem.financas.api.contaspagar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.serviconanuvem.financas.api.eventosfinanceiros.EventosFinanceirosDTO;
import com.serviconanuvem.financas.api.eventosfinanceiros.EventosFinanceirosRepository;
import com.serviconanuvem.financas.api.planodecontas.PlanoDeContasDTO;
import com.serviconanuvem.financas.api.planodecontas.PlanoDeContasRepository;
import com.serviconanuvem.financas.api.tipospagamentos.TiposPagamentosDTO;
import com.serviconanuvem.financas.api.tipospagamentos.TiposPagamentosRepository;
import com.serviconanuvem.financas.core.representation.AbstractRepresentation;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @Builder
public class ContasPagarDTO {
    private Long id;
    private PlanoDeContasDTO planoDeContas;
    private EventosFinanceirosDTO eventoFinanceiro;
    private TiposPagamentosDTO tiposPagamentos;
    private BigDecimal valorParcela;
    private BigDecimal valorTotal;
    private Integer parcela;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private LocalDateTime dataHoraLancamento;
    private String descricao;

    @Tolerate
    public ContasPagarDTO() {
    }

    @Component
    public static class Representation extends AbstractRepresentation<ContasPagar, ContasPagarDTO> {

        @Autowired
        ContasPagarRepository contasPagarRepository;

        @Autowired
        PlanoDeContasDTO.Representation planoRepresentation;

        @Autowired
        PlanoDeContasRepository planoDeContasRepository;

        @Autowired
        EventosFinanceirosDTO.Representation eventosRepresentation;

        @Autowired
        EventosFinanceirosRepository eventosFinanceirosRepository;

        @Autowired
        TiposPagamentosDTO.Representation tiposPagamentosRepresentation;

        @Autowired
        TiposPagamentosRepository tiposPagamentosRepository;


        @Override
        public ContasPagar toEntity(ContasPagarDTO dto) {
            ContasPagar entity = ContasPagar.builder().build();
            if(dto.getId() != null) {
                entity = contasPagarRepository.findById(dto.getId()).get();
            }
            entity.setEventoFinanceiro(eventosFinanceirosRepository.findById(dto.getEventoFinanceiro().getId()).get());
            entity.setPlanoDeContas(planoDeContasRepository.findById(dto.getPlanoDeContas().getId()).get());
            entity.setTiposPagamentos(tiposPagamentosRepository.findById(dto.getTiposPagamentos().getId()).get());
            entity.setDataHoraLancamento(dto.getDataHoraLancamento() == null ?LocalDateTime.now(ZoneId.of("America/Sao_Paulo")): dto.getDataHoraLancamento());
            entity.setDataPagamento(dto.getDataPagamento());
            entity.setDataVencimento(dto.getDataVencimento());
            entity.setDescricao(dto.getDescricao());
            entity.setParcela(dto.getParcela());
            entity.setValorParcela(dto.getValorParcela());
            entity.setValorTotal(dto.getValorTotal());
            return entity;
        }

        @Override
        public ContasPagarDTO toDTO(ContasPagar entity) {
            return ContasPagarDTO.builder()
                    .id(entity.getId())
                    .dataHoraLancamento(entity.getDataHoraLancamento())
                    .dataPagamento(entity.getDataPagamento())
                    .dataVencimento(entity.getDataVencimento())
                    .descricao(entity.getDescricao())
                    .parcela(entity.getParcela())
                    .valorParcela(entity.getValorParcela())
                    .valorTotal(entity.getValorTotal())
                    .eventoFinanceiro(eventosRepresentation.select2(entity.getEventoFinanceiro()))
                    .planoDeContas(planoRepresentation.select2(entity.getPlanoDeContas()))
                    .tiposPagamentos(tiposPagamentosRepresentation.select2(entity.getTiposPagamentos()))
                    .build();
        }

        @Override
        public ContasPagarDTO toList(ContasPagar entity) {
            return ContasPagarDTO.builder()
                    .id(entity.getId())
                    .dataHoraLancamento(entity.getDataHoraLancamento())
                    .dataPagamento(entity.getDataPagamento())
                    .dataVencimento(entity.getDataVencimento())
                    .descricao(entity.getDescricao())
                    .parcela(entity.getParcela())
                    .valorParcela(entity.getValorParcela())
                    .valorTotal(entity.getValorTotal())
                    .eventoFinanceiro(eventosRepresentation.select2(entity.getEventoFinanceiro()))
                    .planoDeContas(planoRepresentation.select2(entity.getPlanoDeContas()))
                    .tiposPagamentos(tiposPagamentosRepresentation.select2(entity.getTiposPagamentos()))
                    .build();
        }

        @Override
        public ContasPagarDTO select2(ContasPagar entity) {
            return ContasPagarDTO.builder()
                    .id(entity.getId())
                    .descricao(entity.getDescricao())
                    .build();
        }
    }

}