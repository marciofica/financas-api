package com.serviconanuvem.financas.api;

import com.serviconanuvem.financas.api.usuarios.Usuarios;
import com.serviconanuvem.financas.api.usuarios.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insere-usuarios")
public class InsereUsuarios {
    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Usuarios insere() {

        Usuarios usuario = Usuarios.builder()
                .email("marciofica@gmail.com")
                .enabled(true)
                .nome("Márcio Figueiredo Cardoso")
                .username("marciofica")
                .password(passwordEncoder.encode("123456"))
                .build();
        return usuariosRepository.saveAndFlush(usuario);
    }

    @RequestMapping(value = "/encripta" ,method = RequestMethod.GET, produces = "application/text; charset=utf-8")
    @ResponseBody
    public String show() {
        return passwordEncoder.encode("marcio.123");
    }
}
