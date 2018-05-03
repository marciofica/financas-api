package com.serviconanuvem.financas.api.usuarios;

import com.serviconanuvem.financas.core.repository.IGenericRepository;

import java.util.Optional;

public interface UsuariosRepository extends IGenericRepository<Usuarios> {
    Optional<Usuarios> findByUsernameOrEmail(String username, String email);
}
