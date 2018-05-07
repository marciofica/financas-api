package com.serviconanuvem.financas.api.usuarios;

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
@RequestMapping("/api/usuarios")
@Transactional
public class UsuariosResource extends AbstractResource<Usuarios, UsuariosDTO> {

    @Autowired
    UsuariosDTO.Representation representation;

    @Autowired
    UsuariosRepository repository;

    @Autowired
    UsuariosService service;

    @Override
    public Specification specifications() {
        return Specification.where(UsuariosSpecification.ativo(ativo))
                .and(UsuariosSpecification.byCriteria(criterio)).or(UsuariosSpecification.byUsuario(criterio));
    }

    @RequestMapping(value = "/select2", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Page<UsuariosDTO> select2(@RequestParam("criteria") String criteria, Pageable pageable){
        Page<Usuarios> entityPage = repository.findByUsernameLikeOrNomeLikeOrEmailLikeAndEnabledTrue("%" + criteria + "%", "%" + criteria + "%", "%" + criteria + "%", pageable);

        Page<UsuariosDTO> pageDto = new PageImpl<>(entityPage.getContent().stream()
                .map(data  -> new UsuariosDTO.Representation().select2(data))
                .collect(Collectors.toList()), pageable, entityPage.getTotalElements());
        return pageDto;
    }
}
