package com.serviconanuvem.financas.api.planodecontas;

import com.serviconanuvem.financas.core.service.AbstractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class PlanoDeContasService extends AbstractService<PlanoDeContas> {

}
