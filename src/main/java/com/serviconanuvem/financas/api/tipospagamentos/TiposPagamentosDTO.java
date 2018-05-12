package com.serviconanuvem.financas.api.tipospagamentos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.serviconanuvem.financas.api.planodecontas.PlanoDeContasDTO;
import com.serviconanuvem.financas.core.representation.AbstractRepresentation;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @Builder
public class TiposPagamentosDTO {

    private Long id;

    @NotNull
    private PlanoDeContasDTO planoDeContas;

    @NotNull
    @NotEmpty
    private String descricao;

    @NotNull
    private Integer ativo;

    @Tolerate
    public TiposPagamentosDTO() {
    }

    @Component
    public static class Representation extends AbstractRepresentation<TiposPagamentos, TiposPagamentosDTO> {

        @Autowired
        TiposPagamentosRepository tiposPagamentosRepository;

        @Autowired
        PlanoDeContasDTO.Representation planoDeContaRepresentation;

        @Override
        public TiposPagamentos toEntity(TiposPagamentosDTO dto) {
            TiposPagamentos entity = TiposPagamentos.builder().build();

            if(dto.getId() != null){
                entity = tiposPagamentosRepository.findById(dto.getId()).get();
            }
            entity.setPlanoDeContas(planoDeContaRepresentation.toEntity(dto.getPlanoDeContas()));
            entity.setDescricao(dto.getDescricao());
            entity.setAtivo(dto.getAtivo());
            return entity;
        }

        @Override
        public TiposPagamentosDTO toDTO(TiposPagamentos entity) {
            return TiposPagamentosDTO.builder()
                    .id(entity.getId())
                    .planoDeContas(planoDeContaRepresentation.select2(entity.getPlanoDeContas()))
                    .descricao(entity.getDescricao())
                    .ativo(entity.getAtivo())
                    .build();
        }

        @Override
        public TiposPagamentosDTO toList(TiposPagamentos entity) {
            return TiposPagamentosDTO.builder()
                    .id(entity.getId())
                    .planoDeContas(planoDeContaRepresentation.select2(entity.getPlanoDeContas()))
                    .descricao(entity.getDescricao())
                    .ativo(entity.getAtivo())
                    .build();
        }

        @Override
        public TiposPagamentosDTO select2(TiposPagamentos entity) {
            return TiposPagamentosDTO.builder()
                    .id(entity.getId())
                    .descricao(entity.getDescricao())
                    .build();
        }
    }

}
