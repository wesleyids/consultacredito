package br.com.eicon.consultacredito.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImpl implements IProducer {

    private static final String TOPIC = "topic_eicon_creditoconsulta_log";

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Override
    public void send(String message) {
        try {
            kafkaTemplate.send(TOPIC, message.getBytes());
            System.out.println(String.format("Mensagem enviada para o tópico: %s", TOPIC));
        } catch (Exception e) {
            System.out.println(String.format("Erro ao enviar mensagem: %s", e.getMessage()));
            e.printStackTrace();
        }
    }
}
