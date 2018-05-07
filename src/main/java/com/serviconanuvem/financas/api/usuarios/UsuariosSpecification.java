package com.serviconanuvem.financas.api.usuarios;

import org.springframework.data.jpa.domain.Specification;

public class UsuariosSpecification {

    public static Specification<Usuarios> ativo(Integer ativo) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(ativo.intValue() == 2){
                return criteriaBuilder.greaterThanOrEqualTo(root.get("ativo"),0);
            }
            return criteriaBuilder.equal(root.get("ativo"), ativo);
        };
    }

    public static Specification<Usuarios> byCriteria(String criterio){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(criterio == null){
                return null;
            }
            return criteriaBuilder.like(root.get("nome"), "%" + criterio + "%");
        };
    }

    public static Specification<Usuarios> byEmail(String criterio){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(criterio == null){
                return null;
            }
            return criteriaBuilder.like(root.get("email"), "%" + criterio + "%");
        };
    }

    public static Specification<Usuarios> byUsuario(String criterio){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(criterio == null){
                return null;
            }
            return criteriaBuilder.like(root.get("username"), "%" + criterio + "%");
        };
    }
}
