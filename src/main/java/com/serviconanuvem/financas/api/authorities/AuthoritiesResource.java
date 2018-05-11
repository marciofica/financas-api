package com.serviconanuvem.financas.api.authorities;

import com.serviconanuvem.financas.core.resource.AbstractResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/authorities")
@Transactional
public class AuthoritiesResource extends AbstractResource<Authorities, AuthoritiesDTO> {
    @Autowired
    AuthoritiesDTO.Representation representation;

    @Autowired
    AuthoritiesRepository repository;

    @Autowired
    AuthoritiesService service;

    @Override
    public Specification specifications() {
        return Specification.where(AuthoritiesSpecification.byCriteria(criterio));
    }

}
