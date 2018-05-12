package com.serviconanuvem.financas.api.tipospagamentos;

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
@RequestMapping("/api/tipos-pagamentos")
@Transactional
public class TiposPagamentosResource extends AbstractResource<TiposPagamentos, TiposPagamentosDTO> {
    @Autowired
    TiposPagamentosDTO.Representation representation;

    @Autowired
    TiposPagamentosRepository repository;

    @Autowired
    TiposPagamentosService service;

    @Override
    public Specification specifications() {
        return Specification.where(TiposPagamentosSpecification.ativo(ativo))
                .and(TiposPagamentosSpecification.byCriteria(criterio));
    }

    @RequestMapping(value = "/select2", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Page<TiposPagamentosDTO> select2(@RequestParam("criteria") String criteria, Pageable pageable){
        Page<TiposPagamentos> entityPage = repository.findByDescricaoLikeAndAtivoEquals("%" + criteria + "%", 1, pageable);
        Page<TiposPagamentosDTO> pageDto = new PageImpl<>(entityPage.getContent().stream()
                .map(data  -> new TiposPagamentosDTO.Representation().select2(data))
                .collect(Collectors.toList()), pageable, entityPage.getTotalElements());
        return pageDto;
    }
}
