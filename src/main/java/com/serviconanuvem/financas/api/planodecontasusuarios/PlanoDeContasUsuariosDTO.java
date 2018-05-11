package com.serviconanuvem.financas.api.planodecontasusuarios;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.serviconanuvem.financas.api.enums.SimNaoEnum;
import com.serviconanuvem.financas.api.planodecontas.PlanoDeContasDTO;
import com.serviconanuvem.financas.api.usuarios.UsuariosDTO;
import com.serviconanuvem.financas.core.representation.AbstractRepresentation;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @Builder
public class PlanoDeContasUsuariosDTO {

    private Long id;

    @NotNull
    private UsuariosDTO usuario;

    @NotNull
    private PlanoDeContasDTO planoDeContas;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SimNaoEnum owner;

    @Tolerate
    public PlanoDeContasUsuariosDTO() {
    }

    @Component
    public static class Representation extends AbstractRepresentation<PlanoDeContasUsuarios, PlanoDeContasUsuariosDTO> {

        @Autowired
        PlanoDeContasUsuariosRepository planoDeContasUsuariosRepository;

        @Autowired
        UsuariosDTO.Representation usuariosRepresentation;

        @Autowired
        PlanoDeContasDTO.Representation planosRepresentation;

        @Override
        public PlanoDeContasUsuarios toEntity(PlanoDeContasUsuariosDTO dto) {
            PlanoDeContasUsuarios entity = PlanoDeContasUsuarios.builder().build();
            if(dto.getId() != null){
                entity = planoDeContasUsuariosRepository.findById(dto.getId()).get();
            }
            entity.setUsuario(usuariosRepresentation.toEntity(dto.getUsuario()));
            entity.setPlanoDeContas(planosRepresentation.toEntity(dto.getPlanoDeContas()));
            entity.setOwner(dto.getOwner());
            return entity;
        }

        @Override
        public PlanoDeContasUsuariosDTO toDTO(PlanoDeContasUsuarios entity) {
            return PlanoDeContasUsuariosDTO.builder()
                    .id(entity.getId())
                    .usuario(usuariosRepresentation.toRelacionamento(entity.getUsuario()))
                    .planoDeContas(planosRepresentation.select2(entity.getPlanoDeContas()))
                    .owner(entity.getOwner())
                    .build();
        }

        @Override
        public PlanoDeContasUsuariosDTO toList(PlanoDeContasUsuarios entity) {
            return PlanoDeContasUsuariosDTO.builder()
                    .id(entity.getId())
                    .usuario(usuariosRepresentation.toRelacionamento(entity.getUsuario()))
                    .planoDeContas(planosRepresentation.select2(entity.getPlanoDeContas()))
                    .owner(entity.getOwner())
                    .build();
        }

        @Override
        public PlanoDeContasUsuariosDTO select2(PlanoDeContasUsuarios entity) {
            return PlanoDeContasUsuariosDTO.builder()
                    .id(entity.getId())
                    .usuario(usuariosRepresentation.toRelacionamento(entity.getUsuario()))
                    .planoDeContas(planosRepresentation.select2(entity.getPlanoDeContas()))
                    .owner(entity.getOwner())
                    .build();
        }
    }
}
