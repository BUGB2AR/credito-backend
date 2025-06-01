package com.jarmison.consulta.credito.domain.service;

import com.jarmison.consulta.credito.domain.dto.CreditoDto;
import com.jarmison.consulta.credito.domain.entity.Credito;
import com.jarmison.consulta.credito.core.exception.ResourceNotFoundException;
import com.jarmison.consulta.credito.core.base.service.BaseServiceImpl;
import com.jarmison.consulta.credito.core.mapper.CreditoMapper;
import com.jarmison.consulta.credito.domain.repository.CreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditoServiceImpl extends BaseServiceImpl<Credito, CreditoDto, Long>
        implements CreditoService {

    private final CreditoRepository creditoRepository;

    public CreditoServiceImpl(CreditoRepository creditoRepository, CreditoMapper creditoMapper) {
        super(creditoRepository, creditoMapper);
        this.creditoRepository = creditoRepository;
    }

    @Override
    public List<CreditoDto> findAllBy(String numeroNfse) {
        return creditoRepository.findByNumeroNfse(numeroNfse)
                .stream()
                .map(super.mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreditoDto findByNumeroCredito(String numeroCredito) {
        Credito credito = creditoRepository.findByNumeroCredito(numeroCredito)
                .orElseThrow(() -> new ResourceNotFoundException("Crédito não encontrado"));
        return super.mapper.toDto(credito);
    }
}
