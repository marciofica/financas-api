package com.serviconanuvem.financas.core.representation;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepresentation<T, D> implements IRepresentation<T, D> {
    @Override
    public abstract T toEntity(D dto);

    @Override
    public abstract D toDTO(T entity);

    @Override
    public List<D> toDTO(List<T> entities) {
        List<D> dtoList = new ArrayList<>();
        entities.stream().forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }

    @Override
    public abstract D toList(T entity);

    @Override
    public abstract D select2(T entity);
}