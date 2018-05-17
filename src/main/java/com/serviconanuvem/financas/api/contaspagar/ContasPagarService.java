package com.serviconanuvem.financas.api.contaspagar;

import com.serviconanuvem.financas.core.service.AbstractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContasPagarService extends AbstractService<ContasPagar> {
}
