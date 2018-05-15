package com.serviconanuvem.financas.api.contasreceber;

import com.serviconanuvem.financas.core.service.AbstractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContasReceberService extends AbstractService<ContasReceber> {
}
