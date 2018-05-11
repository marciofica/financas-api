package com.serviconanuvem.financas.api.usuarios;

import com.serviconanuvem.financas.api.authorities.Authorities;
import com.serviconanuvem.financas.api.authorities.AuthoritiesRepository;
import com.serviconanuvem.financas.api.enums.RoleEnum;
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

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public void afterSave(Usuarios entity) {
        Authorities authoritie = Authorities.builder()
                .usuario(entity)
                .authority(RoleEnum.ROLE_USER)
                .username(entity.getUsername())
                .build();
        authoritiesRepository.save(authoritie);
        super.afterSave(entity);
    }
}
