package com.serviconanuvem.financas.api.eventosfinanceiros;

import com.serviconanuvem.financas.core.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventosFinanceirosRepository extends IGenericRepository<EventosFinanceiros> {
    Page<EventosFinanceiros> findByDescricaoLikeAndAtivoEquals(String descricao, Integer ativo, Pageable page);
}
