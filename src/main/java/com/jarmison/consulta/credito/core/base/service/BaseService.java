package com.jarmison.consulta.credito.core.base.service;

import java.util.List;

public interface BaseService<D, ID> {
    D findById(ID id);
    List<D> findAll();
}
