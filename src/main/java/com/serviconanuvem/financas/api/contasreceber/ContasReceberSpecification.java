package com.serviconanuvem.financas.api.contasreceber;

import org.springframework.data.jpa.domain.Specification;

public class ContasReceberSpecification {
   public static Specification<ContasReceber> byCriteria(String criterio){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(criterio == null){
                return null;
            }
            return criteriaBuilder.like(root.get("descricao"), "%" + criterio + "%");
        };
    }
}
