package com.serviconanuvem.financas.api.eventosfinanceiros;

import com.serviconanuvem.financas.core.service.AbstractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EventosFinanceirosService extends AbstractService<EventosFinanceiros> {
}
