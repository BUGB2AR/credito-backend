package com.jarmison.consulta.credito.base.service;

import com.jarmison.consulta.credito.core.base.mapper.BaseMapper;
import com.jarmison.consulta.credito.core.exception.ResourceNotFoundException;
import com.jarmison.consulta.credito.core.base.service.BaseServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BaseServiceImplTest {

    static class Entity {
        Long id;
        String name;

        Entity(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class Dto {
        Long id;
        String name;

        Dto(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    JpaRepository<Entity, Long> repository;
    BaseMapper<Entity, Dto> mapper;

    BaseServiceImpl<Entity, Dto, Long> service;

    @BeforeEach
    void setUp() {
        repository = mock(JpaRepository.class);
        mapper = mock(BaseMapper.class);

        service = new BaseServiceImpl<Entity, Dto, Long>(repository, mapper) {};
    }

    @Test
    void findById_deveRetornarDto_quandoEntidadeExistir() {
        Entity entity = new Entity(1L, "Teste");
        Dto dto = new Dto(1L, "Teste");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        Dto resultado = service.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id);
        assertEquals("Teste", resultado.name);

        verify(repository).findById(1L);
        verify(mapper).toDto(entity);
    }

    @Test
    void findById_deveLancarExcecao_quandoEntidadeNaoExistir() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));

        verify(repository).findById(1L);
        verifyNoInteractions(mapper);
    }

    @Test
    void findAll_deveRetornarListaDeDtos() {
        Entity e1 = new Entity(1L, "Teste1");
        Entity e2 = new Entity(2L, "Teste2");
        Dto d1 = new Dto(1L, "Teste1");
        Dto d2 = new Dto(2L, "Teste2");

        when(repository.findAll()).thenReturn(List.of(e1, e2));
        when(mapper.toDto(e1)).thenReturn(d1);
        when(mapper.toDto(e2)).thenReturn(d2);

        List<Dto> lista = service.findAll();

        assertNotNull(lista);
        assertEquals(2, lista.size());
        assertEquals(1L, lista.get(0).id);
        assertEquals(2L, lista.get(1).id);

        verify(repository).findAll();
        verify(mapper).toDto(e1);
        verify(mapper).toDto(e2);
    }
}

