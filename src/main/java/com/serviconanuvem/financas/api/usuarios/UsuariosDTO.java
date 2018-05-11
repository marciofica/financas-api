package com.serviconanuvem.financas.api.usuarios;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.serviconanuvem.financas.core.representation.AbstractRepresentation;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @Builder
public class UsuariosDTO {
    private Long id;

    @NotBlank
    @Size(max = 80)
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NotBlank
    @Size(max = 80)
    private String username;

    @NotBlank
    @Size(max = 255)
    private String password;

    private boolean enabled;

    @Tolerate
    public UsuariosDTO() {
    }

    @Component
    public static class Representation extends AbstractRepresentation<Usuarios, UsuariosDTO> {

        @Autowired
        UsuariosRepository usuariosRepository;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Override
        public Usuarios toEntity(UsuariosDTO dto) {

            Usuarios user = Usuarios.builder()
                    .id(dto.getId())
                    .username(dto.getUsername())
                    .nome(dto.getNome())
                    .enabled(dto.isEnabled())
                    .email(dto.getEmail())
                    .build();
            if(user.getId() == null){
                user.setPassword(passwordEncoder.encode(dto.password));
            } else {
                Usuarios userPersisted = usuariosRepository.findById(dto.getId()).get();
                user.setPassword(userPersisted.getPassword());
            }

            return user;
        }

        @Override
        public UsuariosDTO toDTO(Usuarios entity) {
            return UsuariosDTO.builder()
                    .id(entity.getId())
                    .username(entity.getUsername())
                    .nome(entity.getNome())
                    .enabled(entity.isEnabled())
                    .email(entity.getEmail())
                    .build();
        }

        @Override
        public UsuariosDTO toList(Usuarios entity) {
            return UsuariosDTO.builder()
                    .id(entity.getId())
                    .username(entity.getUsername())
                    .nome(entity.getNome())
                    .enabled(entity.isEnabled())
                    .email(entity.getEmail())
                    .build();
        }

        @Override
        public UsuariosDTO select2(Usuarios entity) {
            return UsuariosDTO.builder()
                    .id(entity.getId())
                    .username(entity.getUsername())
                    .nome(entity.getNome())
                    .email(entity.getEmail())
                    .build();
        }

        public UsuariosDTO toRelacionamento(Usuarios entity) {
            return UsuariosDTO.builder()
                    .id(entity.getId())
                    .username(entity.getUsername())
                    .build();
        }
    }

}
