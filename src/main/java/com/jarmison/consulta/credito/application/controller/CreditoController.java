package com.jarmison.consulta.credito.application.controller;

import com.jarmison.consulta.credito.core.base.controller.BaseController;
import com.jarmison.consulta.credito.domain.dto.CreditoDto;
import com.jarmison.consulta.credito.domain.service.CreditoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/creditos")
public class CreditoController extends BaseController<CreditoDto, Long> {
    public CreditoController(CreditoService service) {
        super(service);
    }
}

