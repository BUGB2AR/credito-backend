package com.jarmison.consulta.credito.core.mapper;

import com.jarmison.consulta.credito.domain.dto.CreditoDto;
import com.jarmison.consulta.credito.domain.entity.Credito;
import com.jarmison.consulta.credito.core.base.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class CreditoMapper implements BaseMapper<Credito, CreditoDto> {

    @Override
    public CreditoDto toDto(Credito entity) {
        if (entity == null) return null;

        CreditoDto dto = new CreditoDto();
        dto.setNumeroCredito(entity.getNumeroCredito());
        dto.setNumeroNfse(entity.getNumeroNfse());
        dto.setDataConstituicao(entity.getDataConstituicao());
        dto.setValorIssqn(entity.getValorIssqn());
        dto.setTipoCredito(entity.getTipoCredito());
        dto.setSimplesNacional(entity.isSimplesNacional() ? "Sim" : "Não");
        dto.setAliquota(entity.getAliquota());
        dto.setValorFaturado(entity.getValorFaturado());
        dto.setValorDeducao(entity.getValorDeducao());
        dto.setBaseCalculo(entity.getBaseCalculo());
        return dto;
    }

    @Override
    public Credito toEntity(CreditoDto dto) {
        if (dto == null) return null;

        Credito entity = new Credito();
        entity.setNumeroCredito(dto.getNumeroCredito());
        entity.setNumeroNfse(dto.getNumeroNfse());
        entity.setDataConstituicao(dto.getDataConstituicao());
        entity.setValorIssqn(dto.getValorIssqn());
        entity.setTipoCredito(dto.getTipoCredito());
        entity.setSimplesNacional("Sim".equalsIgnoreCase(dto.getSimplesNacional()));
        entity.setAliquota(dto.getAliquota());
        entity.setValorFaturado(dto.getValorFaturado());
        entity.setValorDeducao(dto.getValorDeducao());
        entity.setBaseCalculo(dto.getBaseCalculo());
        return entity;
    }
}
