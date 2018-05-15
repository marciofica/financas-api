package com.serviconanuvem.financas.api.contasreceber;

import com.serviconanuvem.financas.core.resource.AbstractResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/contas-receber")
@Transactional
public class ContasReceberResource extends AbstractResource<ContasReceber, ContasReceberDTO> {
    @Autowired
    ContasReceberDTO.Representation representation;

    @Autowired
    ContasReceberRepository repository;

    @Autowired
    ContasReceberService service;

    @Override
    public Specification specifications() {
        return Specification.where(ContasReceberSpecification.byCriteria(criterio));
    }
}
