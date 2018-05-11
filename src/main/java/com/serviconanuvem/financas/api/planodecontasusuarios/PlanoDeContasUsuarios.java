package com.serviconanuvem.financas.api.planodecontasusuarios;

import com.serviconanuvem.financas.api.enums.SimNaoEnum;
import com.serviconanuvem.financas.api.planodecontas.PlanoDeContas;
import com.serviconanuvem.financas.api.usuarios.Usuarios;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "plano_usuarios", schema = "financas")
@Data @Builder
public class PlanoDeContasUsuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuarios usuario;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id", referencedColumnName = "id")
    private PlanoDeContas planoDeContas;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SimNaoEnum owner;

    @Tolerate
    public PlanoDeContasUsuarios() {
    }
}
