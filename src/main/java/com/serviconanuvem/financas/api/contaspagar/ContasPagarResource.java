package com.serviconanuvem.financas.api.contaspagar;

import com.serviconanuvem.financas.core.resource.AbstractResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/contas-pagar")
@Transactional
public class ContasPagarResource extends AbstractResource<ContasPagar, ContasPagarDTO> {
    @Autowired
    ContasPagarDTO.Representation representation;

    @Autowired
    ContasPagarRepository repository;

    @Autowired
    ContasPagarService service;

    @Override
    public Specification specifications() {
        return Specification.where(ContasPagarSpecification.byCriteria(criterio));
    }
}
