package com.jarmison.consulta.credito.core.base.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BaseRoutesController<T, ID> {

    @GetMapping("/{numeroNfse}")
    ResponseEntity<List<T>> listarPorNfse(@PathVariable String numeroNfse);

    @GetMapping("/credito/{id}")
    ResponseEntity<T> buscarPorId(@PathVariable ID id);

    @GetMapping("/numero/{numeroCredito}")
    ResponseEntity<T> buscarPorNumeroCredito(@PathVariable String numeroCredito);
}