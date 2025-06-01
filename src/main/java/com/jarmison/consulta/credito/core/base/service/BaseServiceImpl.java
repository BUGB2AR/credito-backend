package com.jarmison.consulta.credito.core.base.service;

import com.jarmison.consulta.credito.core.base.mapper.BaseMapper;
import com.jarmison.consulta.credito.core.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<E, D, ID> implements BaseService<D, ID> {

    protected final JpaRepository<E, ID> repository;
    protected final BaseMapper<E, D> mapper;

    protected BaseServiceImpl(JpaRepository<E, ID> repository, BaseMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public D findById(ID id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return mapper.toDto(entity);
    }

    @Override
    public List<D> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}