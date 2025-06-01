package com.jarmison.consulta.credito.domain.service;

import com.jarmison.consulta.credito.core.base.service.GenericService;
import com.jarmison.consulta.credito.domain.dto.CreditoDto;

import java.util.List;

public interface CreditoService extends GenericService<CreditoDto, Long> {
    List<CreditoDto> findAllBy(String numeroNfse);
    CreditoDto findByNumeroCredito(String numeroCredito);
}
