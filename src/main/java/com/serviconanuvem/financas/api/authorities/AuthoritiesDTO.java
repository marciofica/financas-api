package com.serviconanuvem.financas.api.authorities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.serviconanuvem.financas.api.enums.RoleEnum;
import com.serviconanuvem.financas.api.usuarios.Usuarios;
import com.serviconanuvem.financas.api.usuarios.UsuariosDTO;
import com.serviconanuvem.financas.api.usuarios.UsuariosRepository;
import com.serviconanuvem.financas.core.representation.AbstractRepresentation;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @Builder
public class AuthoritiesDTO {

    private Long id;

    @NotNull
    private RoleEnum authority;

    @NotNull
    private UsuariosDTO usuario;

    @Tolerate
    public AuthoritiesDTO() {
    }

    @Component
    public static class Representation extends AbstractRepresentation<Authorities, AuthoritiesDTO> {

        @Autowired
        AuthoritiesRepository authoritiesRepository;

        @Autowired
        UsuariosDTO.Representation usuariosRepresentation;

        @Autowired
        UsuariosRepository usuariosRepository;

        @Override
        public Authorities toEntity(AuthoritiesDTO dto) {
            Usuarios user = usuariosRepository.findById(dto.getUsuario().getId()).get();
            Authorities authorities = Authorities.builder()
                    .id(dto.getId())
                    .authority(dto.getAuthority())
                    .usuario(user)
                    .username(user.getUsername())
                    .build();
            return authorities;
        }

        @Override
        public AuthoritiesDTO toDTO(Authorities entity) {
            return AuthoritiesDTO.builder()
                    .id(entity.getId())
                    .authority(entity.getAuthority())
                    .usuario(usuariosRepresentation.toDTO(entity.getUsuario()))
                    .build();
        }

        @Override
        public AuthoritiesDTO toList(Authorities entity) {
            return AuthoritiesDTO.builder()
                    .id(entity.getId())
                    .authority(entity.getAuthority())
                    .build();
        }

        @Override
        public AuthoritiesDTO select2(Authorities entity) {
            return AuthoritiesDTO.builder()
                    .id(entity.getId())
                    .authority(entity.getAuthority())
                    .build();
        }
    }
}
