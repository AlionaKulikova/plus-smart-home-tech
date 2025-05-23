package ru.yandex.practicum.config.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaClientImpl implements KafkaClient {
    private final Producer producer;

    @Override
    public Producer<String, SpecificRecordBase> getProducer() {
        return producer;
    }

    @Override
    public void stop() {
        producer.close();
    }
}