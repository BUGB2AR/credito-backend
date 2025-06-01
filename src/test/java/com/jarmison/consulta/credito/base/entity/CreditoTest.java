package com.jarmison.consulta.credito.base.entity;

import com.jarmison.consulta.credito.domain.entity.Credito;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreditoTest {

    @Test
    void deveCriarCreditoComBuilder() {
        LocalDate data = LocalDate.of(2025, 6, 1);

        Credito credito = Credito.builder()
                .id(10L)
                .numeroCredito("123456")
                .numeroNfse("NF1234")
                .dataConstituicao(data)
                .valorIssqn(new BigDecimal("100.50"))
                .tipoCredito("Tipo1")
                .simplesNacional(true)
                .aliquota(new BigDecimal("2.5"))
                .valorFaturado(new BigDecimal("5000"))
                .valorDeducao(new BigDecimal("100"))
                .baseCalculo(new BigDecimal("4900"))
                .build();

        assertEquals(10L, credito.getId());
        assertEquals("123456", credito.getNumeroCredito());
        assertEquals("NF1234", credito.getNumeroNfse());
        assertEquals(data, credito.getDataConstituicao());
        assertEquals(new BigDecimal("100.50"), credito.getValorIssqn());
        assertEquals("Tipo1", credito.getTipoCredito());
        assertTrue(credito.isSimplesNacional());
        assertEquals(new BigDecimal("2.5"), credito.getAliquota());
        assertEquals(new BigDecimal("5000"), credito.getValorFaturado());
        assertEquals(new BigDecimal("100"), credito.getValorDeducao());
        assertEquals(new BigDecimal("4900"), credito.getBaseCalculo());
    }

    @Test
    void devePermitirSettersEGetters() {
        Credito credito = new Credito();

        credito.setId(20L);
        credito.setNumeroCredito("654321");
        credito.setNumeroNfse("NF5678");
        credito.setDataConstituicao(LocalDate.now());
        credito.setValorIssqn(new BigDecimal("200"));
        credito.setTipoCredito("Tipo2");
        credito.setSimplesNacional(false);
        credito.setAliquota(new BigDecimal("3.5"));
        credito.setValorFaturado(new BigDecimal("6000"));
        credito.setValorDeducao(new BigDecimal("200"));
        credito.setBaseCalculo(new BigDecimal("5800"));

        assertEquals(20L, credito.getId());
        assertEquals("654321", credito.getNumeroCredito());
        assertEquals("NF5678", credito.getNumeroNfse());
        assertNotNull(credito.getDataConstituicao());
        assertEquals(new BigDecimal("200"), credito.getValorIssqn());
        assertEquals("Tipo2", credito.getTipoCredito());
        assertFalse(credito.isSimplesNacional());
        assertEquals(new BigDecimal("3.5"), credito.getAliquota());
        assertEquals(new BigDecimal("6000"), credito.getValorFaturado());
        assertEquals(new BigDecimal("200"), credito.getValorDeducao());
        assertEquals(new BigDecimal("5800"), credito.getBaseCalculo());
    }
}

