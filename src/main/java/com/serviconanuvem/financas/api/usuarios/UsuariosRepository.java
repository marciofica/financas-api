package com.serviconanuvem.financas.api.usuarios;

import com.serviconanuvem.financas.core.repository.IGenericRepository;

public interface UsuariosRepository extends IGenericRepository<Usuarios> {
    Usuarios findOneByUsername(String username);
}
