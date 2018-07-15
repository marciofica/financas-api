package com.serviconanuvem.financas.core.resource;

import com.serviconanuvem.financas.core.repository.IGenericRepository;
import com.serviconanuvem.financas.core.representation.IRepresentation;
import com.serviconanuvem.financas.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

public abstract class AbstractResource<T, D> extends DefaultResources {

    @Autowired
    AbstractService<T> service;
    @Autowired
    IGenericRepository<T> repository;
    @Autowired
    IRepresentation<T, D> representation;

    public String situacao;
    public String criterio;
    public Integer ativo;
    public Long idFilho;

    public abstract Specification specifications();

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<D> save(@RequestBody D dto) {
        T response = preSave(dto);
        return new ResponseEntity<>(representation.toDTO(response), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<D> update(@PathVariable("id") Long id, @RequestBody D dto) {
        T response = preSave(dto);
        return new ResponseEntity<>(representation.toDTO(response), HttpStatus.OK);
    }

    private T preSave(D dto) {
        T entity = representation.toEntity(dto);
        entity = service.beforeSave(entity);
        entity = repository.save(entity);
        try {
            service.afterSave(entity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return entity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Page<D> getAll(@RequestParam(name = "ativo", required = false, defaultValue = "2") Integer ativo, @RequestParam(name = "situacao", required = false) String situacao, @RequestParam(name = "criterio", required = false) String criterio, @RequestParam(name = "idFilho", required = false) Long idFilho, Pageable pageable) {
        this.situacao = situacao;
        this.criterio = criterio;
        this.ativo = ativo;
        this.idFilho = idFilho;
        Page<T> entityPage = (Page<T>) repository.findAll(specifications(), pageable);
        Page<D> pageDto = new PageImpl<>(entityPage.getContent().stream()
                .map(data -> representation.toList(data)).collect(Collectors.toList()), pageable,
                entityPage.getTotalElements());
        return pageDto;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public D getById(@PathVariable("id") Long id) {
        return representation.toDTO((T) repository.findById(id).get());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public ResponseEntity<T> delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
