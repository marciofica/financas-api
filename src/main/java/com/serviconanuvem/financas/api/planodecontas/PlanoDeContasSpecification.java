package com.serviconanuvem.financas.api.planodecontas;

import org.springframework.data.jpa.domain.Specification;

public class PlanoDeContasSpecification {
    public static Specification<PlanoDeContas> ativo(Integer ativo) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(ativo.intValue() == 2){
                return criteriaBuilder.greaterThanOrEqualTo(root.get("ativo"),0);
            }
            return criteriaBuilder.equal(root.get("ativo"), ativo);
        };
    }

    public static Specification<PlanoDeContas> byCriteria(String criterio){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(criterio == null){
                return null;
            }
            return criteriaBuilder.like(root.get("descricao"), "%" + criterio + "%");
        };
    }
}
