package ru.yandex.practicum.handler.hub;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.config.kafka.Config;
import ru.yandex.practicum.config.kafka.Producer;
import ru.yandex.practicum.handler.HandlerHubEvent;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.model.hub.enums.HubEventType;
import ru.yandex.practicum.model.hub.events.EventDeviceRemoved;
import ru.yandex.practicum.model.hub.events.HubEvent;


@HandlerHubEvent(HubEventType.DEVICE_REMOVED)
@Component
public class HubDeviceRemovedEventHandler extends HubEventHandler<DeviceRemovedEventAvro> {

    public HubDeviceRemovedEventHandler(Config config, Producer producer) {
        super(config, producer);
    }

    @Override
    protected DeviceRemovedEventAvro mapToAvro(HubEvent event) {
        EventDeviceRemoved hubEvent = (EventDeviceRemoved) event;
        return DeviceRemovedEventAvro.newBuilder()
                .setId(hubEvent.getId())
                .build();
    }
}