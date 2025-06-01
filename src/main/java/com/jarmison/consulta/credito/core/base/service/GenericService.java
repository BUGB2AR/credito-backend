package com.jarmison.consulta.credito.core.base.service;

import java.util.List;

public interface GenericService<T, ID> {
    List<T> findAllBy(String numeroNfse);

    T findById(ID id);

    T findByNumeroCredito(String numeroCredito);
}