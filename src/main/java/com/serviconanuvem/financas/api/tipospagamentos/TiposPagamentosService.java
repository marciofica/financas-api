package com.serviconanuvem.financas.api.tipospagamentos;

import com.serviconanuvem.financas.core.service.AbstractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TiposPagamentosService extends AbstractService<TiposPagamentos> {
}
