package com.serviconanuvem.financas.api.authorities;

import com.serviconanuvem.financas.core.service.AbstractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class AuthoritiesService extends AbstractService<Authorities> {
}
