package com.serviconanuvem.financas.api.tipospagamentos;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;

public class TiposPagamentosSpecification {
    public static Specification<TiposPagamentos> ativo(Integer ativo) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(ativo.intValue() == 2){
                return criteriaBuilder.greaterThanOrEqualTo(root.get("ativo"),0);
            }
            return criteriaBuilder.equal(root.get("ativo"), ativo);
        };
    }

    public static Specification<TiposPagamentos> byCriteria(String criterio){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(criterio == null){
                return null;
            }
            return criteriaBuilder.like(root.get("descricao"), "%" + criterio + "%");
        };
    }

    public static Specification<TiposPagamentos> withPlanoDeContas(Long planoId){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(planoId == null){
                return null;
            }
            return criteriaBuilder.equal(root.join("planoDeContas").get("id"), planoId);
        };
    }
}
