package com.serviconanuvem.financas.core.service;

public abstract class AbstractService<T> {

    public T beforeSave(T entity) {
        return entity;
    }

    public void afterSave(T entity) {
        //Implementation here
    }
}
