package com.jarmison.consulta.credito.base.config;

import com.jarmison.consulta.credito.domain.service.CreditoService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CreditoTestConfig {

    @Bean
    public CreditoService creditoService() {
        return Mockito.mock(CreditoService.class);
    }
}

