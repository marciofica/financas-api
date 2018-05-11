package com.serviconanuvem.financas.api.eventosfinanceiros;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.serviconanuvem.financas.api.enums.EnumEventoFinanceiro;
import com.serviconanuvem.financas.api.planodecontas.PlanoDeContasDTO;
import com.serviconanuvem.financas.core.representation.AbstractRepresentation;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @Builder
public class EventosFinanceirosDTO {

    private Long id;

    @NotNull
    private PlanoDeContasDTO planoDeContas;

    @NotNull
    private String descricao;

    @NotNull
    private EnumEventoFinanceiro tipo;

    @NotNull
    private Integer ativo;

    @Tolerate
    public EventosFinanceirosDTO() {
    }

    @Component
    public static class Representation extends AbstractRepresentation<EventosFinanceiros, EventosFinanceirosDTO> {

        @Autowired
        EventosFinanceirosRepository eventosFinanceirosRepository;

        @Autowired
        PlanoDeContasDTO.Representation planodeContasRepresentation;

        @Override
        public EventosFinanceiros toEntity(EventosFinanceirosDTO dto) {
            EventosFinanceiros entity = EventosFinanceiros.builder().build();
            if(dto.getId() != null){
                entity = eventosFinanceirosRepository.findById(dto.getId()).get();
            }
            entity.setAtivo(dto.getAtivo());
            entity.setDescricao(dto.getDescricao());
            entity.setPlanoDeContas(planodeContasRepresentation.toEntity(dto.getPlanoDeContas()));
            entity.setTipo(dto.getTipo());
            return entity;
        }

        @Override
        public EventosFinanceirosDTO toDTO(EventosFinanceiros entity) {
            return EventosFinanceirosDTO.builder()
                    .id(entity.getId())
                    .ativo(entity.getAtivo())
                    .descricao(entity.getDescricao())
                    .tipo(entity.getTipo())
                    .planoDeContas(planodeContasRepresentation.select2(entity.getPlanoDeContas()))
                    .build();
        }

        @Override
        public EventosFinanceirosDTO toList(EventosFinanceiros entity) {
            return EventosFinanceirosDTO.builder()
                    .id(entity.getId())
                    .ativo(entity.getAtivo())
                    .descricao(entity.getDescricao())
                    .tipo(entity.getTipo())
                    .planoDeContas(planodeContasRepresentation.select2(entity.getPlanoDeContas()))
                    .build();
        }

        @Override
        public EventosFinanceirosDTO select2(EventosFinanceiros entity) {
            return EventosFinanceirosDTO.builder()
                    .id(entity.getId())
                    .descricao(entity.getDescricao())
                    .build();
        }
    }
}
