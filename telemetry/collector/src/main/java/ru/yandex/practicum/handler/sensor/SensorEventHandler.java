package ru.yandex.practicum.handler.sensor;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.model.sensor.events.SensorEvent;

@Component
@RequiredArgsConstructor
public abstract class SensorEventHandler<T extends SpecificRecordBase> {
    private final Producer producer;
    private static final String SENSOR_TOPIC = "telemetry.sensors.v1";

    protected abstract T mapToAvro(SensorEvent event);
    public void handle(SensorEvent event) {
        T avroObj = mapToAvro(event);
        SensorEventAvro sensorEventAvro = SensorEventAvro.newBuilder()
                .setId(event.getId())
                .setHubId(event.getHubId())
                .setTimestamp(event.getTimestamp())
                .setPayload(avroObj)
                .build();
        producer.send(sensorEventAvro, event.getHubId(), event.getTimestamp(), SENSOR_TOPIC);
    }
}