package ru.yandex.practicum.config.kafka;

import java.time.Duration;
import java.time.Instant;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer implements AutoCloseable {
    private final KafkaProducer<String, SpecificRecordBase> producer;

    public <T extends SpecificRecordBase> void send(T event, String hubId, Instant timestamp, String topic) {
        ProducerRecord<String, SpecificRecordBase> record = new ProducerRecord<>(
                topic,
                null,
                timestamp.toEpochMilli(),
                hubId,
                event
        );
        producer.send(record);
    }

    @Override
    public void close() {
        producer.flush();
        producer.close(Duration.ofSeconds(10));
    }
}