package com.serviconanuvem.financas.api.planodecontas;

import com.serviconanuvem.financas.core.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanoDeContasRepository extends IGenericRepository<PlanoDeContas> {
    Page<PlanoDeContas> findByDescricaoLikeAndAtivoEquals(String descricao, Integer ativo, Pageable page);
}
