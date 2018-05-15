package com.serviconanuvem.financas.api.contasreceber;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.serviconanuvem.financas.api.eventosfinanceiros.EventosFinanceirosDTO;
import com.serviconanuvem.financas.api.eventosfinanceiros.EventosFinanceirosRepository;
import com.serviconanuvem.financas.api.planodecontas.PlanoDeContasDTO;
import com.serviconanuvem.financas.api.planodecontas.PlanoDeContasRepository;
import com.serviconanuvem.financas.core.representation.AbstractRepresentation;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @Builder
public class ContasReceberDTO {

    private Long id;

    @NotNull
    private PlanoDeContasDTO planoDeContas;

    @NotNull
    private EventosFinanceirosDTO eventoFinanceiro;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate dataCredito;

    private LocalDateTime dataHoraLancamento;

    private String descricao;

    @Tolerate
    public ContasReceberDTO() {
    }

    @Component
    public static class Representation extends AbstractRepresentation<ContasReceber, ContasReceberDTO> {

        @Autowired
        ContasReceberRepository contasReceberRepository;

        @Autowired
        PlanoDeContasDTO.Representation planoRepresentation;

        @Autowired
        PlanoDeContasRepository planoDeContasRepository;

        @Autowired
        EventosFinanceirosDTO.Representation eventosRepresentation;

        @Autowired
        EventosFinanceirosRepository eventosFinanceirosRepository;

        @Override
        public ContasReceber toEntity(ContasReceberDTO dto) {
            ContasReceber entity = ContasReceber.builder().build();
            if(dto.getId() != null) {
                entity = contasReceberRepository.findById(dto.getId()).get();
            }
            entity.setDataCredito(dto.getDataCredito());
            entity.setDescricao(dto.getDescricao());
            entity.setEventoFinanceiro(eventosFinanceirosRepository.findById(dto.getEventoFinanceiro().getId()).get());
            entity.setPlanoDeContas(planoDeContasRepository.findById(dto.getPlanoDeContas().getId()).get());
            entity.setValor(dto.getValor());
            entity.setDataHoraLancamento(dto.getDataHoraLancamento() == null ?LocalDateTime.now(ZoneId.of("America/Sao_Paulo")): dto.getDataHoraLancamento());
            return entity;
        }

        @Override
        public ContasReceberDTO toDTO(ContasReceber entity) {
            return ContasReceberDTO.builder()
                    .id(entity.getId())
                    .descricao(entity.getDescricao())
                    .dataCredito(entity.getDataCredito())
                    .dataHoraLancamento(entity.getDataHoraLancamento())
                    .valor(entity.getValor())
                    .eventoFinanceiro(eventosRepresentation.select2(entity.getEventoFinanceiro()))
                    .planoDeContas(planoRepresentation.select2(entity.getPlanoDeContas()))
                    .build();
        }

        @Override
        public ContasReceberDTO toList(ContasReceber entity) {
            return ContasReceberDTO.builder()
                    .id(entity.getId())
                    .descricao(entity.getDescricao())
                    .dataCredito(entity.getDataCredito())
                    .dataHoraLancamento(entity.getDataHoraLancamento())
                    .valor(entity.getValor())
                    .eventoFinanceiro(eventosRepresentation.select2(entity.getEventoFinanceiro()))
                    .planoDeContas(planoRepresentation.select2(entity.getPlanoDeContas()))
                    .build();
        }

        @Override
        public ContasReceberDTO select2(ContasReceber entity) {
            return ContasReceberDTO.builder()
                    .id(entity.getId())
                    .descricao(entity.getDescricao())
                    .build();
        }
    }
}
