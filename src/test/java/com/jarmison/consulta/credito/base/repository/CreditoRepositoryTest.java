package com.jarmison.consulta.credito.base.repository;

import com.jarmison.consulta.credito.domain.entity.Credito;
import com.jarmison.consulta.credito.domain.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CreditoRepositoryTest {

    @Autowired
    private CreditoRepository creditoRepository;

    private Credito credito1;
    private Credito credito2;

    @BeforeEach
    void setup() {
        creditoRepository.deleteAll();

        credito1 = Credito.builder()
                .numeroCredito("123456")
                .numeroNfse("NF001")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(new BigDecimal("100"))
                .tipoCredito("TipoA")
                .simplesNacional(true)
                .aliquota(new BigDecimal("2.0"))
                .valorFaturado(new BigDecimal("500"))
                .valorDeducao(new BigDecimal("50"))
                .baseCalculo(new BigDecimal("450"))
                .build();

        credito2 = Credito.builder()
                .numeroCredito("789012")
                .numeroNfse("NF002")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(new BigDecimal("200"))
                .tipoCredito("TipoB")
                .simplesNacional(false)
                .aliquota(new BigDecimal("3.0"))
                .valorFaturado(new BigDecimal("1000"))
                .valorDeducao(new BigDecimal("100"))
                .baseCalculo(new BigDecimal("900"))
                .build();

        creditoRepository.saveAll(List.of(credito1, credito2));
    }

    @Test
    void deveEncontrarCreditosPorNumeroNfse() {
        List<Credito> resultado = creditoRepository.findByNumeroNfse("NF001");
        assertThat(resultado).isNotEmpty();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNumeroCredito()).isEqualTo("123456");
    }

    @Test
    void deveRetornarListaVaziaSeNaoExistirNumeroNfse() {
        List<Credito> resultado = creditoRepository.findByNumeroNfse("NF999");
        assertThat(resultado).isEmpty();
    }

    @Test
    void deveEncontrarCreditoPorNumeroCredito() {
        Optional<Credito> resultado = creditoRepository.findByNumeroCredito("789012");
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNumeroNfse()).isEqualTo("NF002");
    }

    @Test
    void deveRetornarVazioSeNaoExistirNumeroCredito() {
        Optional<Credito> resultado = creditoRepository.findByNumeroCredito("000000");
        assertThat(resultado).isNotPresent();
    }
}
