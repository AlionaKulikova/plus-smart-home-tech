package ru.yandex.practicum.handler.hub;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public abstract class HubEventHandler<T extends SpecificRecordBase> {
    private final Producer producer;
    private static final String HUB_TOPIC = "telemetry.hubs.v1";

    protected abstract T mapToAvro(HubEventProto event);


    public void handle(HubEventProto event) {
        T avroObj = mapToAvro(event);
        // Проверка типа timestamp
        Instant instantTimestamp = Instant.ofEpochSecond(
                event.getTimestamp().getSeconds(),
                event.getTimestamp().getNanos()
        );

        HubEventAvro hubEventAvro = HubEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setTimestamp(instantTimestamp) // Передаем Instant
                .setPayload(avroObj)
                .build();

        producer.send(hubEventAvro, event.getHubId(), instantTimestamp, HUB_TOPIC);
    }
}
