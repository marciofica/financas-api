package com.serviconanuvem.financas.api.authorities;

import com.serviconanuvem.financas.api.enums.RoleEnum;
import com.serviconanuvem.financas.api.usuarios.Usuarios;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authorities", schema = "financas", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "authority"})
})
@Data @Builder
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleEnum authority;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuarios usuario;

    @NotNull
    private String username;

    @Tolerate
    public Authorities() {
    }
}
