package com.jarmison.consulta.credito.core.base.mapper;

public interface BaseMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}

