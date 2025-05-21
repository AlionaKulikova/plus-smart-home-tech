package ru.yandex.practicum.handler.sensor;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public abstract class SensorEventHandler<T extends SpecificRecordBase> {
    private final Producer producer;
    private static final String SENSOR_TOPIC = "telemetry.sensors.v1";

    protected abstract T mapToAvro(SensorEventProto event);

    public void handle(SensorEventProto event) {
        T avroObj = mapToAvro(event);
        Instant instantTimestamp = Instant.ofEpochSecond(
                event.getTimestamp().getSeconds(),
                event.getTimestamp().getNanos()
        );

        SensorEventAvro sensorEventAvro = SensorEventAvro.newBuilder()
                .setId(event.getId())
                .setHubId(event.getHubId())
                .setTimestamp(instantTimestamp) // Передаем Instant
                .setPayload(avroObj)
                .build();

        producer.send(sensorEventAvro, event.getHubId(), instantTimestamp, SENSOR_TOPIC);
    }
}