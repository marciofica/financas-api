package com.serviconanuvem.financas.api.planodecontasusuarios;

import com.serviconanuvem.financas.core.resource.AbstractResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/plano-de-contas-usuarios")
@Transactional
public class PlanoDeContasUsuariosResource extends AbstractResource<PlanoDeContasUsuarios, PlanoDeContasUsuariosDTO> {

    @Autowired
    PlanoDeContasUsuariosDTO.Representation representation;

    @Autowired
    PlanoDeContasUsuariosRepository repository;

    @Autowired
    PlanoDeContasUsuariosService service;

    @Override
    public Specification specifications() {
        return null;
    }
}
