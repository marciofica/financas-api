package com.serviconanuvem.financas.api.usuarios;

import com.serviconanuvem.financas.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userDetailsService")
@Transactional
public class UsuariosService extends AbstractService<Usuarios> implements UserDetailsService {
    @Autowired
    private UsuariosRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username);
    }
}
