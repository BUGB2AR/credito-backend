package com.jarmison.consulta.credito.core.messages.publisher;

import com.jarmison.consulta.credito.core.messages.event.ConsultaCreditoEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisher {

    private final KafkaTemplate<String, ConsultaCreditoEvent> kafkaTemplate;
    private static final String TOPIC = "consulta-credito-topic";

    public KafkaPublisher(KafkaTemplate<String, ConsultaCreditoEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(ConsultaCreditoEvent event) {
        kafkaTemplate.send(TOPIC, event.getNumeroCredito(), event);
    }
}
