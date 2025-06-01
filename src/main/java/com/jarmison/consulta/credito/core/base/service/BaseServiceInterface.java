package com.jarmison.consulta.credito.core.base.service;

import java.util.List;

public interface BaseServiceInterface<E, D> {
    List<D> findAllBy(String param);
    D findById(String id);
}
