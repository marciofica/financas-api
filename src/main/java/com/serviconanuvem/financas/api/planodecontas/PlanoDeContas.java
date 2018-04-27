package com.serviconanuvem.financas.api.planodecontas;

import com.serviconanuvem.financas.core.entity.DefaultEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "PlanoDeContas")
@Table(name = "plano_contas", schema = "financas")
@Data @Builder
public class PlanoDeContas extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "descricao", length = 30, nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "ativo", length = 1, nullable = false)
    private Integer ativo;

    @Tolerate
    public PlanoDeContas() {
    }
}
