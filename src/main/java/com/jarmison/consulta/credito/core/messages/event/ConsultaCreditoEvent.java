package com.jarmison.consulta.credito.core.messages.event;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsultaCreditoEvent {
    private String numeroCredito;
    private String descricao;
    private long timestamp;
}
