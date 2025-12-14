package br.com.eicon.consultacredito.producer;

public class KafkaProducerFake implements IProducer {

    @Override
    public void send(String message) {
        System.out.println("KafkaProducerFake: " + message);
    }
}
