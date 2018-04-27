package com.serviconanuvem.financas.core.representation;

import java.util.List;

public interface IRepresentation<T, D> {
    T toEntity(D dto);

    D toDTO(T entity);

    List<D> toDTO(List<T> entities);

    D toList(T entity);

    D select2(T entity);
}
