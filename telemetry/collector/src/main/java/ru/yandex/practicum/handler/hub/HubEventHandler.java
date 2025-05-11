package ru.yandex.practicum.handler.hub;

import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.model.hub.events.HubEvent;

@Component
@RequiredArgsConstructor
public abstract class HubEventHandler<T extends SpecificRecordBase> {
    private final Producer producer;
    private static final String HUB_TOPIC = "telemetry.hubs.v1";

    protected abstract T mapToAvro(HubEvent event);

    public void handle(HubEvent event) {
        T avroObj = mapToAvro(event);
        HubEventAvro hubEventAvro = HubEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setTimestamp(event.getTimestamp())
                .setPayload(avroObj)
                .build();

        producer.send(hubEventAvro, event.getHubId(), event.getTimestamp(), HUB_TOPIC);
    }
}
