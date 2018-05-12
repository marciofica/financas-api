package com.serviconanuvem.financas.api.tipospagamentos;

import com.serviconanuvem.financas.core.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TiposPagamentosRepository extends IGenericRepository<TiposPagamentos> {
    Page<TiposPagamentos> findByDescricaoLikeAndAtivoEquals(String descricao, Integer ativo, Pageable page);
}
