package com.jarmison.consulta.credito.base.service;

import com.jarmison.consulta.credito.core.exception.ResourceNotFoundException;
import com.jarmison.consulta.credito.core.mapper.CreditoMapper;
import com.jarmison.consulta.credito.core.messages.event.ConsultaCreditoEvent;
import com.jarmison.consulta.credito.core.messages.publisher.KafkaPublisher;
import com.jarmison.consulta.credito.domain.dto.CreditoDto;
import com.jarmison.consulta.credito.domain.entity.Credito;
import com.jarmison.consulta.credito.domain.repository.CreditoRepository;
import com.jarmison.consulta.credito.domain.service.CreditoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreditoServiceImplTest {

    @Mock
    private CreditoRepository creditoRepository;

    @Mock
    private CreditoMapper creditoMapper;

    @Mock
    private KafkaPublisher kafkaPublisher;

    @InjectMocks
    private CreditoServiceImpl creditoService;

    private Credito creditoEntity;
    private CreditoDto creditoDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        creditoEntity = Credito.builder()
                .id(1L)
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

        creditoDto = new CreditoDto();
        creditoDto.setNumeroCredito("123456");
        creditoDto.setNumeroNfse("NF001");
    }

    @Test
    void deveRetornarListaDeCreditoDtoAoBuscarPorNumeroNfse() {

        when(creditoRepository.findByNumeroNfse("NF001")).thenReturn(List.of(creditoEntity));
        when(creditoMapper.toDto(creditoEntity)).thenReturn(creditoDto);

        List<CreditoDto> resultado = creditoService.findAllBy("NF001");

        assertThat(resultado).isNotEmpty();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNumeroCredito()).isEqualTo("123456");

        verify(creditoRepository).findByNumeroNfse("NF001");
        verify(creditoMapper).toDto(creditoEntity);

        ArgumentCaptor<ConsultaCreditoEvent> eventCaptor = ArgumentCaptor.forClass(ConsultaCreditoEvent.class);
        verify(kafkaPublisher, times(1)).publish(eventCaptor.capture());

        ConsultaCreditoEvent publishedEvent = eventCaptor.getValue();
        assertThat(publishedEvent.getNumeroCredito()).isEqualTo("NF001");
    }

    @Test
    void deveLancarExceptionQuandoCreditoNaoEncontradoPorNumeroCredito() {
        when(creditoRepository.findByNumeroCredito("999999")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> creditoService.findByNumeroCredito("999999"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Crédito não encontrado");

        verify(creditoRepository).findByNumeroCredito("999999");
        verifyNoInteractions(creditoMapper);
    }

    @Test
    void deveRetornarCreditoDtoAoBuscarPorNumeroCredito() {
        when(creditoRepository.findByNumeroCredito("123456")).thenReturn(Optional.of(creditoEntity));
        when(creditoMapper.toDto(creditoEntity)).thenReturn(creditoDto);

        CreditoDto resultado = creditoService.findByNumeroCredito("123456");

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNumeroCredito()).isEqualTo("123456");

        verify(creditoRepository).findByNumeroCredito("123456");
        verify(creditoMapper).toDto(creditoEntity);

        verify(kafkaPublisher).publish(argThat(event ->
                "123456".equals(event.getNumeroCredito())
        ));
    }
}
