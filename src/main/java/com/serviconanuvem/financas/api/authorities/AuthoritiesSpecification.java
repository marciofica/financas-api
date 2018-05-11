package com.serviconanuvem.financas.api.authorities;

import org.springframework.data.jpa.domain.Specification;

public class AuthoritiesSpecification {
    public static Specification<Authorities> byCriteria(String criterio){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(criterio == null){
                return null;
            }
            return criteriaBuilder.like(root.get("authority"), "%" + criterio + "%");
        };
    }
}
