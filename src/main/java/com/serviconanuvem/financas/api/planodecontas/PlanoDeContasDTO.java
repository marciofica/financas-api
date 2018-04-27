package com.serviconanuvem.financas.api.planodecontas;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.serviconanuvem.financas.core.representation.AbstractRepresentation;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @Builder
public class PlanoDeContasDTO {

    private Long id;
    private String descricao;
    private Integer ativo;
    private Integer version;
    private String usuarioCriacao;
    private String usuarioAlteracao;
    private LocalDateTime dhCriacao;
    private LocalDateTime dhAlteracao;

    @Tolerate
    public PlanoDeContasDTO() {
    }

    @Component
    public static class Representation extends AbstractRepresentation<PlanoDeContas, PlanoDeContasDTO> {

        @Autowired
        PlanoDeContasRepository planoDeContasRepository;

        @Override
        public PlanoDeContas toEntity(PlanoDeContasDTO dto) {
            PlanoDeContas entity = PlanoDeContas.builder()
                    .id(dto.getId())
                    .descricao(dto.getDescricao())
                    .ativo(dto.getAtivo())
                    .build();
            if(dto.getId() != null){
                PlanoDeContas entityOld = planoDeContasRepository.findById(dto.getId()).get();
                entity.setVersion(entityOld.getVersion());
            }
            return entity;
        }

        @Override
        public PlanoDeContasDTO toDTO(PlanoDeContas entity) {
            return PlanoDeContasDTO.builder()
                    .id(entity.getId())
                    .descricao(entity.getDescricao())
                    .ativo(entity.getAtivo())
                    .version(entity.getVersion())
                    .usuarioCriacao(entity.getCriadoPor())
                    .usuarioAlteracao(entity.getAlteradoPor())
                    .dhCriacao(entity.getDhCriacao())
                    .dhAlteracao(entity.getDhAlteracao())
                    .build();
        }

        @Override
        public PlanoDeContasDTO toList(PlanoDeContas entity) {
            return toDTO(entity);
        }

        @Override
        public PlanoDeContasDTO select2(PlanoDeContas entity) {
            return PlanoDeContasDTO.builder()
                    .id(entity.getId())
                    .descricao(entity.getDescricao())
                    .build();
        }
    }
}
