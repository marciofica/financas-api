package com.serviconanuvem.financas.api.eventosfinanceiros;

import com.serviconanuvem.financas.core.resource.AbstractResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/eventos-financeiros")
@Transactional
public class EventosFinanceirosResource extends AbstractResource<EventosFinanceiros, EventosFinanceirosDTO> {

    @Autowired
    EventosFinanceirosDTO.Representation representation;

    @Autowired
    EventosFinanceirosRepository repository;

    @Autowired
    EventosFinanceirosService service;

    @Override
    public Specification specifications() {
        return Specification.where(EventosFinanceirosSpecification.ativo(ativo))
                .and(EventosFinanceirosSpecification.byCriteria(criterio));
    }

    @RequestMapping(value = "/select2", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Page<EventosFinanceirosDTO> select2(@RequestParam("criteria") String criteria, Pageable pageable){
        Page<EventosFinanceiros> entityPage = repository.findByDescricaoLikeAndAtivoEquals("%" + criteria + "%", 1, pageable);
        Page<EventosFinanceirosDTO> pageDto = new PageImpl<>(entityPage.getContent().stream()
                .map(data  -> new EventosFinanceirosDTO.Representation().select2(data))
                .collect(Collectors.toList()), pageable, entityPage.getTotalElements());
        return pageDto;
    }
}
