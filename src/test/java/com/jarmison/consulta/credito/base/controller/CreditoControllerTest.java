package com.jarmison.consulta.credito.base.controller;

import com.jarmison.consulta.credito.application.controller.CreditoController;
import com.jarmison.consulta.credito.base.config.CreditoTestConfig;
import com.jarmison.consulta.credito.domain.dto.CreditoDto;
import com.jarmison.consulta.credito.domain.service.CreditoService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditoController.class)
@Import(CreditoTestConfig.class)
public class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreditoService creditoService;

    @Test
    void deveListarPorNumeroNfse() throws Exception {
        CreditoDto dto1 = new CreditoDto();
        dto1.setNumeroCredito("123456");
        CreditoDto dto2 = new CreditoDto();
        dto2.setNumeroCredito("789012");

        List<CreditoDto> lista = Arrays.asList(dto1, dto2);

        Mockito.when(creditoService.findAllBy(Mockito.anyString()))
                .thenReturn(lista);

        mockMvc.perform(get("/api/creditos/7891011")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].numeroCredito").value("123456"))
                .andExpect(jsonPath("$[1].numeroCredito").value("789012"));
    }

    @Test
    void deveBuscarPorId() throws Exception {
        CreditoDto dto = new CreditoDto();
        dto.setNumeroCredito("123456");

        Mockito.when(creditoService.findById(anyLong()))
                .thenReturn(dto);

        mockMvc.perform(get("/api/creditos/credito/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCredito").value("123456"));
    }

    @Test
    void deveBuscarPorNumeroCredito() throws Exception {
        CreditoDto dto = new CreditoDto();
        dto.setNumeroCredito("789012");

        Mockito.when(creditoService.findByNumeroCredito(anyString()))
                .thenReturn(dto);

        mockMvc.perform(get("/api/creditos/numero/789012")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCredito").value("789012"));
    }
}

