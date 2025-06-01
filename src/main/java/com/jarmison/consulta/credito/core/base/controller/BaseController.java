package com.jarmison.consulta.credito.core.base.controller;

import com.jarmison.consulta.credito.core.base.service.GenericService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BaseController<T, ID> implements BaseRoutesController<T, ID> {

    protected final GenericService<T, ID> service;

    protected BaseController(GenericService<T, ID> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<T>> listarPorNfse(String numeroNfse) {
        return ResponseEntity.ok(service.findAllBy(numeroNfse));
    }

    @Override
    public ResponseEntity<T> buscarPorId(ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<T> buscarPorNumeroCredito(String numeroCredito) {
        return ResponseEntity.ok(service.findByNumeroCredito(numeroCredito));
    }

}

