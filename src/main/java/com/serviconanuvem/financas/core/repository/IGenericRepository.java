package com.serviconanuvem.financas.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
