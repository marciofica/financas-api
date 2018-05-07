package com.serviconanuvem.financas.api.planodecontas;

import com.serviconanuvem.financas.core.resource.AbstractResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/plano-de-contas")
@Transactional
public class PlanoDeContasResource extends AbstractResource<PlanoDeContas, PlanoDeContasDTO> {

    @Autowired
    PlanoDeContasDTO.Representation representation;

    @Autowired
    PlanoDeContasRepository repository;

    @Autowired
    PlanoDeContasService service;

    @Override
    public Specification specifications() {
        return Specification.where(PlanoDeContasSpecification.ativo(ativo))
                .and(PlanoDeContasSpecification.byCriteria(criterio));
    }

    @RequestMapping(value = "/select2", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Page<PlanoDeContasDTO> select2(@RequestParam("criteria") String criteria, Pageable pageable){
        Page<PlanoDeContas> entityPage = repository.findByDescricaoLikeAndAtivoEquals("%" + criteria + "%", 1, pageable);

        Page<PlanoDeContasDTO> pageDto = new PageImpl<>(entityPage.getContent().stream()
                .map(data  -> new PlanoDeContasDTO.Representation().select2(data))
                .collect(Collectors.toList()), pageable, entityPage.getTotalElements());
        return pageDto;
    }
}
