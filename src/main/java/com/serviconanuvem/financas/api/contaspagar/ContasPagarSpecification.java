package com.serviconanuvem.financas.api.contaspagar;

import org.springframework.data.jpa.domain.Specification;

public class ContasPagarSpecification {
    public static Specification<ContasPagar> byCriteria(String criterio){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(criterio == null){
                return null;
            }
            return criteriaBuilder.like(root.get("descricao"), "%" + criterio + "%");
        };
    }
}
