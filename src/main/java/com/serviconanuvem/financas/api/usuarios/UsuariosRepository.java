package com.serviconanuvem.financas.api.usuarios;

import com.serviconanuvem.financas.core.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuariosRepository extends IGenericRepository<Usuarios> {
    Usuarios findOneByUsername(String username);
    Page<Usuarios> findByUsernameLikeOrNomeLikeOrEmailLikeAndEnabledTrue(String user, String nome, String email, Pageable page);
}
